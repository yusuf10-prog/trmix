package com.trmix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends BasePage {
    
    private final By linkedinIcon = By.xpath("//a[contains(@href, 'linkedin.com/company/trmixcom')]|//a[contains(@class, 'tt-magnetic-item')][1]");
    private final By facebookIcon = By.xpath("//a[contains(@class, 'tt-magnetic-item')][2]");
    private final By twitterIcon = By.xpath("//a[contains(@class, 'tt-magnetic-item')][3]");
    private final By instagramIcon = By.xpath("//a[contains(@class, 'tt-magnetic-item')][4]");
    private final By hamburgerMenu = By.xpath("//button[contains(@class, 'menu') or contains(@class, 'navbar') or contains(@class, 'nav-toggle')]|//div[contains(@class, 'menu-toggle')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://www.trmix.com");
    }

    private void openMobileMenuIfNeeded() {
        try {
            WebElement menuButton = driver.findElement(hamburgerMenu);
            if (menuButton.isDisplayed()) {
                js.executeScript("arguments[0].click();", menuButton);
            }
        } catch (Exception ignored) {}
    }

    public void clickAllSocialMediaIcons() {
        List<By> socialMediaIcons = List.of(linkedinIcon, facebookIcon, twitterIcon, instagramIcon);
        String mainWindow = driver.getWindowHandle();
        
        for (By iconLocator : socialMediaIcons) {
            try {
                driver.switchTo().window(mainWindow);
                WebElement icon = driver.findElement(iconLocator);
                js.executeScript("arguments[0].click();", icon);
            } catch (Exception ignored) {}
        }
        
        driver.getWindowHandles().stream()
            .filter(window -> !window.equals(mainWindow))
            .forEach(window -> {
                driver.switchTo().window(window);
                driver.close();
            });
            
        driver.switchTo().window(mainWindow);
    }

    private void clickMenuByText(String... textOptions) {
        openMobileMenuIfNeeded();
        
        for (String text : textOptions) {
            try {
                WebElement link = driver.findElement(By.xpath(String.format("//a[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='%s']|//button[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')='%s']", text.toLowerCase(), text.toLowerCase())));
                js.executeScript("arguments[0].click();", link);
                return;
            } catch (Exception ignored) {}
        }
    }

    public void clickHomeMenu() {
        clickMenuByText("ANA SAYFA", "HOME");
    }

    public void clickProductsMenu() {
        clickMenuByText("ÜRÜNLER", "PRODUCTS");
    }

    public void clickTechnologyMenu() {
        clickMenuByText("TEKNOLOJİ VE ÇÖZÜMLER", "TECHNOLOGY AND SOLUTIONS");
    }

    public void clickCompanyMenu() {
        clickMenuByText("ŞİRKET", "COMPANY");
    }

    public void clickContactMenu() {
        clickMenuByText("İLETİŞİM", "CONTACT");
    }

    public void clickEnglishButton() {
        try {
            WebElement englishButton = driver.findElement(By.xpath("//a[contains(@class, 'tt-btn') and contains(text(), 'ENGLISH')]"));
            js.executeScript("arguments[0].click();", englishButton);
        } catch (Exception ignored) {}
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
