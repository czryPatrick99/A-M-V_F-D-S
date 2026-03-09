import java.util.ArrayList;
import java.util.Scanner;

public class Prototype {
    static Scanner sc = new Scanner(System.in);

    // User account details
    static String fullName;
    static String email;
    static String phone;
    static String address;
    static String password;

    // Login state
    static String loginEmail;
    static boolean checkingLogin = true;

    // Order details
    static ArrayList<String> orderItems = new ArrayList<>();
    static double totalAmount = 0.0;
    static String restaurantNameOrdered;
    static int orderId;
    static String restaurantAddress;  // address of the chosen restaurant

    // Payment details 
    static int paymentOption;
    static String cardNumber;
    static String expirationDate;
    static String cvv;
    static String mobileBankingNumber;
    static String eWalletAccountNumber;

    public static void main(String args[]) {
        boolean running = true;

        System.out.println("------ | WELCOME TO AMV FOOD DELIVERY SYSTEM | ------");
        System.out.println("-------Your digital hunger satisfaction partner---------");

        while (running) {
            System.out.println("\n\n");
            System.out.println("[1] Signup/Register");
            System.out.println("[2] Login");
            System.out.println("[3] Exit");
            System.out.print("\nPlease select an option: ");

            // Read integer
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Signup/Register selected.");
                    getRegister();
                    break;
                case 2:
                    System.out.println("Login selected.");
                    getLogin();
                    break;
                case 3:
                    System.out.println("Exiting the application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("\n=================------------------------------=======================");
        System.out.println("      © 2026 AMV Food Delivery. All rights reserved.");
        sc.close();
    }

    // -------------------- REGISTRATION --------------------
    static void getRegister() {
        System.out.println("--------------------Signup/Register--------------------");
        System.out.println("Please enter your details to create an account.\n");

        System.out.print("Enter your Full Name: ");
        fullName = sc.nextLine();

        System.out.print("Enter your Email: ");
        email = sc.nextLine();

        System.out.print("Enter Your Phone Number: ");
        phone = sc.nextLine();

        System.out.print("Enter Your Address: ");
        address = sc.nextLine();

        System.out.print("Create a Password: ");
        password = sc.nextLine();

        System.out.println("Account created successfully! You can now log in.");
    }

    // -------------------- LOGIN --------------------
    static void getLogin() {
        System.out.println("--------------------Login--------------------");
        System.out.println("Please enter your email and password to log in.\n");

        System.out.print("Enter your Email: ");
        loginEmail = sc.nextLine();

        System.out.print("Enter your Password: ");
        String loginPassword = sc.nextLine();

        if (loginEmail.equals(email) && loginPassword.equals(password)) {
            System.out.println("Login successful! Welcome back, " + fullName + "!");

            checkingLogin = true;
            while (checkingLogin) {
                System.out.println("\n\n");
                System.out.println("[1] View Restaurants");
                System.out.println("[2] Place Order");
                System.out.println("[3] View My Order");
                System.out.println("[4] Make Payment");
                System.out.println("[5] Track Delivery");
                System.out.println("[6] Logout");
                System.out.print("\nPlease select an option: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next();
                }
                int userOption = sc.nextInt();
                sc.nextLine();

                switch (userOption) {
                    case 1:
                        viewRestaurants();
                        break;
                    case 2:
                        placeOrder();
                        break;
                    case 3:
                        showOrderDetails();
                        break;
                    case 4:
                        makePayment();
                        break;
                    case 5:
                        trackDelivery();
                        break;
                    case 6:
                        System.out.println("Logging out. Goodbye!");
                        checkingLogin = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    // -------------------- VIEW RESTAURANTS (just a list) --------------------
    static void viewRestaurants() {
        System.out.println("--------------------Available Restaurants--------------------");
        System.out.println("[1] McDonald's");
        System.out.println("[2] Ramu Dai Ko Khaja Ghar");
        System.out.println("[3] KFC");
        System.out.println("[4] Pizza Hut");
        System.out.println("[5] Narayan Sir's MoMos");
        System.out.println("[6] Burger King");
        System.out.println("[7] Chiya Chapau");
        System.out.println("[8] Bhojanalaya");
        System.out.println("[9] Hemja Khaja Ghar");
        System.out.println("[10] Sekhuwa Corner");
        System.out.println("[11] Classic Bakery");
        // Note: ordering is done via "Place Order" option
    }

    // -------------------- PLACE ORDER --------------------
    static void placeOrder() {
        System.out.println("--------------------Place Order--------------------");

        // Ask for restaurant name (could be listed or custom)
        System.out.print("Enter restaurant name (or type 'list' to see available): ");
        String input = sc.nextLine();
        if (input.equalsIgnoreCase("list")) {
            viewRestaurants();
            System.out.print("Enter restaurant name: ");
            restaurantNameOrdered = sc.nextLine();
        } else {
            restaurantNameOrdered = input;
        }

        System.out.print("Enter restaurant phone: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid phone number. Please enter digits only.");
            sc.next();
        }
        int restPhone = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter restaurant address: ");
        restaurantAddress = sc.nextLine();

        // Check if they have a prepared order
        char hasPrepared = getYesNoInput("Do you have a prepared order? (Y/N): ");
        if (hasPrepared == 'y') {
            System.out.print("Enter order ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid order ID. Please enter a number.");
                sc.next();
            }
            orderId = sc.nextInt();
            sc.nextLine();
            System.out.println("Order ID recorded.");
        } else {
            char orderThroughSys = getYesNoInput("Would you like to order through our system? (Y/N): ");
            if (orderThroughSys == 'y') {
                takeOrderItems();
            } else {
                System.out.println("You can place your order directly with the restaurant.");
            }
        }
    }

    // Helper to repeatedly ask for items until user is done
    static void takeOrderItems() {
        System.out.println("Start adding items to your order. (Enter item names when prompted)");
        boolean adding = true;
        while (adding) {
            System.out.println("\nCategories:");
            System.out.println("[1] Fast Food");
            System.out.println("[2] Beverages");
            System.out.println("[3] Desserts");
            System.out.println("[4] Main Course");
            System.out.println("[5] Snacks");
            System.out.println("[6] Done");
            System.out.print("Choose a category: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
            int cat = sc.nextInt();
            sc.nextLine();

            String categoryName = "";
            switch (cat) {
                case 1: categoryName = "Fast Food"; break;
                case 2: categoryName = "Beverage"; break;
                case 3: categoryName = "Dessert"; break;
                case 4: categoryName = "Main Course"; break;
                case 5: categoryName = "Snack"; break;
                case 6:
                    adding = false;
                    continue;
                default:
                    System.out.println("Invalid category. Please try again.");
                    continue;
            }

            System.out.print("Enter " + categoryName + " item name: ");
            String item = sc.nextLine();
            orderItems.add(item + " (" + categoryName + ")");

            // For simplicity, each item costs Rs. 5. In a real app, you would have prices per item.
            totalAmount += 5.0;
            System.out.println("Added \"" + item + "\". Current total: Rs. " + totalAmount);
        }
        System.out.println("Finished adding items.");
    }

    // -------------------- VIEW ORDER --------------------
    static void showOrderDetails() {
        if (orderItems.isEmpty()) {
            System.out.println("No items in your order yet.");
            return;
        }
        System.out.println("-------------------- Your Order --------------------");
        System.out.println("Restaurant: " + (restaurantNameOrdered != null ? restaurantNameOrdered : "Not specified"));
        if (orderId != 0) System.out.println("Order ID: " + orderId);
        System.out.println("Items:");
        for (String item : orderItems) {
            System.out.println("  - " + item);
        }
        System.out.println("Total amount: Rs. " + totalAmount);
    }

    // -------------------- MAKE PAYMENT --------------------
    static void makePayment() {
        if (orderItems.isEmpty()) {
            System.out.println("You have no order to pay for. Please place an order first.");
            return;
        }

        System.out.println("-------------------- Payment --------------------");
        System.out.println("Total amount to pay: Rs. " + totalAmount);
        System.out.println("Select payment method:");
        System.out.println("[1] Credit/Debit Card");
        System.out.println("[2] Mobile Banking");
        System.out.println("[3] E-Wallet");
        System.out.println("[4] Cash on Delivery");

        boolean paymentDone = false;
        while (!paymentDone) {
            System.out.print("Enter choice (1-4): ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
            paymentOption = sc.nextInt();
            sc.nextLine();

            switch (paymentOption) {
                case 1:
                    System.out.println("--- Credit/Debit Card ---");
                    System.out.print("Enter card number: ");
                    cardNumber = sc.nextLine();
                    System.out.print("Expiry date (MM/YY): ");
                    expirationDate = sc.nextLine();
                    System.out.print("CVV: ");
                    cvv = sc.nextLine();
                    System.out.println("Payment successful via Card!");
                    paymentDone = true;
                    break;
                case 2:
                    System.out.println("--- Mobile Banking ---");
                    System.out.print("Enter mobile banking number: ");
                    mobileBankingNumber = sc.nextLine();
                    System.out.println("Payment successful via Mobile Banking!");
                    paymentDone = true;
                    break;
                case 3:
                    System.out.println("--- E-Wallet ---");
                    System.out.print("Enter e-wallet account number: ");
                    eWalletAccountNumber = sc.nextLine();
                    System.out.println("Payment successful via E-Wallet!");
                    paymentDone = true;
                    break;
                case 4:
                    System.out.println("--- Cash on Delivery ---");
                    System.out.println("Please have Rs. " + totalAmount + " ready when your order arrives.");
                    paymentDone = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
        }

        // After successful payment, you might want to clear the order or mark it as paid.
        // For this demo, we keep the order details but you could add a flag.
        System.out.println("Thank you for your payment!");
    }

    // -------------------- TRACK DELIVERY --------------------
    static void trackDelivery() {
        if (orderItems.isEmpty()) {
            System.out.println("No active order to track.");
            return;
        }
        System.out.println("-------------------- Delivery Tracking --------------------");
        System.out.println("Your order is being prepared and will be delivered to:");
        System.out.println("  " + address);
        if (restaurantAddress != null && !restaurantAddress.isEmpty()) {
            System.out.println("From restaurant at: " + restaurantAddress);
        }
        System.out.println("\nEstimated delivery time: 30-45 minutes");
        System.out.println("Thank you for choosing AMV Food Delivery!");
    }

    // -------------------- HELPER: Yes/No Input --------------------
    static char getYesNoInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.length() == 1 && (input.charAt(0) == 'y' || input.charAt(0) == 'n')) {
                return input.charAt(0);
            }
            System.out.println("Please enter Y or N.");
        }
    }
}