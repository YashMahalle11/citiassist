package com.citi.citiassist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

/**
 * CitiAssist Backend - Main Application Entry Point
 * 
 * Production-grade banking chatbot backend application.
 * Enables Spring Boot auto-configuration, WebSocket support, async processing,
 * and Spring Security.
 * 
 * @author CitiAssist Development Team
 * @version 1.0.0
 */
@SpringBootApplication
@EnableWebSocket
@EnableWebSecurity
@EnableAsync
@ComponentScan(basePackages = {
    "com.citi.citiassist.controller",
    "com.citi.citiassist.service",
    "com.citi.citiassist.config",
    "com.citi.citiassist.security",
    "com.citi.citiassist.exception"
})
public class CitiAssistApplication {

    public static void main(String[] args) {
        SpringApplication.run(CitiAssistApplication.class, args);
    }
}
