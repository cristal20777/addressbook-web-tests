package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test (enabled = false)
  public void testContactModification() {
    app.getContactHelper().gotoHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru", "gfh"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoHome();
    app.getContactHelper().selectContacts(before.size() - 1);
    app.getContactHelper().editContact();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru", "gfh");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().updateContact();
    app.getContactHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId=(c1,c2)-> Integer.compare (c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
