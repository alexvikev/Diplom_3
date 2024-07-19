package site.nomoreparties.stellarburgers.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.main.WebDriverFactory;
import site.nomoreparties.stellarburgers.models.User;
import site.nomoreparties.stellarburgers.pageObjects.LoginPage;
import site.nomoreparties.stellarburgers.pageObjects.MainPage;
import site.nomoreparties.stellarburgers.pageObjects.ProfilePage;
import site.nomoreparties.stellarburgers.steps.UserSteps;

/*
Класс содержит объекты, характерные для большинства тестовых классов
А так же вызов драйвера, куда передаем переменную браузера в виде константы
 */
public class BasicObjectsCalls {
    protected static WebDriver driver;
    protected static User user;
    protected static UserSteps userSteps;
    protected static MainPage mainPage;
    protected static LoginPage loginPage;
    protected static ProfilePage profilePage;

    @Before
    public void setUp(){
        //драйвер
        driver = WebDriverFactory.getWebDriver();

        //пользователь
        user = new User();
        userSteps = new UserSteps();

        //страницы сайта
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
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
