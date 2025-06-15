package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class Case4test {
    WebDriver driver;

    @Test(groups = {"FormValidation"})
    public void verifyAllErrorMessages() throws InterruptedException {
    	WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(); 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	 driver.get("https://nocode.autify.com/");
        // Click Start Free Trial 
        WebElement trialBtn = driver.findElement(By.linkText("Start Free Trial"));
        trialBtn.click();

        Thread.sleep(3000); // wait for form page

        // Click Sign Up without entering fields
        driver.findElement(By.xpath("//button[normalize-space()='Sign up']")).click();

        // Assert error messages
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='first_name']/following-sibling::span")).getText().contains("First name can't be blank"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='last_name']/following-sibling::span")).getText().contains("Last name can't be blank"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='company']/following-sibling::span")).getText().contains("Company name can't be blank"));
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name='company_size']/following-sibling::span")).getText().contains("Company size can't be blank"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='email']/following-sibling::span")).getText().contains("Email can't be blank"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='password']/following-sibling::span")).getText().contains("Password cannot be blank"));
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='phone_number']/following-sibling::span")).getText().contains("Phone Number can't be blank"));
        driver.quit();
    }

}
