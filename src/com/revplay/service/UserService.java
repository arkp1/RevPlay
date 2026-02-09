package com.revplay.service;

import com.revplay.dao.UserDAO;
//import com.revplay.model.User;
import com.revplay.util.ValidationUtil;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    /* ================= REGISTER ================= */
    public void register(String name, String email, String password, String securityQuestion, String securityAnswer) {

        email = email.trim().toLowerCase();

        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("❌ Invalid email format!");
            return;
        }

        if (!ValidationUtil.isStrongPassword(password)) {
            System.out.println("❌ Weak password!");
            System.out.println("Password must contain uppercase, lowercase, digit & min 8 chars");
            return;
        }

        userDAO.registerUser(name, email, password, securityQuestion, securityAnswer);
    }

    /* ================= LOGIN ================= */
    public Integer login(String email, String password) {

        Integer userId = userDAO.loginUser(email, password);

        if (userId != null) {
            System.out.println("✅ Login successful!");
            return userId;
        } else {
            System.out.println("❌ Invalid email or password!");
            return null;
        }
    }

    /* ================= CHANGE PASSWORD ================= */
    public void changePassword(String email, String oldPassword, String newPassword) {

        if (!ValidationUtil.isStrongPassword(newPassword)) {
            System.out.println("❌ New password is weak!");
            return;
        }

        boolean success = userDAO.changePassword(email, oldPassword, newPassword);

        if (success) {
            System.out.println("✅ Password changed successfully!");
        } else {
            System.out.println("❌ Old password incorrect!");
        }
    }
}
