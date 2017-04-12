package assignment2.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import assignment2.BaseScript;

public class LoginTest extends BaseScript {

  static WebDriver driver = getDriver();

  public static void main(String[] args) throws InterruptedException {
    // TODO Script to execute login and logout steps
    driver.get(ADMIN_URL);
    logIn(ADMIN_EMAIL, ADMIN_PASSWORD);
    Thread.sleep(2000);
    logOut();
    Thread.sleep(2000);
    tearDown();
  }

  private static void logIn(String email, String pass) throws InterruptedException {
    WebElement emailField = driver.findElement(By.id("email"));
    emailField.sendKeys(email);
    WebElement passwordField = driver.findElement(By.id("passwd"));
    passwordField.sendKeys(pass);
    WebElement submitButton = driver.findElement(By.name("submitLogin"));
    submitButton.click();
  }

  private static void logOut() throws InterruptedException {
    WebElement userAvatar = driver.findElement(By.className("employee_avatar_small"));
    userAvatar.click();
    WebElement logoutLink = driver.findElement(By.id("header_logout"));
    logoutLink.click();
  }

  private static void tearDown() {
    driver.quit();
  }
}
