package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Антон on 29.10.2017.
 */
public class RemoveContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {


    Groups groups = app.db().groups();

    if (app.db().contacts().size() == 0) {
      app.goTo().groupPage();
      if (app.db().groups().size() == 0) {
        app.group().create(new GroupData().withName("test15"));
      }
      app.goTo().addContactPage();
      app.contact().create(new ContactData().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withHomePhone("324324").
              withWorkPhone("9888345").withEmail2("djkgnkj@sfd").withEmail3("dsfds@hj").inGroup(groups.iterator().next()));

      ContactData contact = app.db().contacts().iterator().next();
      GroupData group = app.db().groups().iterator().next();
      app.goTo().HomePage();
      app.contact().selectContactById(contact.getId());
      app.contact().addInGroupWithId(group.getId());
      app.goTo().pageSelectedGroup(group.getId());

    }

  }

  @Test
  public void testDelFromGroup() {
    Contacts contacts = app.db().contacts();
    Iterator<ContactData> iteratorContacts = contacts.iterator();
    ContactData contact = iteratorContacts.next();
    GroupData group = contact.getGroups().iterator().next();

    app.goTo().HomePage();

    while (iteratorContacts.hasNext()) {
      if (contact.getGroups().size() > 0) {
        group = contact.getGroups().iterator().next();
        app.contact().filteredGroupsById(group.getId());
        break;
      } else {
        contact = iteratorContacts.next();
      }
    }

    app.contact().selectContactById(contact.getId());

    app.contact().removeContactFromGroup();
    app.goTo().pageSelectedGroup(group.getId());

    Groups groupsContactAfter = app.db().loadContactById(contact.getId()).iterator().next().getGroups();

    assertThat(groupsContactAfter, equalTo(
            contact.getGroups().without(group)));
  }


}

