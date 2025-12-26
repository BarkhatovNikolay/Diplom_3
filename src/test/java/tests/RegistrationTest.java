package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.Configuration;
import pages.*;

import java.time.Duration;

public class RegistrationTest extends Configuration {

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);

        String generator = String.valueOf(System.currentTimeMillis());
        String name = "User_" + generator.substring(8);
        String email = "test_" + generator + "@email.com";
        String password = "password123";

        registrationPage.register(name, email, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("login"));

        loginPage.login(email, password);

        wait.until(ExpectedConditions.urlContains("stellarburgers.education-services.ru"));

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
        String email = "test_123@email.com";
        String shortPassword = "12345";

        registrationPage.register(name, email, shortPassword);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(driver -> registrationPage.getErrorMessage() != null &&
                !registrationPage.getErrorMessage().isEmpty());

        String error = registrationPage.getErrorMessage();
        assert error.contains("Некорректный пароль") :
                "Ожидалось'Некорректный пароль', но получено: " + error;
    }
}
