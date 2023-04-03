package model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {
  private Set<ContactData> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>(contacts.delegate);
  }
  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<ContactData>();
  }

  @Override
  protected Set<ContactData> delegate() {
    return null;
  }

  public Groups withAdded(ContactData contact) {
    Contacts contacts= new Contacts(this);
    contacts.add(contacts);
    return contacts;
  }
  public Groups without(ContactData contact) {
    Contacts contacts= new Contacts(this);
    contacts.remove(contacts);
    return contacts;
  }
}