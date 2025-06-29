package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class MakeMyTripFlightOneWay {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.makemytrip.com");
    }

    @Test
    public void bookOneWayFlight() throws InterruptedException {
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
        Thread.sleep(5000);
        driver.findElement(By.xpath("//p[text()='Chennai International Airport']")).click();

        driver.findElement(By.xpath("//label[@for='toCity']")).click();
        WebElement toInput = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toInput.clear();
        toInput.sendKeys("Bhubaneshwar");
        driver.findElement(By.xpath("//p[text()='Bhubaneswar, India']")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
