package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//h1[text()='Соберите бургер']")
    private WebElement mainHeader;

    @FindBy(xpath = "//span[text()='Булки']/parent::div")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']/parent::div")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']/parent::div")
    private WebElement fillingsTab;

    @FindBy(xpath = "//div[span[text()='Булки']]")
    private WebElement activeBunsTab;

    @FindBy(xpath = "//div[span[text()='Соусы']]")
    private WebElement activeSaucesTab;

    @FindBy(xpath = "//div[span[text()='Начинки']]")
    private WebElement activeFillingsTab;

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    public void clickBunsTab() {
        bunsTab.click();
    }

    public void clickSaucesTab() {
        saucesTab.click();
    }

    public void clickFillingsTab() {
        fillingsTab.click();
    }

    public boolean isMainPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(mainHeader));
        return mainHeader.isDisplayed();
    }

    public boolean isBunsTabActive() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(activeBunsTab));
        return activeBunsTab.isDisplayed();
    }

    public boolean isSaucesTabActive() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(activeSaucesTab));
        return activeSaucesTab.isDisplayed();
    }

    public boolean isFillingsTabActive() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(activeFillingsTab));
        return activeFillingsTab.isDisplayed();
    }
}