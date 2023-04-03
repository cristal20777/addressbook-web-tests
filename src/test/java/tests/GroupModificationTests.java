package tests;

import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
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
    Groups before = app.Group().all();
    GroupData modifiedGroup=before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test0").withFooter("test4");
    app.Group().modify(group);
    Groups after = app.Group().all();
    Assert.assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
