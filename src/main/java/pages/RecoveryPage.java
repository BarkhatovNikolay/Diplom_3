package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoveryPage {
    private WebDriver driver;

    public RecoveryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(xpath = "//h2[text()='Восстановление пароля']")
    private WebElement recoveryHeader;

    public void clickLoginLink() {
        loginLink.click();
    }

    public boolean isRecoveryPageLoaded() {
        return recoveryHeader.isDisplayed();
    }
}