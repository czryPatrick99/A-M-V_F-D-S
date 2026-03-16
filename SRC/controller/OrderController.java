package controller;

import service.OrderService;
import model.Order;
import model.User;
import util.InputValidator;
import java.util.List;
import java.util.Scanner;

 // Controller for viewing order history and tracking.
public class OrderController {
    private OrderService orderService;
    private Scanner scanner;

    public OrderController() {
        this.orderService = new OrderService();
        this.scanner = new Scanner(System.in);
    }

    public void viewOrderHistory(User user) {
        List<Order> orders = orderService.getOrderHistory(user);
        if (orders.isEmpty()) {
            System.out.println("You have no past orders.");
            return;
        }
        System.out.println("\n--- Your Order History ---");
        for (Order o : orders) {
            System.out.println("Order ID: " + o.getOrderId());
            System.out.println("Restaurant ID: " + o.getRestaurantId());
            System.out.println("Items: " + o.getItems());
            System.out.println("Status: " + o.getStatus());
            System.out.println("Date: " + o.getFormattedOrderTime());
            System.out.println("--------------------------");
        }
    }

    public void trackOrder() {
        System.out.print("Enter order ID to track: ");
        String input = scanner.nextLine();
        if (!InputValidator.isPositiveInteger(input)) {
            System.out.println("Invalid order ID.");
            return;
        }
        int orderId = Integer.parseInt(input);
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.println("\n--- Order Tracking ---");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Items: " + order.getItems());
        System.out.println("Placed on: " + order.getFormattedOrderTime());
    }
}