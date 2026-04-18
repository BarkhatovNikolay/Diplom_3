package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = "//a[text()='Восстановить пароль']")
    private WebElement recoverPasswordLink;

    @FindBy(xpath = "//h2[text()='Вход']")
    private WebElement loginHeader;

    @Step("Ввод email")
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    @Step("Ввод пароля")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    @Step("Нажатие кнопки логин")
    public void clickLoginButton() {
        loginButton.click();
    }
    @Step("Нажатие на ссылку Зарегистрироваться")
    public void clickRegisterLink() {
        registerLink.click();
    }
    @Step("Нажатие на Восстановить пароль")
    public void clickRecoverPasswordLink() {
        recoverPasswordLink.click();
    }
    @Step("Выполнить вход")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

}
