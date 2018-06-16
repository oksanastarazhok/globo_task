
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MakeScreenshotTask {
    private WebDriver driver;


    @BeforeTest(description = "Three supported browsers are define in xml file. Default browser is firefox.")
    @Parameters("browser")

    public void setUp(@Optional("firefox") String browser) throws Exception {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    "./src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver",
                    "src/test/resources/drivers/IEDriverServer.exe");


            InternetExplorerOptions options = new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                    true);
            options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            driver = new InternetExplorerDriver(options);


        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver",
                    "./src/test/resources/drivers/geckodriver.exe");

            driver = new FirefoxDriver();
        } else {
            throw new Exception("Browser is not correct");
        }

        driver.manage().window().maximize();

    }

    @Test(description = "This test verifies custom css is not broken by taking screenshot of home page.")
    public void makeScr() throws Exception {

        driver.get("https://staging-web1.corp.globoforce.com/microsites/t/home?client=testclientclone2&setCAG=true");

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("67366T");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("password1");
        WebElement loginBtn = driver.findElement(By.id("signIn-button"));
        loginBtn.click();

        Boolean title = driver.getTitle().contains("Welcome");


        if (!title) {
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

        String homePage = driver.getCurrentUrl().substring(0, 65);
        Assert.assertEquals(homePage, "https://staging-web1.corp.globoforce.com/microsites/t/home?client");
    }


    @AfterTest
    public void tearDown() {

        driver.quit();
    }
}
