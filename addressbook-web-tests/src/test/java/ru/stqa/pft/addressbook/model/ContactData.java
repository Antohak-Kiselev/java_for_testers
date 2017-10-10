package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String first_name;
  private final String last_name;
  private final String address;
  private final String mobile_phone;
  private final String e_mail;
  private  String group;
  private int id;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
    return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
  }

  @Override
  public int hashCode() {
    int result = first_name != null ? first_name.hashCode() : 0;
    result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ContactData(String first_name, String last_name, String address, String mobile_phone, String e_mail, String group) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.address = address;
    this.mobile_phone = mobile_phone;
    this.e_mail = e_mail;
    this.group=group;
  }

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getAddress() {
    return address;
  }

  public String getMobile_phone() {
    return mobile_phone;
  }

  public String getE_mail() {
    return e_mail;
  }

  public String getGroup() {
    return group;
  }
}
