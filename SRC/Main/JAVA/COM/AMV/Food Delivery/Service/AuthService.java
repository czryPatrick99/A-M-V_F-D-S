package com.amv.fooddelivery.service;

import com.amv.fooddelivery.model.User;
import java.util.Scanner;

public class AuthService {
    private Scanner scanner;
    private User currentUser;
    
    public AuthService(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public boolean login(User user) {
        if (user == null) {
            System.out.println("No user registered. Please register first.");
            return false;
        }
        
        System.out.print("Enter Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter Password: ");
        String inputPassword = scanner.nextLine();
        
        if (inputUsername.equals(user.getUsername()) && inputPassword.equals(user.getPassword())) {
            System.out.printf("Welcome back %s!!\n", user.getUsername());
            currentUser = user;
            return true;
        } else {
            System.out.println("Invalid username or password!");
            return false;
        }
    }
    
    public User register() {
        System.out.println("Fill The Below Questions!");
        
        System.out.print("Set A User Name For Your Account: ");
        String username = scanner.nextLine();
        
        System.out.print("Set A Password For Your Account: ");
        String password = scanner.nextLine();
        
        System.out.print("Set Your Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Set Your Phone Number: ");
        String phone = scanner.nextLine();
        
        User newUser = new User(username, password, address, phone);
        currentUser = newUser;
        System.out.println("Registration successful!");
        return newUser;
    }
    
    public void logout() {
        currentUser = null;
        System.out.println("Logged out successfully!");
    }
}