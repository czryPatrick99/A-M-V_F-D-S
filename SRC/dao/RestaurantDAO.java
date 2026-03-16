package dao;

import model.Restaurant;
import util.*;
import java.io.*;
import java.util.*;

public class RestaurantDAO {
    private static final String FILE_NAME = FileUtil.getDataFilePath(Constants.RESTAURANTS_FILE);

    public RestaurantDAO() {
        FileUtil.ensureDataDirectoryExists();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        int newId = getNextId();
        restaurant.setId(newId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(restaurantToCsv(restaurant));
            writer.newLine();
            System.out.println("Restaurant added with ID: " + newId);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        return restaurant;
    }

    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Restaurant r = csvToRestaurant(line);
                if (r != null) list.add(r);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public Restaurant getRestaurantById(int id) {
        return getAllRestaurants().stream()
                .filter(r -> r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean updateRestaurant(Restaurant updated) {
        List<Restaurant> list = getAllRestaurants();
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == updated.getId()) {
                list.set(i, updated);
                found = true;
                break;
            }
        }
        if (found) {
            saveAllRestaurants(list);
            System.out.println("Restaurant updated.");
        } else {
            System.out.println("Restaurant with ID " + updated.getId() + " not found.");
        }
        return found;
    }

    public boolean deleteRestaurant(int id) {
        List<Restaurant> list = getAllRestaurants();
        boolean removed = list.removeIf(r -> r.getId() == id);
        if (removed) {
            saveAllRestaurants(list);
            System.out.println("Restaurant deleted.");
        } else {
            System.out.println("Restaurant with ID " + id + " not found.");
        }
        return removed;
    }

    private String restaurantToCsv(Restaurant r) {
        return r.getId() + Constants.CSV_SEPARATOR +
               CsvUtil.escape(r.getName()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(r.getAddress()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(r.getContact()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(r.getCuisineType());
    }

    private Restaurant csvToRestaurant(String line) {
        String[] parts = line.split(Constants.CSV_SEPARATOR, -1);
        if (parts.length < 5) return null;
        try {
            int id = Integer.parseInt(parts[0]);
            String name = CsvUtil.unescape(parts[1]);
            String address = CsvUtil.unescape(parts[2]);
            String contact = CsvUtil.unescape(parts[3]);
            String cuisine = CsvUtil.unescape(parts[4]);
            return new Restaurant(id, name, address, contact, cuisine);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private int getNextId() {
        return getAllRestaurants().stream()
                .mapToInt(Restaurant::getId)
                .max()
                .orElse(0) + 1;
    }

    private void saveAllRestaurants(List<Restaurant> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Restaurant r : list) {
                writer.write(restaurantToCsv(r));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving restaurants: " + e.getMessage());
        }
    }
}