import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pageobjects.MainPage;
import java.time.Duration;
import static ui.Constants.BASE_URL;

public class SwitchFromProfileTest extends BeforeAfterTest {
    @Test
    @DisplayName("Switch from the personal account to the main page by Constructor button")
    @Description("Check that the main page is displayed")
    public void switchFromProfileByConstructor() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickConstructorButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(BASE_URL));
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Switch from the personal account to the main page by Logo")
    @Description("Check that the main page is displayed")
    public void switchFromProfileByLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser()
                .clickOnLogo();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(BASE_URL));
        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
    }
}