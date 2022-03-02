package testcases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoriesPage;
import utilities.DataUtil;

public class CategoriesTests extends BaseTest {

    @Test (dataProviderClass = DataUtil.class, dataProvider="dp")
    public void sortCategory(String categoryId)
        {
            CategoriesPage cp = new CategoriesPage(driver);
            cp.allCategoriesOpen();
            cp.openCategory(categoryId);
            Assert.assertTrue(cp.sortDrop.isDisplayed());
        }

}
