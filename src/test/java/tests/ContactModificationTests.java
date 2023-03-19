package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getcontactHelper().gotoHome();
    if (! app.getcontactHelper().isThereAContact()) {
      app.getcontactHelper().createContact (new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru","gfh"));
    }
    app.getcontactHelper().gotoHome();
    app.getcontactHelper().selectContacts();
    app.getcontactHelper().editContact();
    app.getcontactHelper().fillContactForm(new ContactData("34567", "23456", "dfg", "dfg", "dfgdf", null), false);
    app.getcontactHelper().updateContact();

  }
}
