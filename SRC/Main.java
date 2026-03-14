

import controller.*;
import model.User;
import util.SessionManager;
import util.InputValidator;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserController userController = new UserController();
    private static RestaurantController restaurantController = new RestaurantController();
    private static CartController cartController = new CartController();
    private static OrderController orderController = new OrderController();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("   Welcome to Food Delivery System   ");
        System.out.println("=====================================");

        while (true) {
            if (SessionManager.isLoggedIn()) {
                showUserMenu();
            } else {
                showGuestMenu();
            }
        }
    }

     // Menu for non-authenticated users.
    private static void showGuestMenu() {
        System.out.println("\n--- Guest Menu ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                userController.register();
                break;
            case "2":
                userController.login();
                break;
            case "3":
                System.out.println("Thank you for using Food Delivery System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

     // Menu for logged-in users.
    private static void showUserMenu() {
        User currentUser = SessionManager.getCurrentUser();
        System.out.println("\n--- Main Menu (Logged in as: " + currentUser.getName() + ") ---");
        System.out.println("1. Browse Restaurants");
        System.out.println("2. View Restaurant Menu");
        System.out.println("3. Add Item to Cart");
        System.out.println("4. View Cart");
        System.out.println("5. Checkout");
        System.out.println("6. View Order History");
        System.out.println("7. Track Order");
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.print("Choose option: ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                restaurantController.browseRestaurants();
                break;
            case "2":
                viewMenuFlow();
                break;
            case "3":
                addToCartFlow();
                break;
            case "4":
                cartController.viewCart();
                break;
            case "5":
                cartController.checkout(currentUser);
                break;
            case "6":
                orderController.viewOrderHistory(currentUser);
                break;
            case "7":
                orderController.trackOrder();
                break;
            case "8":
                userController.logout(); // SessionManager cleared inside
                break;
            case "9":
                System.out.println("Thank you for using Food Delivery System. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

     //Helper flow: select a restaurant and view its menu.
    private static void viewMenuFlow() {
        restaurantController.selectRestaurantAndViewMenu();
    }

     //Helper flow: add an item to the cart.
     // Prompts for restaurant ID and menu item ID.
     
    private static void addToCartFlow() {
        System.out.print("Enter restaurant ID: ");
        String restInput = scanner.nextLine();
        if (!InputValidator.isPositiveInteger(restInput)) {
            System.out.println("Invalid restaurant ID.");
            return;
        }
        int restId = Integer.parseInt(restInput);

        // Show menu for that restaurant
        restaurantController.viewMenu(restId);

        System.out.print("Enter menu item ID to add to cart: ");
        String itemInput = scanner.nextLine();
        if (!InputValidator.isPositiveInteger(itemInput)) {
            System.out.println("Invalid menu item ID.");
            return;
        }
        int itemId = Integer.parseInt(itemInput);

        cartController.addToCart(restId, itemId);
    }
}