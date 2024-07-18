package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.BASE_URL;

//Класс содержит тесты переходов в хедере
public class HeaderClickTests extends BasicObjectsCalls {

    @Before
    public void setUpObjectsForTest(){
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
