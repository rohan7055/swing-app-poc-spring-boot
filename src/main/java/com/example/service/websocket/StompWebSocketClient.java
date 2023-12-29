package com.example.service.websocket;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

public class StompWebSocketClient {

    private StompSession stompSession;

    public StompWebSocketClient() {
        connectToWebSocketServer();
    }

    private void connectToWebSocketServer() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        String url = "ws://localhost:9123/websocket";

        stompClient.setMessageConverter(new StringMessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        ListenableFuture<StompSession> future = stompClient.connect(url, sessionHandler);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(StompSession session) {
                stompSession = session;
                subscribeToTopic("/topic/greetings");
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void subscribeToTopic(String topic) {
        stompSession.subscribe(topic, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                String message = (String) payload;
                // Handle incoming message
                System.out.println("Received message: " + message);
            }
        });
    }

    public void sendMessageToTopic(String topic, String message) {
        stompSession.send(topic, message);
    }

    // Other methods to handle disconnection, error handling, etc.
}
