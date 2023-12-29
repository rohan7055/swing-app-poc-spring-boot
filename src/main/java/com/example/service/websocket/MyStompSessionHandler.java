package com.example.service.websocket;

import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("Connected to the STOMP WebSocket server");
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        System.err.println("Error in STOMP WebSocket transport: " + exception.getMessage());
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Received message: " + payload.toString());
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        System.err.println("STOMP exception: " + exception.getMessage());
    }
}

