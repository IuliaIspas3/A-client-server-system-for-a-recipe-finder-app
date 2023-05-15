package model;

import client.Client;
import client.ClientInterface;
import javafx.application.Platform;
import model.validation.EmailValidator;
import model.validation.PasswordValidator;
import model.validation.UsernameValidator;
import shared.Connector;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ModelManager implements Model, PropertyChangeListener
{
  private final ClientInterface client;
  private ArrayList<Recipe> recipes;
  private ArrayList<Recipe> personRecipes;
  private ArrayList<Recipe> favouriteRecipes;
  private ArrayList<Person> persons;
  private ArrayList<Ingredient> ingredients;
  private final PropertyChangeSupport support;

  public ModelManager(Connector connector) throws RemoteException
  {
    this.client = new Client(connector);
    this.recipes = new ArrayList<>();
    this.personRecipes = new ArrayList<>();
    this.favouriteRecipes = new ArrayList<>();
    this.persons = new ArrayList<>();
    this.ingredients = new ArrayList<>();
    this.client.addPropertyChangeListener(this);
    this.support = new PropertyChangeSupport(this);
  }

  @Override public void createAccount(String email, String username,
      String password)
  {
    try
    {
      UsernameValidator.validateUsername(username);
      EmailValidator.validateEmail(email);
      PasswordValidator.validatePassword(password);
      this.client.createAccount(email, username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public String login(String username, String password)
  {
    try
    {
      return this.client.login(username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public String getUsername()
  {
    try
    {
      return client.getUsername();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void addRecipe(String title, String description,
      ArrayList<Ingredient> ingredients)
  {
    try
    {
      this.client.addRecipe(title, description, ingredients);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void editRecipe(Recipe recipe, String title,
      String description, ArrayList<Ingredient> ingredients)
  {
    try
    {
      this.client.editRecipe(recipe, title, description, ingredients);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void removeRecipe(Recipe recipe)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    try
    {
      this.client.removeRecipe(recipe);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void addToFavourites(Recipe recipe)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    try
    {
      this.client.addToFavourites(recipe);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override public void removeFromFavourites(Recipe recipe)
  {
    if (recipe == null)
      throw new NullPointerException("Recipe not selected");

    try
    {
      this.client.removeFromFavourites(recipe);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public ArrayList<Ingredient> getAllIngredients()
  {
    try
    {
      ArrayList<Ingredient> ingredients1 = client.getAllIngredients();
      this.ingredients.clear();
      this.ingredients.addAll(ingredients1);
      return ingredients1;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Ingredient> getIngredientsCopy()
  {
    return ingredients;
  }

  @Override public ArrayList<Recipe> getAllRecipes()
  {
    try
    {
      ArrayList<Recipe> recipes1 = client.getAllRecipes();
      this.recipes.clear();
      this.recipes.addAll(recipes1);
      return recipes1;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getRecipesCopy()
  {
    return recipes;
  }

  @Override public ArrayList<Recipe> getRecipesByUsername()
  {
    try
    {
      ArrayList<Recipe> recipes1 = client.getRecipesByUsername();
      this.personRecipes.clear();
      this.personRecipes.addAll(recipes1);
      return recipes1;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getPersonRecipesCopy()
  {
    return personRecipes;
  }

  @Override public ArrayList<Recipe> getFavouriteRecipes()
  {
    try
    {
      ArrayList<Recipe> recipes1 = client.getFavouriteRecipes();
      this.favouriteRecipes.clear();
      this.favouriteRecipes.addAll(recipes1);
      return recipes1;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getFavouriteRecipesCopy()
  {
    return favouriteRecipes;
  }

  @Override public ArrayList<Person> getAllMembers()
  {
    try
    {
      ArrayList<Person> persons1 = client.getAllMembers();
      this.persons.clear();
      this.persons.addAll(persons1);
      return persons1;
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Person> getMembersCopy()
  {
    return persons;
  }

  @Override public void editEmail(String username, String email)
  {
    try{
      EmailValidator.validateEmail(email);
      this.client.editEmail(username, email);
    }catch (Exception e){
      throw new IllegalArgumentException(e);
    }

  }

  @Override public void editPassword(String username, String password)
  {
    try
    {
      PasswordValidator.validatePassword(password);
      this.client.editPassword(username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }

  }

  @Override public void deleteProfile(String username)
  {
    try
    {
      this.client.deleteProfile(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void rateRecipe(int rate, Recipe recipe){
    try
    {
      client.rateRecipe(rate, recipe);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("ResetRecipes"))
        this.support.firePropertyChange("ResetRecipes", false, true);
      else if (evt.getPropertyName().equals("ResetFavourites"))
        this.support.firePropertyChange("ResetFavourites", false, true);
      else if (evt.getPropertyName().equals("ResetIngredients"))
        this.support.firePropertyChange("ResetIngredients", null, evt.getNewValue());
      else if (evt.getPropertyName().equals("AccountCreated"))
        this.support.firePropertyChange("AccountCreated", null, evt.getNewValue());
      else if (evt.getPropertyName().equals("AccountRemoved"))
        this.support.firePropertyChange("AccountRemoved", false, true);
      else if (evt.getPropertyName().equals("YourAccountRemoved"))
        this.support.firePropertyChange("YourAccountRemoved", false, true);
    });
  }
}
