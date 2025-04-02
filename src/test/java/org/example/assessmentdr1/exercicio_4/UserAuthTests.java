package org.example.assessmentdr1.exercicio_4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAuthTests {
    private WebDriver driver;
    private final String baseUrl = "https://automationexercise.com";

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void testSignupValidUser() {
        driver.findElement(By.linkText("Signup / Login")).click();
        SignupPage signupPage = new SignupPage(driver);
        String email = "user" + System.currentTimeMillis() + "@test.com";
        signupPage.fillSignupForm("Test User", email);

        Assertions.assertTrue(driver.getPageSource().contains("Enter Account Information"));
    }

    @Test
    @Order(2)
    public void testLoginWithValidCredentials() {
        driver.findElement(By.linkText("Signup / Login")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("valid_email@test.com");
        loginPage.enterPassword("valid_password");
        loginPage.clickLogin();

        Assertions.assertTrue(driver.getPageSource().contains("Logged in as"));
    }

    @Test
    @Order(3)
    public void testLoginWithInvalidCredentials() {
        driver.findElement(By.linkText("Signup / Login")).click();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail("invalid_email@test.com");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        Assertions.assertTrue(loginPage.getErrorMessage().contains("Your email or password is incorrect!"));
    }
}
