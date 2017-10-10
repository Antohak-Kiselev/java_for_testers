package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactDeletionTest extends  TestBase {

 @Test
  public void testContactDeletion(){
app.getNavigationHelper().gotoHomePage();
  if (!app.getContactHelper().isThereAContact()){
   app.getContactHelper().createContact(new ContactData("erer","sdfdsf","sdfdsf","78787","sdfsadf@dsf","test1"));

  }
   List<ContactData> before=app.getContactHelper().getContactList();
   app.getContactHelper().selectContact(before.size()-1);
   app.getContactHelper().initContactModification();
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().returnToHomePage();

   List<ContactData> after=app.getContactHelper().getContactList();
   Assert.assertEquals(after.size(),before.size()-1);

 }
}
