import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pageobjects.MainPage;

import java.time.Duration;

public class ChooseMenuTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    @Step("Precondition step for test")
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        mainPage = new MainPage(driver);
    }
    @Test
    @DisplayName("Check that Sauce section is active")
    @Description("Comparing the name of the selected section")
    public void menuSauceIsActiveByClick() {
        mainPage.openMainPage()
                .clickMenuSauce();
        Assert.assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Check that Bun section is active")
    @Description("Comparing the name of the selected section")
    public void menuBunIsActiveByClick() {
        mainPage.openMainPage()
                .clickMenuSauce()
                .clickMenuBun();
        Assert.assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }
    @Test
    @DisplayName("Check that Fillings section is active")
    @Description("Comparing the name of the selected section")
    public void menuFillingsIsActiveByClick() {
        mainPage.openMainPage()
                .clickMenuFillings();
        Assert.assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }
    @After
    @Step("Close browser")
    public void tearDown() {
        driver.quit();
    }
}