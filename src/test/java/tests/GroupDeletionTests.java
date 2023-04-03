package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;


public class GroupDeletionTests extends TestBase {
  private JavascriptExecutor js;
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().List().size()==0) {
      app.Group().create(new GroupData().withName("test1"));
    }
  }
  @Test
  public void testGroupDeletion() throws Exception {

    Set<GroupData> before=app.Group().all();
    GroupData  deletedGroup=before.iterator().next();
    app.Group().deleted(deletedGroup);
    Set<GroupData> after =app.Group().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedGroup);
    Assert.assertEquals(before,after);
  }


}
