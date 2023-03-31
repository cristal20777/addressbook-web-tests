package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.List;


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

    List<GroupData> before=app.Group().List();
    int index=before.size() - 1;
    app.Group().delete(index);
    List<GroupData> after =app.Group().List();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before,after);
  }


}
