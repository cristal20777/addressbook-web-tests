package tests;

import model.ContactData;
import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.Contact().gotoHome();
    if (!app.Contact().isThereAContact()) {
      app.Contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {

    Set<ContactData>  before= app.Contact().all();
    ContactData deletedContact=before.iterator().next();
    app.Contact().deleted(deletedContact);
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
