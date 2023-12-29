package com.example.service.websocket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WebSocketClient {

    private final WebSocketStompClient stompClient;
    private StompSession session;

    public WebSocketClient() {
        stompClient = new WebSocketStompClient(webSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setAutoStartup(true);
    }

    public org.springframework.web.socket.client.WebSocketClient webSocketClient() {
        List<Transport> transports = new ArrayList<Transport>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return new SockJsClient(transports);
    }

    public void connect(String url) {
        try {
            stompClient.connect(url, new CustomStompSessionHandler()).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private StompHeaders createHeaders(String username, String password) {
        StompHeaders headers = new StompHeaders();
        headers.setLogin(username);
        headers.setPasscode(password);
        // You can add other headers if needed
        return headers;
    }

    public void sendMessage(String destination, Object payload) {
        if (session != null && session.isConnected()) {
            session.send(destination, payload);
        }
    }
}
