import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleSearchTestOnIE {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty( "webdriver.ie.driver",
                "src/test/resources/drivers/IEDriverServer.exe" );

        DesiredCapabilities сapabilities = new DesiredCapabilities().internetExplorer();
        сapabilities.setCapability(
                InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                true );
        сapabilities.setCapability( InternetExplorerDriver.IGNORE_ZOOM_SETTING, true );
        driver = new InternetExplorerDriver( сapabilities );

        //driver.manage().window().maximize();


        driver.get( "http://www.google.com" );
    }

    @Test
    public void testGoogleSearch() {

        WebElement element = driver.findElement( By.name( "q" ) );

        element.clear();

        element.sendKeys( "Globoforce" );

        element.submit();

        new WebDriverWait( driver, 10 ) {
            public Boolean apply(WebDriver driver) {
                return driver.getTitle().toLowerCase()
                        .startsWith( "globoforce" );
            }
        };

        Assert.assertEquals( driver.getTitle(), "Globoforce" );
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }


}
