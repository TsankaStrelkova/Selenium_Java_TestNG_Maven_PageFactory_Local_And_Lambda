package testcases;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.DataUtil;

public class LoginTests extends BaseTest {

    @Test(dataProviderClass= DataUtil.class, dataProvider ="dp")
    public void doLogin(String userEmail, String userPassword)
    {
      LoginPage lp = new LoginPage(driver);
      lp.clickOnNotLoggedUserButton();
      wait.until(ExpectedConditions.visibilityOf(lp.emailField));
      lp.submitCredentials(userEmail,userPassword);
      Assert.assertTrue(lp.loggedUser.isDisplayed());
    }

}
