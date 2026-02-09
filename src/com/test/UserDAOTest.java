package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.revplay.dao.UserDAO;
import com.revplay.util.DBConnection;
import com.revplay.util.ValidationUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    private static UserDAO userDAO;
    private static String testEmail = "testuser@gmail.com";
    private static String testPassword = "1234567890";
    private static final String QUESTION = "What is your pet's name?";
  	private static final String ANSWER = "jimmy";


    @BeforeAll
    static void setup() {
        userDAO = new UserDAO();
    }

    /* Reset DB before each test */
    @BeforeEach
    void resetUser() throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM users WHERE email=?")) {

            ps.setString(1, testEmail);
            ps.executeUpdate();
        }

        userDAO.registerUser("Test User", testEmail, testPassword, QUESTION, ANSWER);
    }

    @Test
    @Order(1)
    void testLoginSuccess() {
        Integer id = userDAO.loginUser(testEmail, testPassword);
        assertNotNull(id);
    }

    @Test
    @Order(2)
    void testChangePassword() {
        boolean result = userDAO.changePassword(testEmail, testPassword,  "NewPass@123");
        assertTrue(result);
    }

    @Test
    @Order(3)
    void testLoginWithNewPassword() {
        userDAO.changePassword(testEmail, testPassword, "NewPass@123");
        Integer id = userDAO.loginUser(testEmail, "NewPass@123");
        assertNotNull(id);
    }
    
    @Test
    @Order(4)
    void testEmailAndPasswordRegex() {
    	assertTrue(ValidationUtil.isValidEmail(testEmail));
    	assertTrue(ValidationUtil.isStrongPassword(testPassword));
    }
}

//
//package com.test;
//
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//import com.revplay.dao.UserDAO;
//import com.revplay.util.DBConnection;
//
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserDAOTest {
//
//    private static UserDAO userDAO;
//
//    private static final String NAME = "Test User";
//    private static final String EMAIL = "user@test.com";
//    private static final String PASSWORD = "Strong@123";
//    private static final String QUESTION = "Your pet name?";
//    private static final String ANSWER = "Dog";
//
//    @BeforeAll
//    static void init() {
//        userDAO = new UserDAO();
//    }
//
//    @BeforeEach
//    void cleanup() throws Exception {
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps =
//                 con.prepareStatement("DELETE FROM users WHERE email=?")) {
//            ps.setString(1, EMAIL);
//            ps.executeUpdate();
//        }
//    }
//
//    @Test
//    @Order(1)
//    void testRegisterUser() {
//        assertTrue(
//            userDAO.registerUser(
//                NAME,
//                EMAIL,
//                PASSWORD,
//                QUESTION,
//                ANSWER
//            )
//        );
//    }
//
//    @Test
//    @Order(2)
//    void testLoginSuccess() {
//        userDAO.registerUser(NAME, EMAIL, PASSWORD, QUESTION, ANSWER);
//        Integer id = userDAO.loginUser(EMAIL, PASSWORD);
//        assertNotNull(id);
//    }
//
//    @Test
//    @Order(3)
//    void testLoginFailure() {
//        userDAO.registerUser(NAME, EMAIL, PASSWORD, QUESTION, ANSWER);
//        assertNull(userDAO.loginUser(EMAIL, "Wrong@123"));
//    }
//
//    @Test
//    @Order(4)
//    void testValidateSecurityAnswer() {
//        userDAO.registerUser(NAME, EMAIL, PASSWORD, QUESTION, ANSWER);
//        assertTrue(userDAO.verifySecurityAnswer(EMAIL, ANSWER));
//    }
//
//    @Test
//    @Order(5)
//    void testChangePasswordUsingSecurityAnswer() {
//        userDAO.registerUser(NAME, EMAIL, PASSWORD, QUESTION, ANSWER);
//        assertTrue(
//            userDAO.resetPassword(
//                EMAIL,
//                ANSWER));
//    }
//}

