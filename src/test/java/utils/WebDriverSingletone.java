package utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverSingletone {
    public static WebDriver driver;
    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            System.setProperty("webdriver", "C:\\Program Files (x86)\\Selenium drivers\\geckodriver.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setPlatform(Platform.WINDOWS);
            driver = new FirefoxDriver(capabilities);
        }
        return driver;
}}
