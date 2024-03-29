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
    if (app.db().contacts().size() == 0) {
    app.contact().gotoHome();
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail( "golikmisha1@mail.ru"),false);
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before= app.db().contacts();
    ContactData deletedContact=before.iterator().next();
    app.contact().deleted(deletedContact);
    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
