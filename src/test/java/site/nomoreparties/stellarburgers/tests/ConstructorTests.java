package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.main.WebDriverFactory;
import site.nomoreparties.stellarburgers.pageObjects.MainPage;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

//Класс сожержит тесты переходов по вкладкам конструктора
public class ConstructorTests {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver("chrome");
        mainPage = new MainPage(driver);
    }

    @DisplayName("Переход по вкладке Булки")
    @Test
    public void bunConstructorReturnTrueTest(){
        driver.get(BASE_URL);
        Assert.assertTrue("Веб-элементы булок не появились",
                mainPage.mainConstructorBunClick());
    }

    @DisplayName("Переход по вкладке Соусы")
    @Test
    public void sauceConstructorReturnTrueTest(){
        driver.get(BASE_URL);
        Assert.assertTrue("Веб-элементы соусов не появились",
                mainPage.mainConstructorSauceClick());
    }

    @DisplayName("Переход по вкладке Начинки")
    @Test
    public void fillingConstructorReturnTrueTest(){
        driver.get(BASE_URL);
        Assert.assertTrue("Веб-элементы начинок не появились",
                mainPage.mainConstructorFillingClick());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
