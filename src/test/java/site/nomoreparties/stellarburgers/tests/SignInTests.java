package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.main.WebDriverFactory;
import site.nomoreparties.stellarburgers.models.User;
import site.nomoreparties.stellarburgers.pageObjects.*;
import site.nomoreparties.stellarburgers.steps.UserSteps;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

/*Класс содержит тесты авторизации пользователя с разных страниц
 Проверка авторизации производится путем сравнения имени
 созданного пользователя и имени в личном кабинете
 */
public class SignInTests {
    private WebDriver driver;
    private User user;
    private UserSteps userSteps;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;
    private RegisterPage registerPage;
    private RecoverPasswordPage recoverPasswordPage;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver("chrome");
        user = new User();
        userSteps = new UserSteps();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        registerPage = new RegisterPage(driver);
        recoverPasswordPage = new RecoverPasswordPage(driver);

        userSteps.generateUserData(user);
        userSteps.createUser(user);
        driver.get(BASE_URL);
    }

    @DisplayName("Вход с главной страницы сайта по кнопке Войти в аккаунт")
    @Test
    public void mainPageSignInButtonLoginReturnTrueTest(){
        mainPage.mainSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();

        Assert.assertTrue("Текст в поле Имя не совпадает с именем пользователя",
                profilePage.isTheCreatedUserAuthorized(user));
    }

    @DisplayName("Вход с главной страницы сайта по кнопке Личный кабинет")
    @Test
    public void mainPageAccountButtonLoginReturnTrueTest(){
        mainPage.mainAccountButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();

        Assert.assertTrue("Текст в поле Имя не совпадает с именем пользователя",
                profilePage.isTheCreatedUserAuthorized(user));
    }

    @DisplayName("Вход со страницы регистрации по кнопке Войти")
    @Test
    public void registerPageSignInButtonLoginReturnTrueTest(){
        mainPage.mainSignInButtonClick();
        loginPage.loginSignUpButtonClick();
        registerPage.registerSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();

        Assert.assertTrue("Текст в поле Имя не совпадает с именем пользователя",
                profilePage.isTheCreatedUserAuthorized(user));
    }

    @DisplayName("Вход со страницы восстановления пароля по кнопке Войти")
    @Test
    public void recoverPasswordPageSignInButtonLoginReturnTrueTest(){
        mainPage.mainSignInButtonClick();
        loginPage.loginRecoverButtonClick();
        recoverPasswordPage.recoverSignInButtonClick();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();

        Assert.assertTrue("Текст в поле Имя не совпадает с именем пользователя",
                profilePage.isTheCreatedUserAuthorized(user));
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
