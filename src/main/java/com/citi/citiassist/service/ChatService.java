package com.citi.citiassist.service;

import com.citi.citiassist.model.ChatMessage;
import com.citi.citiassist.model.ChatResponse;
import com.citi.citiassist.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * ChatService - Business Logic Layer
 * 
 * Processes user chat messages and generates rule-based banking responses.
 * Implements banking chatbot logic for CitiAssist.
 * 
 * @author CitiAssist Development Team
 * @version 1.0.0
 */
@Slf4j
@Service
public class ChatService {
    
    private static final String BOT_NAME = "CitiAssist";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    /**
     * Process incoming chat message and generate response
     * 
     * @param message Incoming ChatMessage from user
     * @return ChatResponse with appropriate banking information or assistance
     */
    public ChatResponse processMessage(ChatMessage message) {
        log.info("Processing message from user: {} | Content: {}", message.getSender(), message.getContent());
        
        // Set default timestamp if not set
        message.setDefaultTimestamp();
        
        // Extract user query and convert to lowercase for matching
        String userQuery = message.getContent().toLowerCase().trim();
        
        // Route to appropriate handler based on query
        String responseContent = routeAndProcess(userQuery);
        
        // Build and return response
        ChatResponse response = ChatResponse.builder()
                .responseId(message.getMessageId() + "-response")
                .sender(BOT_NAME)
                .content(responseContent)
                .responseType(MessageType.BOT_RESPONSE)
                .timestamp(LocalDateTime.now())
                .build();
        
        log.info("Generated response: {} | Type: {}", response.getResponseId(), response.getResponseType());
        return response;
    }
    
    /**
     * Route user query to appropriate handler
     * 
     * @param userQuery Lowercase user input
     * @return Response content
     */
    private String routeAndProcess(String userQuery) {
        
        // Check for balance inquiry
        if (userQuery.contains("balance") || userQuery.contains("account balance")) {
            return handleBalanceInquiry();
        }
        
        // Check for transactions
        if (userQuery.contains("transaction") || userQuery.contains("recent activity")) {
            return handleTransactionsInquiry();
        }
        
        // Check for card blocking
        if (userQuery.contains("block") && userQuery.contains("card")) {
            return handleCardBlocking();
        }
        
        // Check for loan inquiry
        if (userQuery.contains("loan") || userQuery.contains("borrow")) {
            return handleLoanInquiry();
        }
        
        // Check for statement
        if (userQuery.contains("statement") || userQuery.contains("mini statement")) {
            return handleStatementRequest();
        }
        
        // Check for help
        if (userQuery.contains("help") || userQuery.contains("assist") || userQuery.contains("services")) {
            return handleHelpRequest();
        }
        
        // Default: unknown query
        return handleUnknownQuery();
    }
    
    /**
     * Handle balance inquiry - Rule 1
     */
    private String handleBalanceInquiry() {
        log.debug("Handling balance inquiry");
        return "Your current savings account balance is ₹1,25,450\n\n" +
               "Account Details:\n" +
               "• Account Number: XXX1234\n" +
               "• Account Type: Savings\n" +
               "• Last Updated: " + LocalDateTime.now().format(DATE_FORMATTER) + "\n\n" +
               "Type 'help' for more options.";
    }
    
    /**
     * Handle transactions inquiry - Rule 2
     * Returns last 3 mock transactions
     */
    private String handleTransactionsInquiry() {
        log.debug("Handling transactions inquiry");
        
        List<String> transactions = new ArrayList<>();
        transactions.add("1. Online Purchase - Flipkart | -₹2,500 | 25-May-2026 14:30");
        transactions.add("2. Salary Deposit - Acme Corp | +₹75,000 | 25-May-2026 09:15");
        transactions.add("3. Electricity Bill - BESCOM | -₹1,850 | 24-May-2026 10:45");
        
        StringBuilder response = new StringBuilder();
        response.append("Your Last 3 Transactions:\n\n");
        for (String transaction : transactions) {
            response.append(transaction).append("\n");
        }
        response.append("\nFor detailed statement, type 'statement'\n");
        response.append("Type 'help' for more options.");
        
        return response.toString();
    }
    
    /**
     * Handle card blocking request - Rule 3
     * OTP verification workflow response
     */
    private String handleCardBlocking() {
        log.debug("Handling card blocking request");
        return "I can help you block your card.\n\n" +
               "Security Verification Required:\n" +
               "Please confirm the last 4 digits of your Debit Card: ****XXXX\n\n" +
               "For immediate assistance:\n" +
               "• Call: 1860-425-0000\n" +
               "• WhatsApp: +91-9876543210\n\n" +
               "Reason for blocking:\n" +
               "1. Card Lost\n" +
               "2. Card Stolen\n" +
               "3. Suspicious Activity\n" +
               "4. Upgrade Card\n\n" +
               "Reply with option number (1-4)";
    }
    
