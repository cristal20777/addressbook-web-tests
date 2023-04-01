package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;
import model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactCreationTests extends TestBase {
  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.Contact().getContactList();
    if (! app.Group().isThereAGroup()) {
      app.Group().create(new GroupData().withName("test1"));
    }
    ContactData contact = new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.Contact().createContact(contact);
    app.goTo().gotoHomePage();
    Set<ContactData> after = app.Contact().all();
    Assert.assertEquals(after.size(), before.size()+1);
    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }
}
