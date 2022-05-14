package com.ajsmdllz.fitomatic.P2PMessaging;

public class Message {
    private String sender;
    private String message;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }
}
