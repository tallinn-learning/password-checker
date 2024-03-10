package org.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars() {
        assertTrue(PasswordChecker.checkPasswordComplexity("Pswrd12!"));
    }

    @Test
    void shouldReturnTrueForComplexPasswordWithoutLetters() {
        assertTrue(PasswordChecker.checkPasswordComplexity("123456#$@!$!"));
    }

    @Test
    void shouldReturnTrueForComplexLongPasswordWithDigitsAndSpecialChar() {
        assertTrue(PasswordChecker.checkPasswordComplexity("R$2aF8*9TqP!l0vOcJ3NtBkMzX7gIhYuE5dHnGqLpVrAs1fC4wXeZbR!2t3q0oW8yU2b0L5w0g9N2v8d3O1i2n2g0C4o6m6P4l5e5x2a0m3p1l2e5t4R1i2g5h0t*6n9o3l1o4v8e8y6o3u0f4t0h1i9s3P2a1s4s0w5o9r8d!"));
    }

    @Test
    void shouldReturnFalseForEmptyPassword() {

        assertFalse(PasswordChecker.checkPasswordComplexity(""));
    }

    @Test
    void shouldReturnFalseForComplexPasswordWithoutSpecialChars() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Password12345"));
    }

    @Test
    void shouldReturnFalseForComplexPasswordWithoutDigits() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Password!@#$%"));
    }

    @Test
    void shouldReturnFalseForShortComplexPasswordWithDigitsAndSpecialChars() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Pswrd1$"));
    }

}
