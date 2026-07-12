package tests;

import base.BaseTest;
import pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidLoginTest extends BaseTest {

    @Test(description = "Verify all critical UI elements are displayed on the login page")
    public void verifyLoginPageUIElements() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            Assert.assertTrue(loginPage.isUsernameFieldDisplayed(),
                    "Username field should be displayed on the login page");
            Assert.assertTrue(loginPage.isPasswordFieldDisplayed(),
                    "Password field should be displayed on the login page");
            Assert.assertTrue(loginPage.isLoginButtonDisplayed(),
                    "Login button should be displayed on the login page");
            Assert.assertTrue(loginPage.isRememberMeDisplayed(),
                    "Remember Me checkbox should be displayed on the login page");
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("UI element verification failed: " + e.getMessage());
        }
    }

    @Test(description = "Verify login page loads with correct title and URL")
    public void verifyLoginPageLoads() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            String pageTitle = loginPage.getPageTitle();
            String currentUrl = loginPage.getCurrentUrl();

            Assert.assertTrue(pageTitle.toLowerCase().contains("salesforce"),
                    "Page title should contain 'Salesforce'. Actual: " + pageTitle);
            Assert.assertTrue(currentUrl.contains("login.salesforce.com"),
                    "URL should be login.salesforce.com. Actual: " + currentUrl);
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Login page load verification failed: " + e.getMessage());
        }
    }

    @Test(description = "Login with valid credentials and verify successful redirection to Salesforce home")
    public void loginWithValidCredentials() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            loginPage.enterUsername("validuser@example.com");
            loginPage.enterPassword("ValidPass123");
            loginPage.clickLogin();

            String currentUrl = loginPage.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("salesforce.com"),
                    "After valid login, URL should contain salesforce.com. Actual: " + currentUrl);
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Valid login test failed: " + e.getMessage());
        }
    }
}
