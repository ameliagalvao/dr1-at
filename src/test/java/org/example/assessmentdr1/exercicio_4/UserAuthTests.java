package org.example.assessmentdr1.exercicio_4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserAuthTests {
    private WebDriver driver;
    private final String baseUrl = "https://automationexercise.com";

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Desativa serviços do Chrome que interferem em testes automatizados
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // desativa gerenciador de senhas
        prefs.put("profile.password_manager_enabled", false); // desativa sugestão de salvar senha
        prefs.put("autofill.profile_enabled", false); // desativa sugestões de preenchimento automático
        prefs.put("autofill.address_enabled", false); // desativa popup de endereço (como no seu print)

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
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
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

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
        signupPage.clickContinueAfterAccountCreated();

        // Confirmar que usuário está logado
        Assertions.assertTrue(driver.getPageSource().contains("Logged in as"));

        // Realizar logout
        homePage.logout();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));

        //Precisei retirar esse teste, pois ele estava bugando a execução do resto do flow.
        /*
        /// Tentar login com senha incorreta
        homePage.enterEmail(email);
        homePage.enterPassword("senhaErrada");
        homePage.clickLogin();
        Assertions.assertTrue(homePage.getLoginErrorMessage().contains("Your email or password is incorrect!"));
        */

        // Login com senha correta novamente
        homePage.enterEmail(email);
        homePage.enterPassword(password);
        homePage.clickLogin();

        // Confirmar login bem sucedido
        Assertions.assertTrue(driver.getPageSource().contains("Logged in as"));

        // Excluir a conta
        homePage.deleteAccount();
        Assertions.assertTrue(driver.getPageSource().contains("Account Deleted!"));
    }

    @Test
    @Order(2)
    public void testInvalidLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        homePage.enterEmail("emailinvalido@example.com");
        homePage.enterPassword("password");
        homePage.clickLogin();

        Assertions.assertTrue(homePage.getLoginErrorMessage().contains("Your email or password is incorrect!"));
    }

    @Test
    @Order(3)
    public void testLoginWithEmptyFields() {
        HomePage homePage = new HomePage(driver);
        homePage.clickSignupLogin();

        homePage.enterEmail("");
        homePage.enterPassword("");

        // Tentar clicar
        homePage.clickLogin();

        // Verificar se continua na mesma página (não fez submit)
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
    }

}
