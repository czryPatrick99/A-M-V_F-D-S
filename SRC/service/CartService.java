package service;

import model.MenuItem;
import java.util.ArrayList;
import java.util.List;


 //Service for cart management. This service is session-scoped (one instance per user session).
 
public class CartService {
    // Inner class to represent cart items with quantity
    public static class CartItem {
        private MenuItem menuItem;
        private int quantity;

        public CartItem(MenuItem menuItem, int quantity) {
            this.menuItem = menuItem;
            this.quantity = quantity;
        }

        public MenuItem getMenuItem() { return menuItem; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        public double getTotalPrice() {
            return menuItem.getPrice() * quantity;
        }
    }

    private List<CartItem> items;
    private Integer restaurantId; // All items must be from same restaurant

    public CartService() {
        this.items = new ArrayList<>();
        this.restaurantId = null;
    }

    
     // Add an item to cart. If item already exists, quantity is increased.
    
    public boolean addItem(MenuItem menuItem, int quantity) {
        if (restaurantId == null) {
            restaurantId = menuItem.getRestaurantId();
        } else if (!restaurantId.equals(menuItem.getRestaurantId())) {
            return false; // cannot mix restaurants
        }

        for (CartItem ci : items) {
            if (ci.getMenuItem().getId() == menuItem.getId()) {
                ci.setQuantity(ci.getQuantity() + quantity);
                return true;
            }
        }
        items.add(new CartItem(menuItem, quantity));
        return true;
    }

    /**
     * Remove an item from cart by menu item ID.
     */
    public boolean removeItem(int menuItemId) {
        return items.removeIf(ci -> ci.getMenuItem().getId() == menuItemId);
    }

    /**
     * Update quantity of an item.
     */
    public boolean updateQuantity(int menuItemId, int newQuantity) {
        for (CartItem ci : items) {
            if (ci.getMenuItem().getId() == menuItemId) {
                if (newQuantity <= 0) {
                    return removeItem(menuItemId);
                } else {
                    ci.setQuantity(newQuantity);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get all cart items.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Get total price of all items.
     */
    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    /**
     * Clear the cart.
     */
    public void clear() {
        items.clear();
        restaurantId = null;
    }

    /**
     * Check if cart is empty.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Get the restaurant ID for all items (null if empty).
     */
    public Integer getRestaurantId() {
        return restaurantId;
    }

    /**
     * Generate a summary string of items for order.
     */
    public String getItemsSummary() {
        StringBuilder sb = new StringBuilder();
        for (CartItem ci : items) {
            sb.append(ci.getMenuItem().getName())
              .append(" x")
              .append(ci.getQuantity())
              .append(", ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // remove trailing comma and space
        }
        return sb.toString();
    }
}