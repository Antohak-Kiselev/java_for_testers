package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by Антон on 22.09.2017.
 */
public class ApplicationManager {
  FirefoxDriver wd;

  private NavigationHelper navigationHelper;
  private  GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;



  public void init() {
    System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    contactHelper=new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper= new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }
  public void stop() {
    wd.quit();
  }
  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }


  public ContactHelper getContactHelper() {
    return contactHelper;
  }
  }