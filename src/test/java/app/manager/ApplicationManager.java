package app.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import model.ContactData;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private JavascriptExecutor js;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }

  public void init() {

    if (browser.equals(BrowserType.CHROME)) {
      wd= new ChromeDriver();
    } else if (browser.equals(BrowserType. FIREFOX)) {
      wd= new FirefoxDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
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



  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getcontactHelper() { return contactHelper;
  }
}
