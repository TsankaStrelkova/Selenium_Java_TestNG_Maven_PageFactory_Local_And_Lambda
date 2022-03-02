# Selenium_Java_TestNG_Maven_PageFactory_Local_And_Lambda


Framework – with Page Factory , TestNG, Maven, Extent Reports, Parallel test execution

https://www.udemy.com/course/selenium-training/learn/lecture/13477828#overview

https://github.com/TsankaStrelkova/Selenium_Java_TestNG_Maven_PageFactory_Local_And_Lambda/

1.	Create New maven project in Intelij
•	Don’t choose any model
•	Group ID– some common name of organization project Artifact ID – name of the project (For example: PageFactoryTestNGMavenRahulArora)
2.	Add in POM.xml dependencies taken from https://mvnrepository.com/
                
3.	In the \src\main\java create a package base
In this new package place the file related to base business logic – BasePage.java



4.	In the \src\main\java create a package pages
Here will be placed all pages . They will extent the BasePage

5.	In the \src\test package create package  resources ( \src\test\resources)
In this new package add several packages
-	excel   (here will be placed xlsx files with data)
-	logs     (here logs will be placed)
-	properties (here will be placed Config.properties and log4j.properties)
-	runner (here will be placed testing.xml)
 
6.	In src\test\java create the following new packages
-	base (here the BaseTest.java will be placed as hearth of the framework)
-	managers (here DriverManager.java and FileReader.java will be placed)
-	listeners (here ExtentListener.java and ExtentManager.java will be placed to implement extent reports)
-	testcases (all java classes with test cases will be placed here)
-	utilities (DataUtil.java some class helpful for dataprovider , ExcelReader.java )

7.	In the src/test/java/base  add BaseTest.java – it is a class to initialize driver

Here is very important to use driver that is not static.
It is needed because we are doing end-to-end test. Each test should have his own driver. In addition parallel execution is planned. 




8.	In src/test/java/managers add DriverManager.java and FileReader.java

9.	In src/test/java/listeners  add ExtentListener.java


10.	In src/test/java/listeners  add ExtentManager.java

11.	In src/test/java/utilities add DataUtil.java

Important: Here getData is designed to read data form a sheet that has the same name as the test . It is super important to remember it. 


12.	In src/test/java/utilities add ExcelReader.java

13.	In src/test/java/utilities add DriverType.java  
package utilities;

public enum DriverType {
    FIREFOX,
    CHROME,
    INTERNETEXPLORER
}



14.	In src/test/java/utilities add EnvironmentType.java  

package utilities;

public enum EnvironmentType {
    LOCAL,
    REMOTE
}

15.	In \src\test\resources – add excel package and place there the excel file form which you are going to read data. 

Each sheet of the excel file should be named as is named the test (method with @Test annotation) that uses data form the file 
            File is named testdata.xlsx

16.	In \src\test\resources – add properties package
Place in this package both files Config.properties and log4j.properties

Config.properties


implicit.wait=10
explicit.wait=5

driverPath=src/test/resources/drivers/chromedriver.exe
testsiteurl=https://www.dodax.ca/
os=WIN10
#this is environment. It can be local or remote
environment=local
browser=chrome
version=98.0
windowMaximize=true


#this is the name of the test suite that appears on the Dashboard of LambdaTest
build=Dodax Page Object1
#this is the name of each individual test in the test suite
name=Dodax UI Automation Tests
resolution=1024x768


#Lambdatest Credentials
LambdaTest_UserName=tsstrelkova21
LambdaTest_AppKey=nqcARfKa4QD0t0yjt6VLHEkOzcqMjYvykIJIUVcyukDbOsfE1n

log4j.properties

log4j.rootLogger=INFO, Appender
log4j.appender.Appender=org.apache.log4j.FileAppender
log4j.appender.Appender.File=src/test/resources/logs/app_log.txt
log4j.appender.Appender.layout=org.apache.log4j.PatternLayout
log4j.appender.Appender.layout.ConversionPattern=%-7p %d [%t] %c %x - %m%n
log4j.appender.Appender.Append=false
log4j.appender.file.maxFileSize=5000KB
log4j.appender.file.maxBackupIndex=3

17.	In \src\test\resources  - add package runner
Place in this package the testing.xml file 

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Dodax Tests">

   <listeners>
      <listener class-name="listeners.ExtentListener" />
   </listeners>

   <test  name="Home">
      <classes>
         <class name="testcases.HomePageTests" />
      </classes>
   </test> <!-- Test -->

   <test name="Login">
      <classes>
         <class name="testcases.LoginTests" />
      </classes>
   </test> <!-- Test -->

   <test  name="Categories">
      <classes>
         <class name="testcases.CategoriesTests" />
      </classes>
   </test> <!-- Test -->


</suite> <!-- Suite -->

18.	In \src\test\java\testcases package add your test cases – they should extend BaseTest
For example HomePageTests.java

For example LoginTest.java
Important for tests that are parametrized:
A)	After @Test annotation provide (dataProviderClass= DataUtil.class, dataProvider ="dp")
B)	In the method add parameters  
For example doLogin(String userEmail, String userPassword)
@Test(dataProviderClass= DataUtil.class, dataProvider ="dp")
public void doLogin(String userEmail, String userPassword)

C)	In \src\test\resources\excel\testdata.xlsx add a spreadsheet with the same name as the method of the test (For example: doLogin) and write there data for all parameters
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





