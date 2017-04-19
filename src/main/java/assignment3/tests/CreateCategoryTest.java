package assignment3.tests;

import org.openqa.selenium.WebDriver;

import assignment3.BaseScript;
import assignment3.GeneralActions;

public class CreateCategoryTest extends BaseScript {

  private static String login = "webinar.test@gmail.com";
  private static String password = "Xcg7299bnSmMuRLp9ITw";
  private static String categoryName = "testCategoryName";

  public static void main(String[] args) throws InterruptedException {
    WebDriver driver = getConfiguredDriver();
    GeneralActions generalActions = new GeneralActions(driver);
    // login
    generalActions.login(login,password);
    // create category
    generalActions.createCategory(categoryName);
    // check that new category appears in Categories table
    generalActions.checkCategory(categoryName);
    // finish script
    driver.close();
  }
}


//    targetElement.findElement(By.cssSelector(".user-info"));
//    WebElement targetElement = driver.findElement(By.cssSelector("a[title='Войти в учетную запись']"));
//    WebElement targetElement = driver.findElement(By.cssSelector("#_desktop_user_info a"));
//    targetElement = driver.findElement(By.xpath("//a[@title='Войти в учетную запись']"));
