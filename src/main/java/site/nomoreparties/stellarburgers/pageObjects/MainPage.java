package site.nomoreparties.stellarburgers.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

//Класс содержит локаторы и степы для главной страницы
public class MainPage {
    private final WebDriver driver;

    //Локаторы главной страницы
    private final By mainAccountButtonLocator =
            By.xpath(".//*[text()='Личный Кабинет']");
    private final By mainSignInButtonLocator =
            By.xpath(".//*[text()='Войти в аккаунт']");
    private final By mainCreateOrderButtonLocator =
            By.xpath(".//*[text()='Оформить заказ']");

    //Локаторы кнопок конструктора
    private final By mainConstructorBunButtonLocator =
            By.xpath(".//span[text()='Булки']");
    private final By mainConstructorSauceButtonLocator =
            By.xpath(".//span[text()='Соусы']");
    private final By mainConstructorFillingButtonLocator =
            By.xpath(".//span[text()='Соусы']");

    //Локаторы ингредиентов
    private final By mainBunLocator =
            By.xpath(".//*[text()='Флюоресцентная булка R2-D3']");
    private final By mainSauceLocator =
            By.xpath(".//*[text()='Соус Spicy-X']");
    private final By mainFillingLocator =
            By.xpath(".//*[text()='Мясо бессмертных моллюсков Protostomia']");

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void mainSignInButtonClick(){
        new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainSignInButtonLocator));

        driver.findElement(mainSignInButtonLocator).click();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void mainAccountButtonClick(){
        driver.findElement(mainAccountButtonLocator).click();
    }

    @Step("Клик и проверка перехода по вкладке Булки")
    public boolean mainConstructorBunClick(){
        driver.findElement(mainConstructorFillingButtonLocator).click();

        driver.findElement(mainConstructorBunButtonLocator).click();
        WebElement bun = new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainBunLocator));

        return bun.isDisplayed();
    }

    @Step("Клик и проврека перехода по вкладке Соусы")
    public boolean mainConstructorSauceClick(){
        driver.findElement(mainConstructorSauceButtonLocator).click();

        WebElement sauce = new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainSauceLocator));

        return sauce.isDisplayed();
    }

    @Step("Клик и проверка перехода по вкладке Начинки")
    public boolean mainConstructorFillingClick(){
        driver.findElement(mainConstructorFillingButtonLocator).click();

        WebElement filling = new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainFillingLocator));

        return filling.isDisplayed();
    }

    @Step("Проверка появления кнопки Оформить заказ")
    public boolean mainIsCreateOrderButtonVisible(){
        WebElement createOrderButton = new WebDriverWait(driver, ofSeconds(3))
                .until(ExpectedConditions
                        .visibilityOfElementLocated(mainCreateOrderButtonLocator));

        return createOrderButton.isDisplayed();
    }
}
