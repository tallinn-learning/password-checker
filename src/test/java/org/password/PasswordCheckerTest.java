package org.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars() {
        assertTrue(PasswordChecker.checkPasswordComplexity("Password123!"));
    }
}
