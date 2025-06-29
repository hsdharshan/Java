package stepDefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.IframePage;

import java.util.ArrayList;

public class IframeSteps extends BaseTest {
    IframePage iframePage;

    @Given("User launches the application")
    public void user_launches_application() {
        initializeBrowser();
        driver.get("http://webdriveruniversity.com/index.html");
    }

    @Then("Title of the page should be {string}")
    public void title_of_the_page_should_be(String expectedTitle) {
        Assert.assertEquals(driver.getTitle(), expectedTitle);
    }

    @When("User clicks on IFRAME link")
    public void click_iframe_link() {
        driver.findElement(By.id("iframe")).click();
    }

    @And("Switches to new tab")
    public void switches_to_new_tab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.switchTo().frame("frame");
        iframePage = new IframePage(driver);
    }

    @Then("Verify image is displayed")
    public void verify_image_displayed() {
        Assert.assertTrue(iframePage.isImageDisplayed(), "Image is not displayed");
    }

    @And("Clicks on the right arrow button")
    public void click_right_arrow_button() {
        iframePage.clickRightArrow();
    }

    @Then("Verify that image changes")
    public void verify_image_changes() {
        Assert.assertTrue(iframePage.isImageDisplayed(), "Image did not change");
    }
}