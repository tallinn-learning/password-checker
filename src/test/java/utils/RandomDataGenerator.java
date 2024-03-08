package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomDataGenerator {
    public static String generateName() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedCustomerName = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedCustomerName;
    }

    // 11 HOMEWORK.  generated random Phone number witch contains only numerics with RandomStringUtils
    public static String generateCustomerPhone() {
        int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String generatedCustomerPhone = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedCustomerPhone;
    }
    // 11 HOMEWORK. generated random comment with alphanumeric values which contains 50 characters
    public static String generateComment() {
        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedComment = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedComment;

    }

}