Feature: Verify IFrame Page functionality

  Scenario: Open iframe page and validate image change
    Given User launches the application
    Then Title of the page should be "Automation & AI Testing Courses by Gianni Bruno | WebDriver University"
    When User clicks on IFRAME link
    And Switches to new tab
    Then Verify image is displayed
    And Clicks on the right arrow button
    Then Verify that image changes