    /**
     * Handle loan inquiry - Rule 4
     * Loan eligibility and options
     */
    private String handleLoanInquiry() {
        log.debug("Handling loan inquiry");
        return "Welcome to CitiAssist Loan Services!\n\n" +
               "Loan Products Available:\n" +
               "1. Personal Loan - Up to ₹25,00,000 | 7.5% onwards\n" +
               "2. Home Loan - Up to ₹1 Cr | 6.5% onwards\n" +
               "3. Auto Loan - Up to ₹20,00,000 | 8.0% onwards\n" +
               "4. Education Loan - Up to ₹30,00,000 | 7.0% onwards\n\n" +
               "Your Loan Eligibility:\n" +
               "✓ Based on your salary & credit score\n" +
               "✓ Approved Amount: Up to ₹18,00,000\n" +
               "✓ Processing Time: 24 hours\n\n" +
               "Reply with loan type number (1-4) for more details.";
    }
    
    /**
     * Handle statement request - Rule 5
     * Statement generation response
     */
    private String handleStatementRequest() {
        log.debug("Handling statement request");
        return "Generating Your Account Statement...\n\n" +
               "Statement Details:\n" +
               "• Period: 01-May-2026 to 25-May-2026\n" +
               "• Opening Balance: ₹1,28,200\n" +
               "• Closing Balance: ₹1,25,450\n" +
               "• Total Debits: ₹4,350\n" +
               "• Total Credits: ₹75,000\n\n" +
               "Your statement will be emailed to:\n" +
               "user@example.com\n\n" +
               "Processing Time: 2-3 minutes\n" +
               "You can also download the PDF from your mobile app.";
    }
    
    /**
     * Handle help request - Rule 6
     * Available banking services
     */
    private String handleHelpRequest() {
        log.debug("Handling help request");
        return "Welcome to CitiAssist Banking Chatbot!\n\n" +
               "I can help you with:\n\n" +
               "💰 Financial Services:\n" +
               "• Type 'balance' - Check account balance\n" +
               "• Type 'transactions' - View recent transactions\n" +
               "• Type 'statement' - Download account statement\n\n" +
               "🛡️ Card Services:\n" +
               "• Type 'block card' - Block/deactivate your card\n" +
               "• Type 'replace card' - Order replacement card\n\n" +
               "💳 Loan Services:\n" +
               "• Type 'loan' - Check loan eligibility\n" +
               "• Type 'loan details' - View loan options\n\n" +
               "📞 Support:\n" +
               "• Type 'contact' - Customer support details\n" +
               "• Type 'complaint' - Lodge a complaint\n\n" +
               "How can I assist you today?";
    }
    
    /**
     * Handle unknown/unsupported query
     */
    private String handleUnknownQuery() {
        log.debug("Handling unknown query");
        return "I didn't quite understand that query. 🤔\n\n" +
               "Here are some things I can help with:\n" +
               "• Check your balance\n" +
               "• View transactions\n" +
               "• Block your card\n" +
               "• Apply for a loan\n" +
               "• Download statement\n\n" +
               "Type 'help' for complete list of services, or feel free to ask anything about your account!";
    }
    
    /**
     * Generate typing indicator response
     * Used when broadcasting typing notifications
     */
    public ChatResponse generateTypingIndicator(ChatMessage message) {
        log.debug("Generating typing indicator for: {}", message.getSender());
        
        return ChatResponse.builder()
                .responseId(message.getMessageId())
                .sender(message.getSender())
                .content(message.getSender() + " is typing...")
                .responseType(MessageType.TYPING)
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    /**
     * Generate user join notification
     */
    public ChatResponse generateJoinNotification(String userId) {
        log.info("Generating join notification for user: {}", userId);
        
        return ChatResponse.builder()
                .responseId("join-" + System.currentTimeMillis())
                .sender(BOT_NAME)
                .content(userId + " has joined the chat")
                .responseType(MessageType.JOIN)
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    /**
     * Generate user leave notification
     */
    public ChatResponse generateLeaveNotification(String userId) {
        log.info("Generating leave notification for user: {}", userId);
        
        return ChatResponse.builder()
                .responseId("leave-" + System.currentTimeMillis())
                .sender(BOT_NAME)
                .content(userId + " has left the chat")
                .responseType(MessageType.LEAVE)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
