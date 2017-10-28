package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactDeletionTest extends  TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if( app.db().contacts().size()==0){
      app.contact().create(new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1"));

    }
  }

 @Test (enabled=true)
  public void testContactDeletion(){

   Contacts before=app.db().contacts();
   ContactData deletedContact=before.iterator().next();
   app.contact().delete(deletedContact);
   assertThat(app.contact().count(), equalTo(before.size()-1));

   Contacts after=app.db().contacts();

   assertThat(after, equalTo(before.without(deletedContact)));
   verifyContactListInUI();
 }


}
