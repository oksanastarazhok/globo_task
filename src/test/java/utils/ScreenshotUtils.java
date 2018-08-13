package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

/**
 * Take screenshot if test fails
 */
public class ScreenshotUtils {
    public static void captureScreenshot(WebDriver driver, ITestResult result) {
        if (!result.isSuccess()) {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs( OutputType.FILE);
            try {
                FileHandler.copy(srcFile, new File(".\\src\\test\\screenshots" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
