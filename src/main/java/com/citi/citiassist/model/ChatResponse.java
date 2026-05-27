package com.citi.citiassist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * ChatResponse Model
 * 
 * Represents an outgoing response message from the chatbot.
 * Sent to WebSocket subscribers after processing a ChatMessage.
 * 
 * @author CitiAssist Development Team
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponse {
    
    /**
     * Unique identifier for the response message
     * Typically references the original message ID
     */
    @NotBlank(message = "Response ID cannot be blank")
    private String responseId;
    
    /**
     * Sender of the response
     * Usually "CitiAssist" or "ChatBot" for automated responses
     */
    @NotBlank(message = "Sender cannot be blank")
    private String sender;
    
    /**
     * The response content
     * Contains the chatbot's reply or banking information
     */
    @NotBlank(message = "Content cannot be blank")
    private String content;
    
    /**
     * Type of response message
     * Usually BOT_RESPONSE, but can be JOIN, LEAVE, or TYPING
     */
    @NotNull(message = "Response type cannot be null")
    private MessageType responseType;
    
    /**
     * Server-side timestamp when response was created
     * Set by server during message processing
     */
    private LocalDateTime timestamp;
    
    /**
     * Initialize timestamp to current time if not already set
     */
    public void setDefaultTimestamp() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
    
    /**
     * Create a ChatResponse from a ChatMessage
     * Useful for building responses based on incoming messages
     * 
     * @param messageId Original message ID
     * @param sender Sender of the response (usually chatbot name)
     * @param content Response content
     * @return Configured ChatResponse instance
     */
    public static ChatResponse from(String messageId, String sender, String content) {
        return ChatResponse.builder()
                .responseId(messageId + "-response")
                .sender(sender)
                .content(content)
                .responseType(MessageType.BOT_RESPONSE)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
