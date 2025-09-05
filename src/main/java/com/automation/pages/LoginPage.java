package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//h2[text()='New User Signup!']")
    private WebElement newUserSignupText;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement signupNameField;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement signupEmailField;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    private WebElement signupButton;

    // Login elements
    @FindBy(xpath = "//h2[text()='Login to your account']")
    private WebElement loginToYourAccountText;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private WebElement loginEmailField;

    @FindBy(xpath = "//input[@data-qa='login-password']")
    private WebElement loginPasswordField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNewUserSignupVisible() {
        return isDisplayed(newUserSignupText);
    }

    public void enterSignupName(String name) {
        type(signupNameField, name);
    }

    public void enterSignupEmail(String email) {
        type(signupEmailField, email);
    }

    public void clickSignupButton() {
        click(signupButton);
    }

    public void performSignup(String name, String email) {
        enterSignupName(name);
        enterSignupEmail(email);
        clickSignupButton();
    }

    // Login methods
    public boolean isLoginToYourAccountVisible() {
        return isDisplayed(loginToYourAccountText);
    }

    public void enterLoginEmail(String email) {
        type(loginEmailField, email);
    }

    public void enterLoginPassword(String password) {
        type(loginPasswordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void performLogin(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        clickLoginButton();
    }
}
