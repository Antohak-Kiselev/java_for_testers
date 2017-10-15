package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {


  @Test (enabled=true)
  public void testContactCreation() {
    app.goTo().HomePage();
   Contacts before = app.contact().all();
    ContactData contact = new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
            withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1");
    app.contact().create(contact);
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() + 1);



    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }


}
