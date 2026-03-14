package util;
import java.util.regex.Pattern;


 //Utility class for input validation.
public class InputValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final String PHONE_REGEX = "^\\d{10}$"; // simple 10-digit phone


     //Check if a string is null or empty after trimming.
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    
     //Validate email format.
     
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        return Pattern.matches(EMAIL_REGEX, email.trim());
    }

    
     // Validate phone number (10 digits).
     
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        return Pattern.matches(PHONE_REGEX, phone.trim());
    }

     //Check if a string can be parsed as a positive integer.
    public static boolean isPositiveInteger(String input) {
        if (input == null) return false;
        try {
            int num = Integer.parseInt(input.trim());
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

     //Check if a string can be parsed as a non-negative double (for prices).
    public static boolean isValidPrice(String input) {
        if (input == null) return false;
        try {
            double price = Double.parseDouble(input.trim());
            return price >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}