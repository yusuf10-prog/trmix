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
