package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utils.ExtentReportManager;
import com.automation.utils.TestDataGenerator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ExtentReportManager.class)
public class UserRegistrationTest extends BaseTest {

    @Test(description = "Complete User Registration Flow - End to End Test with 18 Steps")
    public void testUserRegistration() {
        // Generate test data
        ExtentReportManager.logStep("Generating dynamic test data");
        String name = TestDataGenerator.generateRandomName();
        String email = TestDataGenerator.generateRandomEmail();
        String password = TestDataGenerator.generateRandomPassword();
        String firstName = TestDataGenerator.generateRandomName();
        String lastName = TestDataGenerator.generateRandomName();
        String company = TestDataGenerator.generateRandomCompany();
        String address1 = TestDataGenerator.generateRandomAddress();
        String address2 = "Apartment 101";
        String city = TestDataGenerator.generateRandomCity();
        String zipcode = TestDataGenerator.generateRandomZipcode();
        String mobileNumber = TestDataGenerator.generateRandomMobile();

        ExtentReportManager.logPass("Test data generated successfully");
        ExtentReportManager.getTest().info("<b>Generated Test Data:</b><br>" +
            "👤 Name: " + name + "<br>" +
            "📧 Email: " + email + "<br>" +
            "🔐 Password: " + password + "<br>" +
            "🏢 Company: " + company);

        // Initialize page objects
        ExtentReportManager.logStep("Initializing Page Object Model classes");
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);
        ExtentReportManager.logPass("Page objects initialized successfully");

        // Step 1-3: Launch browser, Navigate to URL, and Verify home page is visible
        ExtentReportManager.logStep("Step 1-3: Verifying home page is visible after navigation");
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "Home page should be visible successfully");
        ExtentReportManager.logPass("✅ Home page is visible successfully");

        // Step 4: Click on 'Signup / Login' button
        ExtentReportManager.logStep("Step 4: Clicking on 'Signup / Login' button");
        homePage.clickSignupLogin();
        ExtentReportManager.logPass("✅ Clicked on 'Signup / Login' button successfully");

        // Step 5: Verify 'New User Signup!' is visible
        ExtentReportManager.logStep("Step 5: Verifying 'New User Signup!' text is visible");
        Assert.assertTrue(loginPage.isNewUserSignupVisible(), 
            "'New User Signup!' text should be visible");
        ExtentReportManager.logPass("✅ 'New User Signup!' text is visible");

        // Step 6-7: Enter name and email address, Click 'Signup' button
        ExtentReportManager.logStep("Step 6-7: Entering signup details and clicking signup button");
        loginPage.performSignup(name, email);
        ExtentReportManager.logPass("✅ Signup form filled and submitted successfully");

        // Step 8: Verify that 'ENTER ACCOUNT INFORMATION' is visible
        ExtentReportManager.logStep("Step 8: Verifying 'ENTER ACCOUNT INFORMATION' is visible");
        Assert.assertTrue(signupPage.isEnterAccountInfoVisible(), 
            "'ENTER ACCOUNT INFORMATION' should be visible");
        ExtentReportManager.logPass("✅ 'ENTER ACCOUNT INFORMATION' page is visible");

        // Step 9: Fill details: Title, Name, Email, Password, Date of birth
        ExtentReportManager.logStep("Step 9: Filling account information details");
        signupPage.fillAccountInformation("Mr", password, "15", "5", "1990");
        ExtentReportManager.logPass("✅ Account information filled successfully");

        // Step 10-11: Checkboxes are handled in fillAccountInformation method
        ExtentReportManager.logStep("Step 10-11: Newsletter and offers checkboxes selected");
        ExtentReportManager.logPass("✅ Newsletter and special offers checkboxes selected");

        // Step 12: Fill details: First name, Last name, Company, Address, etc.
        ExtentReportManager.logStep("Step 12: Filling address information details");
        signupPage.fillAddressInformation(firstName, lastName, company, 
            address1, address2, "United States", "California", city, zipcode, mobileNumber);
        ExtentReportManager.logPass("✅ Address information filled successfully");

        // Step 13: Click 'Create Account' button
        ExtentReportManager.logStep("Step 13: Clicking 'Create Account' button");
        signupPage.clickCreateAccount();
        ExtentReportManager.logPass("✅ 'Create Account' button clicked successfully");

        // Step 14: Verify that 'ACCOUNT CREATED!' is visible
        ExtentReportManager.logStep("Step 14: Verifying 'ACCOUNT CREATED!' message is visible");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedVisible(), 
            "'ACCOUNT CREATED!' should be visible");
        ExtentReportManager.logPass("✅ 'ACCOUNT CREATED!' message is visible");

        // Step 15: Click 'Continue' button
        ExtentReportManager.logStep("Step 15: Clicking 'Continue' button after account creation");
        accountCreatedPage.clickContinue();
        ExtentReportManager.logPass("✅ 'Continue' button clicked successfully");

        // Step 16: Verify that 'Logged in as username' is visible
        ExtentReportManager.logStep("Step 16: Verifying user is logged in successfully");
        Assert.assertTrue(homePage.isLoggedInAsVisible(name), 
            "'Logged in as " + name + "' should be visible");
        ExtentReportManager.logPass("✅ User logged in successfully as: " + name);

        // Step 17: Click 'Delete Account' button
        ExtentReportManager.logStep("Step 17: Clicking 'Delete Account' button");
        homePage.clickDeleteAccount();
        ExtentReportManager.logPass("✅ 'Delete Account' button clicked successfully");

        // Step 18: Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        ExtentReportManager.logStep("Step 18: Verifying account deletion and clicking continue");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedVisible(), 
            "'ACCOUNT DELETED!' should be visible");
        ExtentReportManager.logPass("✅ 'ACCOUNT DELETED!' message is visible");
        
        accountDeletedPage.clickContinue();
        ExtentReportManager.logPass("✅ Account deletion process completed successfully");
        
        // Final summary
        ExtentReportManager.getTest().pass("<div style='background: linear-gradient(90deg, #00C851, #007E33); color: white; padding: 10px; border-radius: 5px;'>" +
            "<h4>🎉 TEST COMPLETED SUCCESSFULLY!</h4>" +
            "<p><b>All 18 steps executed successfully:</b></p>" +
            "<ul>" +
            "<li>✅ Browser launched and navigated to application</li>" +
            "<li>✅ Home page verification completed</li>" +
            "<li>✅ User registration flow completed</li>" +
            "<li>✅ Account creation verified</li>" +
            "<li>✅ User login verification completed</li>" +
            "<li>✅ Account cleanup completed</li>" +
            "</ul>" +
            "<p><b>Test Data Used:</b><br>" +
            "👤 Name: " + name + "<br>" +
            "📧 Email: " + email + "<br>" +
            "🔐 Password: " + password + "</p>" +
            "</div>");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("🎉 USER REGISTRATION TEST COMPLETED SUCCESSFULLY!");
        System.out.println("=".repeat(60));
        System.out.println("Test Data Used:");
        System.out.println("👤 Name: " + name);
        System.out.println("📧 Email: " + email);
        System.out.println("🔐 Password: " + password);
        System.out.println("=".repeat(60));
    }
}
