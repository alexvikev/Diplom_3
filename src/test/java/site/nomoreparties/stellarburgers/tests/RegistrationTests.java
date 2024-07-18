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
import site.nomoreparties.stellarburgers.pageObjects.RegisterPage;
import site.nomoreparties.stellarburgers.steps.UserSteps;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

//Класс содержит тесты регистрации нового пользователя
public class RegistrationTests {
    private WebDriver driver;
    private User user;
    private UserSteps userSteps;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ProfilePage profilePage;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver("chrome");
        user = new User();
        userSteps = new UserSteps();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);

        userSteps.generateUserData(user);
    }

    @DisplayName("Успешная регистрация пользователя")
    @Test
    public void successfulUserRegistrationReturnTrueTest(){
        driver.get(BASE_URL);
        mainPage.mainSignInButtonClick();
        loginPage.loginSignUpButtonClick();
        registerPage.registerFillUserData(user);
        registerPage.registerSignUpButtonClick();
        loginPage.goToLoginPage();
        loginPage.loginFillUserDataAndSignIn(user);
        mainPage.mainAccountButtonClick();

        Assert.assertTrue("Текст в поле Имя не совпадает с именем пользователя",
                profilePage.isTheCreatedUserAuthorized(user));
    }

    @DisplayName("Сообщение ошибки валидации поля Пароль")
    @Test
    public void invalidPasswordInputReturnTrueTest(){
        driver.get(BASE_URL);
        mainPage.mainSignInButtonClick();
        loginPage.loginSignUpButtonClick();
        registerPage.registerInvalidPasswordInput();
        registerPage.registerSignUpButtonClick();

        Assert.assertTrue("Сообщение об ошибке не появилось",
                registerPage.registerCheckInvalidPasswordMessage());
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
