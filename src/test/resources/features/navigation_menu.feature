@NavigationMenu
Feature: Navigation Menu
  As a user
  I want to click on all navigation menu items
  So that I can verify all menu items are working

  Scenario: Click all navigation menu items
    Given user navigates to Trmix homepage
    When user clicks on all menu items
    Then all menu clicks should be successful
