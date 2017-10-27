package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
    app.goTo().HomePage();
    File photo=new File("src/test/resources/stru.png");

    if( app.db().contacts().size()==0){
      app.contact().create(new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1").
              withPhoto(photo));

    }
     }


  @Test  (enabled=true)
  public void testContactModification() {

    app.goTo().HomePage();
    Contacts before=app.db().contacts();
    ContactData modifiedContact=before.iterator().next();
    File photo=new File("src/test/resources/stru.png");
    ContactData contact=new ContactData ().withId(modifiedContact.getId()).withFirstName("test_First_Name").withLastName("test_Last_name").
            withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1").withPhoto(photo);;
    app.goTo().HomePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after=app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact.withPhoto(photo)).withAdded(contact)));

  }


}
