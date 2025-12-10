package com.amv.fooddelivery.model;

public class Order {
    private String restaurant;
    private String item;
    private double price;
    private int quantity;
    private double total;
    private String status;
    
    public Order() {}
    
    public Order(String restaurant, String item, double price, int quantity) {
        this.restaurant = restaurant;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.total = price * quantity;
        this.status = "Preparing";
    }
    
    // Getters and Setters
    public String getRestaurant() { return restaurant; }
    public void setRestaurant(String restaurant) { this.restaurant = restaurant; }
    
    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}