package tests;

import model.GroupData;
import org.testng.annotations.*;
import model.ContactData;


public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup (new GroupData("test1",null,null));
    }
    app.getcontactHelper().createContact(new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru","test1"));

    app.getNavigationHelper().gotoHomePage();
  }
}
