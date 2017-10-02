package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
   app.getContactHelper().setContact();
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().returnToHomePage();

  }
}
