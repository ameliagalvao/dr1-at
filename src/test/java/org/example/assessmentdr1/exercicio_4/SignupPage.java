package org.example.assessmentdr1.exercicio_4;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
    private WebDriver driver;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[text()='Signup']")
    private WebElement signupButton;

    @FindBy(id = "id_gender1")
    private WebElement genderMale;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "days")
    private WebElement daySelect;

    @FindBy(id = "months")
    private WebElement monthSelect;

    @FindBy(id = "years")
    private WebElement yearSelect;

    @FindBy(id = "first_name")
    private WebElement firstName;

    @FindBy(id = "last_name")
    private WebElement lastName;

    @FindBy(id = "address1")
    private WebElement address;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "zipcode")
    private WebElement zipcode;

    @FindBy(id = "mobile_number")
    private WebElement mobile;

    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement createAccountButton;

    @FindBy(linkText = "Continue")
    private WebElement continueLink;

    public void fillSignupForm(String name, String email) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        signupButton.click();
    }

    public void fillAccountInfoAndSubmit(String password) {
        genderMale.click();
        passwordInput.sendKeys(password);
        daySelect.sendKeys("1");
        monthSelect.sendKeys("January");
        yearSelect.sendKeys("2000");
        firstName.sendKeys("Test");
        lastName.sendKeys("User");
        address.sendKeys("123 Main St");
        state.sendKeys("State");
        city.sendKeys("City");
        zipcode.sendKeys("12345");
        mobile.sendKeys("1234567890");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createAccountButton);
        createAccountButton.click();
    }

    public void clickContinueAfterAccountCreated() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", continueLink);
        continueLink.click();
    }

}
