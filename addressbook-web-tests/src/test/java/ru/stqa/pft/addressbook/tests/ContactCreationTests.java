package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @DataProvider
   public Iterator<Object[]> validContacts() throws IOException {
   try( BufferedReader reader=new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))){
     String xml="";
     String line= reader.readLine();
     while (line!=null){
       xml +=line;
       line= reader.readLine();
     }
     XStream xstream = new XStream();
     xstream.processAnnotations(ContactData.class);
     List<ContactData> contacts= (List<ContactData>)xstream.fromXML(xml);
     return  contacts.stream().map((g)->new Object[] {g}).collect(Collectors.toList()).iterator();
   }



      }


  @Test(enabled = true, dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().HomePage();
    Contacts before=app.db().contacts();
    File photo=new File("src/test/resources/stru.png");
    contact.withPhoto(photo);
    app.contact().create(contact);
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after=app.db().contacts();
    assertEquals(after.size(), before.size() + 1);



    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    verifyContactListInUI();
  }



}
