package com.amv.fooddelivery.service;

import com.amv.fooddelivery.model.User;
import java.util.Scanner;

public class UserService {
    private Scanner scanner;
    
    public UserService(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public void editProfile(User user) {
        System.out.print("New Username (current: " + user.getUsername() + "): ");
        String newUsername = scanner.nextLine();
        if (!newUsername.isEmpty()) {
            user.setUsername(newUsername);
        }
        
        System.out.print("New Address (current: " + user.getAddress() + "): ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            user.setAddress(newAddress);
        }
        
        System.out.print("New Phone (current: " + user.getPhone() + "): ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            user.setPhone(newPhone);
        }
        
        System.out.println("Profile updated successfully!");
    }
}