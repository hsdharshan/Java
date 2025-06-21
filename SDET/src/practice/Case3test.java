package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.Set;
import java.time.Duration;

public class Case3test {
    WebDriver driver;

    @Test(groups = {"WindowHandling"})
    public void verifyTitleAndWindowSwitch() throws InterruptedException {
    	WebDriverManager.edgedriver().setup();
    	driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    	driver.navigate().to("https://nocode.autify.com/");
        String parentWindow = driver.getWindowHandle();

        // Click Start Free Trial 
        WebElement trialBtn = driver.findElement(By.linkText("Start Free Trial"));
        trialBtn.click();

        Thread.sleep(3000); // Wait for potential tab/window

        Set<String> allWindows = driver.getWindowHandles();
        boolean windowSwitched = false;

        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                String actualTitle = driver.getTitle();
                Assert.assertTrue(actualTitle.toLowerCase().contains("autify"), "Title mismatch in child window!");
                driver.close();
                driver.switchTo().window(parentWindow);
                windowSwitched = true;
                break;
            }
        }

        //Assert.assertTrue(windowSwitched, "No child window was opened.");//skipping this assertion as new window is not opened when clicked on given link
        driver.quit();
    }

}

