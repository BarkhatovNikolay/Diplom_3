package tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import driver.Configuration;
import pages.*;

import java.time.Duration;

public class LoginTest extends Configuration {
    private final String TEST_EMAIL = "test-Barkhatov25@email.com";
    private final String TEST_PASSWORD = "password123";

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TEST_EMAIL, TEST_PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("stellarburgers.education-services.ru"));

        assert mainPage.isMainPageLoaded() : "Не удалось войти через главную кнопку";
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TEST_EMAIL, TEST_PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("stellarburgers.education-services.ru"));

        assert mainPage.isMainPageLoaded() : "Не удалось войти через личный кабинет";
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    public void testLoginViaRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickLoginLink();

        loginPage.login(TEST_EMAIL, TEST_PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("stellarburgers.education-services.ru"));

        assert mainPage.isMainPageLoaded() : "Не удалось войти через форму регистрации";
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    public void testLoginViaPasswordRecoveryForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRecoverPasswordLink();

        RecoveryPage recoveryPage = new RecoveryPage(driver);
        recoveryPage.clickLoginLink();

        loginPage.login(TEST_EMAIL, TEST_PASSWORD);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("stellarburgers.education-services.ru"));

        assert mainPage.isMainPageLoaded() : "Не удалось войти через восстановление пароля";
    }
}
