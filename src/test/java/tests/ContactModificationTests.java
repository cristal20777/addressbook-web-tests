package tests;

import model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getcontactHelper().gotoHome();
    app.getcontactHelper().selectContacts();
    app.getcontactHelper().editContact();
    app.getcontactHelper().fillContactForm(new ContactData("34567", "23456", "dfg", "dfg", "dfgdf",null),false);
    app.getcontactHelper().updateContact();

  }
}
