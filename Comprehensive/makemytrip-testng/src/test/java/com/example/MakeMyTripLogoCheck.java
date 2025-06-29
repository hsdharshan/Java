package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MakeMyTripLogoCheck {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.makemytrip.com");
    }

    @Test
    public void verifyLogoPresence() throws InterruptedException {
        Thread.sleep(5000);
        try {
            driver.findElement(By.cssSelector("span[class='commonModal_close']")).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            // Popup not present
        }

        WebElement logo = driver.findElement(By.cssSelector("img[alt='Make My Trip']"));
        assert logo.isDisplayed();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
