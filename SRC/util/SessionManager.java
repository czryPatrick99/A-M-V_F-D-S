package util;

import model.User;

 //Manages the current user session. Since this is a CLI app, we will use a static field.
public class SessionManager {
    private static User currentUser = null;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }
}