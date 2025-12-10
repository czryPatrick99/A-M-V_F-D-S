package com.amv.fooddelivery.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);
    
    // Validation patterns
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^[0-9\\-+()\\s]{10,15}$");
    private static final Pattern USERNAME_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9_]{3,20}$");
    
    public static int getIntInput(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    DisplayUtil.printError("Input cannot be empty.");
                    continue;
                }
                
                int value = Integer.parseInt(input);
                
                if (value < min || value > max) {
                    DisplayUtil.printError(String.format("Please enter a number between %d and %d.", min, max));
                    continue;
                }
                
                return value;
            } catch (NumberFormatException e) {
                DisplayUtil.printError("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    public static String getStringInput(String prompt, boolean required) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (required && input.isEmpty()) {
                DisplayUtil.printError("This field is required.");
                continue;
            }
            
            return input;
        }
    }
    
    public static String getUsernameInput(String prompt) {
        while (true) {
            String username = getStringInput(prompt, true);
            
            if (!USERNAME_PATTERN.matcher(username).matches()) {
                DisplayUtil.printError("Username must be 3-20 characters and can only contain letters, numbers, and underscores.");
                continue;
            }
            
            return username;
        }
    }
    
    public static String getPasswordInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String password = scanner.nextLine().trim();
            
            if (password.length() < Constants.MIN_PASSWORD_LENGTH) {
                DisplayUtil.printError(String.format("Password must be at least %d characters long.", Constants.MIN_PASSWORD_LENGTH));
                continue;
            }
            
            return password;
        }
    }
    
    public static String getEmailInput(String prompt) {
        while (true) {
            String email = getStringInput(prompt, false);
            
            if (!email.isEmpty() && !EMAIL_PATTERN.matcher(email).matches()) {
                DisplayUtil.printError("Please enter a valid email address.");
                continue;
            }
            
            return email;
        }
    }
    
    public static String getPhoneInput(String prompt) {
        while (true) {
            String phone = getStringInput(prompt, false);
            
            if (!phone.isEmpty() && !PHONE_PATTERN.matcher(phone).matches()) {
                DisplayUtil.printError("Please enter a valid phone number (10-15 digits).");
                continue;
            }
            
            return phone;
        }
    }
    
    public static double getDoubleInput(String prompt, double min) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    DisplayUtil.printError("Input cannot be empty.");
                    continue;
                }
                
                double value = Double.parseDouble(input);
                
                if (value < min) {
                    DisplayUtil.printError(String.format("Value must be at least %.2f.", min));
                    continue;
                }
                
                return value;
            } catch (NumberFormatException e) {
                DisplayUtil.printError("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    public static boolean getConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                DisplayUtil.printError("Please enter 'y' for yes or 'n' for no.");
            }
        }
    }
    
    public static void closeScanner() {
        scanner.close();
    }
    
    public static String getNonEmptyInput(String prompt) {
        return getStringInput(prompt, true);
    }
    
    public static String getOptionalInput(String prompt) {
        return getStringInput(prompt, false);
    }
}