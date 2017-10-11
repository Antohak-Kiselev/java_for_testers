package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("erer", "sdfdsf", "sdfdsf", "78787", "sdfsadf@dsf", "test1"));

    }
    List<ContactData> before=app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);

    ContactData contact=new ContactData(before.get(before.size()-1).getId(),"testfn_mod", "testln_mod", "testad_mod", "91799992", "test@test.test", "null");
    app.getContactHelper().initContactModificationById((before.get(before.size()-1).getId()));
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitModificationContact();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after=app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());


    before.remove(before.size()-1);
    before.add(contact);
    Comparator<? super ContactData> ById=(c1,c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);
    System.out.println(app.getContactHelper().getIdContactWithHelpIndex(before.size()-1));
  }
}
