package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Paths;
import java.time.Duration;

public class Configuration {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://stellarburgers.education-services.ru";
    @Before
    public void installDriver() {
        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {
            setupYandexBrowser();
        } else {
            WebDriverManager.chromedriver().clearResolutionCache().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    private void setupYandexBrowser() {

        String yandexDriverPath = Paths.get("src/test/java/drivers/yandexdriver.exe")
                .toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", yandexDriverPath);


        String yandexBrowserPath = "C:\\Users\\Nikolay\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";


        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexBrowserPath);
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }


    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}