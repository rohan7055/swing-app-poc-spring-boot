package com.example.service.websocket;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class WebSocketClientEndpoint {

    private Session session;

    public WebSocketClientEndpoint() {
        connectToWebSocketServer();
    }

    private void connectToWebSocketServer() {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:9123/websocket";

        try {
            container.connectToServer(this, new URI(uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("Connected to server");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        // Handle incoming message
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
