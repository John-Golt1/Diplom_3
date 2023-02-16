import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ui.pageobjects.MainPage;
import ui.pageobjects.RecoveryPasswordPage;
import ui.pageobjects.RegisterPage;

public class LoginTest extends BeforeAfterTest{
    @Test
    @DisplayName("Checking the login by Войти в аккаунт button on the main page")
    @Description("Check that the main page is displayed with Оформить заказ")
    public void loginFromMainePageByEnterAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue(mainPage.isManePageOpen());
    }
    @Test
    @DisplayName("Checking the login using the Личный Кабинет button on the main page")
    @Description("Check that the main page is displayed with Оформить заказ")
    public void loginFromMainPageProfileButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        Assert.assertTrue(mainPage.isManePageOpen());
    }
    @Test
    @DisplayName("Checking the login by Вход on the register page")
    @Description("Check that the main page is displayed with Оформить заказ")
    public void loginFromRegistrationPage() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage()
                .clickEnterButtonOnRegistrationPage()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isManePageOpen());
    }
    @Test
    @DisplayName("Checking the login by Вход on the recovery page")
    @Description("Check that the main page is displayed with Оформить заказ")
    public void loginFromRecoveryPage() {
        RecoveryPasswordPage recoveryPasswordPage = new RecoveryPasswordPage(driver);
        recoveryPasswordPage.openRecoveryPage()
                .clickEnterButtonOnRecoveryPage()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton();
        MainPage mainPage = new MainPage(driver);
        Assert.assertTrue(mainPage.isManePageOpen());
    }
}
