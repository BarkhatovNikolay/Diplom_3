package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='name']")
    private WebElement nameField;

    @FindBy(xpath = "//label[text()='Email']/following-sibling::input")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='Пароль']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[contains(@class, 'input__error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    @Step("Ввод имени")
    public void enterName(String name) {
        nameField.sendKeys(name);
    }
    @Step("Ввод Email")
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }
    @Step("Ввод пароля")
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    @Step("Клик по кнопке Регистрации")
    public void clickRegisterButton() {
        registerButton.click();
    }
    @Step("Клик по ссылке Войти")
    public void clickLoginLink() {
        loginLink.click();
    }
    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return errorMessage.getText();
    }
    @Step("Регистрация")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }


}
