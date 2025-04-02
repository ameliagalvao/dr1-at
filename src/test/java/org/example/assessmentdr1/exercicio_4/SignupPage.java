package org.example.assessmentdr1.exercicio_4;

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

    public void fillSignupForm(String name, String email) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        signupButton.click();
    }
}
