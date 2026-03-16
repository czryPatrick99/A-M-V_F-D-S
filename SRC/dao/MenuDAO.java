package dao;

import model.MenuItem;
import util.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MenuDAO {
    private static final String FILE_NAME = FileUtil.getDataFilePath(Constants.MENU_ITEMS_FILE);

    public MenuDAO() {
        FileUtil.ensureDataDirectoryExists();
    }

    public MenuItem addMenuItem(MenuItem item) {
        int newId = getNextId();
        item.setId(newId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(menuItemToCsv(item));
            writer.newLine();
            System.out.println("Menu item added with ID: " + newId);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        return item;
    }

    public List<MenuItem> getMenuItemsByRestaurant(int restaurantId) {
        return getAllMenuItems().stream()
                .filter(item -> item.getRestaurantId() == restaurantId)
                .collect(Collectors.toList());
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                MenuItem item = csvToMenuItem(line);
                if (item != null) list.add(item);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public MenuItem getMenuItemById(int id) {
        return getAllMenuItems().stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean updateMenuItem(MenuItem updated) {
        List<MenuItem> list = getAllMenuItems();
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == updated.getId()) {
                list.set(i, updated);
                found = true;
                break;
            }
        }
        if (found) {
            saveAllMenuItems(list);
            System.out.println("Menu item updated.");
        } else {
            System.out.println("Menu item with ID " + updated.getId() + " not found.");
        }
        return found;
    }

    public boolean deleteMenuItem(int id) {
        List<MenuItem> list = getAllMenuItems();
        boolean removed = list.removeIf(item -> item.getId() == id);
        if (removed) {
            saveAllMenuItems(list);
            System.out.println("Menu item deleted.");
        } else {
            System.out.println("Menu item with ID " + id + " not found.");
        }
        return removed;
    }

    public void deleteMenuItemsByRestaurant(int restaurantId) {
        List<MenuItem> list = getAllMenuItems();
        list.removeIf(item -> item.getRestaurantId() == restaurantId);
        saveAllMenuItems(list);
    }

    private String menuItemToCsv(MenuItem item) {
        return item.getId() + Constants.CSV_SEPARATOR +
               item.getRestaurantId() + Constants.CSV_SEPARATOR +
               CsvUtil.escape(item.getName()) + Constants.CSV_SEPARATOR +
               item.getPrice() + Constants.CSV_SEPARATOR +
               CsvUtil.escape(item.getDescription());
    }

    private MenuItem csvToMenuItem(String line) {
        String[] parts = line.split(Constants.CSV_SEPARATOR, -1);
        if (parts.length < 5) return null;
        try {
            int id = Integer.parseInt(parts[0]);
            int restId = Integer.parseInt(parts[1]);
            String name = CsvUtil.unescape(parts[2]);
            double price = Double.parseDouble(parts[3]);
            String desc = CsvUtil.unescape(parts[4]);
            return new MenuItem(id, restId, name, price, desc);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int getNextId() {
        return getAllMenuItems().stream()
                .mapToInt(MenuItem::getId)
                .max()
                .orElse(0) + 1;
    }

    private void saveAllMenuItems(List<MenuItem> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (MenuItem item : list) {
                writer.write(menuItemToCsv(item));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving menu items: " + e.getMessage());
        }
    }
}