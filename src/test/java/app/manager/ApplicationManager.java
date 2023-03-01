package app.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private JavascriptExecutor js;

  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  public void returnToGroupPage() {
    wd.findElement(By.linkText("groupPage")).click();
  }
  public void stop() {
    wd.quit();
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void gotohome() {
    wd.findElement(By.linkText("home")).click();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getcontactHelper() { return contactHelper;
  }
}
