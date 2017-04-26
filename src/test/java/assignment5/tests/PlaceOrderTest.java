package assignment5.tests;

import org.testng.annotations.Test;

import assignment5.BaseTest;
import assignment5.model.ProductData;

public class PlaceOrderTest extends BaseTest {

  ProductData product;

//  @Test
//  public void checkSiteVersion() {
//    actions.openMainPage();
//
//    RemoteWebDriver remoteWebDriver = (RemoteWebDriver) driver;
//    DesiredCapabilities desiredCapabilities = (DesiredCapabilities) remoteWebDriver.getCapabilities();
//    String browserName = desiredCapabilities.getBrowserName().toLowerCase();
//    String operationSystem = desiredCapabilities.getPlatform().toString();
//    String versionOS = desiredCapabilities.getVersion().toString();
//    System.out.println("browserName - " + browserName +
//        "; operationSystem - " + operationSystem +
//        "; versionOS - " + versionOS);
//
//    System.out.println(browserName + " : " + browser);
//    Assert.assertEquals(browserName, browser);
//
//    if (isMobileTesting) {
//      System.out.println("mobile");
//      List<WebElement> checkElementInMobileVersion = driver.findElements(By.cssSelector("#_mobile_cart > div"));
//      System.out.println("checkElementInMobileVersion " + checkElementInMobileVersion.size());
//      Assert.assertTrue(checkElementInMobileVersion.size() > 0, "Mobile version is enabled, but desktop version site is show.");
//    } else {
//      System.out.println("Desktop");
//      List<WebElement> checkElementInDesktopVersion = driver.findElements(By.cssSelector("#_desktop_cart > div"));
//      System.out.println("checkElementInDesktopVersion " + checkElementInDesktopVersion.size());
//      Assert.assertTrue(checkElementInDesktopVersion.size() > 0, "Desktop version is enabled, but mobile version site is show.");
//    }
//  }

  @Test
  public void createNewOrder() {
    actions.openMainPage();
    // open random product
    actions.openRandomProduct();
    // save product parameters
    product = actions.getOpenedProductInfo();

    // add product to Cart and validate product information in the Cart
    actions.addProductToCart();
    actions.validateProductInformation(product);

    // proceed to order creation, fill required information
    actions.proceedOrder();

    // place new order and validate order summary

    // check updated In Stock value
  }

}
