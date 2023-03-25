package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{
  @Test
  public void testGroupModification(){

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup (new GroupData("test1",null,null));
    }
    app.getGroupHelper().selectedGroup(before-1);
    app.getGroupHelper().initgroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("gfh", "dhth", "dhdth"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);

  }
}
