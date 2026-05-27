package com.citi.citiassist.config;

import com.citi.citiassist.model.ChatResponse;
import com.citi.citiassist.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.util.Objects;

/**
 * WebSocket Event Listener
 * 
 * Listens to WebSocket connection and disconnection events.
 * Broadcasts notifications when users join or leave the chat.
 * 
 * Compatible with Spring Boot 3.2.5 using SimpMessageHeaderAccessor
 * Handles session lifecycle and broadcasts JOIN/LEAVE events.
 * 
 * @author CitiAssist Development Team
 * @version 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    
    /**
     * Handle WebSocket session connection event
     * 
     * Triggered when a new WebSocket session connects.
     * Extracts user information from message headers using SimpMessageHeaderAccessor.
     * Broadcasts a JOIN notification to all subscribers on /topic/public.
     * 
     * @param event SessionConnectEvent containing connection details
     */
    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        try {
            // Wrap the message to extract headers using Spring Boot 3.2.5 compatible accessor
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
            
            // Extract session ID from headers
            String sessionId = Objects.toString(
                    headerAccessor.getSessionId(), 
                    "unknown-session-" + System.currentTimeMillis()
            );
            
            // Extract user from headers (Spring Boot 3.2.5 compatible)
            Principal user = headerAccessor.getUser();
            
            // Get username from principal (anonymous if no auth)
            String username = user != null ? user.getName() : "User-" + sessionId.substring(0, Math.min(8, sessionId.length()));
            
            log.info("User connected - SessionId: {} | Username: {}", sessionId, username);
            
            // Generate JOIN notification
            ChatResponse joinNotification = chatService.generateJoinNotification(username);
            
            // Broadcast to all subscribers
            simpMessagingTemplate.convertAndSend("/topic/public", joinNotification);
            
            log.info("JOIN notification broadcasted for user: {}", username);
            
        } catch (Exception e) {
            log.error("Error handling session connection event", e);
        }
    }
    
    /**
     * Handle WebSocket session disconnection event
     * 
     * Triggered when a WebSocket session disconnects.
     * Extracts user information from message headers using SimpMessageHeaderAccessor.
     * Broadcasts a LEAVE notification to all remaining subscribers on /topic/public.
     * 
     * @param event SessionDisconnectEvent containing disconnection details
     */
    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        try {
            // Wrap the message to extract headers using Spring Boot 3.2.5 compatible accessor
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
            
            // Extract session ID from headers
            String sessionId = Objects.toString(
                    headerAccessor.getSessionId(), 
                    "unknown-session-" + System.currentTimeMillis()
            );
            
            // Extract user from headers (Spring Boot 3.2.5 compatible)
            Principal user = headerAccessor.getUser();
            
            // Get username from principal (anonymous if no auth)
            String username = user != null ? user.getName() : "User-" + sessionId.substring(0, Math.min(8, sessionId.length()));
            
            log.info("User disconnected - SessionId: {} | Username: {}", sessionId, username);
            
            // Generate LEAVE notification
            ChatResponse leaveNotification = chatService.generateLeaveNotification(username);
            
            // Broadcast to all remaining subscribers
            simpMessagingTemplate.convertAndSend("/topic/public", leaveNotification);
            
            log.info("LEAVE notification broadcasted for user: {}", username);
            
        } catch (Exception e) {
            log.error("Error handling session disconnection event", e);
        }
    }
}
