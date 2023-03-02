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
    type(By.name("firstname"),contactData.getFirstname());
    type (By.name("lastname"),contactData.getLastname());
    type (By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomephone());
    type(By.name("email"),contactData.getEmail());
  }
}
