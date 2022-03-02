package managers;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import utilities.DriverType;
import utilities.EnvironmentType;

public class DriverManager {

    private WebDriver driver;
    private String testName;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public DriverManager(Method m) {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
        testName = m.getName();
    }

    public WebDriver getDriver() throws MalformedURLException {
        if (driver == null)
            driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() throws MalformedURLException {
        switch (environmentType) {
            case LOCAL:
                driver =  createLocalDriver();
                System.out.println("Create local driver");
                break;
            case REMOTE:
                driver =  createRemoteDriver();
                System.out.println("Create remote driver");
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() throws MalformedURLException {


        String res = FileReaderManager.getInstance().getConfigReader().getResolution();
        String build = FileReaderManager.getInstance().getConfigReader().getBuild();
        String username = FileReaderManager.getInstance().getConfigReader().getUser();
        String accesskey = FileReaderManager.getInstance().getConfigReader().getKey();



        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        options.setCapability("build", build);
        options.setCapability("name", testName);
        options.setCapability("platformName", "Windows 10");
        options.setCapability("browserName", "Chrome");
        options.setCapability("browserVersion", "latest");

        try {
            driver = new RemoteWebDriver(new URL("http://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub"), ((Capabilities) options));
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        }


        return driver;
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case INTERNETEXPLORER:
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                //System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) {
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}