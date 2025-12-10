package com.amv.fooddelivery.ui;

import com.amv.fooddelivery.service.UserService;
import com.amv.fooddelivery.model.User;
import com.amv.fooddelivery.util.DisplayUtil;
import java.util.Scanner;

public class ProfileUI {
    private Scanner scanner;
    private UserService userService;
    private User currentUser;
    
    public ProfileUI(Scanner scanner, UserService userService, User currentUser) {
        this.scanner = scanner;
        this.userService = userService;
        this.currentUser = currentUser;
    }
    
    public void showProfile() {
        DisplayUtil.displayHeader("MY PROFILE");
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Address: " + currentUser.getAddress());
        System.out.println("Phone: " + currentUser.getPhone());
        
        System.out.println("\n[1] Edit Profile");
        System.out.println("[2] Back to Dashboard");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            editProfile();
        }
    }
    
    private void editProfile() {
        DisplayUtil.displayHeader("EDIT PROFILE");
        userService.editProfile(currentUser);
    }
}