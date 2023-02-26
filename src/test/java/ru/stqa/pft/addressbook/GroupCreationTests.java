package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test1", "test2"));
    submitGroupCreation();
    returnToGroupPage();
    wd.findElement(By.linkText("Logout")).click();
  }
}
