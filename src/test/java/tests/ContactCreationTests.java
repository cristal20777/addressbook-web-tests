package tests;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;
import model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {
  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.Contact().getContactList();
    if (! app.Group().isThereAGroup()) {
      app.Group().create(new GroupData().withName("test1"));
    }
    ContactData contact = new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomephone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test2");
    app.Contact().createContact(contact);
    app.goTo().gotoHomePage();
    List<ContactData> after = app.Contact().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);



    Comparator<? super ContactData> byId=(c1,c2)-> Integer.compare (c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

  }
}
