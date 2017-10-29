package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Антон on 22.09.2017.
 */
public class NavigationHelper extends  HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {

    click(By.linkText("groups"));

    if (isElementPresent(By.tagName("h1")) && wd.findElement(By.tagName("h1")).getText().equals("Groups") &&
            isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }
  public void HomePage(){
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }
  public void pageSelectedGroup(int id) {
        if (isElementPresent(By.id("maintable"))) {
            return;
          }
        click(By.cssSelector("a[href='./?group=" + id + "']"));
      }
  public void addContactPage() {

    if (isElementPresent ( By.tagName ( "h1" ) )
            && wd.findElement ( By.tagName ( "h1" ) ).getText ().equals ( "Edit / add address book entry" )
            && isElementPresent ( By.name ( "submit" ) )) {
      return;
    }
    click ( By.linkText ( "add new" ) );
  }
}