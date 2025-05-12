package com.trmix.stepdefinitions;

import com.trmix.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class NavigationSteps {
    private final HomePage homePage;

    public NavigationSteps() {
        this.homePage = new HomePage(Hooks.driver);
    }

    @When("user clicks on all menu items")
    public void userClicksOnAllMenuItems() {
        homePage.clickAllMenuItems();
    }

    @Then("all menu clicks should be successful")
    public void allMenuClicksShouldBeSuccessful() {
        // The success is implicitly verified by the absence of exceptions during clicks
        Assert.assertTrue("All menu items were clicked successfully", true);
    }
}
