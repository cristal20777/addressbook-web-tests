package model;

import java.io.File;
import java.util.Objects;

public class ContactData {

  private int id = Integer.MAX_VALUE;
  private String firstname;
  private String lastname;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String faxPhone;
  private String email;
  private String group;
  private String allPhones;
  private String firstMail;
  private String secondMail;
  private String thirdMail;
  private String allEmails;
  private File photo;

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public File getPhoto() {
    return photo;
  }


  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  } public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }
  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }
  public String getMobilePhone() {
    return mobilePhone;
  }
  public String getWorkPhone() {
    return workPhone;
  }
  public String getFaxPhone() {
    return faxPhone;
  }
  public String getAllPhones() {
    return allPhones;
  }
  public String getEmail() {
    return email;
  }
  public String getAllEmails() {
    return allEmails;
  }
  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }
  public String getFirstMail() {
    return firstMail;
  }

  public ContactData withFirstMail(String firstMail) {
    this.firstMail = firstMail;
    return this;
  }

  public String getSecondMail() {
    return secondMail;
  }

  public ContactData withSecondMail(String secondMail) {
    this.secondMail = secondMail;
    return this;
  }

  public String getThirdMail() {
    return thirdMail;
  }

  public ContactData withThirdMail(String thirdMail) {
    this.thirdMail = thirdMail;
    return this;
  }
  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homephone) {
    this.homePhone = homephone;
    return this;
  }
  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }
  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }
  public ContactData withFaxPhone(String faxPhone) {
    this.faxPhone = faxPhone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }
  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
  public int getId() {
    return id;
  }
}
