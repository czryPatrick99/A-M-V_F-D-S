package com.amv.fooddelivery.util;

public class DisplayUtil {
    
    public static void displayHeader(String title) {
        System.out.println("====================================================");
        System.out.println("                 " + title);
        System.out.println("====================================================");
    }
    
    public static void displayMainHeader() {
        System.out.print("====================================================\n" +
                "         A M V FOOD DELIVERY SYSTEM  (CLI VERSION)\n" +
                "====================================================\n");
    }
}