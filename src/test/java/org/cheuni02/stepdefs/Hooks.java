package org.cheuni02.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Hooks {
    private WebDriver driver;

    @Before(order = 0, value = "@Chrome")
    public void setUpChrome() {
        driver = new ChromeDriver();
    }

    @Before(order = 0, value = "@Firefox")
    public void setUpFirefox() {
        driver = new FirefoxDriver();
    }

    @Before(order = 0, value = "@Edge")
    public void setUpEdge() {
        driver = new EdgeDriver();
    }

    @Before(order = 1)
    public void conditionBrowser() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            System.out.println("Quitting driver ...");
            driver.quit();
        }
    }


}
