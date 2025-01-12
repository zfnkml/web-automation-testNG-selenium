package net.roadtocareer.dailyfinance.setup;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/*
 ** 2025, January 10, Friday, 12:17 PM
 */
public class Setup {
    public WebDriver webDriver;
    static Properties properties;
    private static String fileConfig = "./src/test/resources/config.properties";

    @BeforeTest
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        webDriver.get("https://dailyfinance.roadtocareer.net/");
    }

    @BeforeTest
    public static Properties configFile() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(fileConfig));
        return properties;
    }

    public static void setProperty(String key, String value) throws ConfigurationException {
        PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(fileConfig);
        propertiesConfiguration.setProperty(key, value);
        propertiesConfiguration.save();
    }

    @BeforeMethod
    public void wait1s() throws InterruptedException {
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        webDriver.quit();
    }
}
