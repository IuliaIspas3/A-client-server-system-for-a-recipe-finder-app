package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Person implements Serializable
{
  private final String email;
  private String username;
  private String password;

  public Person(String email, String username, String password)
  {
    this.email = email;
    this.username = username;
    this.password = password;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getEmail()
  {
    return email;
  }

  public String getUsername()
  {
    return username;
  }

  public String getPassword()
  {
    return password;
  }

  public String toString()
  {
    return username;
  }

  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != getClass())
    {
      return false;
    }

    Person other = (Person) obj;

    return this.email.equals(other.email) && this.username.equals(other.username) && this.password.equals(other.password);
  }
}
