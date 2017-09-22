package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactDeletionTest extends  TestBase {

 @Test
  public void testContactDeletion(){
   app.getContactHelper().setContact();
   app.getContactHelper().deleteSelectedContact();
   app.getContactHelper().returnToHomePage();

  }
}
