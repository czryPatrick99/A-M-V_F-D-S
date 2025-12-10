package com.amv.fooddelivery.service;

import com.amv.fooddelivery.model.Order;
import com.amv.fooddelivery.model.User;
import java.util.Scanner;

public class OrderService {
    private Scanner scanner;
    private Order currentOrder;
    private boolean hasActiveOrder = false;
    
    public OrderService(Scanner scanner) {
        this.scanner = scanner;
    }
    
    public Order getCurrentOrder() {
        return currentOrder;
    }
    
    public boolean hasActiveOrder() {
        return hasActiveOrder;
    }
    
    public void createOrder(String restaurant, String item, double price, int quantity) {
        currentOrder = new Order(restaurant, item, price, quantity);
        hasActiveOrder = true;
    }
    
    public void confirmOrder(User user) {
        System.out.println("\n====================================================");
        System.out.println("              ORDER PLACED SUCCESSFULLY!");
        System.out.println("====================================================");
        System.out.println("Restaurant: " + currentOrder.getRestaurant());
        System.out.println("Item: " + currentOrder.getItem() + " x " + currentOrder.getQuantity());
        System.out.println("Total Amount: Rs." + currentOrder.getTotal());
        System.out.println("Delivery Address: " + user.getAddress());
        System.out.println("Status: " + currentOrder.getStatus());
        System.out.println("Estimated Delivery: 30-45 minutes");
        System.out.println("====================================================");
        System.out.println("Thank you for your order!");
        
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }
    
    public void markAsDelivered() {
        if (currentOrder != null) {
            currentOrder.setStatus("Delivered");
            System.out.println("Order marked as delivered! Thank you!");
            resetOrder();
        }
    }
    
    public void resetOrder() {
        hasActiveOrder = false;
        currentOrder = null;
    }
}