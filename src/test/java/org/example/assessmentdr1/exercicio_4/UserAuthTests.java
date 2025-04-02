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

        // Preencher campos
        signupPage.fillAccountInfoAndSubmit(password);

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
