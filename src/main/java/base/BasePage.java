package base;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {


    public static WebDriver driver;
    public static WebDriverWait wait;


    public BasePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
        wait =  new WebDriverWait(driver, Duration.ofSeconds(5000));
    }




    public static void scrollToTheBottomOfThePage() {

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollToTheTopOfThePage() {

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, -document.body.scrollHeight)");

    }

    public static void scrollToTheMiddleOfThePage() {

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight / 2)");
    }

    public void scrollToAnElement(WebElement element) {

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        } catch (RuntimeException e) {
            throw new RuntimeException("Unsuccessful operation: " + e.getMessage());
        }
    }


}