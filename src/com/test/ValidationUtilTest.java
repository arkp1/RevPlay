package com.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.revplay.util.ValidationUtil;

public class ValidationUtilTest {

    @Test
    void emailValidation() {
        assertTrue(ValidationUtil.isValidEmail("test@gmail.com"));
        assertFalse(ValidationUtil.isValidEmail("bad@"));
    }

    @Test
    void passwordValidation() {
        assertTrue(ValidationUtil.isStrongPassword("Strong@123"));
        assertFalse(ValidationUtil.isStrongPassword("weakpass"));
    }
}
