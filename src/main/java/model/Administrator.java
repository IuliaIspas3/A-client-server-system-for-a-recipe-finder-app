package model;

import java.io.Serializable;

public class Administrator extends Person implements Serializable
{
  public static final String USERNAME = "Administrator";
  private static Administrator instance;
  private static final long SerialVersionUID = 6529685098267757694L;

  private Administrator(String email, String password)
  {
    super(email, USERNAME, password);
  }

  public static synchronized Administrator getInstance()
  {
    if (instance == null)
    {
      instance = new Administrator("admin@via.dk", "Administrator123");
    }
    return instance;
  }

  public String toString()
  {
    return USERNAME;
  }
}