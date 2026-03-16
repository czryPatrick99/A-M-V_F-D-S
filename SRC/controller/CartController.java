package controller;

import service.CartService;
import service.RestaurantService;
import service.OrderService;
import model.MenuItem;
import model.User;
import model.Order;
import util.InputValidator;
import java.util.List;
import java.util.Scanner;

 //Controller for cart operations and checkout.
public class CartController {
    private CartService cartService;
    private RestaurantService restaurantService;
    private OrderService orderService;
    private Scanner scanner;

    public CartController() {
        this.cartService = new CartService();
        this.restaurantService = new RestaurantService();
        this.orderService = new OrderService();
        this.scanner = new Scanner(System.in);
    }

    public boolean addToCart(int restaurantId, int menuItemId) {
        MenuItem item = restaurantService.getMenuItemById(menuItemId);
        if (item == null || item.getRestaurantId() != restaurantId) {
            System.out.println("Invalid menu item for this restaurant.");
            return false;
        }

        int qty = getPositiveInteger("Enter quantity: ");
        if (qty <= 0) return false;

        boolean added = cartService.addItem(item, qty);
        if (added) {
            System.out.println("Added " + item.getName() + " to cart.");
        } else {
            System.out.println("Cannot add items from different restaurants. Clear cart or place order first.");
        }
        return added;
    }

    public void viewCart() {
        if (cartService.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        List<CartService.CartItem> items = cartService.getItems();
        System.out.println("\n--- Your Cart ---");
        for (int i = 0; i < items.size(); i++) {
            CartService.CartItem ci = items.get(i);
            System.out.printf("%d. %s x%d = $%.2f\n", i+1, ci.getMenuItem().getName(), ci.getQuantity(), ci.getTotalPrice());
        }
        System.out.printf("Total: $%.2f\n", cartService.getTotal());
    }

    public void checkout(User user) {
        if (cartService.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to checkout.");
            return;
        }

        viewCart();
        System.out.print("Proceed to checkout? (y/n): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Checkout cancelled.");
            return;
        }

        try {
            Order order = orderService.placeOrder(user, cartService);
            System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void clearCart() {
        cartService.clear();
        System.out.println("Cart cleared.");
    }

    // Helper for integer input
    private int getPositiveInteger(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (!InputValidator.isPositiveInteger(input)) {
                System.out.println("Please enter a positive number.");
            }
        } while (!InputValidator.isPositiveInteger(input));
        return Integer.parseInt(input);
    }
}