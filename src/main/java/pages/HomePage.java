package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage {

    WebDriver driver;


    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(css ="#hd-search__input")
    public WebElement search ;

    @FindBy(css=".icon-search.fs-18")
    public WebElement searchButton;

    public void searchByName(String name)
    {
        search.sendKeys(name);
        searchButton.click();
        wait.until(ExpectedConditions.urlContains("search"));

    }

    @FindBy(css="[data-qa='emptySearchResultPageTextMessage']")
    public WebElement emptyResultPageMessage;

    @FindBy(css="[data-qa='emptySearchResultPageGoBackBtn']")
    public WebElement backButton;

    public void clickOnBackButton()
    {
        backButton.click();
        wait.until(ExpectedConditions.invisibilityOf(emptyResultPageMessage));
    }
}
