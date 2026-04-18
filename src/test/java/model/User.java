package model;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;
    private String accessToken;

    private static final Faker faker = new Faker(new Locale("ru"));

    public static User createRandom() {
        String randomId = faker.regexify("[a-z0-9]{10}");
        return new User(
                "user_" + randomId + "@example.com",
                faker.internet().password(8, 10),
                faker.name().fullName(),
                null
        );
    }
}