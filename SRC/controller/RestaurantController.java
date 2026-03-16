package controller;

import service.RestaurantService;
import model.Restaurant;
import model.MenuItem;
import util.InputValidator;
import java.util.List;
import java.util.Scanner;


 //Controller for browsing restaurants and viewing menus.
public class RestaurantController {
    private RestaurantService restaurantService;
    private Scanner scanner;

    public RestaurantController() {
        this.restaurantService = new RestaurantService();
        this.scanner = new Scanner(System.in);
    }

    public void browseRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available at the moment.");
            return;
        }
        System.out.println("\n--- Restaurants ---");
        for (Restaurant r : restaurants) {
            System.out.println(r.getId() + ". " + r.getName() + " | " + r.getAddress() + " | Cuisine: " + r.getCuisineType());
        }
    }

    public void viewMenu(int restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant == null) {
            System.out.println("Restaurant not found.");
            return;
        }
        List<MenuItem> menu = restaurantService.getMenuForRestaurant(restaurantId);
        if (menu.isEmpty()) {
            System.out.println("This restaurant has no menu items yet.");
            return;
        }
        System.out.println("\n--- Menu of " + restaurant.getName() + " ---");
        for (MenuItem item : menu) {
            System.out.printf("%d. %s - $%.2f\n", item.getId(), item.getName(), item.getPrice());
            if (item.getDescription() != null && !item.getDescription().isEmpty()) {
                System.out.println("   " + item.getDescription());
            }
        }
    }

     //Interactive method to select a restaurant and view its menu.
    public int selectRestaurantAndViewMenu() {
        browseRestaurants();
        System.out.print("\nEnter restaurant ID to view menu (0 to go back): ");
        String input = scanner.nextLine();
        if (!InputValidator.isPositiveInteger(input)) {
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
        int id = Integer.parseInt(input);
        if (id == 0) return -1;
        Restaurant r = restaurantService.getRestaurantById(id);
        if (r == null) {
            System.out.println("Invalid restaurant ID.");
            return -1;
        }
        viewMenu(id);
        return id;
    }
}