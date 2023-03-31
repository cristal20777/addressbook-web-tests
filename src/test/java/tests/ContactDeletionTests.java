package tests;

import model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.Contact().gotoHome();
    if (!app.Contact().isThereAContact()) {
      app.Contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test1"));
    }
  }

  @Test (enabled = false)
  public void testContactDeletion() {

    List<ContactData> before = app.Contact().getContactList();
    int index = before.size() - 1;
    app.Contact().delete(index);
    List<ContactData> after = app.Contact().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
