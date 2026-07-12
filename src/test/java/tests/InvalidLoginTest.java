package tests;

import base.BaseTest;
import pages.LoginPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test(description = "Login with invalid email format and verify error message")
    public void loginWithInvalidEmail() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            loginPage.doLogin("invalid-email", "SomePass123");

            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should be displayed for invalid email");
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Invalid email test failed: " + e.getMessage());
        }
    }

    @Test(description = "Login with blank credentials and verify validation")
    public void loginWithBlankCredentials() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            loginPage.doLogin("", "");

            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should be displayed for blank credentials");
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Blank credentials test failed: " + e.getMessage());
        }
    }

    @Test(description = "Login with valid email but wrong password and verify error")
    public void loginWithWrongPassword() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            loginPage.doLogin("user@example.com", "WrongPassword999");

            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should be displayed for wrong password");
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Wrong password test failed: " + e.getMessage());
        }
    }

    @Test(description = "Login with unregistered email and verify error")
    public void loginWithUnregisteredEmail() {
        try {
            driver.get("https://login.salesforce.com/?locale=in");
            LoginPage loginPage = new LoginPage(driver);

            loginPage.doLogin("nonexistentuser123456@test.com", "SomePass123");

            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error should be displayed for unregistered email");
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertFalse(errorMessage.isEmpty(), "Error message should not be empty");
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            Assert.fail("Unregistered email test failed: " + e.getMessage());
        }
    }
}
