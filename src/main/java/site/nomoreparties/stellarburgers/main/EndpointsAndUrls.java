package site.nomoreparties.stellarburgers.main;

//Класс содержит url и эндпоинты приложения
public class EndpointsAndUrls {

    //Базовый url
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    //Адреса страниц
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    public static final String USER_ACCOUNT = "https://stellarburgers.nomoreparties.site/account/profile";

    //Эндпоинты пользователя
    public static final String CREATE_USER_URL = "/api/auth/register";
    public static final String USER_DATA_URL = "/api/auth/user";
    public static final String USER_LOGIN_URL = "/api/auth/login";
}
