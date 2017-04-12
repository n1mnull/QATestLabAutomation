package assignment2.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import assignment2.BaseScript;

public class CheckMainMenuTest extends BaseScript {

  static WebDriver driver = getDriver();

  public static void main(String[] args) throws InterruptedException {
    // TODO Script to check Main Menu items
    driver.get(ADMIN_URL);
    logIn(ADMIN_EMAIL, ADMIN_PASSWORD);
    Thread.sleep(2000);
    clickMainMenuElement();
    tearDown();
    Thread.sleep(2000);
  }

  private static void logIn(String email, String pass) throws InterruptedException {
    WebElement emailField = driver.findElement(By.id("email"));
    emailField.sendKeys(email);
    WebElement passwordField = driver.findElement(By.id("passwd"));
    passwordField.sendKeys(pass);
    WebElement submitButton = driver.findElement(By.name("submitLogin"));
    submitButton.click();
  }

  private static void clickMainMenuElement() {
    List<WebElement> mainMenuElements = driver.findElements(By.className("maintab"));
    for (int i = 0; i < mainMenuElements.size(); i++) {
      System.out.println("Opening page \"" + mainMenuElements.get(i).getText() + "\" is " + (i + 1) + " from " + mainMenuElements.size());
      mainMenuElements.get(i).click();
      String checkTitle = driver.getTitle();
      driver.navigate().refresh();
      String actualTitle = driver.getTitle();
      System.out.println("\"" + checkTitle + "\" compare with \"" + actualTitle + "\" is " + checkTitle.equals(actualTitle));
      driver.navigate().to(ADMIN_URL);
      mainMenuElements = driver.findElements(By.className("maintab"));
    }
  }

  private static void tearDown() {
    driver.quit();
  }
}
