package com.automation.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    
    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = System.getProperty("user.dir") + "/test-output/screenshots/" + screenshotName;
        
        try {
            // Create screenshots directory if it doesn't exist
            File screenshotDir = new File(System.getProperty("user.dir") + "/test-output/screenshots/");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            
            if (driver != null) {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
                File destFile = new File(screenshotPath);
                FileUtils.copyFile(sourceFile, destFile);
                
                System.out.println("📸 Screenshot captured: " + screenshotPath);
                return screenshotPath;
            } else {
                System.out.println("⚠️ Driver is null, cannot capture screenshot");
                return null;
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public static String captureScreenshotBase64(WebDriver driver) {
        try {
            if (driver != null) {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                return takesScreenshot.getScreenshotAs(OutputType.BASE64);
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to capture base64 screenshot: " + e.getMessage());
        }
        return null;
    }
}
