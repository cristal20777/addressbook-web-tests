package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHome();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail("golikmisha1@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getFirstMail(), contact.getSecondMail(), contact.getThirdMail())
            .stream().filter((s) -> ! (s == null || s.equals("")))
            .map(ContactEmailTest::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone) {

    return phone.replaceAll("\\s","").replaceAll("-" + " + "+
            "()","");
  }
}
