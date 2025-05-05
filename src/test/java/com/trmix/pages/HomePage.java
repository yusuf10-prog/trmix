package com.trmix.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://www.trmix.com");
        acceptCookiesIfPresent();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
