package assignment5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import assignment5.utils.DriverFactory;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest {
  protected WebDriver driver;
  protected GeneralActions actions;
  protected boolean isMobileTesting;
  protected String browser;

  /**
   * Prepares {@link WebDriver} instance with timeout and browser window configurations.
   *
   * Driver type is based on passed parameters to the automation project,
   * creates {@link ChromeDriver} instance by default.
   */
  @BeforeClass
  @Parameters({"selenium.browser", "selenium.grid"})
  public void setUp(@Optional("chrome") String browser, @Optional("http://localhost:4444/wd/hub") String gridUrl) {
    this.browser = browser;
//    driver = DriverFactory.initDriver(browser);
    driver = DriverFactory.initDriver(browser, gridUrl);
//    driver = new EventFiringWebDriver(DriverFactory.initDriver(browser, gridUrl));
//    driver.register(new EventHandler());
    if (browser.equals("chrome")) {
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }
    isMobileTesting = isMobileTesting(browser);
//    if (!isMobileTesting)
//      driver.manage().window().maximize();
    actions = new GeneralActions(driver);
  }

  /**
   * Closes driver instance after test class execution.
   */
  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  /**
   * @return Whether required browser displays content in mobile mode.
   */
  private boolean isMobileTesting(String browser) {
    switch (browser) {
      case "android":
        return true;
      case "firefox":
      case "ie":
      case "internet explorer":
      case "chrome":
      case "phantomjs":
      default:
        return false;
    }
  }
}
