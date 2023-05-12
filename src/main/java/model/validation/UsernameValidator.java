package model.validation;

import model.Administrator;
import model.PersonList;

public class UsernameValidator {
  public static final int MINIMAL_LENGTH = 3;

  public static void validateUsername(String username) throws IllegalArgumentException {
    if (username == null || username.length() < MINIMAL_LENGTH) {
      throw new IllegalArgumentException("Username needs " + MINIMAL_LENGTH + " or more characters.");
    }
    else if (username.equalsIgnoreCase(Administrator.USERNAME) || username.equalsIgnoreCase("admin")) {
      throw new IllegalArgumentException("This username is already taken.");
    }
    else
    {
      PersonList personList = PersonList.getInstance();
      for (int i = 0; i < personList.size(); i++)
      {
        if (personList.getMembers().get(i).getUsername().equals(username))
          throw new IllegalArgumentException("This username is already taken.");
      }
    }
  }
}