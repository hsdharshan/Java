package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class MakeMyTripTests {

    WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("edge") String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.makemytrip.com");
    }

    @Test(priority = 1)
    public void verifyLogoPresence() {
        try {
            driver.findElement(By.cssSelector("span[class='commonModal__close']")).click();
        } catch (Exception e) {
            // Popup not present
        }

        WebElement logo = driver.findElement(By.cssSelector("img[alt='Make My Trip']"));
        assert logo.isDisplayed();
        System.out.println("Logo is displayed.");
    }

    @Test(priority = 2)
    public void bookOneWayFlight() {
        try {
            driver.findElement(By.cssSelector("span[class='commonModal__close']")).click();
        } catch (Exception e) {
            System.out.println("No pop up found on screen");
        }

        driver.findElement(By.xpath("//li[@data-cy='menu_Flights']")).click();
        driver.findElement(By.xpath("//li[@data-cy='oneWayTrip']")).click();
        driver.findElement(By.xpath("//label[@for='fromCity']")).click();

        WebElement fromInput = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromInput.clear();
        fromInput.sendKeys("Chennai");
        driver.findElement(By.xpath("//p[text()='Chennai International Airport']")).click();

        driver.findElement(By.xpath("//label[@for='toCity']")).click();
        WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toInput.clear();
        toInput.sendKeys("Bhubaneshwar");
        driver.findElement(By.xpath("//p[text()='Bhubaneswar, India']")).click();

        System.out.println("One-way flight booking form filled.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
