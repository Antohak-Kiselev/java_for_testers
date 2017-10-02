package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("erer", "sdfdsf", "sdfdsf", "78787", "sdfsadf@dsf", "test1"));

    }
    app.getContactHelper().setContact();
    app.getContactHelper().fillContactForm(new ContactData("testfn_mod", "testln_mod", "testad_mod", "91799992", "test@test.test", "null"), false);
    app.getContactHelper().submitModificationContact();
    app.getNavigationHelper().gotoHomePage();
  }
}
