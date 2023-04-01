package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage();
    Set<GroupData> before=app.Group().all();
    GroupData group = new GroupData().withName("test2");
    app.Group().create(group);
    Set<GroupData> after =app.Group().all();
    Assert.assertEquals(after.size(), before.size() +1);
group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
  before.add(group);
    Assert.assertEquals(before,after);

  }

}
