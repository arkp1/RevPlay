package com.test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revplay.model.UserType;
import com.revplay.service.AuthService;
import com.revplay.util.DBConnection;

public class AuthServiceTest {

    private static AuthService authService;

    private static final String EMAIL = "auth@test.com";
    private static final String PASSWORD = "Auth@123";
    private static final String QUESTION = "Fav city?";
    private static final String ANSWER = "Hyd";

    @BeforeAll
    static void init() {
        authService = new AuthService();
    }

    @BeforeEach
    void cleanup() throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("DELETE FROM users WHERE email=?");
        ps.setString(1, EMAIL);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    @Test
    void register_and_login_user() {
        authService.register(
            UserType.USER,
            "Auth User",
            EMAIL,
            PASSWORD,
            QUESTION,
            ANSWER,
            null, null, null, null
        );

        Integer id =
            authService.login(UserType.USER, EMAIL, PASSWORD);

        assertNotNull(id);
    }
}
