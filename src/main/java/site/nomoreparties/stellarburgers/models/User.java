package site.nomoreparties.stellarburgers.models;

import lombok.Data;

/* Класс содержит данные пользователя,
для удобства использована библиотека lombok */
@Data
public class User {
    private String email;
    private String password;
    private String name;
    private String accessToken;
}
