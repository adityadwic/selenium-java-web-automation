package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends BasePage {

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    private WebElement enterAccountInfoText;

    @FindBy(id = "id_gender1")
    private WebElement titleMr;

    @FindBy(id = "id_gender2")
    private WebElement titleMrs;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement dayDropdown;

    @FindBy(id = "months")
    private WebElement monthDropdown;

    @FindBy(id = "years")
    private WebElement yearDropdown;

    @FindBy(id = "newsletter")
    private WebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private WebElement specialOffersCheckbox;

    @FindBy(id = "first_name")
    private WebElement firstNameField;

    @FindBy(id = "last_name")
    private WebElement lastNameField;

    @FindBy(id = "company")
    private WebElement companyField;

    @FindBy(id = "address1")
    private WebElement address1Field;

    @FindBy(id = "address2")
    private WebElement address2Field;

    @FindBy(id = "country")
    private WebElement countryDropdown;

    @FindBy(id = "state")
    private WebElement stateField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "zipcode")
    private WebElement zipcodeField;

    @FindBy(id = "mobile_number")
    private WebElement mobileNumberField;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    private WebElement createAccountButton;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEnterAccountInfoVisible() {
        return isDisplayed(enterAccountInfoText);
    }

    public void selectTitle(String title) {
        if (title.equalsIgnoreCase("Mr")) {
            click(titleMr);
        } else if (title.equalsIgnoreCase("Mrs")) {
            click(titleMrs);
        }
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectByValue(dayDropdown, day);
        selectByValue(monthDropdown, month);
        selectByValue(yearDropdown, year);
    }

    public void selectNewsletterCheckbox() {
        if (!newsletterCheckbox.isSelected()) {
            click(newsletterCheckbox);
        }
    }

    public void selectSpecialOffersCheckbox() {
        if (!specialOffersCheckbox.isSelected()) {
            click(specialOffersCheckbox);
        }
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterCompany(String company) {
        type(companyField, company);
    }

    public void enterAddress1(String address1) {
        type(address1Field, address1);
    }

    public void enterAddress2(String address2) {
        type(address2Field, address2);
    }

    public void selectCountry(String country) {
        selectByVisibleText(countryDropdown, country);
    }

    public void enterState(String state) {
        type(stateField, state);
    }

    public void enterCity(String city) {
        type(cityField, city);
    }

    public void enterZipcode(String zipcode) {
        type(zipcodeField, zipcode);
    }

    public void enterMobileNumber(String mobileNumber) {
        type(mobileNumberField, mobileNumber);
    }

    public void clickCreateAccount() {
        click(createAccountButton);
    }

    public void fillAccountInformation(String title, String password, String day, String month, String year) {
        selectTitle(title);
        enterPassword(password);
        selectDateOfBirth(day, month, year);
        selectNewsletterCheckbox();
        selectSpecialOffersCheckbox();
    }

    public void fillAddressInformation(String firstName, String lastName, String company, 
                                     String address1, String address2, String country, 
                                     String state, String city, String zipcode, String mobileNumber) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCompany(company);
        enterAddress1(address1);
        enterAddress2(address2);
        selectCountry(country);
        enterState(state);
        enterCity(city);
        enterZipcode(zipcode);
        enterMobileNumber(mobileNumber);
    }
}
