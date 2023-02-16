import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.data.AuthorizationData;
import ui.data.User;
import ui.data.UserGenerator;
import ui.pageobjects.LoginPage;
import ui.pageobjects.MainPage;
import ui.pageobjects.RegisterPage;
import ui.user.UserClient;

import java.time.Duration;

public class RegistrationTest {

    private WebDriver driver;
    private User user;
    private UserClient userClient;
    private String accessToken;
    private AuthorizationData authorizationData;

    @Before
    @Step("Precondition step for test")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = UserGenerator.getNewRandomUser();
    }
    @Test
    @DisplayName("Registration via the registration page")
    @Description("Check that the main page is displayed")
    public void successfullyRegistrationOnRegisterPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isManePageOpen());
    }
    @Test
    @DisplayName("Registration via the login page originally")
    @Description("Check that the main page is displayed with Оформить заказ")
    public void successfullyRegistrationOnLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage()
                .clickRegisterButtonLoginPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isManePageOpen());
    }
    @Test
    @DisplayName("Registration via the main page originally")
    @Description("Check that the main page is displayed with Оформить заказ")
    public void successfullyRegistrationOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .clickRegisterButtonLoginPage()
                .registerNewUser(user)
                .clickRegistrationButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue(mainPage.isManePageOpen());
    }
    @After
    @Step("Delete test user and close browser")
    public void tearDown() {
        userClient = new UserClient();
        authorizationData = new AuthorizationData(user.getEmail(), user.getPassword());
        ValidatableResponse responseLoginUser = userClient.loginUser(authorizationData);
        accessToken = responseLoginUser.extract().path("accessToken").toString().substring(7);
        userClient.deleteUser(accessToken);
        driver.quit();
    }
}