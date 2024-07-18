package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.main.WebDriverFactory;
import site.nomoreparties.stellarburgers.models.User;
import site.nomoreparties.stellarburgers.pageObjects.LoginPage;
import site.nomoreparties.stellarburgers.pageObjects.MainPage;
import site.nomoreparties.stellarburgers.pageObjects.ProfilePage;
import site.nomoreparties.stellarburgers.steps.UserSteps;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

//Класс срдердит тесты выхода пользователя из системы
public class LogOutTests {
    private WebDriver driver;
    private User user;
    private UserSteps userSteps;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver("chrome");
        user = new User();
        userSteps = new UserSteps();
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);

        userSteps.generateUserData(user);
        userSteps.createUser(user);
        driver.get(BASE_URL);
    }

    @DisplayName("Выход авторизованного пользователя из системы")
    @Test
    public void userLogOutTest(){
        mainPage.mainSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();
        profilePage.profileExitButtonClick();

        Assert.assertTrue("Заголовок Вход не появился",
                loginPage.isHeaderEnterVisible());
    }

    @After
    public void tearDown(){
        userSteps.setUserAccessToken(user);

        if (user.getAccessToken() != null){
            userSteps.deleteUser(user);
        } else {
            System.out.println("Токен null");
        }

        driver.quit();
    }
}
