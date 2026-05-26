package com.citi.citiassist.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket Configuration
 * 
 * Configures Spring WebSocket and STOMP messaging protocol.
 * Enables real-time bidirectional communication for the chatbot.
 */
@Slf4j
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configure message broker for STOMP protocol
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        log.info("Configuring message broker for STOMP");
        
        // Enable simple in-memory message broker for /topic and /queue destinations
        config.enableSimpleBroker("/topic", "/queue");
        
        // Set application destination prefix for client-to-server messages
        config.setApplicationDestinationPrefixes("/app");
        
        // Set user destination prefix for user-specific messages
        config.setUserDestinationPrefix("/user");
    }

    /**
     * Register STOMP endpoints
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("Registering WebSocket STOMP endpoints");
        
        // Register the WebSocket endpoint
        registry.addEndpoint("/ws/chat")
                .setAllowedOrigins("*")
                .withSockJS();
        
        // Additional endpoint for advanced WebSocket features
        registry.addEndpoint("/ws/notifications")
                .setAllowedOrigins("*")
                .withSockJS();
        
        log.info("WebSocket endpoints registered successfully");
    }
}
