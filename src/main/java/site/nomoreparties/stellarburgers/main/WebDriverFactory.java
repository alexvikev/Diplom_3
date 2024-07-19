package site.nomoreparties.stellarburgers.main;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

//Класс содержит метод выбора драйвера браузера
public class WebDriverFactory {
    private static WebDriver driver;

    public static WebDriver getWebDriver(){
        String browserName = System.getProperty("browser", "chrome");

        if(driver == null){
            switch (browserName){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    return new ChromeDriver();

                case "yandex":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                    return new ChromeDriver();

                default:
                    throw new RuntimeException("Неверное название браузера: " + browserName);
            }
        }
        return driver;
    }
}
