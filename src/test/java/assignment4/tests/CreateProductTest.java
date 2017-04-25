package assignment4.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import assignment4.BaseTest;
import assignment4.model.ProductData;

public class CreateProductTest extends BaseTest {

  ProductData product = ProductData.generate();

  @DataProvider
  public Object[][] dataProvider() {
    return new Object[][]{
        {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw", product}
    };
  }

  @Test(dataProvider = "dataProvider")
  public void createNewProduct(String login, String password, ProductData product) {
    actions.login(login, password);
    actions.createProduct(product);
//    Assert.assertTrue(driver.findElement(By.xpath("//div[@class='growl-message']"))
//            .getText().contains("Настройки обновлены."),
//        product.getName() + " isn`t saved in Products");
  }

  @Test(dependsOnMethods = "createNewProduct", dataProvider = "dataProvider")
  public void checkExistProduct(String login, String password, ProductData product) {
    actions.checkExistProduct(product);
  }
}
