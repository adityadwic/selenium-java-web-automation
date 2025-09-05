package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Signup / Login')]")
    private WebElement signupLoginButton;

    @FindBy(xpath = "//img[@alt='Website for automation practice']")
    private WebElement homePageLogo;

    @FindBy(xpath = "//li[contains(.,'Logged in as')]")
    private WebElement loggedInAsText;

    @FindBy(xpath = "//a[contains(text(),'Delete Account')]")
    private WebElement deleteAccountButton;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageVisible() {
        return isDisplayed(homePageLogo);
    }

    public void clickSignupLogin() {
        click(signupLoginButton);
    }

    public boolean isLoggedInAsVisible(String username) {
        waitForElementToBeVisible(loggedInAsText);
        String actualText = getText(loggedInAsText);
        return actualText.contains("Logged in as " + username);
    }

    public void clickDeleteAccount() {
        click(deleteAccountButton);
    }

    public void clickLogout() {
        click(logoutButton);
    }
}
