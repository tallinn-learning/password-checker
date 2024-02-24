package org.password;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordCheckerTest {

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars() {
         assertTrue( PasswordChecker.checkPasswordComplexity("Password123!") );

    }

    @Test
    void shouldReturnTrueForComplexPasswordWithDigitsAndSpecialChars2() {
        assertTrue(PasswordChecker.checkPasswordComplexity("Password12!"));
    }

    @Test
    void shouldReturnFalseForShortPassword() {
        assertFalse( PasswordChecker.checkPasswordComplexity("Pass!") );
        assertFalse( PasswordChecker.checkPasswordComplexity("qwerty1231") );
    }

    @Test
    void shouldReturnFalseForNonComplexPassword(){
        assertFalse( PasswordChecker.checkPasswordComplexity("abcdefjklp") );
        assertFalse( PasswordChecker.checkPasswordComplexity("1234567890") );
        assertFalse( PasswordChecker.checkPasswordComplexity("abcd1234") );
        assertFalse( PasswordChecker.checkPasswordComplexity("1234,./=") );
        assertFalse( PasswordChecker.checkPasswordComplexity("asdf,./=") );
        assertFalse( PasswordChecker.checkPasswordComplexity("!?#,<;:-(*()") );
        assertFalse( PasswordChecker.checkPasswordComplexity(" ") );
        assertFalse( PasswordChecker.checkPasswordComplexity("") );


}


}
//changes to try commit