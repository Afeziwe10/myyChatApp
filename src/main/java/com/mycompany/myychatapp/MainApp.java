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
    }
}
