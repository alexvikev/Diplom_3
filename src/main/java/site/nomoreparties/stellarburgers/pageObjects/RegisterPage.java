package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.models.User;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

//Класс содержит локаторы и степы для страницы регистрации
public class RegisterPage {
    private final WebDriver driver;

    private final By registerNameInputLocator =
            By.xpath(".//*[text()='Имя']//following-sibling::input");
    private final By registerEmailInputLocator =
            By.xpath(".//*[text()='Email']//following-sibling::input");
    private final By registerPasswordInputLocator =
            By.xpath(".//*[text()='Пароль']//following-sibling::input");
    private final By registerSignUpButtonLocator =
            By.xpath(".//*[text()='Зарегистрироваться']");
    private final By registerSignInButtonLocator =
            By.xpath(".//*[text()='Войти']");
    private final By registerInvalidPasswordValidationMessageLocator =
            By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Заполнить поля регистрации")
    public void registerFillUserData(User user){
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(registerNameInputLocator));
        driver.findElement(registerNameInputLocator).sendKeys(user.getName());
        driver.findElement(registerEmailInputLocator).sendKeys(user.getEmail());
        driver.findElement(registerPasswordInputLocator).sendKeys(user.getPassword());
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void registerSignUpButtonClick(){
        driver.findElement(registerSignUpButtonLocator).click();
    }

    @Step("Клик на кнопку Войти")
    public void registerSignInButtonClick(){
        driver.findElement(registerSignInButtonLocator).click();
    }

    @Step("Ввод некорректного пароля")
    public void registerInvalidPasswordInput(){
        String invalidPassword = "1234";
        new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(registerPasswordInputLocator));
        driver.findElement(registerPasswordInputLocator).sendKeys(invalidPassword);
    }

    @Step("Проверка сообщения о некорректном пароле")
    public boolean registerCheckInvalidPasswordMessage(){
        WebElement invalidPasswordMessage = new WebDriverWait(driver, ofSeconds(5))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(registerInvalidPasswordValidationMessageLocator));

        return invalidPasswordMessage.isDisplayed();
    }
}
