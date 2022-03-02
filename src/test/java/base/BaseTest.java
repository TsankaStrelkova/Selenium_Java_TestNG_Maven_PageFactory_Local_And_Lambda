package base;


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;



import managers.DriverManager;
import managers.FileReaderManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import utilities.ExcelReader;



public class BaseTest {

    public  WebDriver driver;
    public  Logger log = Logger.getLogger(BaseTest.class.getName());
    public  ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
    public  WebDriverWait wait;



    @BeforeMethod
    public void browserInit(Method m) {

        PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");


        String testName = m.getName();
        log.info("BEFORE METHOD - Driver init for test:" + testName.toUpperCase());


        DriverManager driverManager = new DriverManager(m);
        try {
            driver = driverManager.getDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        log.info("Navigating to the URL : " + FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait()));
        wait = new WebDriverWait(driver, FileReaderManager.getInstance().getConfigReader().getExplicitWait());

    }


    @AfterMethod
    public void tearDown(Method m) {
        driver.quit();
        log.info("AFTER METHOD - Test Execution completed!!!"+m.getName().toUpperCase());
    }


}

