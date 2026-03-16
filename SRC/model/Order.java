package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private String items;       // Simple representation like "Pizza x2, Burger x1"
    private OrderStatus status; // Use enum instead of plain string
    private LocalDateTime orderTime; // Timestamp of order placement

    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(int orderId, int userId, int restaurantId, String items, OrderStatus status, LocalDateTime orderTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.items = items;
        this.status = status;
        this.orderTime = orderTime;
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public LocalDateTime getOrderTime() { return orderTime; }
    public void setOrderTime(LocalDateTime orderTime) { this.orderTime = orderTime; }

    // Helper to format date/time nicely
    public String getFormattedOrderTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderTime.format(formatter);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", items='" + items + '\'' +
                ", status=" + status +
                ", orderTime=" + getFormattedOrderTime() +
                '}';
    }
}