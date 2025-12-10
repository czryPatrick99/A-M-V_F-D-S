package com.amv.fooddelivery.ui;

import com.amv.fooddelivery.service.AuthService;
import com.amv.fooddelivery.model.User;
import com.amv.fooddelivery.util.DisplayUtil;

public class LoginUI {
    private AuthService authService;
    
    public LoginUI(AuthService authService) {
        this.authService = authService;
    }
    
    public boolean showLogin(User existingUser) {
        DisplayUtil.displayHeader("LOGIN");
        return authService.login(existingUser);
    }
}