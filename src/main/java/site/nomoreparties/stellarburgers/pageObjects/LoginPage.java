package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.models.User;
import static java.time.Duration.ofSeconds;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.LOGIN_URL;

//Класс содержит локаторы и степы для страницы логина
public class LoginPage {
    private final WebDriver driver;

    private final By loginEmailInputLocator =
            By.xpath(".//*[text()='Email']//following-sibling::input");
    private final By loginPasswordInputLocator =
            By.xpath(".//*[text()='Пароль']//following-sibling::input");
    private final By loginSignInButtonLocator =
            By.xpath(".//*[text()='Войти']");
    private final By loginSignUpButtonLocator =
            By.xpath(".//*[text()='Зарегистрироваться']");
    private final By loginRecoverPasswordLocator =
            By.xpath(".//*[text()='Восстановить пароль']");
    private final By loginEnterStringLocator =
            By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Переход на страницу авторизации пользователя")
    public void goToLoginPage(){
        driver.get(LOGIN_URL);
    }

    @Step("Заполнение данных пользователя и вход")
    public void loginFillUserDataAndSignIn(User user){
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginSignInButtonLocator));
        driver.findElement(loginEmailInputLocator).sendKeys(user.getEmail());
        driver.findElement(loginPasswordInputLocator).sendKeys(user.getPassword());
        driver.findElement(loginSignInButtonLocator).click();
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void loginSignUpButtonClick(){
        driver.findElement(loginSignUpButtonLocator).click();
    }

    @Step("Клик по кнопке Восстановить пароль")
    public void loginRecoverButtonClick(){
        driver.findElement(loginRecoverPasswordLocator).click();
    }

    @Step("Проверка появления заголовка Вход")
    public boolean isHeaderEnterVisible(){
        WebElement headerEnter = new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(loginEnterStringLocator));

        return headerEnter.isDisplayed();
    }
}
