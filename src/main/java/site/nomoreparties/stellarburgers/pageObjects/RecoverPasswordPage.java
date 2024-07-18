package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Класс содержит локаторы и степы для страницы восстановления пароля
public class RecoverPasswordPage {
    private final WebDriver driver;

    private final By recoverSignInButtonLocator =
            By.xpath(".//*[text()='Войти']");

    public RecoverPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Клик по кнопке Войти")
    public void recoverSignInButtonClick(){
        driver.findElement(recoverSignInButtonLocator).click();
    }

}
