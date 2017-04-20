package assignment3;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import assignment3.utils.Properties;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
  private WebDriver driver;
  private WebDriverWait wait;
  private By catalogueLink = By.cssSelector("#subtab-AdminCatalog");
  private By categoriesLink = By.cssSelector("#subtab-AdminCategories");
  private By addLink = By.cssSelector("#page-header-desc-category-new_category");
  private By nameField = By.cssSelector("#name_1");
  private By saveButton = By.cssSelector("#category_form_submit_btn");
  private By sortField = By.cssSelector("[name='categoryFilter_name']");
  private By sortButton = By.cssSelector("#submitFilterButtoncategory");

  public GeneralActions(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 30);
  }

  /**
   * Logs in to Admin Panel.
   */
  public void login(String login, String password) {
    driver.navigate().to(Properties.getBaseAdminUrl());
    driver.findElement(By.cssSelector("#email")).sendKeys(login);
    driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
    driver.findElement(By.cssSelector("#login_form button[name='submitLogin']")).click();
  }

  /**
   * Adds new category in Admin Panel.
   */
  public void createCategory(String categoryName) {
    waitForContentLoad(catalogueLink);
    WebElement catalogLink = driver.findElement(this.catalogueLink);
    WebElement categoriesLink = driver.findElement(this.categoriesLink);

    Actions actions = new Actions(driver);
    actions.moveToElement(catalogLink)
//        .moveToElement(categoriesLink)
        .click(categoriesLink).build().perform();
    driver.findElement(this.addLink).click();
    driver.findElement(this.nameField).sendKeys(categoryName);
    driver.findElement(this.saveButton).click();

    try {
      driver.findElement(By.xpath("//div[@class='alert alert-success']"));
      System.out.println(categoryName + " is create in Categories");
    } catch (NoSuchElementException e) {
      System.out.println(categoryName + " isn`t create in Categories");
    }
  }

  public void checkCategory(String categoryName) {
    waitForContentLoad(sortField);
    driver.findElement(this.sortField).sendKeys(categoryName);
    driver.findElement(this.sortButton).click();
    try {
      driver.findElement(By.className("list-empty"));
      System.out.println(categoryName + " isn`t exist in Categories");
    } catch (NoSuchElementException e) {
      System.out.println(categoryName + " is exist in Categories");
    }
  }

  /**
   * Waits until page loader disappears from the page
   */
  public void waitForContentLoad(By by) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
  }

}
