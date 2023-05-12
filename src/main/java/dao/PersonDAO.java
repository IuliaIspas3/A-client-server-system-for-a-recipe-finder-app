package dao;

import model.Member;
import model.Person;
import model.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PersonDAO
{
  void createMember(Member member) throws SQLException;
  boolean login(String username, String password) throws SQLException;
  void updateEmail(String username, String email) throws SQLException;
  void updatePassword(String username, String password) throws SQLException;
  void removeMember(String username) throws SQLException;
  ArrayList<Person> readMembers() throws SQLException;
  void addToFavourites(Recipe recipe, String username) throws SQLException;
  void removeFromFavourites(Recipe recipe, String username) throws SQLException;
  void rate(Recipe recipe, String username, int rate) throws SQLException;
}
