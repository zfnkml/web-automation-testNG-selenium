package net.roadtocareer.dailyfinance.runner;

import io.restassured.path.json.JsonPath;
import net.roadtocareer.dailyfinance.page.ForgotPassword;
import net.roadtocareer.dailyfinance.page.LoginPage;
import net.roadtocareer.dailyfinance.page.ResetPassword;
import net.roadtocareer.dailyfinance.page.User;
import net.roadtocareer.dailyfinance.service.GmailService;
import net.roadtocareer.dailyfinance.setup.ItemsDataProviderCSV;
import net.roadtocareer.dailyfinance.setup.Setup;
import net.roadtocareer.dailyfinance.utils.MyUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/*
 ** 2025, January 10, Friday, 12:34 PM
 */
public class TestRunner extends Setup {
    @Test(priority = 1)
    public void testRegistration_withEmail_receiveGmailConfirmation() throws ConfigurationException, IOException, InterruptedException {
//        1. Visit the site https://dailyfinance.roadtocareer.net/.
//        Register a new user (e.g. yourvalidgmailuser+1@gmail.com).
//        Assert the congratulations email is received.

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.registrationWith("nfzkml11.cs");

        JsonPath gmail = GmailService.readLatestMail().jsonPath();
        Assert.assertTrue(gmail.get("snippet").toString().contains("Welcome to our platform!"));

        setProperty("registeredEmail", gmail.get("payload.headers[0].value").toString());
    }

    @Test(priority = 2)
    public void testResetPassword_withBlankEmail_warning() throws IOException {
//        2. Now click on the reset password link. Write 2 negative test case and assert.

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickResetButton();

        ForgotPassword forgotPassword = new ForgotPassword(webDriver);
        forgotPassword.sendResetLinkToEmail("");
        Assert.assertTrue(forgotPassword.getTooltipMessage().contains("Please fill out this field"));

        webDriver.navigate().back();
    }

    @Test(priority = 2)
    public void testResetPassword_withUnregisteredEmail_error() throws IOException {
//        2. Now click on the reset password link. Write 2 negative test case and assert.

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickResetButton();

        ForgotPassword forgotPassword = new ForgotPassword(webDriver);
        forgotPassword.sendResetLinkToEmail("a1b2c3.d4e5f6@gmail.com");
        Assert.assertTrue(forgotPassword.getParagraphMessage().contains("Your email is not registered"));

        webDriver.navigate().back();
    }

    @Test(priority = 3)
    public void testResetPassword_withRegisteredEmail_success() throws IOException {
//        3. Now Input valid gmail account you have registered and click on send reset link button

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.clickResetButton();

        ForgotPassword forgotPassword = new ForgotPassword(webDriver);
        forgotPassword.sendResetLinkToEmail(configFile().getProperty("registeredEmail"));

        Assert.assertTrue(forgotPassword.getParagraphMessage().contains("Password reset link sent to your email"));
    }

    @Test(priority = 4)
    public void testResetPassword_passwordResetMail_setNewPassword() throws ConfigurationException, IOException, InterruptedException {
//        4. Now retrieve password reset mail from your gmail and set new password

        String passwordResetMail = GmailService.readLatestMail().jsonPath().get("snippet").toString();
        System.out.println(passwordResetMail);

        webDriver.get(MyUtils.extractPasswordResetLinkFromMail(passwordResetMail));

        ResetPassword resetPassword = new ResetPassword(webDriver);
        String newPassword = resetPassword.newPassword();
        Assert.assertTrue(resetPassword.getSuccessMessage());

        setProperty("password", newPassword);
    }

    @Test(priority = 5)
    public void testLogin_withNewPassword_successful() throws IOException {
//        5. Now login with the new password to ensure login successful
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginForm(
                configFile().getProperty("registeredEmail"),
                configFile().getProperty("password")
        );

        Assert.assertTrue(loginPage.loginSuccessful());
    }

    @Test(priority = 6, dataProviderClass = ItemsDataProviderCSV.class, dataProvider = "ItemsCSV")
    public void testAddItem_fromCSV(String itemName, String quantity, String amount, String date, String month, String year, String remarks) throws InterruptedException {
//        6. Add random 2 items (1 for all fields, another for only mandatory fields) and
//        assert 2 items are showing on the item list

        User user = new User(webDriver);
        user.addCost(itemName, quantity, amount, date, month, year, remarks);
        Assert.assertTrue(user.searchByItemName(itemName));
    }

    @Test(priority = 7)
    public void testUser_updateGmail_successful() throws InterruptedException, IOException, ConfigurationException {
//        7. Now go to user profile and update user gmail with a new gmail

        User user = new User(webDriver);
        String updatedEmail = user.updateGmail();

        setProperty("updatedEmail", updatedEmail);

        System.out.println(updatedEmail);
        System.out.println(configFile().getProperty("updatedEmail"));

        user.logout();
    }

    @Test(priority = 8)
    public void testLogin_withRegisteredEmail_errorMessageDisplayed() throws IOException {
//        8. Now logout and login with the updated gmail account.
//        Assert that using new email login is successful
//        and using previous email login is failed.
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginForm(
                configFile().getProperty("registeredEmail"),
                configFile().getProperty("password")
        );

        Assert.assertTrue(loginPage.errorMessage());

        webDriver.navigate().refresh();
    }

    @Test(priority = 9)
    public void testLogin_withUpdatedEmail_success() throws IOException {
//        8. Now logout and login with the updated gmail account.
//        Assert that using new email login is successful
//        and using previous email login is failed.

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginForm(
                configFile().getProperty("updatedEmail"),
                configFile().getProperty("password")
        );


        User user = new User(webDriver);
        user.loadUserDashboard();
        user.logout();
    }

    @Test(priority = 10)
    public void testLogin_asAdmin_successful() throws IOException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginForm(
                System.getProperty("aEmail"),
                System.getProperty("aPassword")
        );

        Assert.assertTrue(loginPage.loginSuccessful());
    }

    @Test(priority = 11)
    public void testSearch_updatedGmail_found() throws IOException {
//        10. Search by the updated gmail and
//        Assert that updated user email is showing on admin dashboard.

        User admin = new User(webDriver);
        Assert.assertTrue(admin.searchByEmail(configFile().getProperty("updatedEmail")));
    }
}
