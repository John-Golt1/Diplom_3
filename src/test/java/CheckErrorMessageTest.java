import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.data.User;
import ui.data.UserGenerator;
import ui.pageobjects.RegisterPage;

import java.time.Duration;

import static ui.Constants.ERROR_MESSAGE_OF_WRONG_PASSWORD;
import static ui.pageobjects.RegisterPage.registerWrongPasswordMessageInRegisterPage;

public class CheckErrorMessageTest {
    private WebDriver driver;
    private User user;

    @Before
    @Step("Precondition step for test")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        user = UserGenerator.getNewRandomUser();
    }
    @Test
    @DisplayName("Try create new order with wrong password")
    @Description("Tap in password field 3 numbers")
    public void shownErrorMessageWithWrongPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage()
                .enterRegisterName(user.getName())
                .enterRegisterEmail(user.getEmail())
                .enterRegisterPassword("123")
                .clickRegistrationButton();
        Assert.assertEquals("Ошибка не появилась", ERROR_MESSAGE_OF_WRONG_PASSWORD,
                driver.findElement(registerWrongPasswordMessageInRegisterPage).getText());
    }
    @After
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}