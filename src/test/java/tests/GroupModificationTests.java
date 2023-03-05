package tests;

import model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase{
  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectedGroup();
    app.getGroupHelper().initgroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("gfh", "dhth", "dhdth"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
  }
}
