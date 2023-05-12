package server;

import dao.*;
import dk.via.remote.observer.RemotePropertyChangeListener;
import dk.via.remote.observer.RemotePropertyChangeSupport;
import model.*;
import model.validation.EmailValidator;
import model.validation.PasswordValidator;
import model.validation.UsernameValidator;
import shared.Connector;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

public class RemoteConnector implements Connector
{
  private PersonDAO personDAO;
  private RecipeDAO recipeDAO;
  private IngredientDAO ingredientDAO;
  private final RemotePropertyChangeSupport support;

  public RemoteConnector()
  {
    try
    {
      this.personDAO = PersonDAOImplementation.getInstance();
      this.recipeDAO = RecipeDAOImplementation.getInstance();
      this.ingredientDAO = IngredientDAOImplementation.getInstance();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    this.support = new RemotePropertyChangeSupport();
  }

  @Override public synchronized String createAccount(String email,
      String username, String password) throws RemoteException
  {
    Member member = new Member(email, username, password);
    try
    {
      this.personDAO.createMember(member);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    this.support.firePropertyChange("AccountCreated", null, member);
    return username;
  }

  @Override public synchronized String login(String username, String password)
      throws RemoteException
  {
    try
    {
      if (personDAO.login(username, password))
      {
        return username;
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    throw new IllegalArgumentException("The account does not exist.");
  }

  @Override public synchronized void addRecipe(String title, String description,
      ArrayList<Ingredient> ingredients, String username) throws RemoteException
  {
    if (title.isEmpty() || title.equals(""))
      throw new IllegalArgumentException("Title cannot be empty.");
    else if (description.isEmpty() || description.equals(""))
      throw new IllegalArgumentException("Description cannot be empty.");
    else if (ingredients.isEmpty() || ingredients.size() == 0)
      throw new IllegalArgumentException("Recipe cannot have 0 ingredients.");

    Recipe recipe = new Recipe(title, description, username);
    recipe.addAllIngredients(ingredients);

    try
    {
      this.ingredientDAO.createIngredients(ingredients);
      this.recipeDAO.addRecipeToPerson(recipe, username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }

    this.support.firePropertyChange("RecipeAdded", null, recipe);
  }

  @Override public synchronized void editRecipe(Recipe recipe, String title,
      String description, ArrayList<Ingredient> ingredients, String username)
      throws RemoteException
  {
    if (recipe == null)
      throw new IllegalArgumentException("No recipe selected.");
    else if (title.isEmpty() || title.equals(""))
      throw new IllegalArgumentException("Title cannot be empty.");
    else if (description.isEmpty() || description.equals(""))
      throw new IllegalArgumentException("Description cannot be empty.");
    else if (ingredients.isEmpty() || ingredients.size() == 0)
      throw new IllegalArgumentException("Recipe cannot have 0 ingredients.");

    try
    {
      this.recipeDAO.editPersonRecipe(recipe, title, description, ingredients);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }

    this.support.firePropertyChange("RecipeEdited", null, recipe);
  }

  @Override public synchronized void removeRecipe(Recipe recipe,
      String username) throws RemoteException
  {
    try
    {
      this.recipeDAO.removeRecipe(recipe);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    this.support.firePropertyChange("RecipeRemoved", null, recipe);
  }

  @Override public synchronized void addToFavourites(Recipe recipe,
      String username) throws RemoteException
  {
    try
    {
      this.personDAO.addToFavourites(recipe, username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    this.support.firePropertyChange("AddedToFavourites", null, recipe);
  }

  @Override public synchronized void removeFromFavourites(Recipe recipe,
      String username) throws RemoteException
  {
    try
    {
      this.personDAO.removeFromFavourites(recipe, username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    this.support.firePropertyChange("RemovedFromFavourites", null, recipe);
  }

  @Override public synchronized ArrayList<Recipe> getAllRecipes()
      throws RemoteException
  {
    try
    {
      return this.recipeDAO.readRecipes();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getRecipesByUsername(String username)
      throws RemoteException
  {
    try
    {
      return this.recipeDAO.readRecipesByUsername(username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getFavouriteRecipes(String username)
      throws RemoteException
  {
    try
    {
      return this.recipeDAO.readFavouriteRecipes(username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Person> getAllMembers() throws RemoteException
  {
    try
    {
      return this.personDAO.readMembers();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public synchronized ArrayList<Ingredient> getAllIngredients()
      throws RemoteException
  {
    try
    {
      return this.ingredientDAO.readIngredients();
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void editEmail(String username, String email)
      throws RemoteException
  {
    try
    {
      this.personDAO.updateEmail(username, email);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void editPassword(String username, String password)
      throws RemoteException
  {
    try
    {
      this.personDAO.updatePassword(username, password);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void deleteProfile(String username) throws RemoteException
  {
    try
    {
      this.personDAO.removeMember(username);
      this.support.firePropertyChange("AccountRemoved", null, username);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void rateRecipe(int rate, Recipe recipe, String username)
      throws RemoteException
  {
    try
    {
      this.personDAO.rate(recipe, username, rate);
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public synchronized void addRemotePropertyChangeListener(
      RemotePropertyChangeListener listener) throws RemoteException
  {
    this.support.addPropertyChangeListener(listener);
  }
}
