package net.roadtocareer.dailyfinance.page;

import net.roadtocareer.dailyfinance.utils.MyDataGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/*
 ** 2025, January 09, Thursday, 9:36 PM
 */
public class LoginPage {
    WebDriver webDriver;

    @FindBy(id = "email")
    WebElement inpEmail;

    @FindBy(id = "password")
    WebElement inpPassword;

    @FindBy(tagName = "button")
    WebElement btnLogin;

    @FindBy(tagName = "p")
    WebElement pError;

    @FindBy(linkText = "Register")
    WebElement aRegister;

    @FindBy(linkText = "Reset it here")
    WebElement aReset;

    @FindBy(tagName = "p")
    WebElement pErrorMessage;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void loginForm(String email, String password) {
        inpEmail.sendKeys(email);
        inpPassword.sendKeys(password);
        btnLogin.click();
    }

    public boolean errorMessage() {
        return pError.isDisplayed();
    }

    public void registrationWith(String emailPrefix) {
        aRegister.click();

        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.registrationForm(new MyDataGenerator(emailPrefix));
    }

    public void clickResetButton() {
        aReset.click();
    }

    public boolean loginSuccessful() {
        User user = new User(webDriver);
        return user.loadUserDashboard();
    }
}
