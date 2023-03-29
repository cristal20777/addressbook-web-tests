package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test(enabled = false)
  public void testContactDeletion() {

    app.getContactHelper().gotoHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact (new ContactData( "Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru",null));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContacts(before.size()-1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()-1);
before.remove(before.size() - 1);
Assert.assertEquals(before,after);
  }
}
