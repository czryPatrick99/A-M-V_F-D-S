package service;

import dao.OrderDAO;
import model.Order;
import model.User;
import model.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;


 //Service for order placement and retrieval.

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

     //Place an order from the current cart.
     
    public Order placeOrder(User user, CartService cartService) {
        if (cartService.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }
        Integer restaurantId = cartService.getRestaurantId();
        if (restaurantId == null) {
            throw new IllegalStateException("No restaurant selected");
        }

        String itemsSummary = cartService.getItemsSummary();
        Order order = new Order(
            0, // ID will be set by DAO
            user.getId(),
            restaurantId,
            itemsSummary,
            OrderStatus.PENDING,
            LocalDateTime.now()
        );
        Order placedOrder = orderDAO.addOrder(order);
        cartService.clear(); // clear cart after successful order
        return placedOrder;
    }

    
     // Get order history for a user.
     
    public List<Order> getOrderHistory(User user) {
        return orderDAO.getOrdersByUser(user.getId());
    }

    
     //Get order by ID.
     
    public Order getOrderById(int orderId) {
        return orderDAO.getOrderById(orderId);
    }

    
     // Cancel an order if it's still pending.
     
    public boolean cancelOrder(int orderId) {
        Order order = orderDAO.getOrderById(orderId);
        if (order != null && order.getStatus() == OrderStatus.PENDING) {
            return orderDAO.updateOrderStatus(orderId, OrderStatus.CANCELLED);
        }
        return false;
    }
}