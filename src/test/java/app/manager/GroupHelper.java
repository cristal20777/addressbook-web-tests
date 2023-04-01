package app.manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectedGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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
    returnToGroupPage();
  }
  public  void modify(int index, GroupData group) {
    selectedGroup(index);
    initgroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }
  public  void delete(int index) {
    selectedGroup(index);
    deleteSelectedGroup();
    returnToGroupPage();
  }
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));

  }


  public int getGroupCount() {
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

  public Set<GroupData> all() {
    Set<GroupData> groups= new HashSet<GroupData>();
    List<WebElement> elements=wd.findElements(cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));

    }
    return groups;
  }
  }

