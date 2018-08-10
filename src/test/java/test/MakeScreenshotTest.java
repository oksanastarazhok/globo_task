package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.TestUtilsPage;


public class MakeScreenshotTest {
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

        @Test(description = "Log in to non-sso test client")
        public void loginSSO() {
            driver.get("https://dubtest01.corp.globoforce.com/testutils/LoginListServlet");
            TestUtilsPage testUtilsPage = new TestUtilsPage(driver);

            testUtilsPage.loginSSO();

            HomePage homePage = new HomePage(driver);
            Assert.assertTrue(homePage.loginIsCorrect(), "Looks you are NOT logged in correctly!");
            System.out.println("Login was completed correctly.");
            homePage.makeScr();

        }

    @Test(description = "Log in to non-sso test client")
    public void loginNonSSO() {
        driver.get("https://staging-web1.corp.globoforce.com/microsites/t/home?client=testclientclone2&setCAG=true");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login();
        HomePage homePage = new HomePage(driver);
        homePage.makeScr();
        Assert.assertTrue(homePage.loginIsCorrect(), "Looks you are NOT logged in correctly!");
        System.out.println("Login was completed correctly.");

    }



        @AfterTest
        public void tearDown() {

            driver.quit();
        }

    }
