package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration() {
    app.registration().start("user12332", "user12323@mail.ru");

  }
}