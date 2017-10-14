package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactDeletionTest extends  TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("erer", "sdfdsf", "sdfdsf", "78787", "sdfsadf@dsf", "test1"));

    }
  }

 @Test (enabled=true)
  public void testContactDeletion(){

   List<ContactData> before=app.contact().list();
   int index=before.size()-1;
   int idDeletedContact=before.get(before.size()-1).getId();
   app.contact().delete(index, idDeletedContact);

   List<ContactData> after=app.contact().list();
   Assert.assertEquals(after.size(),before.size()-1);

   before.remove(index);
   Assert.assertEquals(before,after);
 }


}
