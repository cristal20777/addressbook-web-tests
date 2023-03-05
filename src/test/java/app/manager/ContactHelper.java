package app.manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("email"), contactData.getEmail());
  }
public void selectContacts(){
    wd.findElement(By.name("selected[]")).click();
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
  public void updateContact(){
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }
}