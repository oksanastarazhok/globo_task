package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage {
    @FindBy(id = "giveAward-button")
    public WebElement recognizeButton;

    @FindBy(xpath = "//*[@id=\"programLogo\"]")
    public WebElement logInCheck;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void waiterOfElementLoaded() {
        WebDriverWait waitForOne = new WebDriverWait(driver, 10);
        waitForOne.until(ExpectedConditions.visibilityOf(logInCheck));
    }

    public boolean loginIsCorrect() {
        return logInCheck.isDisplayed();

    }

    public void makeScr() {
        Boolean title = driver.getTitle().contains("Welcome");


        if (!title) {

            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-ui-app\"]/legal-component/div/button")));
            WebElement legalBtn = driver.findElement(By.xpath("//*[@id=\"login-ui-app\"]/legal-component/div/button"));
            legalBtn.click();
        } else {
            System.out.println("Aggreement has been accepted earlier.");
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            FileHandler.copy(src, new File(".\\src\\test\\screenshots\\screenshot" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }
}
