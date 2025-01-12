package net.roadtocareer.dailyfinance.runner;

import net.roadtocareer.dailyfinance.page.LoginPage;
import net.roadtocareer.dailyfinance.page.User;
import net.roadtocareer.dailyfinance.setup.Setup;
import net.roadtocareer.dailyfinance.utils.MyUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;

/*
 ** 2025, January 10, Friday, 12:34 PM
 */
public class CleanUserEmal extends Setup {
    @Test
    public void testSearch_asAdmin_found() throws IOException, InterruptedException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.loginForm(
                "admin@test.com",
                "admin123"
        );

        User admin = new User(webDriver);

        while (admin.loadUserDashboard()) {
            Assert.assertTrue(admin.searchByEmail("nfzkml11.cs"));

            webDriver.findElements(By.tagName("button")).get(1).click();
            webDriver.findElements(By.tagName("button")).get(2).click();
            MyUtils.acceptAlert(webDriver);
            MyUtils.acceptAlert(webDriver);
            Thread.sleep(1000);
        }
    }
}
