package tests;

import model.GroupData;
import model.Groups;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase {
  private JavascriptExecutor js;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size()==0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test2"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.goTo().GroupPage();
    app.group().delete(deletedGroup);
    Groups after = app.db().groups();
    assertThat(app.group().count(), equalTo(before.size() - 1));
    assertThat(after, equalTo(before.without(deletedGroup)));

  }


}
