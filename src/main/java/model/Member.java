package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Member extends Person implements Serializable
{
  private static final long SerialVersionUID = 1503;

  public Member(String email, String username, String password)
  {
    super(email, username, password);
  }

  public boolean equals(Object obj)
  {
    if (obj ==  null || obj.getClass() != getClass())
    {
      return false;
    }

    Member other = (Member) obj;

    return super.equals(other);
  }
}
