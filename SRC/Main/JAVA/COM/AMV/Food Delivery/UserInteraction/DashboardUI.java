package com.amv.fooddelivery.ui;

import com.amv.fooddelivery.service.AuthService;
import com.amv.fooddelivery.service.OrderService;
import com.amv.fooddelivery.util.DisplayUtil;
import java.util.Scanner;

public class DashboardUI {
    private Scanner scanner;
    private AuthService authService;
    private OrderService orderService;
    private RestaurantUI restaurantUI;
    private OrderUI orderUI;
    private ProfileUI profileUI;
    
    public DashboardUI(Scanner scanner, AuthService authService, OrderService orderService,
                      RestaurantUI restaurantUI, OrderUI orderUI, ProfileUI profileUI) {
        this.scanner = scanner;
        this.authService = authService;
        this.orderService = orderService;
        this.restaurantUI = restaurantUI;
        this.orderUI = orderUI;
        this.profileUI = profileUI;
    }
    
    public void showDashboard() {
        while (authService.getCurrentUser() != null) {
            DisplayUtil.displayHeader("USER DASHBOARD");
            System.out.println("[1] Browse Restaurants");
            System.out.println("[2] Place New Order");
            if (orderService.hasActiveOrder()) {
                System.out.println("[3] View Current Order");
            } else {
                System.out.println("[3] No Active Orders");
            }
            System.out.println("[4] My Profile");
            System.out.println("[5] Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    restaurantUI.browseRestaurants();
                    break;
                case 2:
                    orderUI.placeNewOrder();
                    break;
                case 3:
                    if (orderService.hasActiveOrder()) {
                        orderUI.viewCurrentOrder();
                    } else {
                        System.out.println("No active orders. Place a new order first!");
                    }
                    break;
                case 4:
                    profileUI.showProfile();
                    break;
                case 5:
                    authService.logout();
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}