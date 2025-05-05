package com.trmix.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            String browser = System.getProperty("browser", "chrome");
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
            }
            driverPool.get().manage().window().maximize();
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
