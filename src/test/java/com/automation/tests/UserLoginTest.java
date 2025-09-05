package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import com.automation.utils.ConfigReader;
import com.automation.utils.ExtentReportManager;
import com.automation.utils.TestDataGenerator;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Test Case 2: Login User with correct email and password
 * 
 * This test class demonstrates login functionality with valid credentials
 * following the Page Object Model (POM) design pattern.
 */
@Listeners(ExtentReportManager.class)
public class UserLoginTest extends BaseTest {

    @Test(description = "Login user with correct email and password")
    public void testUserLoginWithValidCredentials() {
        // First, we need to create a user account to login with
        String testName = TestDataGenerator.generateRandomName();
        String testEmail = TestDataGenerator.generateRandomEmail();
        String testPassword = TestDataGenerator.generateRandomPassword();

        // Page Objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);

        // Pre-requisite: Create a user account first
        ExtentReportManager.logStep("🔧 PRE-REQUISITE: Creating user account for login test");
        createUserAccount(homePage, loginPage, signupPage, accountCreatedPage, testName, testEmail, testPassword);
        
        // Logout to prepare for login test
        ExtentReportManager.logStep("📤 Logging out to prepare for login test");
        homePage.clickLogout();

        // Start Test Case 2: Login User with correct email and password
        ExtentReportManager.logStep("🎯 Starting Test Case 2: Login User with correct email and password");

        step1_LaunchBrowserAndNavigate(homePage);
        step2_VerifyHomePage(homePage);
        step3_ClickSignupLogin(homePage);
        step4_VerifyLoginToYourAccount(loginPage);
        step5_EnterCorrectEmailAndPassword(loginPage, testEmail, testPassword);
        step6_ClickLoginButton(loginPage);
        step7_VerifyLoggedInAsUsername(homePage, testName);
        step8_ClickDeleteAccount(homePage);
        step9_VerifyAccountDeleted(accountDeletedPage);

        // Test completion summary
        ExtentReportManager.logStep("✅ Test Case 2 completed successfully!");
        
        System.out.println("\n============================================================");
        System.out.println("🎉 USER LOGIN TEST COMPLETED SUCCESSFULLY!");
        System.out.println("============================================================");
        System.out.println("Test Data Used:");
        System.out.println("👤 Name: " + testName);
        System.out.println("📧 Email: " + testEmail);
        System.out.println("🔐 Password: " + testPassword);
        System.out.println("============================================================\n");
    }

    // Pre-requisite method to create user account
    private void createUserAccount(HomePage homePage, LoginPage loginPage, SignupPage signupPage, 
                                 AccountCreatedPage accountCreatedPage, String name, String email, String password) {
        // Navigate to the website first
        step1_LaunchBrowserAndNavigate(homePage);
        
        homePage.clickSignupLogin();
        loginPage.enterSignupName(name);
        loginPage.enterSignupEmail(email);
        loginPage.clickSignupButton();
        
        signupPage.selectTitle("Mr");
        signupPage.enterPassword(password);
        signupPage.selectDateOfBirth("15", "5", "1990");
        signupPage.selectNewsletterCheckbox();
        signupPage.selectSpecialOffersCheckbox();
        signupPage.enterFirstName("John");
        signupPage.enterLastName("Doe");
        signupPage.enterCompany("Test Company");
        signupPage.enterAddress1("123 Test Street");
        signupPage.enterAddress2("Apt 456");
        signupPage.selectCountry("United States");
        signupPage.enterState("California");
        signupPage.enterCity("Los Angeles");
        signupPage.enterZipcode("90210");
        signupPage.enterMobileNumber("1234567890");
        signupPage.clickCreateAccount();
        
        accountCreatedPage.clickContinue();
    }

    // Test Case 2 Steps

    private void step1_LaunchBrowserAndNavigate(HomePage homePage) {
        ExtentReportManager.logStep("1. 🚀 Launch browser and navigate to 'http://automationexercise.com'");
        String baseUrl = ConfigReader.getProperty("url");
        driver.get(baseUrl);
    }

    private void step2_VerifyHomePage(HomePage homePage) {
        ExtentReportManager.logStep("2. ✅ Verify that home page is visible successfully");
        assertTrue(homePage.isHomePageVisible(), "Home page should be visible");
    }

    private void step3_ClickSignupLogin(HomePage homePage) {
        ExtentReportManager.logStep("3. 🔘 Click on 'Signup / Login' button");
        homePage.clickSignupLogin();
    }

    private void step4_VerifyLoginToYourAccount(LoginPage loginPage) {
        ExtentReportManager.logStep("4. 👀 Verify 'Login to your account' is visible");
        assertTrue(loginPage.isLoginToYourAccountVisible(), "Login to your account text should be visible");
    }

    private void step5_EnterCorrectEmailAndPassword(LoginPage loginPage, String email, String password) {
        ExtentReportManager.logStep("5. 📝 Enter correct email address and password");
        ExtentReportManager.logStep("   📧 Email: " + email);
        ExtentReportManager.logStep("   🔐 Password: [PROTECTED]");
        loginPage.enterLoginEmail(email);
        loginPage.enterLoginPassword(password);
    }

    private void step6_ClickLoginButton(LoginPage loginPage) {
        ExtentReportManager.logStep("6. 🔑 Click 'login' button");
        loginPage.clickLoginButton();
    }

    private void step7_VerifyLoggedInAsUsername(HomePage homePage, String username) {
        ExtentReportManager.logStep("7. ✅ Verify that 'Logged in as " + username + "' is visible");
        assertTrue(homePage.isLoggedInAsVisible(username), "Logged in as " + username + " should be visible");
    }

    private void step8_ClickDeleteAccount(HomePage homePage) {
        ExtentReportManager.logStep("8. 🗑️ Click 'Delete Account' button");
        homePage.clickDeleteAccount();
    }

    private void step9_VerifyAccountDeleted(AccountDeletedPage accountDeletedPage) {
        ExtentReportManager.logStep("9. ✅ Verify that 'ACCOUNT DELETED!' is visible");
        assertTrue(accountDeletedPage.isAccountDeletedVisible(), "Account Deleted text should be visible");
    }
}
