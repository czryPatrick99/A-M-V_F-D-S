package com.amv.fooddelivery.util;

public class Constants {
    // Application Info
    public static final String APP_NAME = "A M V FOOD DELIVERY SYSTEM";
    public static final String APP_VERSION = "1.0.0";
    public static final String DEVELOPER = "AMV Team";
    
    // Main Menu Options
    public static final String[] MAIN_MENU = {
        "Login",
        "Register",
        "Exit"
    };
    
    // Dashboard Menu Options
    public static final String[] DASHBOARD_MENU = {
        "Browse Restaurants",
        "Place New Order",
        "View Current Order",
        "View Order History",
        "My Profile",
        "Logout"
    };
    
    // Profile Menu Options
    public static final String[] PROFILE_MENU = {
        "View Profile",
        "Edit Profile",
        "Change Password",
        "Back to Dashboard"
    };
    
    // Order Status Messages
    public static final String ORDER_PREPARING = "Your order is being prepared...";
    public static final String ORDER_OUT_FOR_DELIVERY = "Your order is out for delivery!";
    public static final String ORDER_DELIVERED = "Your order has been delivered!";
    
    // Validation Constants
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_ORDER_QUANTITY = 10;
    public static final int MIN_ORDER_QUANTITY = 1;
    
    // Display Constants
    public static final int CONSOLE_WIDTH = 60;
    public static final String LINE_SEPARATOR = "=".repeat(CONSOLE_WIDTH);
    
    // File Paths
    public static final String USERS_FILE = "data/users.txt";
    public static final String RESTAURANTS_FILE = "data/restaurants.txt";
    public static final String ORDERS_FILE = "data/orders.txt";
}