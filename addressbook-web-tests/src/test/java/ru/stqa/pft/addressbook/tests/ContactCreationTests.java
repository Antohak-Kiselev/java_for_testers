package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test (enabled=true)
  public void testContactCreation() {
    app.goTo().HomePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("test_first_name", "test_last_name", "test_adress", "891782324", "test_first_name.test_last_name@mail.ru", "test1");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);





    before.add(contact);
    Comparator<? super ContactData> ById=(c1, c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);

  }


}
