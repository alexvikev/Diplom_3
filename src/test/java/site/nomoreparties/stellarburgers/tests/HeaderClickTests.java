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

//Класс содержит тесты переходов в хедере
public class HeaderClickTests {
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

    @DisplayName("Переход по кнопке Личный кабинет")
    @Test
    public void goToProfilePageReturnTrue(){
        mainPage.mainSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();

        Assert.assertTrue("Текст в поле Имя не совпадает с именем пользователя",
                profilePage.isTheCreatedUserAuthorized(user));
    }

    @DisplayName("Переход из личного кабинета по кнопке Конструктор")
    @Test
    public void fromProfilePageClickConstructorButtonReturnTrue(){
        mainPage.mainSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();
        profilePage.profileConstructorButtonClick();

        Assert.assertTrue("Переход по кнопке Конструктор не выполнен",
                mainPage.mainIsCreateOrderButtonVisible());
    }

    @DisplayName("Переход из личного кабинета по лого сайта")
    @Test
    public void fromProfilePageClickLogoReturnTrue(){
        mainPage.mainSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();
        profilePage.profileLogoClick();

        Assert.assertTrue("Переход по кнопке Конструктор не выполнен",
                mainPage.mainIsCreateOrderButtonVisible());
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
