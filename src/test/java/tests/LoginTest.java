package tests;

import io.qameta.allure.junit4.DisplayName;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.ApiUtils;
import driver.Configuration;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPage;
import pages.RegistrationPage;

public class LoginTest extends Configuration {
    private User testUser;

    @Before
    public void setUp() {
        // Создаем рандомного пользователя через API перед каждым тестом
        testUser = ApiUtils.createRandomUser();
    }

    @After
    public void deleteDown() {
        // Удаляем пользователя после каждого теста
        if (testUser != null && testUser.getAccessToken() != null) {
            ApiUtils.deleteUser(testUser.getAccessToken());
        }
    }

    @Test
    @DisplayName("Вход через кнопку Войти в аккаунт")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPage();

        assert mainPage.isMainPageLoaded() : "Не удалось войти через главную кнопку";
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPage();

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

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPage();

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

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPage();

        assert mainPage.isMainPageLoaded() : "Не удалось войти через восстановление пароля";
    }
}
