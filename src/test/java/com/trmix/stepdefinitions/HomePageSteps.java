package com.trmix.stepdefinitions;

import com.trmix.pages.HomePage;
import com.trmix.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class HomePageSteps {
    private HomePage homePage = new HomePage(Driver.getDriver());

    @Given("user navigates to Trmix homepage")
    public void userNavigatesToTrmixHomepage() {
        homePage.navigateToHomePage();
    }

    @Then("user should see the homepage")
    public void userShouldSeeTheHomepage() {
        Assert.assertTrue("Homepage title should not be empty", 
            !homePage.getPageTitle().isEmpty());
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }
}
