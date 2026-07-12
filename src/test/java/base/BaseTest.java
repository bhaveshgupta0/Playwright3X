package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeTest
    public void setup() {
        try {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage(), e);
        }
    }

    @AfterTest
    public void teardown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("Error during WebDriver teardown: " + e.getMessage());
        }
    }
}
