package model;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface Model
{
  void createAccount(String email, String username, String password);
  String login(String username, String password);
  String getUsername();
  void addRecipe(String title, String description, ArrayList<Ingredient> ingredients);
  void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients);
  void removeRecipe(Recipe recipe);
  void addToFavourites(Recipe recipe);
  void removeFromFavourites(Recipe recipe);
  ArrayList<Ingredient> getAllIngredients();
  ArrayList<Ingredient> getIngredientsCopy();
  ArrayList<Recipe> getAllRecipes();
  ArrayList<Recipe> getRecipesCopy();
  ArrayList<Recipe> getRecipesByUsername();
  ArrayList<Recipe> getPersonRecipesCopy();
  ArrayList<Recipe> getFavouriteRecipes();
  ArrayList<Recipe> getFavouriteRecipesCopy();
  ArrayList<Person> getAllMembers();
  ArrayList<Person> getMembersCopy();
  void editEmail(String username, String email);
  void editPassword(String username, String password);
  void deleteProfile(String username);
  void rateRecipe(int rate, Recipe recipe);
  void addPropertyChangeListener(PropertyChangeListener listener);
}
