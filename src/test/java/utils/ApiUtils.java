package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.User;

public class ApiUtils {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    public static User createRandomUser() {
        User user = User.createRandom();

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/api/auth/register");

        if (response.statusCode() == 200) {
            user.setAccessToken(response.path("accessToken"));
            return user;
        } else {
            throw new RuntimeException("Не удалось создать пользователя: " + response.body().asString());
        }
    }

    public static void deleteUser(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            RestAssured.given()
                    .header("Authorization", accessToken)
                    .delete(BASE_URL + "/api/auth/user");
        }
    }
}
