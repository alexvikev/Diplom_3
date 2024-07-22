package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

//Класс срдердит тесты выхода пользователя из системы
public class LogOutTests extends BasicObjectsCalls {

    @Before
    public void setUpObjectsForTest(){
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

}
