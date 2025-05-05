package com.trmix.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // 60 saniyeye çıkardık
        this.js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
    }

    protected void waitForPageLoad() {
        wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
        try {
            Thread.sleep(2000); // Sayfa yüklendikten sonra ekstra bekleme
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void switchToFrameIfExists() {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            if (!frames.isEmpty()) {
                for (WebElement frame : frames) {
                    try {
                        driver.switchTo().frame(frame);
                        // Frame içinde menü elemanlarını kontrol et
                        if (!driver.findElements(By.tagName("a")).isEmpty()) {
                            return;
                        }
                        driver.switchTo().defaultContent();
                    } catch (Exception e) {
                        driver.switchTo().defaultContent();
                    }
                }
            }
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }
    }

    protected void click(By by) {
        try {
            waitForPageLoad();
            switchToFrameIfExists();
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
            scrollIntoView(element);
            try {
                element.click();
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", element);
            }
        } catch (Exception e) {
            throw new RuntimeException("Element not clickable: " + by.toString(), e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    protected void scrollIntoView(WebElement element) {
        try {
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            Thread.sleep(1000); // Scroll animasyonunun tamamlanmasını bekle
        } catch (Exception ignored) {}
    }

    protected void sendKeys(By by, String text) {
        waitForPageLoad();
        switchToFrameIfExists();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by)).sendKeys(text);
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    protected WebElement waitForElement(By by) {
        waitForPageLoad();
        switchToFrameIfExists();
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } finally {
            driver.switchTo().defaultContent();
        }
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
        try {
            waitForPageLoad();
            // Common cookie accept button selectors
            By[] cookieSelectors = {
                By.xpath("//*[contains(text(), 'Accept') or contains(text(), 'Kabul')]"),
                By.xpath("//*[contains(@class, 'cookie') and (contains(text(), 'Accept') or contains(text(), 'Kabul'))]"),
                By.id("cookieAccept"),
                By.className("cookie-accept"),
                By.xpath("//*[contains(@class, 'cookie')]//button"),
                By.xpath("//button[contains(@class, 'accept')]"),
                By.xpath("//button[contains(@class, 'cookie')]"),
                By.xpath("//div[contains(@class, 'cookie')]//button")
            };

            for (By selector : cookieSelectors) {
                try {
                    List<WebElement> elements = driver.findElements(selector);
                    for (WebElement element : elements) {
                        if (element.isDisplayed()) {
                            try {
                                element.click();
                                Thread.sleep(1000);
                                return;
                            } catch (ElementClickInterceptedException e) {
                                js.executeScript("arguments[0].click();", element);
                                Thread.sleep(1000);
                                return;
                            }
                        }
                    }
                } catch (Exception ignored) {}
            }
        } catch (Exception ignored) {}
    }
}
