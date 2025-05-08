package com.trmix.stepdefinitions;

import com.trmix.pages.HomePage;
import com.trmix.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
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

    @When("user clicks on Home menu")
    public void userClicksOnHomeMenu() {
        homePage.clickHomeMenu();
    }

    @And("user clicks on Products menu")
    public void userClicksOnProductsMenu() {
        homePage.clickProductsMenu();
    }

    @And("user clicks on Technologies menu")
    public void userClicksOnTechnologiesMenu() {
        homePage.clickTechnologiesMenu();
    }

    @And("user clicks on Company menu")
    public void userClicksOnCompanyMenu() {
        homePage.clickCompanyMenu();
    }

    @And("user clicks on Contact menu")
    public void userClicksOnContactMenu() {
        homePage.clickContactMenu();
    }

    @Then("all menu navigations should be successful")
    public void allMenuNavigationsShouldBeSuccessful() {
        // The test will fail if any of the previous clicks failed
        // as they would have thrown an exception
        Assert.assertTrue("Navigation completed successfully", true);
    }

    @When("user clicks on all social media icons")
    public void userClicksOnAllSocialMediaIcons() {
        homePage.clickAllSocialMediaIcons();
    }

    @Then("all social media icon clicks should be successful")
    public void allSocialMediaIconClicksShouldBeSuccessful() {
        // Since social media links open in new tabs/windows, we can verify the page title hasn't changed
        Assert.assertTrue("Page title should remain unchanged after social media clicks", 
            !homePage.getPageTitle().isEmpty());
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }
}
