package net.roadtocareer.dailyfinance.utils;

import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 ** 2025, January 11, Saturday, 2:00 PM
 */
public class MyUtils {
    public static void acceptAlert(WebDriver webDriver) throws InterruptedException {
        Thread.sleep(1000);
        webDriver.switchTo().alert().accept();
        Thread.sleep(1000);
    }

    public static String extractPasswordResetLinkFromMail(String passwordResetMail) {
        String pattern = "https://dailyfinance\\.roadtocareer\\.net/reset-password\\?token=[a-zA-Z0-9]+";
        Matcher matcher = Pattern.compile(pattern).matcher(passwordResetMail);
        if (matcher.find())
            return matcher.group();

        return "unable to extract";
    }
}
