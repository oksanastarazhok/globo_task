import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleSearchTestOnFireFox {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty( "webdriver.gecko.driver",
                "./src/test/resources/drivers/geckodriver.exe" );

        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get( "http://www.google.com" );
    }

    @Test
    public void testGoogleSearch() {
        WebElement element = driver.findElement( By.name( "q" ) );


        element.sendKeys( "Globoforce" );

        element.submit();

        new WebDriverWait( driver, 10 ) {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase()
                        .startsWith( "globoforce" );
            }
        };
        String title = driver.getTitle().substring( 0, 10 );
        Assert.assertEquals( "Globoforce",
                title );
    }

    @AfterTest
    public void tearDown() throws Exception {

        driver.quit();
    }
}
