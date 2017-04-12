package lecture02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task01 {

  private static String ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";
  private static String ADMIN_EMAIL = "webinar.test@gmail.com";
  private static String ADMIN_PASSWORD = "Xcg7299bnSmMuRLp9ITw";

  static WebDriver driver;

  public static void main(String[] args) throws InterruptedException {

    System.setProperty("webdriver.chrome.driver", "Y:\\Drivers\\chromedriver.exe");
    driver = new ChromeDriver();

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
