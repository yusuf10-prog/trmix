package com.trmix.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends BasePage {
    
    private final By hamburgerMenu = By.cssSelector("[class*='menu'], [class*='navbar'], [class*='nav-toggle']");
    private final By allNavLinks = By.cssSelector("a[href], button[role='link']");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage() {
        driver.get("https://www.trmix.com");
        waitForPageLoad();
        acceptCookiesIfPresent();
        // Sayfanın yüklenmesi için ekstra bekleme
        try {
            Thread.sleep(5000);
            // Tüm linkleri logla
            logAllLinks();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logAllLinks() {
        System.out.println("\n=== All Navigation Links ===");
        List<WebElement> links = driver.findElements(allNavLinks);
        for (WebElement link : links) {
            try {
                System.out.println("Text: '" + link.getText() + "'");
                System.out.println("Href: '" + link.getAttribute("href") + "'");
                System.out.println("Class: '" + link.getAttribute("class") + "'");
                System.out.println("Is Displayed: " + link.isDisplayed());
                System.out.println("---");
            } catch (Exception e) {
                // Ignore stale elements
            }
        }
        System.out.println("=== End of Navigation Links ===\n");
    }

    private void openMobileMenuIfNeeded() {
        try {
            List<WebElement> menuButtons = driver.findElements(hamburgerMenu);
            for (WebElement button : menuButtons) {
                if (button.isDisplayed()) {
                    try {
                        System.out.println("Found menu button with classes: " + button.getAttribute("class"));
                        button.click();
                        Thread.sleep(2000); // Menü açılma animasyonunu bekle
                        logAllLinks(); // Menü açıldıktan sonra linkleri tekrar logla
                        return;
                    } catch (Exception e) {
                        js.executeScript("arguments[0].click();", button);
                        Thread.sleep(2000);
                        logAllLinks();
                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Menu button not found or not needed");
        }
    }

    private void clickMenuByText(String... textOptions) {
        openMobileMenuIfNeeded();
        List<WebElement> links = driver.findElements(allNavLinks);
        System.out.println("\nTrying to find menu item with options: " + String.join(", ", textOptions));
        
        for (WebElement link : links) {
            try {
                String text = link.getText().trim().toLowerCase();
                String href = link.getAttribute("href");
                String className = link.getAttribute("class");
                
                System.out.println("Checking link - Text: '" + text + "', Href: '" + href + "', Class: '" + className + "'");
                
                // Text veya href içinde aranan kelimeler var mı kontrol et
                boolean matchFound = false;
                for (String option : textOptions) {
                    if ((text.contains(option.toLowerCase())) || 
                        (href != null && href.toLowerCase().contains(option.toLowerCase())) ||
                        (className != null && className.toLowerCase().contains(option.toLowerCase()))) {
                        matchFound = true;
                        System.out.println("Match found with option: " + option);
                        break;
                    }
                }
                
                if (matchFound) {
                    System.out.println("Attempting to click matching element");
                    js.executeScript("arguments[0].style.visibility = 'visible'; arguments[0].style.display = 'block';", link);
                    scrollIntoView(link);
                    try {
                        link.click();
                        System.out.println("Successfully clicked element");
                        return;
                    } catch (Exception e) {
                        System.out.println("Direct click failed, trying JavaScript click");
                        js.executeScript("arguments[0].click();", link);
                        System.out.println("Successfully clicked element with JavaScript");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error processing link: " + e.getMessage());
                continue;
            }
        }
        throw new RuntimeException("No clickable menu item found with text options: " + String.join(", ", textOptions));
    }

    public void clickHomeMenu() {
        clickMenuByText("anasayfa", "home", "/", "index");
    }

    public void clickProductsMenu() {
        clickMenuByText("ürünler", "products", "urunler", "product");
    }

    public void clickTechnologiesMenu() {
        clickMenuByText("teknoloji", "technology", "tech");
    }

    public void clickCompanyMenu() {
        clickMenuByText("şirket", "sirket", "company", "about", "hakkında", "hakkimizda");
    }

    public void clickContactMenu() {
        clickMenuByText("iletişim", "iletisim", "contact");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
