package com.citi.citiassist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * ChatMessage Model
 * 
 * Represents an incoming chat message from a user.
 * Used for WebSocket message transmission and processing.
 * 
 * @author CitiAssist Development Team
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    
    /**
     * Unique identifier for the message
     * Generated client-side or server-side
     */
    @NotBlank(message = "Message ID cannot be blank")
    private String messageId;
    
    /**
     * Username or identifier of the message sender
     * Typically the authenticated user
     */
    @NotBlank(message = "Sender cannot be blank")
    private String sender;
    
    /**
     * The actual message content
     * Contains the user's query or input
     */
    @NotBlank(message = "Content cannot be blank")
    private String content;
    
    /**
     * Type of message (CHAT, JOIN, LEAVE, TYPING, BOT_RESPONSE)
     * Determines how the message is processed
     */
    @NotNull(message = "Message type cannot be null")
    private MessageType messageType;
    
    /**
     * Server-side timestamp when message was received
     * Set by server, not by client
     */
    private LocalDateTime timestamp;
    
    /**
     * Post-construct: Set timestamp to current time if not set
     */
    public void setDefaultTimestamp() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}
