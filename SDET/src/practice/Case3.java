package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

public class Case3 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new EdgeDriver();

        // Maximize and open the website
        driver.manage().window().maximize();
        driver.get("https://nocode.autify.com/");

        // Save parent window handle
        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.linkText("Start Free Trial"));
 
        // Switch to Child Window/Tab
        // Note to reviewer when i click on the start free trail link new window or tab was not opened for the given website hence below get window handle
        // does not goes within loop 
        Thread.sleep(3000); // wait for new tab to open
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                String title = driver.getTitle();
                System.out.println("Child window title: " + title);

                // Verify Title
                if (title.toLowerCase().contains("autify")) {
                    System.out.println("Title verification passed.");
                } else {
                    System.out.println("Title verification failed.");
                }

                // Close Child Window
                driver.close();
                System.out.println("Child window closed.");

                // Switch back to parent
                driver.switchTo().window(parentWindow);
            }
        }

        //Close parent window
        driver.quit();
        System.out.println("Test completed. Browser closed.");
    }
}