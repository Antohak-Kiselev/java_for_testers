package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Table(name = "mantis_user_table")
public class UserData {

  @Id
  @Column(name = "id")
  private int id;

  @Expose
  @Column(name = "username")
  private String username;

  @Expose
  @Column(name = "email")
  private String email;

  @Expose
  @Column(name = "password")
  private String password;

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public int  getId() {
    return id;
  }


  public  UserData withId(int id) {
    this.id = id;
    return this;
  }

  public UserData withName(String username) {
    this.username = username;
    return this;
  }
  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}