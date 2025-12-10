package com.amv.fooddelivery.ui;

import com.amv.fooddelivery.service.OrderService;
import com.amv.fooddelivery.service.RestaurantService;
import com.amv.fooddelivery.model.User;
import com.amv.fooddelivery.util.DisplayUtil;
import java.util.Scanner;

public class OrderUI {
    private Scanner scanner;
    private OrderService orderService;
    private RestaurantService restaurantService;
    private RestaurantUI restaurantUI;
    
    public OrderUI(Scanner scanner, OrderService orderService, 
                  RestaurantService restaurantService, RestaurantUI restaurantUI) {
        this.scanner = scanner;
        this.orderService = orderService;
        this.restaurantService = restaurantService;
        this.restaurantUI = restaurantUI;
    }
    
    public void placeNewOrder() {
        String restaurantName = restaurantUI.selectRestaurant();
        if (restaurantName == null) {
            System.out.println("Order cancelled.");
            return;
        }
        
        String[] itemDetails = restaurantUI.selectMenuItem(restaurantName);
        if (itemDetails == null) {
            System.out.println("Order cancelled.");
            return;
        }
        
        String itemName = itemDetails[0];
        double price = Double.parseDouble(itemDetails[1]);
        int quantity = Integer.parseInt(itemDetails[2]);
        
        orderService.createOrder(restaurantName, itemName, price, quantity);
        checkout();
    }
    
    public void checkout() {
        DisplayUtil.displayHeader("ORDER SUMMARY");
        
        var order = orderService.getCurrentOrder();
        System.out.println("Restaurant: " + order.getRestaurant());
        System.out.println("Item: " + order.getItem());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Price per item: Rs." + order.getPrice());
        System.out.println("Total Amount: Rs." + order.getTotal());
        System.out.println("====================================================");
        
        System.out.println("[1] Confirm Order");
        System.out.println("[2] Cancel Order");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 1) {
            // Order will be confirmed in Main.java
            System.out.println("Proceeding to confirm order...");
        } else {
            System.out.println("Order cancelled.");
            orderService.resetOrder();
        }
    }
    
    public void viewCurrentOrder() {
        DisplayUtil.displayHeader("CURRENT ORDER STATUS");
        
        var order = orderService.getCurrentOrder();
        System.out.println("Restaurant: " + order.getRestaurant());
        System.out.println("Item: " + order.getItem());
        System.out.println("Quantity: " + order.getQuantity());
        System.out.println("Total Paid: Rs." + order.getTotal());
        System.out.println("Status: " + order.getStatus());
        
        if (order.getStatus().equals("Preparing")) {
            System.out.println("\nYour food is being prepared...");
            System.out.println("[1] Mark as Delivered");
            System.out.println("[2] Back to Dashboard");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            if (choice == 1) {
                orderService.markAsDelivered();
            }
        } else {
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        }
    }
}