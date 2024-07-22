package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.pageObjects.RegisterPage;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

//Класс содержит тесты регистрации нового пользователя
public class RegistrationTests extends BasicObjectsCalls {
    private RegisterPage registerPage;

    @Before
    public void setUpObjectsForTests(){
        registerPage = new RegisterPage(driver);

        userSteps.generateUserData(user);

        driver.get(BASE_URL);
    }

    @DisplayName("Успешная регистрация пользователя")
    @Test
    public void successfulUserRegistrationReturnTrueTest(){
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
        mainPage.mainSignInButtonClick();
        loginPage.loginSignUpButtonClick();
        registerPage.registerInvalidPasswordInput();
        registerPage.registerSignUpButtonClick();

        Assert.assertTrue("Сообщение об ошибке не появилось",
                registerPage.registerCheckInvalidPasswordMessage());
    }

}
