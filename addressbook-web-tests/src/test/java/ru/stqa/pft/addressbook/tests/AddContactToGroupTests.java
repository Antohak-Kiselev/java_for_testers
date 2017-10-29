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

public class AddContactToGroupTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.db().groups();
    app.goTo().HomePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("test_First_Name").withLastName("test_Last_name").
              withAddress("addressTest").withEmail("mail@ru.ru").withMobilePhone("89889934").withHomePhone("324324").
              withWorkPhone("9888345").withEmail2("djkgnkj@sfd").withEmail3("dsfds@hj").inGroup(groups.iterator().next()));

    }


  }

  @Test
  public void testContactAddToGroup() {
    Groups listGroupsBefore = app.db().groups();
    Contacts listContactsBefore = app.db().contacts();
    ContactData selectedContact = listContactsBefore.iterator().next();
    Groups groupsFromSelectedContact = selectedContact.getGroups();
    GroupData selectedGroup;
    Iterator<ContactData> iteratorContacts = listContactsBefore.iterator();
    while (iteratorContacts.hasNext()) {
      if (groupsFromSelectedContact.equals(listGroupsBefore)) {
        selectedContact = iteratorContacts.next();
        groupsFromSelectedContact = selectedContact.getGroups();
      } else {
        break;
      }
    }

    if (groupsFromSelectedContact.equals(listGroupsBefore)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test5"));
    }
    listGroupsBefore = app.db().groups();
    groupsFromSelectedContact = selectedContact.getGroups();
    listGroupsBefore.removeAll(groupsFromSelectedContact);

    if (listGroupsBefore.size() > 0) {
      selectedGroup = listGroupsBefore.iterator().next();
    } else {
      throw new RuntimeException("There are no groups");
    }

    app.goTo().HomePage();
    app.contact().selectContactById(selectedContact.getId());

    app.contact().addInGroupWithId(selectedGroup.getId());
    app.goTo().pageSelectedGroup(selectedGroup.getId());

    ContactData listContactAfter = app.db().loadContactById(selectedContact.getId()).iterator().next();
    Groups listGroupsContactAfter = listContactAfter.getGroups();

    assertThat(listGroupsContactAfter, equalTo(groupsFromSelectedContact.withAdded(selectedGroup)));
  }
}
