package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletionTests extends TestBase {
  private JavascriptExecutor js;

  @Test
  public void testGroupDeletion() throws Exception {
    gotoGroupPage();
    selectedGroup();
    deleteSelectedGroup();
    returnToGroupPage();
  }


}
