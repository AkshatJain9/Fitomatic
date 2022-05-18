package com.ajsmdllz.fitomatic;

import static org.junit.Assert.assertEquals;

import com.ajsmdllz.fitomatic.P2PMessaging.Message;

import org.junit.Test;

public class MessageTest {
    private final Message shznMessage = new Message("shzn", "Hello this is a text message to be sent");
    private final Message shaazaanMessage = new Message("shaazaan", "Peer to peer messaging uses this class");
    private final Message shazMessage = new Message("shaz", "This message will be sent");


    @Test
    public void getMessageTest() {
        assertEquals("Hello this is a text message to be sent", shznMessage.getMessage());
        assertEquals("Peer to peer messaging uses this class", shaazaanMessage.getMessage());
        assertEquals("This message will be sent", shazMessage.getMessage());
    }

    @Test
    public void getSenderTest() {
        assertEquals("shzn", shznMessage.getSender());
        assertEquals("shaazaan", shaazaanMessage.getSender());
        assertEquals("shaz", shazMessage.getSender());
    }
}