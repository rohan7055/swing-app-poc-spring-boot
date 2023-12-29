package com.example.service.websocket;

import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Connected to WebSocket Server");
        // You can perform actions after a successful connection here
        // For instance, subscribe to a specific destination
        session.subscribe("/topic/greetings", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return CustomMessage.class; // Define your message class here
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                // Handle incoming messages
                CustomMessage<String> message = (CustomMessage<String>) payload;
                System.out.println("Received message: " + message.getPayload());
                // Update Swing UI or perform other actions based on received messages
            }
        });
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        // Handle generic message frames
    }

    @Override
    public void handleException(StompSession session, StompCommand command,
                                StompHeaders headers, byte[] payload, Throwable exception) {
        // Handle exceptions during the WebSocket session
        System.err.println("Exception occurred: " + exception.getMessage());
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        // Handle transport (network) errors
        System.err.println("Transport error occurred: " + exception.getMessage());
    }
}
