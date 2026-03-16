package controller;

import service.AuthService;
import model.User;
import util.InputValidator;
import util.SessionManager;
import java.util.Scanner;


 // Controller for user authentication (login/register).
public class UserController {
    private AuthService authService;
    private Scanner scanner;

    public UserController() {
        this.authService = new AuthService();
        this.scanner = new Scanner(System.in);
    }

     // Register a new user with input validation.
    public User register() {
        System.out.println("\n--- User Registration ---");
        
        String name = getNonEmptyInput("Enter name: ");
        String email = getValidEmail();
        String password = getNonEmptyInput("Enter password: ");
        String address = getNonEmptyInput("Enter address: ");
        String phone = getValidPhone();
        String paymentInfo = getNonEmptyInput("Enter payment info (e.g., card details): ");

        User newUser = new User(0, name, email, password, address, phone, paymentInfo);
        User registered = authService.register(newUser);
        if (registered != null) {
            System.out.println("Registration successful! Please log in.");
        } else {
            System.out.println("Email already registered. Please use a different email.");
        }
        return null;
    }

     //Log in an existing user.
    public User login() {
        System.out.println("\n--- User Login ---");
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = authService.login(email, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getName() + ".");
            SessionManager.setCurrentUser(user); // Track session
        } else {
            System.out.println("Invalid email or password.");
        }
        return user;
    }

    public void logout() {
        SessionManager.logout();
        System.out.println("You have been logged out.");
    }

    // Helper methods for validated input
    private String getNonEmptyInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!InputValidator.isNotEmpty(input)) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (!InputValidator.isNotEmpty(input));
        return input;
    }

    private String getValidEmail() {
        String email;
        do {
            System.out.print("Enter email: ");
            email = scanner.nextLine().trim();
            if (!InputValidator.isValidEmail(email)) {
                System.out.println("Invalid email format. Please try again.");
            }
        } while (!InputValidator.isValidEmail(email));
        return email;
    }

    private String getValidPhone() {
        String phone;
        do {
            System.out.print("Enter phone number (10 digits): ");
            phone = scanner.nextLine().trim();
            if (!InputValidator.isValidPhone(phone)) {
                System.out.println("Invalid phone number. Must be 10 digits.");
            }
        } while (!InputValidator.isValidPhone(phone));
        return phone;
    }
}