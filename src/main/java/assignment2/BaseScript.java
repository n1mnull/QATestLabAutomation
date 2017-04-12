package assignment2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

  protected final static String ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
  protected final static String ADMIN_EMAIL = "webinar.test@gmail.com";
  protected final static String ADMIN_PASSWORD = "Xcg7299bnSmMuRLp9ITw";

  /**
   * @return New instance of {@link WebDriver} object.
   */
  public static WebDriver getDriver() {
//    String driverPath = System.getProperty("driver.executable");
//    if (driverPath == null) {
//      throw new UnsupportedOperationException("Method doesn't return WebDriver instance");
//    }

    String property = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
    System.setProperty("webdriver.chrome.driver", property);
    return new ChromeDriver();
  }
}
