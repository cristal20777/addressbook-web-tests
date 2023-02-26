package tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import model.GroupData;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test", "test1", "test2"));
    app.submitGroupCreation();
    app.returnToGroupPage();
    app.wd.findElement(By.linkText("Logout")).click();
  }
}
