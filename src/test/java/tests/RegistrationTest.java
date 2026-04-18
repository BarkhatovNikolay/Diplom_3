package tests;

import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Test;
import driver.Configuration;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.ApiUtils;


public class RegistrationTest extends Configuration {
    private User testUser;
    private String userEmail;

    @After
    public void deleteDown() {
        if (testUser != null && testUser.getAccessToken() != null) {
            ApiUtils.deleteUser(testUser.getAccessToken());
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        String timestamp = String.valueOf(System.currentTimeMillis());
        String name = "User_" + timestamp.substring(timestamp.length() - 6);
        userEmail = "test_" + timestamp + "@email.com";
        String password = "password123";

        registrationPage.register(name, userEmail, password);

        mainPage.waitLoginInput();

        loginPage.login(userEmail, password);

        mainPage.waitForMainPage();

        testUser = new User(userEmail, password, name, null);

        testUser = ApiUtils.createRandomUser();

        assert mainPage.isMainPageLoaded() : "Не удалось зарегистрироваться и войти";
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    public void testRegistrationWithShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        String name = "Test User";
        String email = "test_" + System.currentTimeMillis() + "@email.com";
        String shortPassword = "12345";

        registrationPage.register(name, email, shortPassword);


        String error = registrationPage.getErrorMessage();
        assert error.contains("Некорректный пароль") :
                "Ожидалось 'Некорректный пароль', но получено: " + error;
    }
}
