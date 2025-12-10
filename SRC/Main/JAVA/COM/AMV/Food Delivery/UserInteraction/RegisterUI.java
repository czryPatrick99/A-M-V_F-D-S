package com.amv.fooddelivery.ui;

import com.amv.fooddelivery.service.RestaurantService;
import com.amv.fooddelivery.util.DisplayUtil;
import java.util.Scanner;

public class RestaurantUI {
    private Scanner scanner;
    private RestaurantService restaurantService;
    
    public RestaurantUI(Scanner scanner, RestaurantService restaurantService) {
        this.scanner = scanner;
        this.restaurantService = restaurantService;
    }
    
    public void browseRestaurants() {
        DisplayUtil.displayHeader("AVAILABLE RESTAURANTS");
        
        String[] restaurants = restaurantService.getRestaurantNames();
        for (int i = 0; i < restaurants.length; i++) {
            System.out.println("[" + (i + 1) + "] " + restaurants[i]);
            displayMenuItems(restaurants[i]);
            System.out.println();
        }
        System.out.println("[" + (restaurants.length + 1) + "] Back to Dashboard");
        System.out.print("Choose an option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice > 0 && choice <= restaurants.length) {
            System.out.println("To order from this restaurant, go to 'Place New Order' from dashboard!");
        }
    }
    
    private void displayMenuItems(String restaurantName) {
        String[] items = restaurantService.getMenuItems(restaurantName);
        if (restaurantName.equals("Pizza Palace")) {
            for (int i = 0; i < items.length; i++) {
                System.out.println("    - " + items[i] + ": Rs." + 
                    restaurantService.getItemPrice(restaurantName, i));
            }
        } else if (restaurantName.equals("Burger Hub")) {
            for (int i = 0; i < items.length; i++) {
                System.out.println("    - " + items[i] + ": Rs." + 
                    restaurantService.getItemPrice(restaurantName, i));
            }
        } else if (restaurantName.equals("Asian Wok")) {
            for (int i = 0; i < items.length; i++) {
                System.out.println("    - " + items[i] + ": Rs." + 
                    restaurantService.getItemPrice(restaurantName, i));
            }
        }
    }
    
    public String selectRestaurant() {
        DisplayUtil.displayHeader("SELECT RESTAURANT");
        
        String[] restaurants = restaurantService.getRestaurantNames();
        for (int i = 0; i < restaurants.length; i++) {
            System.out.println("[" + (i + 1) + "] " + restaurants[i]);
        }
        System.out.println("[" + (restaurants.length + 1) + "] Cancel");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice > 0 && choice <= restaurants.length) {
            return restaurants[choice - 1];
        }
        return null;
    }
    
    public String[] selectMenuItem(String restaurantName) {
        String[] items = restaurantService.getMenuItems(restaurantName);
        DisplayUtil.displayHeader(restaurantName.toUpperCase() + " - MENU");
        
        for (int i = 0; i < items.length; i++) {
            System.out.println("[" + (i + 1) + "] " + items[i] + " - Rs." + 
                restaurantService.getItemPrice(restaurantName, i));
        }
        System.out.println("[" + (items.length + 1) + "] Cancel");
        System.out.print("Choose your item: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice > 0 && choice <= items.length) {
            String itemName = items[choice - 1];
            double price = restaurantService.getItemPrice(restaurantName, choice - 1);
            
            System.out.print("Enter quantity for " + itemName + ": ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            
            return new String[]{itemName, String.valueOf(price), String.valueOf(quantity)};
        }
        return null;
    }
}