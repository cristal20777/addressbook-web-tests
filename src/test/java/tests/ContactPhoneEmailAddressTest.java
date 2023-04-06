package tests;

import model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneEmailAddressTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    app.contact().gotoHome();
    if (!app.contact().isThereAContact()) {
      app.contact().createContact(new ContactData().withFirstname("Михаил").withLastname("Голик").withAddress("fgfj").withHomePhone("89600267885").withEmail( "golikmisha1@mail.ru").withGroup("test1"));
    }
  }
  @Test
  public void testContactPhones () {
    app.goTo().gotoHomePage();;
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }

  private <T> String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getFaxPhone()).
            stream().filter((s) -> !s.equals("")).
            map(ContactPhoneEmailAddressTest::cleaned).
            collect(Collectors.joining("\n"));
  }


  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getFirstMail(), contact.getSecondMail(), contact.getThirdMail())
            .stream().filter((s) -> !(s == null || s.equals("")))
            .collect(Collectors.joining("\n"));

  }
  private String mergeAddress(ContactData contact) {
    return Stream.of(contact.getAddress()).filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));}
  
  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("-" + " + "+
            "()","");
  }
}
