package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IframePage {
    WebDriver driver;

    By image = By.cssSelector(".carousel-inner .item.active img");
    By rightArrow = By.cssSelector(".carousel-control.right");

    public IframePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isImageDisplayed() {
        WebElement img = driver.findElement(image);
        return img != null && img.isDisplayed();
    }

    public void clickRightArrow() {
        driver.findElement(rightArrow).click();
    }
}