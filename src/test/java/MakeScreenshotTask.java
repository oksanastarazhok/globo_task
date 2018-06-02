import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MakeScreenshotTask {
    private WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setUp(String browser) throws Exception {
        if(browser.equalsIgnoreCase("chrome")){
        System.setProperty("webdriver.chrome.driver",
                "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();}

        else if(browser.equalsIgnoreCase("ie")){
        System.setProperty( "webdriver.ie.driver",
                "src/test/resources/drivers/IEDriverServer.exe" );

        DesiredCapabilities capabilities = new DesiredCapabilities().internetExplorer();
            capabilities.setCapability(
                InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true );
            capabilities.setCapability( InternetExplorerDriver.IGNORE_ZOOM_SETTING, true );
        driver = new InternetExplorerDriver( capabilities );
    }
    else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty( "webdriver.gecko.driver",
                    "./src/test/resources/drivers/geckodriver.exe" );

            driver = new FirefoxDriver();
        }else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void testGoogleSearch() throws Exception {
        driver.get("https://staging-web1.corp.globoforce.com/microsites/t/home?client=testclientclone2&setCAG=true");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("79961T");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("password1");
        WebElement loginBtn = driver.findElement(By.id("signIn-button"));
        loginBtn.click();

        Boolean title = driver.getTitle().contains("Welcome");


        if (!title) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-ui-app\"]/login-ui-app/legal-component/div/button")));
            WebElement legalBtn = driver.findElement(By.xpath("//*[@id=\"login-ui-app\"]/login-ui-app/legal-component/div/button"));
            legalBtn.click();
        } else {
            System.out.println("Aggreement has been accepted earlier.");
        }


        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            FileUtils.copyFile(src, new File(".\\src\\test\\screenshots\\screenshot" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());


        }
        String homePage = driver.getCurrentUrl().substring(0,65);
        Assert.assertEquals(homePage,"https://staging-web1.corp.globoforce.com/microsites/t/home?client");
    }


    @AfterTest
    public void tearDown() throws Exception {

        driver.quit();
    }
}
