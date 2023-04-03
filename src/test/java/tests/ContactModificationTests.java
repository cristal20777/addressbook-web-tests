package tests;

import model.ContactData;
import model.Contacts;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.Contact().gotoHome();
    if (!app.Contact().isThereAContact()) {
      app.Contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2"));
    }
  }

  @Test
  public void testContactModification() {
    app.Contact().gotoHome();
    Contacts before = app.Contact().all();
    ContactData modifiedContact=before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Ивван").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.Contact().modifyContact(contact);
    Contacts after = app.Contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
