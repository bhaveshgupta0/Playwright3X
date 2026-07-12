package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorContainer;

    public LoginPage(WebDriver driver) {
        try {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            PageFactory.initElements(driver, this);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize LoginPage: " + e.getMessage(), e);
        }
    }

    public void enterUsername(String username) {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField)).clear();
            usernameField.sendKeys(username);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter username: " + e.getMessage(), e);
        }
    }

    public void enterPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordField)).clear();
            passwordField.sendKeys(password);
        } catch (Exception e) {
            throw new RuntimeException("Failed to enter password: " + e.getMessage(), e);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to click login button: " + e.getMessage(), e);
        }
    }

    public void selectRememberMe() {
        try {
            if (!rememberMeCheckbox.isSelected()) {
                wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox)).click();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to select Remember Me: " + e.getMessage(), e);
        }
    }

    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        try {
            WebElement errorEl = wait.until(ExpectedConditions.visibilityOf(errorContainer));
            return errorEl.getText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve error message: " + e.getMessage(), e);
        }
    }

    public boolean isErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorContainer)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(loginButton)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRememberMeDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(rememberMeCheckbox)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameFieldDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(usernameField)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordFieldDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(passwordField)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get current URL: " + e.getMessage(), e);
        }
    }

    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            throw new RuntimeException("Failed to get page title: " + e.getMessage(), e);
        }
    }
}
