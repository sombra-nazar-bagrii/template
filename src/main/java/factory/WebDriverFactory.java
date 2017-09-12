package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Created by sombra17 on 30.01.17.
 */

public class WebDriverFactory {

    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";

    private static WebDriver webDriver;
    private static DesiredCapabilities dc;

    private WebDriverFactory() {
    }

    /**
     * Gets the single instance of WebDriverFactory.
     *
     * @param browserName the browser set in properties
     * @return single instance of WebDriverFactory
     * @throws Exception the exception of invalid browser property
     */

    public static WebDriver getInstance(String browserName) throws Exception {
        if (webDriver == null) {
            if (CHROME.equalsIgnoreCase(browserName)) {

                setChromeDriver();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("test-type");
                dc = DesiredCapabilities.chrome();
                dc.setCapability(ChromeOptions.CAPABILITY, options);
                webDriver = new ChromeDriver(dc);

            } else if (FIREFOX.equalsIgnoreCase(browserName)) {

                FirefoxProfile fp = new FirefoxProfile();
                dc = DesiredCapabilities.firefox();
                dc.setCapability("marionette", false);
                dc.setCapability(FirefoxDriver.PROFILE, fp);

                webDriver = new FirefoxDriver(dc);

            } else
                throw new IllegalAccessException("Invalid browser property set in configuration file");

            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    /**
     * Kill driver instance.
     * @throws Exception
     */

    public static void killDriverInstance() throws Exception {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    /**
     * Sets the chrome driver path for specific OS.
     *
     * @throws Exception the exception
     */

    private static void setChromeDriver() throws Exception {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuffer chromeBinaryPath = new StringBuffer(
                "src/main/resources/drivers/");
        if (osName.startsWith("win")) {
            chromeBinaryPath.append("chromedriver.exe");
        } else if (osName.startsWith("lin")) {
            chromeBinaryPath.append("chromedriver");
        } else if (osName.startsWith("mac")) {
            chromeBinaryPath.append("chromedriver");
        } else
            throw new Exception("Your OS is invalid for webdriver tests");
        System.setProperty("webdriver.chrome.driver",
                chromeBinaryPath.toString());
    }
/*
    private static void setFirefoxDriver() throws Exception {

        String osName = System.getProperty("os.name").toLowerCase();
        StringBuffer firefoxBinaryPath = new StringBuffer(
                "src/main/resources/drivers/gecko/");
        if (osName.startsWith("win")) {
            firefoxBinaryPath.append("gecko-win/geckodriver.exe");
        } else if (osName.startsWith("lin")) {
            firefoxBinaryPath.append("gecko-lin/geckodriver");
        } else if (osName.startsWith("mac")) {
            firefoxBinaryPath.append("gecko-mac/geckodriver");
        } else
            throw new Exception("Your OS is invalid for webdriver tests");
        System.setProperty("webdriver.gecko.driver",
                firefoxBinaryPath.toString());
    }
*/
}
