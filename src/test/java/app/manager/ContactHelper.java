package app.manager;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoFormNewContact() {
    click(By.linkText("add new"));
  }

  public void enterContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname=wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname=wd.findElement(By.name("lastname")).getAttribute("value");
    String home=wd.findElement(By.name("home")).getAttribute("value");
    String mobile=wd.findElement(By.name("mobile")).getAttribute("value");
    String work=wd.findElement(By.name("work")).getAttribute("value");
    String fax=wd.findElement(By.name("fax")).getAttribute("value");
    String firstmail=wd.findElement(By.name("email")).getAttribute("value");
    String secondmail=wd.findElement(By.name("email2")).getAttribute("value");
    String thirdmail=wd.findElement(By.name("email3")).getAttribute("value");
    String address=wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).
            withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).
            withFaxPhone(fax).withAddress(address).withFirstMail(firstmail)
            .withSecondMail(secondmail).withThirdMail(thirdmail);
  }
  private void initContactModificationById (int id){
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'] ", id))).click();

  }
  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
    //attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void gotoHome() {
    click(By.linkText("home"));
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContact() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//td/input"));
  }

  public void createContact(ContactData contact) {
    gotoFormNewContact();
    fillContactForm(contact, true);
    enterContactCreation();
  }
  public void modifyContact( ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    gotoHome();
  }
  public void editContactById(int id){
    click(By.xpath("//input[@value='" + id + "']/../../td/a/img[@alt='Edit']"));
  }
  public void delete(int index) {
    selectContacts(index);
    deleteSelectedContact();
    gotoHome();
  }
  public void deleted(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    gotoHome();
  }
  public void selectContactById(int id) {

    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }
  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
      List<WebElement> rows = wd.findElements(By.name("entry"));
      for (WebElement row : rows) {
        List<WebElement> td = row.findElements(By.tagName("td"));
      String lastname = td.get(1).getText();
      String firstname = td.get(2).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
      return contacts;
    }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String allPhones= cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
              withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return contacts;
  }

  public void remove (ContactData contact, GroupData group){
  selectGroupRemove(group.getName());
selectContactById(contact.getId());
    removeFromGroup();

  }
  public void selectGroupRemove(String groupName) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
  }
  public void removeFromGroup() {
    wd.findElement(By.name("remove")).click();
  }

  public void toGroup() {
    click(By.name("to_group"));
  }

  public void selectGroupFromList(int id) {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
  }


  public void addToGroup() {
    click(By.cssSelector("[value='Add to']"));
  }

  public void add(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    toGroup();
    addToGroup();
  }
}

