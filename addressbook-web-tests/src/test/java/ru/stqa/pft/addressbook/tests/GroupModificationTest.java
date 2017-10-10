package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Антон on 22.09.2017.
 */
public class GroupModificationTest extends TestBase {

  @Test
  public void  testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();


    if(!app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1",null,null));
    }
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    GroupData group=new GroupData(before.get(before.size()-1).getId(),"test1","test2","test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after=app.getGroupHelper().getGroupList();

    before.remove(before.size()-1);
    before.add(group);
    Comparator<? super GroupData> ById=(g1,g2)->Integer.compare(g1.getId(),g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);


  }
}
