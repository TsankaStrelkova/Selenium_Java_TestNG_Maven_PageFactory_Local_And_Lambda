package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CategoriesPage extends BasePage {

    WebDriver driver;

    public CategoriesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css="[data-qa='headerCategoriesOpenBtnDesktop']")
    public WebElement headerButtonForCategories;

    @FindBy(css="[data-qa='headerCategoriesTreeTitleLink']")
    public WebElement allCategories;

    @FindBy(css=".cat-categories a")
    public List<WebElement> categoriesList;

    @FindBy(css="select[data-qa='searchResultPageContentSortingSelect']")
    public WebElement sortDrop;

    public void allCategoriesOpen ()
    {
        headerButtonForCategories.click();
        wait.until(ExpectedConditions.visibilityOf(allCategories));
        allCategories.click();
        wait.until(ExpectedConditions.urlContains("categories"));
    }

    public void openCategory(String categoryId)
        {
            for (int i = 0; i < categoriesList.size(); i++) {
                if (categoriesList.get(i).getAttribute("href").contains(categoryId))

                {
                    categoriesList.get(i).click();
                    break;
                }
            }
            wait.until(ExpectedConditions.urlContains(categoryId));

        }




}
