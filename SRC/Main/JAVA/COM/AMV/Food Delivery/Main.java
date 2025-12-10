package com.amv.fooddelivery;

import com.amv.fooddelivery.ui.*;
import com.amv.fooddelivery.service.*;
import com.amv.fooddelivery.model.User;
import com.amv.fooddelivery.util.DisplayUtil;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;
        
        // Initialize services
        AuthService authService = new AuthService(scanner);
        OrderService orderService = new OrderService(scanner);
        RestaurantService restaurantService = new RestaurantService();
        UserService userService = new UserService(scanner);
        
        // Initialize UI components
        LoginUI loginUI = new LoginUI(authService);
        RegisterUI registerUI = new RegisterUI(authService);
        RestaurantUI restaurantUI = new RestaurantUI(scanner, restaurantService);
        OrderUI orderUI = new OrderUI(scanner, orderService, restaurantService, restaurantUI);
        ProfileUI profileUI = new ProfileUI(scanner, userService, currentUser);
        DashboardUI dashboardUI = new DashboardUI(scanner, authService, orderService, 
                                                 restaurantUI, orderUI, profileUI);
        
        boolean isRunning = true;

        while (isRunning) {
            DisplayUtil.displayMainHeader();
            System.out.println("[1] Login");
            System.out.println("[2] Register");
            System.out.println("[3] Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            System.out.println("====================================================");

            switch (option) {
                case 1:
                    if (loginUI.showLogin(currentUser)) {
                        currentUser = authService.getCurrentUser();
                        profileUI = new ProfileUI(scanner, userService, currentUser);
                        dashboardUI = new DashboardUI(scanner, authService, orderService, 
                                                     restaurantUI, orderUI, profileUI);
                        dashboardUI.showDashboard();
                    }
                    break;
                case 2:
                    currentUser = registerUI.showRegister();
                    profileUI = new ProfileUI(scanner, userService, currentUser);
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Thank you for using AMV Food Delivery!");
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
        }
        scanner.close();
    }
}