package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.Group().List().size()==0) {
      app.Group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.Group().all();
    GroupData modifiedGroup=before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test0").withFooter("test4");
    app.Group().modify(group);
    Set<GroupData> after = app.Group().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
  }


}
