package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement mainHeader;

    @FindBy(xpath = "//div[span[text()='Булки']]")
    private WebElement bunsTab;

    @FindBy(xpath = "//div[span[text()='Соусы']]")
    private WebElement saucesTab;

    @FindBy(xpath = "//div[span[text()='Начинки']]")
    private WebElement fillingsTab;

    @Step("Ожидание перехода на главную страницу")
    public void waitForMainPage() {
        wait.until(ExpectedConditions.urlContains("stellarburgers.education-services.ru"));
        wait.until(ExpectedConditions.visibilityOf(mainHeader));
    }
    public void clickLoginButton() {
        loginButton.click();
    }
    @Step("Клик на кнопку Войти в аккаунт")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }
    @Step("Клик на раздел Булки")
    public void clickBunsTab() {
        bunsTab.click();
    }
    @Step("Клик на раздел Соусы")
    public void clickSaucesTab() {
        saucesTab.click();
    }
    @Step("Клик на раздел Начинки")
    public void clickFillingsTab() {
        fillingsTab.click();
    }
    @Step("ожидание появления логина")
    public void waitLoginInput(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("login"));
    }
    @Step("Проверка загрузки главной страницы")
    public boolean isMainPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainHeader));
        return mainHeader.isDisplayed();
    }

    @Step("Проверить, что раздел 'Булки' активен")
    public boolean isBunsTabActive() {
        try {
            String className = bunsTab.getAttribute("class");
            return className != null && className.contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public boolean isSaucesTabActive() {
        try {
            String className = saucesTab.getAttribute("class");
            return className != null && className.contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, что раздел 'Начинки' активен")
    public boolean isFillingsTabActive() {
        try {
            String className = fillingsTab.getAttribute("class");
            return className != null && className.contains("tab_tab_type_current");
        } catch (Exception e) {
            return false;
        }
    }
}