package com.automation.utils;

import org.apache.commons.lang3.RandomStringUtils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDataGenerator {
    
    public static String generateRandomEmail() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "test" + timestamp + "@automation.com";
    }
    
    public static String generateRandomName() {
        return "Test" + RandomStringUtils.randomAlphabetic(5);
    }
    
    public static String generateRandomPassword() {
        return "Test@" + RandomStringUtils.randomAlphanumeric(8);
    }
    
    public static String generateRandomCompany() {
        return RandomStringUtils.randomAlphabetic(6) + " Company";
    }
    
    public static String generateRandomAddress() {
        return RandomStringUtils.randomNumeric(3) + " Test Street";
    }
    
    public static String generateRandomCity() {
        return "Test" + RandomStringUtils.randomAlphabetic(4) + "City";
    }
    
    public static String generateRandomZipcode() {
        return RandomStringUtils.randomNumeric(5);
    }
    
    public static String generateRandomMobile() {
        return RandomStringUtils.randomNumeric(10);
    }
}
