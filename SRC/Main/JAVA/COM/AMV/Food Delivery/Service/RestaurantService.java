package com.amv.fooddelivery.service;

import com.amv.fooddelivery.model.Restaurant;
import com.amv.fooddelivery.model.MenuItem;
import java.util.HashMap;
import java.util.Map;

public class RestaurantService {
    private Map<String, Restaurant> restaurants;
    private Map<String, MenuItem[]> restaurantMenus;
    
    public RestaurantService() {
        initializeRestaurants();
    }
    
    private void initializeRestaurants() {
        restaurants = new HashMap<>();
        restaurantMenus = new HashMap<>();
        
        // Pizza Palace
        String[] pizzaItems = {"Margherita Pizza", "Pepperoni Pizza", "BBQ Chicken Pizza"};
        double[] pizzaPrices = {450, 550, 650};
        restaurants.put("Pizza Palace", new Restaurant("Pizza Palace", pizzaItems, pizzaPrices));
        
        // Burger Hub
        String[] burgerItems = {"Classic Burger", "Cheese Burger", "Chicken Burger"};
        double[] burgerPrices = {250, 300, 350};
        restaurants.put("Burger Hub", new Restaurant("Burger Hub", burgerItems, burgerPrices));
        
        // Asian Wok
        String[] asianItems = {"Chicken Fried Rice", "Vegetable Noodles", "Spring Rolls"};
        double[] asianPrices = {320, 280, 150};
        restaurants.put("Asian Wok", new Restaurant("Asian Wok", asianItems, asianPrices));
    }
    
    public Restaurant getRestaurant(String name) {
        return restaurants.get(name);
    }
    
    public String[] getRestaurantNames() {
        return restaurants.keySet().toArray(new String[0]);
    }
    
    public String[] getMenuItems(String restaurantName) {
        Restaurant restaurant = restaurants.get(restaurantName);
        return restaurant != null ? restaurant.getMenuItems() : new String[0];
    }
    
    public double getItemPrice(String restaurantName, int itemIndex) {
        Restaurant restaurant = restaurants.get(restaurantName);
        if (restaurant != null && itemIndex >= 0 && itemIndex < restaurant.getPrices().length) {
            return restaurant.getPrices()[itemIndex];
        }
        return 0;
    }
}