package org.example.assessmentdr1.exercicio_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAuthTests {
    private WebDriver driver;
    private final String baseUrl = "https://automationexercise.com";

    @BeforeEach
    public void setUp() {
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
    public void testFullUserFlow() {
        // Navegar para a página de login/signup
        driver.findElement(By.linkText("Signup / Login")).click();

        // Preencher formulário de cadastro
        SignupPage signupPage = new SignupPage(driver);
        String email = "user" + System.currentTimeMillis() + "@test.com";
        String password = "123456";
        signupPage.fillSignupForm("Test User", email);

        // Confirmar que foi redirecionado para página de informações da conta
        Assertions.assertTrue(driver.getPageSource().contains("Enter Account Information"));

        // Preencher informações obrigatórias
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("days")).sendKeys("1");
        driver.findElement(By.id("months")).sendKeys("January");
        driver.findElement(By.id("years")).sendKeys("2000");
        driver.findElement(By.id("first_name")).sendKeys("Test");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("address1")).sendKeys("123 Main St");
        driver.findElement(By.id("state")).sendKeys("State");
        driver.findElement(By.id("city")).sendKeys("City");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("1234567890");
        WebElement createAccountButton = driver.findElement(By.xpath("//button[text()='Create Account']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", createAccountButton);
        createAccountButton.click();

        // Confirmar criação da conta
        Assertions.assertTrue(driver.getPageSource().contains("Account Created!"));
        driver.findElement(By.linkText("Continue"))
                .click();

        // Confirmar que usuário está logado
        Assertions.assertTrue(driver.getPageSource().contains("Logged in as"));

        // Realizar logout
        driver.findElement(By.linkText("Logout")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));

        // Fazer login com mesmo usuário
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Confirmar login bem-sucedido
        Assertions.assertTrue(driver.getPageSource().contains("Logged in as"));

        // Excluir a conta
        driver.findElement(By.linkText("Delete Account")).click();
        Assertions.assertTrue(driver.getPageSource().contains("Account Deleted!"));
    }
}
