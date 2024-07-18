package site.nomoreparties.stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import site.nomoreparties.stellarburgers.models.User;
import static io.restassured.RestAssured.given;
import static site.nomoreparties.stellarburgers.main.EndpointsAndUrls.*;

//Класс содержит степы для польователя
public class UserSteps {

    @Step("Генерация данных пользователя")
    public void generateUserData(User user){
        user.setEmail(RandomStringUtils.randomAlphabetic(7)
                .toLowerCase() + "@gmail.com");
        user.setPassword(RandomStringUtils.randomAlphabetic(10));
        user.setName(RandomStringUtils.randomAlphabetic(10));
    }

    @Step("Установить токен авторизации пользователю")
    public void setUserAccessToken(User user){
        String accessToken = userSignIn(user).extract()
                .body().path("accessToken");

        user.setAccessToken(accessToken);
    }

    @Step("Создать нового пользователя")
    public void createUser(User user){
        given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(CREATE_USER_URL)
                .then();
    }

    @Step("Авторизовать пользователя")
    public ValidatableResponse userSignIn(User user){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(user)
                .when()
                .post(USER_LOGIN_URL)
                .then();
    }

    @Step("Удалить пользователя")
    public void deleteUser(User user){
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", user.getAccessToken())
                .baseUri(BASE_URL)
                .when()
                .delete(USER_DATA_URL)
                .then();
    }
}
