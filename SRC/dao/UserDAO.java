package dao;

import model.User;
import util.*;
import java.io.*;
import java.util.*;

 // Data Access Object for User entities.
 //Handles file-based storage for users.txt
public class UserDAO {
    private static final String FILE_NAME = FileUtil.getDataFilePath(Constants.USERS_FILE);

    public UserDAO() {
        FileUtil.ensureDataDirectoryExists();
    }

    // ---------- Create ----------
    public User addUser(User user) {
        int newId = getNextId();
        user.setId(newId);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(userToCsv(user));
            writer.newLine();
            System.out.println("User added with ID: " + newId);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        return user;
    }

    // ---------- Read All ----------
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return users;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                User user = csvToUser(line);
                if (user != null) users.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return users;
    }

    public User getUserById(int id) {
        return getAllUsers().stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return getAllUsers().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    // ---------- Update ----------
    public boolean updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == updatedUser.getId()) {
                users.set(i, updatedUser);
                found = true;
                break;
            }
        }
        if (found) {
            saveAllUsers(users);
            System.out.println("User updated.");
        } else {
            System.out.println("User with ID " + updatedUser.getId() + " not found.");
        }
        return found;
    }

    // ---------- Delete ----------
    public boolean deleteUser(int id) {
        List<User> users = getAllUsers();
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (removed) {
            saveAllUsers(users);
            System.out.println("User deleted.");
        } else {
            System.out.println("User with ID " + id + " not found.");
        }
        return removed;
    }

    // ---------- Helper Methods ----------
    private String userToCsv(User user) {
        return user.getId() + Constants.CSV_SEPARATOR +
               CsvUtil.escape(user.getName()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(user.getEmail()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(user.getPassword()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(user.getAddress()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(user.getPhone()) + Constants.CSV_SEPARATOR +
               CsvUtil.escape(user.getPaymentInfo());
    }

    private User csvToUser(String line) {
        String[] parts = line.split(Constants.CSV_SEPARATOR, -1);
        if (parts.length < 7) return null;
        try {
            int id = Integer.parseInt(parts[0]);
            String name = CsvUtil.unescape(parts[1]);
            String email = CsvUtil.unescape(parts[2]);
            String password = CsvUtil.unescape(parts[3]);
            String address = CsvUtil.unescape(parts[4]);
            String phone = CsvUtil.unescape(parts[5]);
            String paymentInfo = CsvUtil.unescape(parts[6]);
            return new User(id, name, email, password, address, phone, paymentInfo);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID in line: " + line);
            return null;
        }
    }

    private int getNextId() {
        return getAllUsers().stream()
                .mapToInt(User::getId)
                .max()
                .orElse(0) + 1;
    }

    private void saveAllUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User u : users) {
                writer.write(userToCsv(u));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}