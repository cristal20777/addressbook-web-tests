package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletionTests extends TestBase {
  private JavascriptExecutor js;

  @Test
  public void testGroupDeletion() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup (new GroupData("test1",null,null));
    }
    app.getGroupHelper().selectedGroup(before - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before - 1);
  }
}
