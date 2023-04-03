package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

    Contacts before= app.Contact().all();
    ContactData deletedContact=before.iterator().next();
    app.Contact().deleted(deletedContact);
    Contacts after = app.Contact().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
