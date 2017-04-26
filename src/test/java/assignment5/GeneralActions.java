package assignment5;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

import assignment5.model.ProductData;
import assignment5.utils.Properties;
import assignment5.utils.logging.CustomReporter;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
  private WebDriver driver;
  private WebDriverWait wait;

  private By allProductsLink = By.xpath("//a[@class='all-product-link pull-xs-left pull-md-right h4']");

  public GeneralActions(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 30);
  }

  public void openMainPage() {
    driver.navigate().to(Properties.getBaseUrl());
    waitForContentLoad(By.id("main"));
  }

  public void openRandomProduct() {
    CustomReporter.log("Start method openRandomProduct");
    waitForContentLoad(this.allProductsLink);
    driver.findElement(allProductsLink).click();

    List<WebElement> listProducts = driver.findElements(By.xpath("//h1[@class='h3 product-title']"));
    Random random = (new Random());
    int randomInt = random.nextInt(listProducts.size());
    System.out.println(randomInt + " " + listProducts.get(randomInt).getText());
    listProducts.get(randomInt).click();
    waitForContentLoad(By.id("product"));
  }

  /**
   * Extracts product information from opened product details page.
   */
  public ProductData getOpenedProductInfo() {
    CustomReporter.logAction("Get information about currently opened product");
    String name = driver.findElement(By.xpath("//h1[@itemprop='name']")).getText();
    int qty = getQtyProduct(driver.getCurrentUrl());
    String priceStr = driver.findElement(By.xpath("//div[@class='current-price']")).getText().replace(',', '.');
    float price = Float.valueOf(priceStr.substring(0, priceStr.indexOf(" ")));
    System.out.println(name + " " + qty + " " + price);
    ProductData product = new ProductData(name,qty,price);
    return product;
  }

  public int getQtyProduct(String url) {
//    driver.get(url);
    driver.findElement(By.xpath("//a[text()='Подробнее о товаре']")).click();
    waitForContentLoad(By.cssSelector("#product-details > div.product-quantities > span"));
    String qtyStr = driver.findElement(By.cssSelector("#product-details > div.product-quantities > span")).getText();
    return Integer.parseInt(qtyStr.substring(0,qtyStr.indexOf(" ")));
  }

  public void addProductToCart() {
    driver.findElement(By.className("add-to-cart")).click();
    CustomReporter.logAction("Product add to cart");
    waitForContentLoad(By.id("myModalLabel"));
    Assert.assertTrue(driver.findElement(By.cssSelector("h4.modal-title")).getText().contains("Товар добавлен в корзину"));
    driver.findElement(By.cssSelector("div.cart-content > a")).click();
    waitForContentLoad(By.xpath("//h1[@class='h1']"));
  }

  public void validateProductInformation(ProductData product) {
    String name = driver.findElement(By.cssSelector("div.product-line-info > a")).getText();
    System.out.println(name);
    Assert.assertTrue(name.toUpperCase().equals(product.getName()),"Other product in the Cart");

    String priceStr = driver.findElement(By.cssSelector("div.cart-summary-line.cart-total > span.value")).getText();
    System.out.println(priceStr);
    float price = Float.valueOf(priceStr.substring(0, priceStr.indexOf(" ")).replace(',', '.'));
    System.out.println(price);
    Assert.assertTrue(price == product.getPrice(), "Price is not equals with Product");

    String qty = driver.findElement(By.cssSelector("#cart-subtotal-products > span.label.js-subtotal")).getText();
    System.out.println(qty);
    Assert.assertTrue(qty.equals("1 шт."));
  }

  public void proceedOrder() {
    driver.findElement(By.className("checkout cart-detailed-actions card-block"));
    waitForContentLoad(By.className("step-title h3"));
    driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Ivan");
    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Poddubny");
    Random random = (new Random());

    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(Keys.BACK_SPACE);
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys("email" + random.nextInt(100) + "@gmail.com");
    driver.findElement(By.name("continue")).click();

    driver.findElement(By.name("address1")).sendKeys("str. Pobedi, 111");
    driver.findElement(By.name("postcode")).sendKeys("69000");
    driver.findElement(By.name("city")).sendKeys("Zaporizhzhya");
    driver.findElement(By.name("confirm-addresses")).click();

    driver.findElement(By.name("confirmDeliveryOption")).click();

    driver.findElement(By.id("payment-option-2")).click();
    driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
    driver.findElement(By.cssSelector("#payment-confirmation button[type=submit]")).click();


  }

  /**
   * Waits until page loader disappears from the page
   */
  public void waitForContentLoad(By by) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

}
