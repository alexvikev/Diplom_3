package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.models.User;
import static java.time.Duration.ofSeconds;

//Класс содержит локаторы и степы для страницы /account/profile
public class ProfilePage {
    private final WebDriver driver;

    private final By profileConstructorButtonLocator =
            By.xpath(".//*[text()='Конструктор']");
    private final  By profileStellarLogoLocator =
            By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']");
    private final By profileExitButtonLocator =
            By.xpath(".//*[text()='Выход']");
    private final By profileUserNameLocator =
            By.xpath(".//label[text()='Имя']//following-sibling::input");

    public ProfilePage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Клик по кнопке Конструктор")
    public void profileConstructorButtonClick(){
        driver.findElement(profileConstructorButtonLocator).click();
    }

    @Step("Клик по лого сайта")
    public void profileLogoClick(){
        driver.findElement(profileStellarLogoLocator).click();
    }

    @Step("Клик по кнопке Выход")
    public void profileExitButtonClick(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .elementToBeClickable(profileExitButtonLocator));

        driver.findElement(profileExitButtonLocator).click();
    }

    @Step("Проверка авторизации, созданного пользователя")
    public boolean isTheCreatedUserAuthorized(User user){
        //Проверим, что имя в профиле совпадает с именем, созданного пользователя
        Boolean isUserName = new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .textToBePresentInElementValue(profileUserNameLocator, user.getName()));

        return isUserName;
    }
}
