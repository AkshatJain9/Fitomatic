package com.ajsmdllz.fitomatic.P2PMessaging;

public class Message {
    /**
     * This class sets the peer to peer text messages
     */
    private final String sender;
    private final String message;

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
