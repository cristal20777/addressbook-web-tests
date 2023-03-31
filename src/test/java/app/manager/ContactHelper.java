package app.manager;

import model.ContactData;
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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("email"), contactData.getEmail());

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
    editContact();
    fillContactForm(contact, false);
    updateContact();
    gotoHome();
  }
  public void editContactById(int id){
    wd.findElements(By.xpath("//input[@value='" + id + "']/..//td/a/img[@alt='Edit']"));
  }
  public void delete(int index) {
    selectContacts(index);
    deleteSelectedContact();
    gotoHome();
  }
  public int getContactCount() {
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
  }

