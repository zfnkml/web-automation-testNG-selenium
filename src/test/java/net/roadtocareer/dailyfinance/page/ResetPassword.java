package net.roadtocareer.dailyfinance.page;

import net.roadtocareer.dailyfinance.utils.MyDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/*
 ** 2025, January 12, Sunday, 7:36 AM
 */
public class ResetPassword {
    WebDriver webDriver;

    @FindBy(tagName = "input")
    List<WebElement> inputList;

    @FindBy(tagName = "button")
    WebElement buttonResetPassword;

    @FindBy(tagName = "p")
    WebElement pSuccessMessage;

    public ResetPassword(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String newPassword() {
        String newPassword = String.valueOf(new MyDataGenerator().generateRandom8Digit());
        webDriver.findElements(By.tagName("input")).get(0).sendKeys(newPassword);
        webDriver.findElements(By.tagName("input")).get(1).sendKeys(newPassword);
        webDriver.findElement(By.tagName("button")).click();
        return newPassword;
    }

    public boolean getSuccessMessage() {
        return pSuccessMessage.isDisplayed();
    }
}
