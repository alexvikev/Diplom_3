# UI тесты для сайта stellarburgers.nomoreparties.site

## Описание
Проект содержит некоторые тесты интерфейса пользователя для веб-интерфейса 
приложения "stellarburgers.nomoreparties.site". Тесты можно запускать в разных
бразуерах. Сейчас реализован запуск в Google Chrome и Yandex Browser.

# Как запустить автотесты
Запуск автотестов реализован через system property. Метод выбора
браузера сожержится в классе WebDriverFactory.

### Запуск тестов в Google Chrome
```bash
mvn clean test -Dbrowser=chrome
```

### Запуск тестов в Yandex Browser
Драйвер для запуска тестов лежит в папке test/resources.
```bash
mvn clean test -Dbrowser=yandex
```

# Отчет Allure
### Собрать отчет allure
```bash
mvn allure:serve
```