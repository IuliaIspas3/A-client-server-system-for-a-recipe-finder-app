package shared;

import dk.via.remote.observer.RemotePropertyChangeListener;
import model.Ingredient;
import model.Person;
import model.Recipe;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Connector extends Remote
{
  void requestRead() throws RemoteException;
  void releaseRead() throws RemoteException;
  void requestWrite() throws RemoteException;
  void releaseWrite() throws RemoteException;
  String createAccount(String email, String username, String password) throws RemoteException;
  String login(String username, String password) throws RemoteException;
  void addRecipe(String title, String description, ArrayList<Ingredient> ingredients, String username) throws RemoteException;
  void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients, String username) throws RemoteException;
  void removeRecipe(Recipe recipe, String username) throws RemoteException;
  void addToFavourites(Recipe recipe, String username) throws RemoteException;
  void removeFromFavourites(Recipe recipe, String username) throws RemoteException;
  ArrayList<Recipe> getAllRecipes() throws RemoteException;
  ArrayList<Recipe> getRecipesByUsername(String username) throws RemoteException;
  ArrayList<Recipe> getFavouriteRecipes(String username) throws RemoteException;
  ArrayList<Person> getAllMembers() throws RemoteException;
  ArrayList<Ingredient> getAllIngredients() throws RemoteException;
  void editEmail(String username, String email) throws RemoteException;
  void editPassword(String username, String password) throws RemoteException;
  void deleteProfile(String username) throws RemoteException;
  void rateRecipe(int rate, Recipe recipe, String username) throws RemoteException;
  void addRemotePropertyChangeListener(RemotePropertyChangeListener listener) throws RemoteException;
}