package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="[data-qa='headerUserNotLoggedIn']")
    public WebElement userNotLoggedButton;

    @FindBy(css="[data-qa='loginInputEmail']")
    public WebElement emailField;

    @FindBy(css="[data-qa='inputPassword']")
    public WebElement passwordField;

    @FindBy(css="[data-qa='loginBtnSubmit']")
    public WebElement submitButton;

    @FindBy(css="[data-qa='headerUserLoggedIn']")
    public WebElement loggedUser;

    public void clickOnNotLoggedUserButton()
    {
        userNotLoggedButton.click();
    }
    public void enterEmail(String userEmail)
    {
        emailField.clear();
        emailField.sendKeys(userEmail);
    }

    public void enterPassword(String userPassword)
    {
        passwordField.clear();
        passwordField.sendKeys(userPassword);
    }

    public void clickOnSubmitButton()
    {
        submitButton.click();
    }

    public void submitCredentials(String userEmail, String userPassword)
    {
        enterEmail(userEmail);
        enterPassword(userPassword);
        clickOnSubmitButton();
        wait.until(ExpectedConditions.visibilityOf(loggedUser));

    }
}
