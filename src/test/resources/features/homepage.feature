Feature: Trmix Website Homepage Tests

  Scenario: User visits Trmix homepage
    Given user navigates to Trmix homepage
    Then user should see the homepage

  Scenario: User navigates through main menu items
    Given user navigates to Trmix homepage
    When user clicks on Home menu
    And user clicks on Products menu
    And user clicks on Technologies menu
    And user clicks on Company menu
    And user clicks on Contact menu
    Then all menu navigations should be successful

  Scenario: User clicks on social media icons
    Given user navigates to Trmix homepage
    When user clicks on Facebook icon
    And user clicks on Twitter icon
    And user clicks on LinkedIn icon
    And user clicks on Instagram icon
    Then all social media icon clicks should be successful
