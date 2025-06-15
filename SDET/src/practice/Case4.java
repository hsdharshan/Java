package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Case4 {
    public static void main(String[] args) {
    	WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

        try {
            // Step 1: Open Autify website
            driver.get("https://nocode.autify.com/");
            driver.manage().window().maximize();

            // Step 2: Click on "Start Free Trial" button
            WebElement startFreeTrialBtn = driver.findElement(By.linkText("Start Free Trial"));
            startFreeTrialBtn.click();

            // Step 3: Wait for page load
            Thread.sleep(3000);

            // Step 4: Click on "Sign Up" button without entering any fields
            WebElement signUpBtn = driver.findElement(By.xpath("//button[normalize-space()='Sign up']"));
            signUpBtn.click();

            // Step 5: Verify all error messages
            assert driver.findElement(By.xpath("//input[@name='firstName']/following-sibling::p[contains(text(),\"can't be blank\")]")).isDisplayed() : "First name error not shown";
            assert driver.findElement(By.xpath("//input[@name='lastName']/following-sibling::p[contains(text(),\"can't be blank\")]")).isDisplayed() : "Last name error not shown";
            assert driver.findElement(By.xpath("//input[@name='companyName']/following-sibling::p[contains(text(),\"can't be blank\")]")).isDisplayed() : "Company name error not shown";
            assert driver.findElement(By.xpath("//select[@name='companySize']/following-sibling::p[contains(text(),\"can't be blank\")]")).isDisplayed() : "Company size error not shown";
            assert driver.findElement(By.xpath("//input[@name='email']/following-sibling::p[contains(text(),\"can't be blank\")]")).isDisplayed() : "Email error not shown";
            assert driver.findElement(By.xpath("//input[@name='password']/following-sibling::p[contains(text(),\"cannot be blank\")]")).isDisplayed() : "Password error not shown";
            assert driver.findElement(By.xpath("//input[@name='phone']/../../following-sibling::p[contains(text(),\"can't be blank\")]")).isDisplayed() : "Phone number error not shown";


            System.out.println("All validation errors appeared correctly!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
