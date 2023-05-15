package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import model.Ingredient;
import model.Person;
import model.Recipe;
import shared.Connector;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements RemotePropertyChangeListener, ClientInterface
{
  private String username;
  private final Connector connector;
  private final PropertyChangeSupport support;

  public Client(Connector connector) throws RemoteException
  {
    this.connector = connector;
    this.support = new PropertyChangeSupport(this);
    this.connector.addRemotePropertyChangeListener(this);
  }

  @Override public void createAccount(String email, String username,
      String password)
      throws RemoteException
  {
    try
    {
      this.username = connector.createAccount(email, username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public String login(String username, String password) throws RemoteException
  {
    try
    {
      this.username = connector.login(username, password);
      return username;
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public String getUsername() throws RemoteException
  {
    return username;
  }

  @Override public void addRecipe(String title, String description,
      ArrayList<Ingredient> ingredients) throws RemoteException
  {
    try
    {
      this.connector.addRecipe(title, description, ingredients, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void editRecipe(Recipe recipe, String title,
      String description, ArrayList<Ingredient> ingredients) throws RemoteException
  {
    try
    {
      this.connector.editRecipe(recipe, title, description, ingredients,
          username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void removeRecipe(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.removeRecipe(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void addToFavourites(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.addToFavourites(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void removeFromFavourites(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.removeFromFavourites(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public void editPassword(String username, String password)
      throws RemoteException
  {
    try
    {
      this.connector.editPassword(username, password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void editEmail(String username, String email) throws RemoteException
  {
    try
    {
      this.connector.editEmail(username, email);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public void deleteProfile(String username) throws RemoteException
  {
    try
    {
      this.connector.deleteProfile(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override public ArrayList<Recipe> getAllRecipes() throws RemoteException
  {
    return this.connector.getAllRecipes();
  }

  @Override public ArrayList<Recipe> getRecipesByUsername() throws RemoteException
  {
    return this.connector.getRecipesByUsername(username);
  }

  @Override public ArrayList<Recipe> getFavouriteRecipes() throws RemoteException
  {
    return this.connector.getFavouriteRecipes(username);
  }

  @Override public ArrayList<Person> getAllMembers() throws RemoteException
  {
    return this.connector.getAllMembers();
  }

  @Override public ArrayList<Ingredient> getAllIngredients() throws RemoteException
  {
    return this.connector.getAllIngredients();
  }

  @Override public void rateRecipe(int rate, Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.rateRecipe(rate, recipe, username);
    }
    catch (Exception e)
    {
      throw new RemoteException();
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(RemotePropertyChangeEvent event)
      throws RemoteException
  {
    Platform.runLater(() -> {
      if (event.getPropertyName().equals("RecipeAdded") || event.getPropertyName().equals("RecipeEdited") || event.getPropertyName().equals("RecipeRemoved") || event.getPropertyName().equals("RatingAdded"))
        this.support.firePropertyChange("ResetRecipes", false, true);
      else if (event.getPropertyName().equals("AddedToFavourites") || event.getPropertyName().equals("RemovedFromFavourites") || event.getPropertyName().equals("RatingAdded"))
        this.support.firePropertyChange("ResetFavourites", false, true);
      else if (event.getPropertyName().equals("IngredientAdded"))
        this.support.firePropertyChange("ResetIngredients", null,
            event.getNewValue());
      else if (event.getPropertyName().equals("AccountCreated"))
        this.support.firePropertyChange("AccountCreated", null,
            event.getNewValue());
      else if (event.getPropertyName().equals("AccountRemoved"))
      {
        if (event.getNewValue().equals(username))
          this.support.firePropertyChange("YourAccountRemoved", null, event.getNewValue());
        this.support.firePropertyChange("AccountRemoved", null,
            event.getNewValue());
      }
    });
  }
}
