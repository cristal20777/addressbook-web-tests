package tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletionTests extends TestBase {
  private JavascriptExecutor js;

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectedGroup();
    app.deleteSelectedGroup();
    app.returnToGroupPage();
  }


}
