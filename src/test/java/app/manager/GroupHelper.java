package app.manager;

import model.GroupData;
import model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

public class GroupHelper extends HelperBase {
  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }
  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"),groupData.getHeader());
    type(By.name("group_footer"),groupData.getFooter());

  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  private void selectedGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void initgroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache=null;
    returnToGroupPage();
  }
  public  void modify(GroupData group) {
    selectedGroupById(group.getId());
    initgroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache=null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectedGroupById(group.getId());
    deleteSelectedGroup();
    groupCache=null;
    returnToGroupPage();


  }


  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));

  }


  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> List() {
List<GroupData> groups= new ArrayList<GroupData>();
List<WebElement> elements=wd.findElements(cssSelector("span.group"));
for (WebElement element : elements) {
  String name = element.getText();
  int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
  groups.add(new GroupData().withId(id).withName(name));

}
return groups;
  }
private Groups groupCache=null;


  public Groups all() {
    if (groupCache != null) {
      return new Groups (groupCache);
    }

    groupCache= new Groups();
    List<WebElement> elements=wd.findElements(cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));

    }
    return new Groups(groupCache);
  }


}

