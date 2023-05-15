package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactAddToGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().gotoHome();
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail("golikmisha1@mail.ru"));
    }
  }

  @Test
  public void contactAddToGroupTest() {
    app.contact().gotoHome();
    Contacts before = app.db().contacts();
    ContactData contactSelected = before.iterator().next();
    Groups groups = app.db().groups();
    GroupData groupSelected = groups.iterator().next();
    app.contact().selectContactById(contactSelected.getId());
    app.contact().toGroup();
    app.contact().selectGroupFromList(groupSelected.getId());
    app.contact().addToGroup();
    verifyContactListInUI();
  }


}
