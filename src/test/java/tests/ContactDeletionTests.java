package tests;

import model.ContactData;
import model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHome();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before= app.contact().all();
    ContactData deletedContact=before.iterator().next();
    app.contact().deleted(deletedContact);
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
