package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(name = "username")
    public WebElement usernameField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy (id = "signIn-button")
    public WebElement loginButton;



    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void login(){
        System.out.println("Starting the signIn process.");
        usernameField.clear();
        usernameField.sendKeys("67366T");
        passwordField.clear();
        passwordField.sendKeys("password1");
        loginButton.click();

    }

}
