package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
import java.util.Set;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactDeletionTest extends  TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().all().size()==0) {
      app.contact().create(new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1"));

    }
  }

 @Test (enabled=true)
  public void testContactDeletion(){

   Set<ContactData> before = app.contact().all();
   ContactData deletedContact=before.iterator().next();
   app.contact().delete(deletedContact);

   Set<ContactData> after = app.contact().all();
   Assert.assertEquals(after.size(),before.size()-1);

   before.remove(deletedContact);
   Assert.assertEquals(before,after);
 }


}
