/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.myychatap;

import com.mycompany.myychatapp.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // Test valid message length
    @Test
    public void testMessageLengthSuccess() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        String expected = "Message ready to send.";

        assertEquals(expected, message.checkMessageLength());
    }

    // Test invalid message length
    @Test
    public void testMessageLengthFailure() {

        String longMessage = "A".repeat(260);

        Message message = new Message(
                1,
                "+27718693002",
                longMessage
        );

        String expected =
                "Message exceeds 250 characters by 10; please reduce the size.";

        assertEquals(expected, message.checkMessageLength());
    }

    // Test valid recipient number
    @Test
    public void testRecipientNumberSuccess() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        String expected =
                "Cell phone number successfully captured.";

        assertEquals(expected, message.checkRecipientCell());
    }

    // Test invalid recipient number
    @Test
    public void testRecipientNumberFailure() {

        Message message = new Message(
                1,
                "0812345678",
                "Hello"
        );

        String expected =
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";

        assertEquals(expected, message.checkRecipientCell());
    }

    // Test message hash creation
    @Test
    public void testCreateMessageHash() {

        Message message = new Message(
                0,
                "+27718693002",
                "Hi Mike can you join us tonight"
        );

        String hash = message.createMessageHash();

        assertTrue(hash.contains(":0:HITONIGHT"));
    }

    // Test message ID generation
    @Test
    public void testMessageIDCreated() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        assertNotNull(message.getMessageID());

        assertEquals(10, message.getMessageID().length());
    }

    // Test send message option
    @Test
    public void testSendMessageOption() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        String expected = "Message successfully sent.";

        assertEquals(expected, message.sentMessage(1));
    }

    // Test disregard message option
    @Test
    public void testDisregardMessageOption() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        String expected = "Press 0 to delete the message.";

        assertEquals(expected, message.sentMessage(2));
    }

    // Test store message option
    @Test
    public void testStoreMessageOption() {

        Message message = new Message(
                1,
                "+27718693002",
                "Hello"
        );

        String expected = "Message successfully stored.";

        assertEquals(expected, message.sentMessage(3));
    }
}