package service;

import dao.UserDAO;
import model.User;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    
      //Register a new user. Checks if email already exists.
        
     // @return The registered user with ID set, or null if email exists.
     
    public User register(User user) {
        // Check if email already registered
        User existing = userDAO.getUserByEmail(user.getEmail());
        if (existing != null) {
            return null; // email taken
        }
        return userDAO.addUser(user);
    }

    /**
     * Log in a user by email and password.
     * @return User object if credentials match, else null
     */
    public User login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}