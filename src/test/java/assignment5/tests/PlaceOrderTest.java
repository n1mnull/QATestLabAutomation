package assignment5.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import assignment5.BaseTest;
import assignment5.model.ProductData;

public class PlaceOrderTest extends BaseTest {

  ProductData product;

  @Test
  public void checkSiteVersion() {
    actions.openMainPage();

    RemoteWebDriver remoteWebDriver = (RemoteWebDriver) driver;
    DesiredCapabilities desiredCapabilities = (DesiredCapabilities) remoteWebDriver.getCapabilities();
    String browserName = desiredCapabilities.getBrowserName().toLowerCase();
    String operationSystem = desiredCapabilities.getPlatform().toString();
    String versionOS = desiredCapabilities.getVersion().toString();
    System.out.println("browserName - " + browserName +
        "; operationSystem - " + operationSystem +
        "; versionOS - " + versionOS);

    Assert.assertEquals(browserName, browser);

    if (isMobileTesting) {
      List<WebElement> checkElementInMobileVersion = driver.findElements(By.cssSelector("#_mobile_cart > div"));
      Assert.assertTrue(checkElementInMobileVersion.size() > 0, "Mobile version is enabled, but desktop version site is show.");
    } else {
      List<WebElement> checkElementInDesktopVersion = driver.findElements(By.cssSelector("#_desktop_cart > div"));
      Assert.assertTrue(checkElementInDesktopVersion.size() > 0, "Desktop version is enabled, but mobile version site is show.");
    }
  }

  @Test
  public void createNewOrder() {

    actions.openMainPage();
    // open random product + save url opened product
    String url = actions.openRandomProduct();
    // save product parameters
    product = actions.getOpenedProductInfo();
    // add product to Cart and validate product information in the Cart
    actions.addProductToCart();
    //
    actions.validateProductInformation(product);
    // proceed to order creation, fill required information
    actions.proceedOrder();
    // place new order and validate order summary
    actions.validateOrderSummary(product);
    // check updated In Stock value
    actions.checkDecrementStock(url, product);
  }

}
