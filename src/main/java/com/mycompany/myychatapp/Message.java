/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myychatapp;


import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;
/**
 * Message class represents one message in the QuickChat application.
 * Handles validation, message hash creation, and message actions.
 */
public class Message {

    // Fields
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    // Keeps track of total sent messages
    private static int totalMessages = 0;

    /**
     * Constructor
     *
     * @param messageNumber
     * @param recipient
     * @param messageText
     */
    public Message(int messageNumber, String recipient, String messageText) {

        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
    }
        // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    /**
     * Generates a random 10-digit message ID.
     *
     * @return message ID
     */
    public String generateMessageID() {

        Random random = new Random();

        String id = "";

        for (int i = 0; i < 10; i++) {

            id += random.nextInt(10);

        }

        return id;
    }

    /**
     * Checks if message ID is valid.
     *
     * @return true if ID is 10 characters or less
     */
    public boolean checkMessageID() {

        return messageID.length() <= 10;

    }

    /**
     * Validates recipient phone number.
     *
     * @return success or failure message
     */
    public String checkRecipientCell() {

        if (recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    /**
     * Validates message length.
     *
     * @return success or failure message
     */
    public String checkMessageLength() {

        if (messageText.length() <= 250) {

            return "Message ready to send.";

        } else {

            int overLimit = messageText.length() - 250;

            return "Message exceeds 250 characters by "
                    + overLimit
                    + "; please reduce the size.";
        }
    }

    /**
     * Creates and returns the message hash.
     *
     * @return message hash
     */
    public String createMessageHash() {

        String firstTwoDigits = messageID.substring(0, 2);

        String[] words = messageText.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = firstTwoDigits + ":"
                + messageNumber + ":"
                + firstWord + lastWord;

        return messageHash.toUpperCase();
    }

    /**
     * Displays message details.
     *
     * @return formatted message details
     */
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    /**
     * Returns total number of messages sent.
     *
     * @return total messages
     */
    public int returnTotalMessages() {

        return totalMessages;

    }
    
    public static int returnTotalMessagesStatic() {

        return totalMessages;

    }
    /**
     * Handles send/store/disregard options.
     *
     * @param choice
     * @return result message
     */
    public String sentMessage(int choice) {

        switch (choice) {

            case 1:

                totalMessages++;

                return "Message successfully sent.";

            case 2:

                return "Press 0 to delete the message.";

            case 3:
                storeMessage();
                return "Message successfully stored.";

            default:

                return "Invalid option.";
        }
    }
    
    public void storeMessage() {

    // Attribution:
    // org.json library used for JSON storage
    // Source: https://mvnrepository.com/artifact/org.json/json

    JSONObject messageObject = new JSONObject();

    messageObject.put("MessageID", messageID);
    messageObject.put("MessageHash", messageHash);
    messageObject.put("Recipient", recipient);
    messageObject.put("Message", messageText);

    try (FileWriter file = new FileWriter("messages.json", true)) {

        file.write(messageObject.toString(4));
        file.write(System.lineSeparator());

    } catch (IOException e) {

        System.out.println("Error writing to JSON file.");
    }
}


}
