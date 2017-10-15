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

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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



  public int getIdContactWithHelpIndex(int index){
    return Integer.parseInt(wd.findElements(By.name("selected[]")).get(index).getAttribute("value"));
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
      int id=Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact=new ContactData().withId(id).withFirstName(firstname).withLastName(lastname);
      contactCache.add(contact);

    }
    return new Contacts(contactCache);
  }



}
