package service;

import dao.RestaurantDAO;
import dao.MenuDAO;
import model.Restaurant;
import model.MenuItem;
import java.util.List;


 // Service for restaurant and menu operations.
public class RestaurantService {
    private RestaurantDAO restaurantDAO;
    private MenuDAO menuDAO;

    public RestaurantService() {
        this.restaurantDAO = new RestaurantDAO();
        this.menuDAO = new MenuDAO();
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantDAO.getAllRestaurants();
    }

    public Restaurant getRestaurantById(int id) {
        return restaurantDAO.getRestaurantById(id);
    }

    public List<MenuItem> getMenuForRestaurant(int restaurantId) {
        return menuDAO.getMenuItemsByRestaurant(restaurantId);
    }

    public MenuItem getMenuItemById(int menuItemId) {
        return menuDAO.getMenuItemById(menuItemId);
    }
}