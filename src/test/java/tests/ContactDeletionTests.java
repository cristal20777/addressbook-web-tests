package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getcontactHelper().gotoHome();
    if (! app.getcontactHelper().isThereAContact()) {
      app.getcontactHelper().createContact (new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru",null));
    }
    app.getcontactHelper().selectContacts();
    app.getcontactHelper().deleteSelectedContact();

  }
}
