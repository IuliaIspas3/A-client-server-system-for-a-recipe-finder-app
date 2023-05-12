package client;

import dk.via.remote.observer.RemotePropertyChangeEvent;
import dk.via.remote.observer.RemotePropertyChangeListener;
import javafx.application.Platform;
import model.Administrator;
import model.Ingredient;
import model.Person;
import model.Recipe;
import server.RemoteConnector;
import shared.Connector;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Client extends UnicastRemoteObject implements RemotePropertyChangeListener
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

  public void createAccount(String email, String username, String password) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.username = connector.createAccount(email, username, password);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.requestWrite();
    }
  }

  public String login(String username, String password) throws RemoteException
  {
    try
    {
      this.connector.requestRead();
      this.username = connector.login(username, password);
      return username;
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.releaseRead();
    }
  }

  public String getUsername() throws RemoteException
  {
    return username;
  }

  public void addRecipe(String title, String description, ArrayList<Ingredient> ingredients) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.addRecipe(title, description, ingredients, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void editRecipe(Recipe recipe, String title, String description, ArrayList<Ingredient> ingredients) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.editRecipe(recipe, title, description, ingredients, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void removeRecipe(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.removeRecipe(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void addToFavourites(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.addToFavourites(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void removeFromFavourites(Recipe recipe) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.removeFromFavourites(recipe, username);
    }
    catch (Exception e)
    {
      throw new IllegalArgumentException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void editPassword(String username, String password) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.editPassword(username, password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void editEmail(String username, String email) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.editEmail(username, email);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void deleteProfile(String username) throws RemoteException
  {
    try
    {
      this.connector.requestWrite();
      this.connector.deleteProfile(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public ArrayList<Recipe> getAllRecipes() throws RemoteException
  {
    try
    {
      this.connector.requestRead();
      return this.connector.getAllRecipes();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseRead();
    }
  }

  public ArrayList<Recipe> getRecipesByUsername() throws RemoteException
  {
    try
    {
      this.connector.requestRead();
      return this.connector.getRecipesByUsername(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseRead();
    }
  }

  public ArrayList<Recipe> getFavouriteRecipes() throws RemoteException
  {
    try
    {
      this.connector.requestRead();
      return this.connector.getFavouriteRecipes(username);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseRead();
    }
  }

  public ArrayList<Person> getAllMembers() throws RemoteException
  {
    try
    {
      this.connector.requestRead();
      return this.connector.getAllMembers();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseRead();
    }
  }

  public ArrayList<Ingredient> getAllIngredients() throws RemoteException
  {
    try
    {
      this.connector.requestRead();
      return this.connector.getAllIngredients();
    }
    catch (RemoteException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      this.connector.releaseRead();
    }
  }

  public void rateRecipe(int rate, Recipe recipe) throws RemoteException{
    try
    {
      this.connector.requestWrite();
      this.connector.rateRecipe(rate,recipe, username);
    }
    catch (Exception e)
    {
      throw new RemoteException();
    }
    finally
    {
      this.connector.releaseWrite();
    }
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(RemotePropertyChangeEvent event) throws RemoteException
  {
    Platform.runLater(() -> {
      if (event.getPropertyName().equals("RecipeAdded") || event.getPropertyName().equals("RecipeEdited") || event.getPropertyName().equals("RecipeRemoved"))
        this.support.firePropertyChange("ResetRecipes", false, true);
      else if (event.getPropertyName().equals("AddedToFavourites") || event.getPropertyName().equals("RemovedFromFavourites"))
        this.support.firePropertyChange("ResetFavourites", false, true);
      else if (event.getPropertyName().equals("IngredientAdded"))
        this.support.firePropertyChange("ResetIngredients", null, event.getNewValue());
      else if (event.getPropertyName().equals("AccountCreated"))
        this.support.firePropertyChange("AccountCreated", null, event.getNewValue());
      else if (event.getPropertyName().equals("AccountRemoved"))
      {
        if (event.getNewValue().equals(username))
          this.support.firePropertyChange("YourAccountRemoved", null, event.getNewValue());
        this.support.firePropertyChange("AccountRemoved",null, event.getNewValue());
      }
    });
  }
}
