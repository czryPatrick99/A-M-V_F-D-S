package com.amv.fooddelivery.model;

public class Restaurant {
    private String name;
    private String[] menuItems;
    private double[] prices;
    
    public Restaurant(String name, String[] menuItems, double[] prices) {
        this.name = name;
        this.menuItems = menuItems;
        this.prices = prices;
    }
    
    public String getName() { return name; }
    public String[] getMenuItems() {
         return menuItems;
         }
    public double[] getPrices() {
         return prices;
         }
}