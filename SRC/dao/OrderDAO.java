package dao;

import model.Order;
import model.enums.OrderStatus;
import util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class OrderDAO {
    private static final String FILE_NAME = FileUtil.getDataFilePath(Constants.ORDERS_FILE);

    public OrderDAO() {
        FileUtil.ensureDataDirectoryExists();
    }

    public Order addOrder(Order order) {
        int newId = getNextId();
        order.setOrderId(newId);
        if (order.getOrderTime() == null) {
            order.setOrderTime(LocalDateTime.now());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(orderToCsv(order));
            writer.newLine();
            System.out.println("Order placed with ID: " + newId);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Order order = csvToOrder(line);
                if (order != null) list.add(order);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public List<Order> getOrdersByUser(int userId) {
        return getAllOrders().stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public Order getOrderById(int orderId) {
        return getAllOrders().stream()
                .filter(o -> o.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    public boolean updateOrderStatus(int orderId, OrderStatus newStatus) {
        List<Order> list = getAllOrders();
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOrderId() == orderId) {
                list.get(i).setStatus(newStatus);
                found = true;
                break;
            }
        }
        if (found) {
            saveAllOrders(list);
            System.out.println("Order status updated.");
        } else {
            System.out.println("Order with ID " + orderId + " not found.");
        }
        return found;
    }

    public boolean cancelOrder(int orderId) {
        return updateOrderStatus(orderId, OrderStatus.CANCELLED);
    }

    private String orderToCsv(Order order) {
        return order.getOrderId() + Constants.CSV_SEPARATOR +
               order.getUserId() + Constants.CSV_SEPARATOR +
               order.getRestaurantId() + Constants.CSV_SEPARATOR +
               CsvUtil.escape(order.getItems()) + Constants.CSV_SEPARATOR +
               order.getStatus().name() + Constants.CSV_SEPARATOR +
               order.getOrderTime().format(Constants.DATE_TIME_FORMATTER);
    }

    private Order csvToOrder(String line) {
        String[] parts = line.split(Constants.CSV_SEPARATOR, -1);
        if (parts.length < 6) return null;
        try {
            int orderId = Integer.parseInt(parts[0]);
            int userId = Integer.parseInt(parts[1]);
            int restaurantId = Integer.parseInt(parts[2]);
            String items = CsvUtil.unescape(parts[3]);
            OrderStatus status = OrderStatus.valueOf(parts[4]);
            LocalDateTime time = LocalDateTime.parse(parts[5], Constants.DATE_TIME_FORMATTER);
            return new Order(orderId, userId, restaurantId, items, status, time);
        } catch (Exception e) {
            System.out.println("Error parsing order line: " + line);
            return null;
        }
    }

    private int getNextId() {
        return getAllOrders().stream()
                .mapToInt(Order::getOrderId)
                .max()
                .orElse(0) + 1;
    }

    private void saveAllOrders(List<Order> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Order o : list) {
                writer.write(orderToCsv(o));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }
}