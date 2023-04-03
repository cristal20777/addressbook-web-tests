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
    Contacts before = app.Contact().all();
    if (! app.Group().isThereAGroup()) {
      app.Group().create(new GroupData().withName("test1"));
    }
    ContactData contact = new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.Contact().createContact(contact);
    app.goTo().gotoHomePage();
    Contacts after = app.Contact().all();
    assertThat(after.size(), equalTo(before.size()+1));
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());

    assertThat(after, equalTo(before.withAdded(contact)));

  }
}
