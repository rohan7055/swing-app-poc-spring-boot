package com.example.service.websocket;

public class CustomMessage<T> {
    private String messageId;
    private String source;
    private T payload;

    public CustomMessage() {
        // Default constructor
    }

    public CustomMessage(String messageId, String source, T payload) {
        this.messageId = messageId;
        this.source = source;
        this.payload = payload;
    }

    // Getters and setters for messageId, source, and payload
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
