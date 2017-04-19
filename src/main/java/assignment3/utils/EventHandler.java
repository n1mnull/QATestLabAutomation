package assignment3.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventHandler implements WebDriverEventListener {

  public void beforeAlertAccept(WebDriver driver) {
  }

  public void afterAlertAccept(WebDriver driver) {
  }

  public void afterAlertDismiss(WebDriver driver) {
  }

  public void beforeAlertDismiss(WebDriver driver) {
  }

  public void beforeNavigateTo(String path, WebDriver driver) {
    System.out.println("Open url " + path);
  }

  public void afterNavigateTo(String path, WebDriver driver) {
  }

  public void beforeNavigateBack(WebDriver driver) {
  }

  public void afterNavigateBack(WebDriver driver) {
  }

  public void beforeNavigateForward(WebDriver driver) {
  }

  public void afterNavigateForward(WebDriver driver) {
  }

  public void beforeNavigateRefresh(WebDriver driver) {
  }

  public void afterNavigateRefresh(WebDriver driver) {
  }

  public void beforeFindBy(By by, WebElement webElement, WebDriver driver) {
    System.out.println("Search element \"" + by + "\"");
  }

  public void afterFindBy(By by, WebElement webElement, WebDriver driver) {
    System.out.println("Element \"" + by + "\" found");
  }

  public void beforeClickOn(WebElement webElement, WebDriver driver) {
    if (webElement.getText().equals("")) {
      System.out.println("Should click \"" + webElement.getTagName()+ "\"");
    } else {
      System.out.println("Should click \"" + webElement.getText() + "\"");
    }
  }

  public void afterClickOn(WebElement webElement, WebDriver driver) {
    System.out.println("Click to element is successful");
  }

  public void beforeChangeValueOf(WebElement webElement, WebDriver driver, CharSequence[] charSequences) {
  }

  public void afterChangeValueOf(WebElement webElement, WebDriver driver, CharSequence[] charSequences) {
  }

  public void beforeScript(String message, WebDriver driver) {
  }

  public void afterScript(String message, WebDriver driver) {
  }

  public void onException(Throwable e, WebDriver driver) {
  }
}