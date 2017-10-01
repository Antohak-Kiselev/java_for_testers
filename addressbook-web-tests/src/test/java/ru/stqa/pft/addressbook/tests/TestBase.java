package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
/**
 * Created by Антон on 21.09.2017.
 */
public class TestBase {


  protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }
  @AfterMethod
  public void tearDown() {
app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }
}



