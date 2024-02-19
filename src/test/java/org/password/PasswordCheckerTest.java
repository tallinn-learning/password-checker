package org.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars() {
        assertTrue(PasswordChecker.checkPasswordComplexity("Password123#"));
    }

    @Test
    void shouldReturnFalseNotMeetingLengthAndAdditionalRequirements() {
        assertFalse(PasswordChecker.checkPasswordComplexity("short"));
    }

    @Test
    void shouldReturnFalseNotMeetingOnlyLengthRequirements() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Short1@"));
    }

    @Test
    void shouldReturnFalseForMissingDigitalCharacters() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Password@"));
    }

    @Test
    void shouldReturnFalseForMissingSpecialCharacters() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Password1"));
    }

    @Test
    void shouldReturnFalseForMissingBothSpecialCharactersAndDigits() {
        assertFalse(PasswordChecker.checkPasswordComplexity("Password"));
    }

}
