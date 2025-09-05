package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportPath;

    public void onStart(org.testng.ITestContext context) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";
        
        // Create test-output directory if it doesn't exist
        File testOutputDir = new File(System.getProperty("user.dir") + "/test-output");
        if (!testOutputDir.exists()) {
            testOutputDir.mkdirs();
        }
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        
        // Configure the report
        sparkReporter.config().setDocumentTitle("🚀 Selenium Java Automation Report");
        sparkReporter.config().setReportName("📊 User Registration Test Results");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        
        // Add custom CSS for better styling
        String customCSS = ".theme-dark .card-panel { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); }" +
                          ".theme-dark .test-node { border-left: 3px solid #00d4aa; }" +
                          ".theme-dark .test-name { color: #00d4aa; font-weight: bold; }" +
                          ".brand-logo { color: #00d4aa !important; }";
        sparkReporter.config().setCss(customCSS);
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // System Information
        extent.setSystemInfo("🖥️ Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("🏗️ Architecture", System.getProperty("os.arch"));
        extent.setSystemInfo("☕ Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("👤 User", System.getProperty("user.name"));
        extent.setSystemInfo("📁 Working Directory", System.getProperty("user.dir"));
        extent.setSystemInfo("🌐 Browser", ConfigReader.getBrowser().toUpperCase());
        extent.setSystemInfo("🔗 Application URL", ConfigReader.getUrl());
        extent.setSystemInfo("⏱️ Timeout", ConfigReader.getTimeout() + " seconds");
        extent.setSystemInfo("📱 Framework", "Selenium WebDriver 4.15.0");
        extent.setSystemInfo("🧪 Test Framework", "TestNG 7.8.0");
        extent.setSystemInfo("📊 Report Framework", "ExtentReports 5.1.1");
    }

    public void onTestStart(ITestResult result) {
        String testDescription = result.getMethod().getDescription();
        if (testDescription == null || testDescription.trim().isEmpty()) {
            testDescription = "Executing " + result.getMethod().getMethodName();
        }
        
        ExtentTest extentTest = extent.createTest(
            "🧪 " + result.getMethod().getMethodName(), 
            "📋 " + testDescription
        );
        
        // Add test categories/tags
        extentTest.assignCategory("User Registration");
        extentTest.assignCategory("E2E Test");
        extentTest.assignCategory("Smoke Test");
        
        // Add author and device info
        extentTest.assignAuthor("QA Automation Team");
        extentTest.assignDevice("Chrome Browser");
        
        test.set(extentTest);
        
        test.get().log(Status.INFO, 
            MarkupHelper.createLabel("🚀 Test Execution Started", ExtentColor.BLUE));
    }

    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, 
            MarkupHelper.createLabel("✅ Test Completed Successfully!", ExtentColor.GREEN));
        
        // Add execution time
        long duration = result.getEndMillis() - result.getStartMillis();
        test.get().log(Status.INFO, "⏱️ Execution Time: " + duration + " ms");
        
        // Log success details
        String successDetails = "<details>" +
                "<summary><b>📊 Test Summary</b></summary>" +
                "<p>✅ All test steps executed successfully</p>" +
                "<p>🎯 All assertions passed</p>" +
                "<p>🔄 Test completed without errors</p>" +
                "</details>";
        test.get().log(Status.INFO, successDetails);
    }

    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, 
            MarkupHelper.createLabel("❌ Test Failed!", ExtentColor.RED));
        
        // Log failure details
        test.get().log(Status.FAIL, "<b>💥 Error Message:</b><br>" + 
            result.getThrowable().getMessage());
        
        // Add stack trace in collapsible section
        String stackTraceDetails = "<details>" +
                "<summary><b>🔍 Stack Trace (Click to expand)</b></summary>" +
                "<pre>" + getStackTrace(result.getThrowable()) + "</pre>" +
                "</details>";
        test.get().log(Status.FAIL, stackTraceDetails);
        
        // Capture screenshot on failure
        if (ConfigReader.isScreenshotOnFailure()) {
            try {
                String screenshotPath = ScreenshotUtils.captureScreenshot(null, 
                    result.getMethod().getMethodName());
                if (screenshotPath != null) {
                    test.get().addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
                    test.get().log(Status.INFO, "📸 Screenshot captured and attached");
                }
            } catch (Exception e) {
                test.get().log(Status.WARNING, "⚠️ Could not capture screenshot: " + e.getMessage());
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, 
            MarkupHelper.createLabel("⏸️ Test Skipped", ExtentColor.ORANGE));
        test.get().log(Status.SKIP, "Reason: " + result.getThrowable().getMessage());
    }

    public void onFinish(org.testng.ITestContext context) {
        if (extent != null) {
            extent.flush();
            
            // Print report location
            System.out.println("\n" + "=".repeat(60));
            System.out.println("🎉 EXTENT REPORT GENERATED SUCCESSFULLY!");
            System.out.println("=".repeat(60));
            System.out.println("📂 Report Location: " + reportPath);
            System.out.println("🌐 Open in Browser: file://" + reportPath);
            System.out.println("=".repeat(60) + "\n");
        }
    }

    public static ExtentTest getTest() {
        return test.get();
    }
    
    public static void logStep(String stepDescription) {
        if (test.get() != null) {
            test.get().log(Status.INFO, "🔹 " + stepDescription);
        }
    }
    
    public static void logPass(String message) {
        if (test.get() != null) {
            test.get().log(Status.PASS, "✅ " + message);
        }
    }
    
    public static void logFail(String message) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, "❌ " + message);
        }
    }
    
    public static void logWarning(String message) {
        if (test.get() != null) {
            test.get().log(Status.WARNING, "⚠️ " + message);
        }
    }
    
    private String getStackTrace(Throwable throwable) {
        if (throwable == null) return "";
        
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.toString()).append("\n");
        for (StackTraceElement element : throwable.getStackTrace()) {
            sb.append("\tat ").append(element.toString()).append("\n");
        }
        return sb.toString();
    }
}
