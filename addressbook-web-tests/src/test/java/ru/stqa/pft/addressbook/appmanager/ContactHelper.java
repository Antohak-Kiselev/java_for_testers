package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Антон on 23.09.2017.
 */
public class ContactHelper extends  HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.id("logo"));
  }

  public  void fillContactForm(ContactData contactData, boolean creation) {
   type(By.name("firstname"),contactData.getFirst_name());
   type(By.name("lastname"),contactData.getLast_name());
    type(By.name("address"),contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile_phone());
    type(By.name("email"),contactData.getE_mail());
    type(By.name("work"),contactData.getWorkPhone());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    attach(By.name("photo"), contactData.getPhoto());
    if(creation){
      if(contactData.getGroups().size()>0){
        Assert.assertTrue(contactData.getGroups().size()==1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }

    } else {
      Assert.assertFalse(isElementPresent (By.name("new_group")));
    }


    }


  public void submitContactCreation() {
   click(By.xpath("//div[@id='content']/form/input[21]"));

  }

  public  void initContactCreation() {
   click(By.linkText("add new"));
  }

  public void initContactModification(){
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void deleteSelectedContact(){
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void submitModificationContact() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact,true);
   submitContactCreation();
    contactCache=null;
    returnToHomePage();

  }

  public  void modify(ContactData contact) {
   selectContactById (contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
   submitModificationContact();
    contactCache=null;
    returnToHomePage();
  }



  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    deleteSelectedContact();
    contactCache=null;
    returnToHomePage();
  }

  public  void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public int count() {
    return wd.findElements(By.name("selected[]")).size();

  }





  public void initContactModificationById(int id) {

    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  private Contacts contactCache=null;

  public Contacts all() {
    if (contactCache!=null){
      return  new Contacts(contactCache);
    }

    contactCache=new Contacts();
    List<WebElement> elements=wd.findElements(By.name("entry"));
    for (WebElement element:elements) {
      List<WebElement> cells = element.findElements((By.tagName("td")));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones= cells.get(5).getText();
      String allEmails=cells.get(4).getText();
      String address=cells.get(3).getText();
      int id=Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact=new ContactData().withId(id).withFirstName(firstname).withLastName(lastname).
             withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
      contactCache.add(contact);

    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact){
    initContactModificationById(contact.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String email=wd.findElement(By.name("email")).getAttribute("value");
    String email2=wd.findElement(By.name("email2")).getAttribute("value");
    String email3=wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).
            withFirstName(firstname).
            withLastName(lastname).
            withMobilePhone(mobile).
            withHomePhone(home).
            withWorkPhone(work).
            withAddress(address).
            withEmail(email).
            withEmail2(email2).
            withEmail3(email3);

  }
}
