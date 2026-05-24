/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myychatapp;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login login = new Login();

        String username, password, phoneNumber;
        String registrationMessage;

        System.out.println("=== REGISTRATION ===");

        // Loop until registration is successful
        while (true) {

            System.out.print("Enter username: ");
            username = input.nextLine();

            System.out.print("Enter password: ");
            password = input.nextLine();

            System.out.print("Enter phone number: ");
            phoneNumber = input.nextLine();

            registrationMessage = login.registerUser(username, password, phoneNumber);
            System.out.println(registrationMessage);

            if (registrationMessage.equals("User registered successfully.")) {
                break;
            }

            System.out.println("\nPlease try again...\n");
        }

        // LOGIN SECTION
        System.out.println("\n=== LOGIN ===");

        boolean isLoggedIn = false;

        while (!isLoggedIn) {

            System.out.print("Enter username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = input.nextLine();

            isLoggedIn = login.loginUser(loginUsername, loginPassword);

            System.out.println(login.returnLoginStatus(isLoggedIn));
        }
       System.out.println("\nWelcome to ChatApp.");
        
        boolean running = true;

        while (running) {

         System.out.println("\n=== MENU ===");
         System.out.println("1) Send Messages");
         System.out.println("2) Show recently sent messages");
         System.out.println("3) Quit");

        System.out.print("Choose an option: ");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {

            case 1:
                System.out.println("Send Messages selected.");
                System.out.print("How many messages would you like to send? ");
                int numMessages = input.nextInt();
                input.nextLine();
                
                for (int i = 0; i < numMessages; i++) {

                int messageNumber = i + 1;

                System.out.println("\n--- Message " + messageNumber + " ---");

                // Recipient input
                System.out.print("Enter recipient number: ");
                 String recipient = input.nextLine();

                // Message input
                System.out.print("Enter your message: ");
                 String messageText = input.nextLine();

                // Create Message object
                Message message = new Message(messageNumber, recipient, messageText);

                // Validate recipient
                System.out.println(message.checkRecipientCell());

                // Validate message length
                System.out.println(message.checkMessageLength());

                 // Create and display hash
                 System.out.println("Message Hash: " + message.createMessageHash());

                 // Ask user what to do
                 System.out.println("\nChoose an option:");
                 System.out.println("1) Send Message");
                 System.out.println("2) Disregard Message");
                 System.out.println("3) Store Message");
    
                 int sendChoice = input.nextInt();
                 input.nextLine();

                 // Show result
                 System.out.println(message.sentMessage(sendChoice));

                // Display full message details
                System.out.println("\n" + message.printMessages());
            }

            System.out.println("\nTotal messages sent: "
            + Message.returnTotalMessagesStatic());
            break;

            case 2:
                System.out.println("Coming Soon.");
            break;

            case 3:
                System.out.println("Goodbye!");
            running = false;
            break;

        default:
            System.out.println("Invalid option. Please try again.");
        }
    }
        input.close();

    }
}