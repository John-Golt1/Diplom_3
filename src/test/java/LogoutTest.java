import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pageobjects.MainPage;
import java.time.Duration;
import static ui.Constants.LOGIN_PAGE_URL;

public class LogoutTest extends BeforeAfterTest{
    @Test
    @DisplayName("Checking the logout by Выход on the profile page")
    @Description("Check that the main page is displayed")
    public void logoutIsSuccessful() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickAccountButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickLogOutButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        Assert.assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}
