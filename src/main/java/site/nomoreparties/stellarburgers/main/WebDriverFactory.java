package site.nomoreparties.stellarburgers.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Класс содержит метод выбора драйвера браузера
public class WebDriverFactory {

    public static WebDriver getWebDriver(String browserName){
        switch (browserName){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();

//          case "yandex":
//                WebDriverManager.yandexdriver().setup();

            default:
                throw new RuntimeException("Неверное название браузера");
        }
    }
}
