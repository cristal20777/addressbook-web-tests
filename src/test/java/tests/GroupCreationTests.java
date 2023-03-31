package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{
  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().GroupPage();
    List<GroupData> before=app.Group().List();
    GroupData group = new GroupData().withName("test2");
    app.Group().create(group);
    List<GroupData> after =app.Group().List();
    Assert.assertEquals(after.size(), before.size() +1);
    Comparator<? super GroupData> byId=(g1,g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort(byId);
    after.sort(byId);
  before.add(group);
    Assert.assertEquals(before,after);

  }

}
