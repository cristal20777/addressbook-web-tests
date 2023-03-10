package tests;

import org.testng.annotations.*;
import model.GroupData;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test", null, null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage();
    app.logout();
  }
}
