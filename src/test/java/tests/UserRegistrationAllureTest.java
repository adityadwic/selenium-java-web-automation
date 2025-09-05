package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import com.automation.pages.*;
import com.automation.base.BaseTest;
import com.automation.utils.ConfigReader;
import com.automation.utils.TestDataGenerator;

import static org.testng.Assert.assertTrue;

/**
 * User Registration Test with Allure Reporting
 * This test class demonstrates Allure annotations for comprehensive reporting
 */
@Epic("E-Commerce Automation")
@Feature("User Registration")
public class UserRegistrationAllureTest extends BaseTest {

    @Test(description = "End-to-end user registration test with Allure reporting")
    @Severity(SeverityLevel.CRITICAL)
    @Story("New User Registration Flow")
    @Description("Complete user registration process from homepage to account creation confirmation")
    public void testUserRegistrationWithAllure() {
        // Test Data Generation
        String testName = TestDataGenerator.generateRandomName();
        String testEmail = TestDataGenerator.generateRandomEmail();
        String testPassword = TestDataGenerator.generateRandomPassword();

        // Page Objects
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        SignupPage signupPage = new SignupPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);

        step1_NavigateToHomePage(homePage);
        step2_VerifyHomePage(homePage);
        step3_ClickSignupLogin(homePage);
        step4_VerifyLoginPage(loginPage);
        step5_EnterSignupDetails(loginPage, testName, testEmail);
        step6_ClickSignupButton(loginPage);
        step7_VerifySignupPage(signupPage);
        step8_FillPersonalInformation(signupPage, testPassword);
        step9_FillAddressInformation(signupPage);
        step10_ClickCreateAccountButton(signupPage);
        step11_VerifyAccountCreated(accountCreatedPage);
        step12_ClickContinue(accountCreatedPage);
        step13_VerifyLoggedInUser(homePage, testName);
        step14_DeleteAccount(homePage);
        step15_VerifyAccountDeleted(accountDeletedPage);
        step16_ClickContinueAfterDeletion(accountDeletedPage);

        // Final verification
        Allure.step("✅ Test Completed Successfully", () -> {
            assertTrue(true, "User registration test completed successfully");
        });

        System.out.println("\n============================================================");
        System.out.println("🎉 USER REGISTRATION TEST COMPLETED SUCCESSFULLY!");
        System.out.println("============================================================");
        System.out.println("Test Data Used:");
        System.out.println("👤 Name: " + testName);
        System.out.println("📧 Email: " + testEmail);
        System.out.println("🔐 Password: " + testPassword);
        System.out.println("============================================================\n");
    }

    @Step("1. Navigate to Application Homepage")
    private void step1_NavigateToHomePage(HomePage homePage) {
        String baseUrl = ConfigReader.getProperty("base.url");
        Allure.addAttachment("Base URL", baseUrl);
        driver.get(baseUrl);
    }

    @Step("2. Verify that home page is visible successfully")
    private void step2_VerifyHomePage(HomePage homePage) {
        assertTrue(homePage.isHomePageVisible(), "Home page should be visible");
    }

    @Step("3. Click on 'Signup / Login' button")
    private void step3_ClickSignupLogin(HomePage homePage) {
        homePage.clickSignupLogin();
    }

    @Step("4. Verify 'New User Signup!' is visible")
    private void step4_VerifyLoginPage(LoginPage loginPage) {
        assertTrue(loginPage.isNewUserSignupVisible(), "New User Signup text should be visible");
    }

    @Step("5. Enter name and email address")
    private void step5_EnterSignupDetails(LoginPage loginPage, String name, String email) {
        Allure.addAttachment("User Name", name);
        Allure.addAttachment("Email Address", email);
        loginPage.enterSignupName(name);
        loginPage.enterSignupEmail(email);
    }

    @Step("6. Click 'Signup' button")
    private void step6_ClickSignupButton(LoginPage loginPage) {
        loginPage.clickSignupButton();
    }

    @Step("7. Verify that 'ENTER ACCOUNT INFORMATION' is visible")
    private void step7_VerifySignupPage(SignupPage signupPage) {
        assertTrue(signupPage.isEnterAccountInfoVisible(), "Enter Account Information text should be visible");
    }

    @Step("8. Fill details: Title, Name, Email, Password, Date of birth")
    private void step8_FillPersonalInformation(SignupPage signupPage, String password) {
        Allure.addAttachment("Password", password);
        signupPage.selectTitle("Mr");
        signupPage.enterPassword(password);
        signupPage.selectDateOfBirth("15", "May", "1990");
    }

    @Step("9. Select checkbox 'Sign up for our newsletter!' and 'Receive special offers from our partners!'")
    private void step9_FillAddressInformation(SignupPage signupPage) {
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
    }

    @Step("10. Click 'Create Account' button")
    private void step10_ClickCreateAccountButton(SignupPage signupPage) {
        signupPage.clickCreateAccount();
    }

    @Step("11. Verify that 'ACCOUNT CREATED!' is visible")
    private void step11_VerifyAccountCreated(AccountCreatedPage accountCreatedPage) {
        assertTrue(accountCreatedPage.isAccountCreatedVisible(), "Account Created text should be visible");
    }

    @Step("12. Click 'Continue' button")
    private void step12_ClickContinue(AccountCreatedPage accountCreatedPage) {
        accountCreatedPage.clickContinue();
    }

    @Step("13. Verify that 'Logged in as username' is visible")
    private void step13_VerifyLoggedInUser(HomePage homePage, String username) {
        assertTrue(homePage.isLoggedInAsVisible(username), "Logged in as " + username + " should be visible");
    }

    @Step("14. Click 'Delete Account' button")
    private void step14_DeleteAccount(HomePage homePage) {
        homePage.clickDeleteAccount();
    }

    @Step("15. Verify that 'ACCOUNT DELETED!' is visible")
    private void step15_VerifyAccountDeleted(AccountDeletedPage accountDeletedPage) {
        assertTrue(accountDeletedPage.isAccountDeletedVisible(), "Account Deleted text should be visible");
    }

    @Step("16. Click 'Continue' button")
    private void step16_ClickContinueAfterDeletion(AccountDeletedPage accountDeletedPage) {
        accountDeletedPage.clickContinue();
    }
}
