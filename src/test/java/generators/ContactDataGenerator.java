package generators;

import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



public class ContactDataGenerator {
  public static void main (String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File (args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File (".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s:%s:%s:%s:%s\n",contact.getFirstname(), contact.getLastname(),
              contact.getAddress(),contact.getHomePhone(),contact.getEmail()));

    }
    writer.close();
  }


  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("test %s",i)).withLastname(String.format("header %s",i))
              .withAddress(String.format("Moskow %s",i)).withHomePhone(String.format("89600264774 %s",i)).withEmail(String.format("cristal207@mail.ru %s",i)));
  }
    return contacts;
}
}