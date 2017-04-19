package assignment3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import assignment3.utils.EventHandler;
import assignment3.utils.Properties;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

  /**
   * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters to
   * the automation project, returns {@link ChromeDriver} instance by default.
   */
  public static WebDriver getDriver() {
    String browser = Properties.getBrowser();
    switch (browser) {
      case "chrome":
        System.setProperty("webdriver.chrome.driver",
            new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
        return new ChromeDriver();
      case "firefox":
        System.setProperty("webdriver.gecko.driver",
            new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
        return new FirefoxDriver();
      case "iexployer":
        System.setProperty("webdriver.ie.driver",
            new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
        return new InternetExplorerDriver();
      default:
        System.setProperty(
            "webdriver.chrome.driver",
            new File(BaseScript.class.getResource("/chromedriver.exe").getFile()).getPath());
        return new ChromeDriver();
    }
  }

  /**
   * Creates {@link WebDriver} instance with timeout and browser window configurations.
   *
   * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed
   * parameters to the automation project, returns {@link ChromeDriver} instance by default.
   */
  public static EventFiringWebDriver getConfiguredDriver() {
    WebDriver driver = getDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
    eventFiringWebDriver.register(new EventHandler());
    return eventFiringWebDriver;
  }
}
