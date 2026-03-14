package util;

import java.time.format.DateTimeFormatter;


 //Application-wide constants.
public class Constants {

    // Data file names
    public static final String USERS_FILE = "users.txt";
    public static final String RESTAURANTS_FILE = "restaurants.txt";
    public static final String MENU_ITEMS_FILE = "menu_items.txt";
    public static final String ORDERS_FILE = "orders.txt";

    // Date/time format for order timestamps
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // CSV separator
    public static final String CSV_SEPARATOR = ",";

    // Other constants...
}