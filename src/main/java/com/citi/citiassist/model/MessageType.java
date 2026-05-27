package com.citi.citiassist.model;

/**
 * MessageType Enum
 * 
 * Defines all supported message types for WebSocket communication.
 * Used to categorize and route different types of chat messages.
 */
public enum MessageType {
    
    /**
     * Regular chat message from user to chatbot
     */
    CHAT("User chat message"),
    
    /**
     * User joins the chat session
     */
    JOIN("User joins chat"),
    
    /**
     * User leaves the chat session
     */
    LEAVE("User leaves chat"),
    
    /**
     * Typing indicator - user is typing
     */
    TYPING("User is typing"),
    
    /**
     * Bot response to user query
     */
    BOT_RESPONSE("Chatbot response");
    
    private final String description;
    
    MessageType(String description) {
        this.description = description;
    }
    
    /**
     * Get description of message type
     */
    public String getDescription() {
        return description;
    }
}
