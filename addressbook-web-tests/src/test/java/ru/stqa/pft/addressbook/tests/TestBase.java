package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

/**
 * Created by Антон on 21.09.2017.
 */
public class TestBase {


  protected final ApplicationManager app = new ApplicationManager();

  @BeforeMethod
  public void setUp() throws Exception {
    app.init();
  }


  public ApplicationManager getApp() {
    return app;
  }
}
