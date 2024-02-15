package org.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialCharsEng() {
         assertTrue( PasswordChecker.checkPasswordComplexity("Password123!") );
    }
}
