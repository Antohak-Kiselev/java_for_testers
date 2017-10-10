package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends  TestBase {

    
    @Test
    public void ContactCreationTests() {
        app.getNavigationHelper().gotoHomePage();
     int before=app.getContactHelper().getContactCount();
        app.getContactHelper().createContact(new ContactData("test_first_name", "test_last_name", "test_adress", "891782324", "test_first_name.test_last_name@mail.ru", "test1"));
        int after=app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before+1);

    }




}
