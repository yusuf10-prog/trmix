package com.trmix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    protected void sendKeys(By by, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
    }

    protected WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected boolean isElementPresent(By by) {
        try {
            waitForElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected void acceptCookiesIfPresent() {
        // Common cookie accept button selectors
        By[] cookieSelectors = {
            By.xpath("//*[contains(text(), 'Accept') or contains(text(), 'Kabul')]"),
            By.xpath("//*[contains(@class, 'cookie') and (contains(text(), 'Accept') or contains(text(), 'Kabul'))]"),
            By.id("cookieAccept"),
            By.className("cookie-accept")
        };

        for (By selector : cookieSelectors) {
            try {
                WebElement cookieButton = driver.findElement(selector);
                if (cookieButton.isDisplayed()) {
                    cookieButton.click();
                    break;
                }
            } catch (Exception ignored) {
            }
        }
    }
}
