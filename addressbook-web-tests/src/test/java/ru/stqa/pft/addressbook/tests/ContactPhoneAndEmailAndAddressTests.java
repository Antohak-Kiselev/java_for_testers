package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by Антон on 15.10.2017.
 */
public class ContactPhoneAndEmailAndAddressTests extends  TestBase {


  @BeforeMethod
   public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }

       app.goTo().HomePage();
    Groups groups=app.db().groups();
       if( app.contact().all().size()==0){
            app.contact().create(new ContactData().withLastName("sdfsdfsdaf").
                            withFirstName("dsafdsaf").
                           withAddress("vreverg").
                            withEmail("dsafadsf@sdfg").
                            withMobilePhone("23434534").
                           withHomePhone("435324").
                    withWorkPhone("2345435").
                    withEmail2("sdffg@sdgffdg").
                    withEmail3("23dsfs@dsf").
                    inGroup(groups.iterator().next())

                   );
         }
        app.goTo().HomePage();
     }

  @Test
  public void testContactPhones() {
    app.contact().returnToHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditor = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditor)));
    assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditor)));
    assertThat(contact.getAddress(),equalTo(contactInfoFromEditor.getAddress()));

  }

  private String  mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobile_phone(),contact.getWorkPhone())
            .stream().
                    filter((s)->! s.equals("")).
                    map(ContactPhoneAndEmailAndAddressTests::cleaned).
                    collect(Collectors.joining("\n"));

  }

  private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getE_mail(),contact.getEmail2(),contact.getEmail3())
                       .stream().
                               filter((s)->! s.equals("")).

                        collect(Collectors.joining("\n"));



  }




  public static  String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
      }

}