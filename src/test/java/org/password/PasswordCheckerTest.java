package org.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars() {
         assertTrue( PasswordChecker.checkPasswordComplexity("Password123!") );
    }

    @Test
    void emptyPasswordFieldTest(){
        assertFalse(PasswordChecker.checkPasswordComplexity(""));
    }

    @Test
    void passwordWithoutInts(){
        assertFalse(PasswordChecker.checkPasswordComplexity("test!@#$"));
    }

    @Test
    void passwordWithoutSpecialChars(){
        assertFalse(PasswordChecker.checkPasswordComplexity("test12345"));
    }

    @Test
    void shortPasswordTest(){
        assertFalse(PasswordChecker.checkPasswordComplexity("abc123!"));
    }
}
