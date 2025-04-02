package org.example.assessmentdr1.exercicio_4;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    @FindBy(css = ".login-form p")
    private WebElement loginErrorMessage;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(linkText = "Delete Account")
    private WebElement deleteAccountLink;

    @FindBy(linkText = "Signup / Login")
    private WebElement signupLoginLink;


    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getLoginErrorMessage() {
        return loginErrorMessage.getText();
    }

    public void logout() {
        logoutLink.click();
    }

    public void clickSignupLogin() {
        signupLoginLink.click();
    }

    public void deleteAccount() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteAccountLink);
        deleteAccountLink.click();
    }

}
