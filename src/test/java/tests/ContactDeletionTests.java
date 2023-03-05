package tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() {
    app.getcontactHelper().gotoHome();
    app.getcontactHelper().selectContacts();
    app.getcontactHelper().deleteSelectedContact();

  }
}
