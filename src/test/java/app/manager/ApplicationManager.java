package app.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  public WebDriver wd;
  private SessionHelper sessionHelper;
  private ContactHelper contactHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private JavascriptExecutor js;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
    if (browser.equals(BrowserType.CHROME)) {
    wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
    wd = new FirefoxDriver();
    }
    wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL"));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));

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


  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }
}
