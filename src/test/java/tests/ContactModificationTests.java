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
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact (new ContactData("Михаил", "Голик", "Москва, Сретенский бульвар 17-64", "89600267885", "golikmisha1@mail.ru","gfh"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().gotoHome();
    app.getContactHelper().selectContacts(before.size()-1);
    app.getContactHelper().editContact();

    app.getContactHelper().fillContactForm(new ContactData("khbk", "23456", "dfg", "dfg", "dfgdf", null), false);
    app.getContactHelper().updateContact();
    app.getContactHelper().gotoHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove (before.size()-1);
    before.add (new ContactData(null,"34567", "23456", "dfg", "dfg", "dfgdf", null));
    Assert.assertEquals(new HashSet<Object> (before), new HashSet<Object>(after));
  }
}
