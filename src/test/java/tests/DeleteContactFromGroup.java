package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    ContactData contactAddToGroup;
    if (app.db().contacts().size() == 0) {
      app.contact().gotoHome();
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail("golikmisha1@mail.ru"),false);
    }

    if (app.db().groups().size()==0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    //contactAddToGroup = app.db().contacts().iterator().next();
   // if ( contactAddToGroup.getGroups().size() >= app.db().groups().size() || app.db().groups().size() == 0) {
    //  app.goTo().GroupPage();
     // app.group().create(new GroupData().withName("test2"));
    //}
  }

@Test
    public void testDeleteFromGroup () {
      ContactData contact = app.db().contacts().iterator().next();
      Groups beforeDelete = contact.getGroups();
      Groups groups = app.db().groups();
      GroupData group = groups.iterator().next();
      if (contact.getGroups().size() == 0) {
        app.contact().add(contact, group);
        app.contact().gotoHome();
        app.contact().remove(contact, group);
      } else {
        GroupData removeGroup = contact.getGroups().iterator().next();
        app.contact().remove(contact, removeGroup);
      }

      Groups afterDelete = app.db().contacts().iterator().next().getGroups();
      assertThat(beforeDelete.size() - 1, equalTo(afterDelete.size()));
      assertThat(afterDelete, equalTo(beforeDelete.without(group)));
    }
  }