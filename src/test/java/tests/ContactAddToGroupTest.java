package tests;

import model.ContactData;
import model.Contacts;
import model.GroupData;
import model.Groups;
import org.hamcrest.MatcherAssert;
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
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail("golikmisha1@mail.ru"));
    }

  }
  @Test
  public void contactAddToGroupTest3() {

    Contacts contacts = app.db().contacts();
    ContactData contactGroups = contactGroups(contacts);
    Contacts before = contacts.without(contactGroups);
    GroupData addGroup = contactNotInGroup();
    app.contact().gotoHome();
    app.contact().selectContactById(contactGroups.getId());
    app.contact().contactAddToGroup(addGroup.getName());
    ContactData contactWithGroup = contactGroups.toGroup(addGroup);
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(contacts.size()));
    MatcherAssert.assertThat(after, equalTo(before.without(contactWithGroup)));
    verifyContactListInUI();
  }

  private GroupData contactNotInGroup() {
    Contacts contacts = app.db().contacts();
    Groups groupInContact = contactGroups(contacts).getGroups();
    Groups listGroups = app.db().groups();
    listGroups.removeAll(groupInContact);
    GroupData group = listGroups.iterator().next();
    return group;

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
