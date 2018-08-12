package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestUtilsPage extends BasePage {

    @FindBy(css = "#autocomplete-combobox > input")
    public WebElement clientField;

    @FindBy(css = "#autocomplete-server > input")
    public WebElement serverField;

    @FindBy(css = "#autocomplete-database > input")
    public WebElement dbField;

    @FindBy(id = "firstname")
    public WebElement firstnameField;

    @FindBy(id = "lastname")
    public WebElement lastnameField;

    @FindBy(id = "username")
    public WebElement usernameField;

    @FindBy(id = "pk_person")
    public WebElement pkField;

    @FindBy(id = "ref_no")
    public WebElement refnumberField;

    @FindBy(id = "showExpired")
    public WebElement showExpiredBox;

    @FindBy(css = "#autocomplete-countryId > input")
    public WebElement countryField;

    @FindBy(css = "#find > a")
    public WebElement findButton;

    @FindBy(xpath = "//*[@id=\"loginstable\"]/tbody/tr/td[1]/div/a")
    public WebElement loginButton;

    public TestUtilsPage(WebDriver driver) {
        super(driver);
    }


    public void loginSSO() {
        System.out.println("Starting the signIn process.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[@id=\"ui-id-7\"]")));

        clientField.clear();
        clientField.sendKeys("staff(33)");
        clientField.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[@id=\"ui-id-7\"]")));
        serverField.clear();
        serverField.sendKeys("staging-web1.corp.globoforce.com");
        serverField.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[@id=\"ui-id-7\"]")));
        dbField.clear();
        dbField.sendKeys("GT12");
        dbField.click();

        firstnameField.clear();
        lastnameField.clear();
        usernameField.clear();
        pkField.clear();
        refnumberField.clear();
        refnumberField.sendKeys("oksanas");
        findButton.click();
        loginButton.click();


    }
}
