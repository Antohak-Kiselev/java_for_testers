package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData ().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1"));

    }
     }


  @Test  (enabled=true)
  public void testContactModification() {


    List<ContactData> before=app.contact().list();
    ContactData contact=new ContactData ().withId(before.get(before.size()-1).getId()).withFirstName("test_First_Name").withLastName("test_Last_name").
            withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withGroup("test1");
    int index=before.size()-1;
    int idModifiedContact=before.get(index).getId();
    app.contact().modify(idModifiedContact, contact, index);
    List<ContactData> after=app.contact().list();
    Assert.assertEquals(after.size(),before.size());


    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> ById=(c1,c2)->Integer.compare(c1.getId(),c2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);
    System.out.println(app.contact().getIdContactWithHelpIndex(before.size()-1));
  }


}
