package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src/test/resources/properties/Config.properties";

    public ConfigFileReader() {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();

            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("configurations.properties file not found at " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");

        if (driverPath != null) return driverPath;
        else
            throw new RuntimeException("Driver Path not specified in the configurations.properties file");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicit.wait");

        if (implicitlyWait != null) {
            try {
                return Long.parseLong(implicitlyWait);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Not able to parse value: " + implicitlyWait + "into Long");
            }
        }
        return 10;
    }

    public long getExplicitWait()
    {
        String explicitWait = properties.getProperty("explicit.wait");

        if (explicitWait != null) {
            try {
                return Long.parseLong(explicitWait);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Not able to parse value: " + explicitWait+ "into Long");
            }
        }
        return 5;
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("testsiteurl");

        if (url != null) return url;
        else
            throw new RuntimeException("url not specified in the configurations.properties file");
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");

        if (browserName == null || browserName.equalsIgnoreCase("chrome")) return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else if (browserName.equalsIgnoreCase("ie")) return DriverType.INTERNETEXPLORER;
        else throw new RuntimeException("Browser Name key in configurations.properties file is not matched: " + browserName);

    }

    public EnvironmentType getEnvironment() {
        String envName = properties.getProperty("environment");

        if (envName == null || envName.equalsIgnoreCase("local")) return EnvironmentType.LOCAL;
        else if (envName.equalsIgnoreCase("remote")) return EnvironmentType.REMOTE;
        else throw new RuntimeException("Env type key value in configurations.properties is not matched: " + envName);
    }

    public Boolean getBrowserWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");

        if (windowSize != null)
            return Boolean.valueOf(windowSize);
        return true;
    }

    public String getUser() {
        String userName = properties.getProperty("LambdaTest_UserName");

        if (userName != null) return userName;
        else
            throw new RuntimeException("User name is not specified in the configurations.properties file");
    }

    public String getKey() {
        String key = properties.getProperty("LambdaTest_AppKey");

        if (key != null) return key;
        else
            throw new RuntimeException("App key not specified in the configurations.properties file");
    }

    public String getName() {
        String name = properties.getProperty("name");

        if (name != null) return name;
        else
            throw new RuntimeException("Name not specified in the configurations.properties file");
    }

    public String getBuild() {
        String build = properties.getProperty("build");

        if (build != null) return build;
        else
            throw new RuntimeException("Build not specified in the configurations.properties file");
    }

    public String getResolution() {
        String resolution = properties.getProperty("resolution");

        if (resolution != null) return resolution;
        else
            throw new RuntimeException("Resolution not specified in the configurations.properties file");
    }
}