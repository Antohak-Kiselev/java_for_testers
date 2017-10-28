package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups=app.db().groups();
    app.goTo().HomePage();
    if( app.db().contacts().size()==0){


      app.contact().create(new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").inGroup(groups.iterator().next()));

    }
  }


  @Test  (enabled=true)
  public void testContactModification() {

    app.goTo().HomePage();
    Groups groups=app.db().groups();
    Contacts before=app.db().contacts();
    ContactData modifiedContact=before.iterator().next();
    File photo=new File("src/test/resources/stru.png");
    ContactData contact=new ContactData ().withId(modifiedContact.getId()).withFirstName("test_First_Name").withLastName("test_Last_name").
            withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withPhoto(photo).withHomePhone("").withWorkPhone("").withEmail2("").withEmail3("").inGroup(groups.iterator().next());
    app.goTo().HomePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after=app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact.withPhoto(photo)).withAdded(contact)));

    verifyContactListInUI();
  }



}
