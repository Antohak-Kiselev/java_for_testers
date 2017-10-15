package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1"));

    }
     }


  @Test  (enabled=true)
  public void testContactModification() {


    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact=before.iterator().next();
    ContactData contact=new ContactData ().withId(modifiedContact.getId()).withFirstName("test_First_Name").withLastName("test_Last_name").
            withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1");

    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(),before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before,after);

  }


}
