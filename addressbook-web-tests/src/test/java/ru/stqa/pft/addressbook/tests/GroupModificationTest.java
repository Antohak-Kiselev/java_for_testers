package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

/**
 * Created by Антон on 22.09.2017.
 */
public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if( app.db().groups().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("test1"));

  }

  @Test(enabled=true)
  public void  testGroupModification(){
    Groups before=app.db().groups();
    GroupData modifiedGroup=before.iterator().next();
    GroupData group=new GroupData().
            withId(modifiedGroup.getId()).withName("test1").withHeader("test2").withFooter("test3");

    app.group().modify(group);
    assertThat(app.group().count(), CoreMatchers.equalTo(before.size() ));
    Groups after=app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();




  }


}
