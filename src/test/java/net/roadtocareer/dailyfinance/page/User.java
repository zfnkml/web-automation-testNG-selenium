package net.roadtocareer.dailyfinance.page;

import net.roadtocareer.dailyfinance.setup.Setup;
import net.roadtocareer.dailyfinance.utils.MyDataGenerator;
import net.roadtocareer.dailyfinance.utils.MyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/*
 ** 2025, January 11, Saturday, 1:12 PM
 */
public class User {
    WebDriver webDriver;

    @FindBy(tagName = "h2")
    WebElement h2PageHeading;

    @FindBy(className = "total-count")
    WebElement spanTotalUsers;

    @FindBy(tagName = "input")
    List<WebElement> inputList;

    @FindBy(tagName = "button")
    List<WebElement> btnList;

    @FindBy(css = "[role=menuitem]")
    List<WebElement> menuItemList;

    public User(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public String updateGmail() throws InterruptedException, IOException {
        String updatedEmail = new MyDataGenerator().getEmail();

        btnList.getFirst().click();
        menuItemList.getFirst().click();

        btnList.get(1).click();

        inputList.get(3).sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        inputList.get(3).sendKeys(updatedEmail);
        btnList.get(2).click();
        MyUtils.acceptAlert(webDriver);

        return updatedEmail;
    }

    public void logout() {
        btnList.getFirst().click();
        menuItemList.get(1).click();
    }

    public boolean loadUserDashboard() {
        return h2PageHeading.isDisplayed();
    }

    public boolean searchByEmail(String email) {
        if (h2PageHeading.getText().contains("Admin Dashboard")) {
            inputList.getFirst().sendKeys(email);
            return spanTotalUsers.getText().contains("Total Users: 1");
        }
        return false;
    }

    public boolean searchByItemName(String itemName) {
        if (h2PageHeading.getText().contains("User Daily Costs")) {
            inputList.getFirst().sendKeys(itemName);
            return webDriver.findElements(By.tagName("span")).getFirst().getText().contains("Total Rows: 1");
        }
        return false;
    }

    public void addCost(String itemName, String quantity, String amount, String date, String month, String year, String remarks) throws InterruptedException {
        btnList.get(1).click();
        AddCost addCost = new AddCost(webDriver);
        addCost.addNewCost(itemName, quantity, amount, date, month, year, remarks);
    }

    public void deleteUser() throws InterruptedException {
//        loadUserDashboard();
//        btnList.getFirst().click();
//        menuItemList.getFirst().click();

        btnList.get(1).click();
        btnList.get(2).click();
        MyUtils.acceptAlert(webDriver);
        MyUtils.acceptAlert(webDriver);
    }
}
