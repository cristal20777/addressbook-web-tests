package tests;

import model.ContactData;
import model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHome();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2"));
    }
  }

  @Test
  public void testContactModification() {
    app.contact().gotoHome();
    Contacts before = app.contact().all();
    ContactData modifiedContact=before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ивван").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.contact().modifyContact(contact);
    Contacts after = app.contact().all();
    assertThat(app.contact().count(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
