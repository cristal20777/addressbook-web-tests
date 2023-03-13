package tests;

import org.testng.annotations.*;
import model.ContactData;


public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    app.getcontactHelper().gotoFormNewContact();
    app.getcontactHelper().fillContactForm(new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru","test1"));
    app.getcontactHelper().enterContactCreation();
    app.gotohome();
  }
}
