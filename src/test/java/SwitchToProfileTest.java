import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pageobjects.MainPage;

import java.time.Duration;

import static ui.Constants.LOGIN_PAGE_URL;
import static ui.Constants.PROFILE_URL;

public class SwitchToProfileTest extends BeforeAfterTest{
    @Test
    @DisplayName("Switch to the personal account from he main page by Личный Кабинет button ")
    @Description("Check that the profile page is displayed")
    public void transferToProfileByConstructorOnManePage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickProfileButton()
                .authorizationFromLoginPage(user.getEmail(), user.getPassword())
                .clickLoginEnterButton()
                .clickProfileButtonFromAuthorizedUser();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(PROFILE_URL));
        Assert.assertEquals(PROFILE_URL, driver.getCurrentUrl());
    }
    @Test
    @DisplayName("Switch to the personal account from he main page by Личный Кабинет button by not authorized user")
    @Description("Check that the login page is displayed")
    public void clickOnProfileByUnauthorizedUserShowsLoginPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickProfileButton();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        Assert.assertEquals(LOGIN_PAGE_URL, driver.getCurrentUrl());
    }
}

