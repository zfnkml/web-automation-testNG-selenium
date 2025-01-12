package net.roadtocareer.dailyfinance.page;

import net.roadtocareer.dailyfinance.utils.MyDataGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/*
 ** 2025, January 09, Thursday, 9:36 PM
 */
public class RegisterPage {
    @FindBy(tagName = "input")
    List<WebElement> inputList;

    @FindBy(tagName = "button")
    WebElement btnRegister;

    @FindBy(className = "Toastify__toast")
    WebElement toastMessage;

    public RegisterPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void registrationForm(MyDataGenerator myDataGenerator) {
        inputList.get(0).sendKeys(myDataGenerator.getFirstName());
        if(myDataGenerator.getLastName()!=null)
            inputList.get(1).sendKeys(myDataGenerator.getLastName());
        inputList.get(2).sendKeys(myDataGenerator.getEmail());
        inputList.get(3).sendKeys(myDataGenerator.getPassword());
        inputList.get(4).sendKeys(myDataGenerator.getPhoneNumber());
        if(myDataGenerator.getAddress()!=null)
            inputList.get(5).sendKeys(myDataGenerator.getAddress());

        if(myDataGenerator.getGender().equalsIgnoreCase("male"))
            inputList.get(6).click();
        else if(myDataGenerator.getGender().equalsIgnoreCase("Female"))
            inputList.get(7).click();

        inputList.get(8).click();

        btnRegister.click();
    }



}
