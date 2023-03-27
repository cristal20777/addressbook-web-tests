package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    app.getContactHelper().gotoHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru", "gfh"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoHome();
    app.getContactHelper().selectContacts(before.size() - 1);
    app.getContactHelper().editContact();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "khbk", "23456", "dfg", "dfg", "dfgdf", null);
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().updateContact();
    app.getContactHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
