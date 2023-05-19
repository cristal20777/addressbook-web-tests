package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {

    Contacts contacts = app.db().contacts();
    if (app.db().groups().size() == 0 ) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    if (app.db().contacts().size() == 0 || contactGroups(contacts) == null) {
      app.contact().gotoHome();
      app.contact().createContact(new ContactData().withFirstname("Биг>").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail("golikmisha1@mail.ru"),false);
    }

  }
  @Test
  public void contactAddToGroupTest3() {

    Contacts contacts = app.db().contacts();
    ContactData contactGroups = contactGroups(contacts);
    GroupData addGroup = contactAddGroup();
    Contacts before = contacts.without(contactGroups);
    app.contact().gotoHome();
    app.contact().selectContactById(contactGroups.getId());
    app.contact().contactAddToGroup(addGroup.name());
    ContactData contactWithGroup = contactGroups.toGroup(addGroup);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contactWithGroup)));
  }

  private GroupData contactAddGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    Groups groupsInContact = contactGroups(contacts).getGroups();
    groups.removeAll(groupsInContact);
    GroupData groupInContact = groups.iterator().next();
    return groupInContact;

  }

  public ContactData contactGroups(Contacts groupContact){
    for (ContactData contact : groupContact) {
      Set<GroupData> contactsGroups = contact.getGroups();
      int allGroups = app.db().groups().size();
      if (allGroups > contactsGroups.size()) {
        return contact;
      }
    }
    return null;
  }

}
