package tests;

import model.Contacts;
import model.GroupData;
import org.testng.annotations.*;
import model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1"));
    }
    ContactData contact = new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.contact().createContact(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after = app.contact().all();
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(contact)));

  }

  @Test
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    if (! app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1"));
    }
    ContactData contact = new ContactData().withFirstname("Михаил'").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.contact().createContact(contact);
    app.goTo().gotoHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAdded(contact)));

  }
}
