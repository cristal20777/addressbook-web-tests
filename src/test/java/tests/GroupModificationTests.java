package tests;

import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modifiedGroup=before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("test0").withFooter("test4");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.db().groups();
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }




}
