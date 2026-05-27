package com.citi.citiassist.controller;

import com.citi.citiassist.model.ChatMessage;
import com.citi.citiassist.model.ChatResponse;
import com.citi.citiassist.model.MessageType;
import com.citi.citiassist.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Chat WebSocket Controller
 * 
 * Handles real-time bidirectional WebSocket communication for the chatbot.
 * Routes incoming messages to appropriate handlers and broadcasts responses.
 * 
 * WebSocket Endpoint: /ws/chat (SockJS with STOMP)
 * Application Destination Prefix: /app
 * Message Broker Destination: /topic/public
 * 
 * @author CitiAssist Development Team
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatService chatService;
    
    /**
     * Handle user chat messages
     * 
     * WebSocket Route:
     * Client sends to: /app/chat.send
     * Server broadcasts to: /topic/public
     * 
     * @param message Incoming chat message from user
     * @return ChatResponse to be broadcast to all subscribers
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatResponse sendMessage(ChatMessage message) {
        log.info("Received chat message from {}: {}", message.getSender(), message.getContent());
        
        try {
            // Set timestamp if not already set
            message.setDefaultTimestamp();
            
            // Validate message
            if (message.getMessageType() == null) {
                message.setMessageType(MessageType.CHAT);
            }
            
            // Process message through ChatService and get response
            ChatResponse response = chatService.processMessage(message);
            
            log.info("Broadcasting response to all subscribers: {}", response.getResponseId());
            
            return response;
        } catch (Exception e) {
            log.error("Error processing chat message", e);
            
            // Return error response
            return ChatResponse.builder()
                    .responseId(message.getMessageId() + "-error")
                    .sender("CitiAssist Error Handler")
                    .content("An error occurred while processing your request. Please try again.")
                    .responseType(MessageType.BOT_RESPONSE)
                    .timestamp(java.time.LocalDateTime.now())
                    .build();
        }
    }
    
    /**
     * Handle typing indicators
     * 
     * WebSocket Route:
     * Client sends to: /app/chat.typing
     * Server broadcasts to: /topic/public
     * 
     * When a user is typing, broadcast typing notification to all connected clients
     * 
     * @param message Message indicating typing activity
     * @return ChatResponse with TYPING message type
     */
    @MessageMapping("/chat.typing")
    @SendTo("/topic/public")
    public ChatResponse handleTyping(ChatMessage message) {
        log.debug("User {} is typing", message.getSender());
        
        try {
            // Generate typing indicator response
            ChatResponse typingResponse = chatService.generateTypingIndicator(message);
            
            log.debug("Broadcasting typing indicator: {}", message.getSender());
            
            return typingResponse;
        } catch (Exception e) {
            log.error("Error handling typing indicator", e);
            
            return ChatResponse.builder()
                    .responseId("typing-error-" + System.currentTimeMillis())
                    .sender("CitiAssist")
                    .content("")
                    .responseType(MessageType.TYPING)
                    .timestamp(java.time.LocalDateTime.now())
                    .build();
        }
    }
}
