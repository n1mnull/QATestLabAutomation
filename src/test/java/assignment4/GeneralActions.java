package assignment4;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import assignment4.model.ProductData;
import assignment4.utils.Properties;
import assignment4.utils.logging.CustomReporter;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
  private WebDriver driver;
  private WebDriverWait wait;

  private By catalogueLink = By.cssSelector("#subtab-AdminCatalog");
  private By productsLink = By.cssSelector("#subtab-AdminProducts");
  private By addProductButton = By.id("page-header-desc-configuration-add");
  private By nameField = By.xpath("//input[@id='form_step1_name_1']");
  private By qtyField = By.id("form_step1_qty_0_shortcut");
  private By priceField = By.id("form_step1_price_ttc_shortcut");
  private By activationButton = By.className("switch-input");
  //  private By noticeText = By.xpath("//div[@class='growl-message']");
  private By saveButton = By.id("submit");
//  private By saveButton = By.xpath("//button[@class='btn btn-primary js-btn-save']");

  private By allProductsLink = By.xpath("//a[@class='all-product-link pull-xs-left pull-md-right h4']");


  public GeneralActions(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 30);
  }

  /**
   * Logs in to Admin Panel.
   */
  public void login(String login, String password) {
    CustomReporter.log("Login as user - " + login + "<br>");
    driver.navigate().to(Properties.getBaseAdminUrl());
    driver.findElement(By.cssSelector("#email")).sendKeys(login);
    driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
    driver.findElement(By.cssSelector("#login_form button[name='submitLogin']")).click();
  }


  public void createProduct(ProductData newProduct) {
    CustomReporter.log("Start method createProduct to save Product - " + newProduct.getName());
    waitForContentLoad(catalogueLink);
    WebElement catalogLink = driver.findElement(this.catalogueLink);
    WebElement productsLink = driver.findElement(this.productsLink);
    Actions actions = new Actions(driver);
    actions.moveToElement(catalogLink).click(productsLink).build().perform();

    waitForContentLoad(this.addProductButton);
    driver.findElement(this.addProductButton).click();

    waitForContentLoad(this.nameField);
    driver.findElement(this.nameField).sendKeys(newProduct.getName());
    driver.findElement(this.qtyField).sendKeys(newProduct.getQty().toString());
    driver.findElement(this.priceField).sendKeys(newProduct.getPrice());

    waitForContentLoad(this.activationButton);
    driver.findElement(this.activationButton).click();

    waitForContentLoad(this.saveButton);
    driver.findElement(this.saveButton).click();

    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='growl-message']"))
                .getText().contains("Настройки обновлены."),
        newProduct.getName() + " isn`t saved in Products");
  }

  public void checkExistProduct(ProductData product) {
    CustomReporter.log("Start method checkProduct to check Product - " + product.getName());
    driver.navigate().to(Properties.getBaseUrl());
//    JavascriptExecutor jse = (JavascriptExecutor)driver;
//    jse.executeScript("window.scrollBy(0,500)","");
    waitForContentLoad(this.allProductsLink);
    driver.findElement(allProductsLink).click();

    waitForContentLoad(By.xpath("//div[@id='js-product-list-top']"));
    List<WebElement> listPages = driver
        .findElement(By.xpath("//ul[@class='page-list clearfix text-xs-center']"))
        .findElements(By.className("js-search-link"));

    List<WebElement> listProducts;
    WebElement checkProduct = null;

    for (int i = 1; i < listPages.size() - 1; i++) {
      listPages = driver
          .findElement(By.xpath("//ul[@class='page-list clearfix text-xs-center']"))
          .findElements(By.className("js-search-link"));
      listProducts = driver.findElements(By.xpath("//h1[@class='h3 product-title']"));
      System.out.println(listProducts.size());

      for (int j = 0; j < listProducts.size(); j++) {
        System.out.println(listProducts.get(j).getText() + " - " + product.getName());
        if (product.getName().equals(listProducts.get(j).getText())) {
          checkProduct = listProducts.get(j);
          break;
        }
      }
      if (checkProduct == null) {
        listPages.get(i + 1).click();
      }
//      waitForContentLoad(By.xpath("//div[@id='js-product-list-top']"));
//      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("js-product-list-top")));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    Assert.assertFalse(checkProduct == null, "Element isn`t find in Products");

    checkProduct.click();
    waitForContentLoad(By.xpath("//div[@class='product-information']"));

    String checkName = driver.findElement(By.xpath("//*[@id='wrapper']/div/nav/ol/li[2]/a/span")).getText();
    System.out.println(checkName + " compare with " + product.getName());
    Assert.assertEquals(checkName, product.getName(), "Product has another name");

    String checkPrice = driver.findElement(By.xpath("//div[@class='current-price']")).getText();
    checkPrice = checkPrice.substring(0, checkPrice.indexOf(" "));
    System.out.println(checkPrice + " compare with " + product.getPrice());
    Assert.assertEquals(checkPrice, product.getPrice(), "Product has another price");

    String checkQty = driver.findElement(By.cssSelector("#product-details > div.product-quantities > span")).getText();
    checkQty = checkQty.substring(0,checkQty.indexOf(" "));
    System.out.println(checkQty + " compare with " + product.getQty());
    Assert.assertEquals(checkQty, product.getQty().toString(), "Product has another qty");
  }

  /**
   * Waits until page loader disappears from the page
   */
  public void waitForContentLoad(By by) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

}
