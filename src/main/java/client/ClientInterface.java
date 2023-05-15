package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import model.Ingredient;
import model.Person;
import model.Recipe;

import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface
{
  void createAccount(String email, String username, String password)
      throws RemoteException;
  String login(String username, String password) throws RemoteException;
  String getUsername() throws RemoteException;
  void addRecipe(String title, String description,
      ArrayList<Ingredient> ingredients) throws RemoteException;
  void editRecipe(Recipe recipe, String title, String description,
      ArrayList<Ingredient> ingredients) throws RemoteException;
  void removeRecipe(Recipe recipe) throws RemoteException;
  void addToFavourites(Recipe recipe) throws RemoteException;
  void removeFromFavourites(Recipe recipe) throws RemoteException;
  void editPassword(String username, String password)
                  throws RemoteException;
  void editEmail(String username, String email) throws RemoteException;
  void deleteProfile(String username) throws RemoteException;
  ArrayList<Recipe> getAllRecipes() throws RemoteException;
  ArrayList<Recipe> getRecipesByUsername() throws RemoteException;
  ArrayList<Recipe> getFavouriteRecipes() throws RemoteException;
  ArrayList<Person> getAllMembers() throws RemoteException;
  ArrayList<Ingredient> getAllIngredients() throws RemoteException;
  void rateRecipe(int rate, Recipe recipe) throws RemoteException;
  void addPropertyChangeListener(PropertyChangeListener listener);
  void propertyChange(RemotePropertyChangeEvent event)
                      throws RemoteException;
}
