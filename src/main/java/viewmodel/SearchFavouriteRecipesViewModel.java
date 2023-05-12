package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ingredient;
import model.IngredientAdapter;
import model.Model;
import model.Recipe;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class SearchFavouriteRecipesViewModel implements PropertyChangeListener
{
  public final static String RECIPESELECTED = "RECIPESELECTED";
  private final Model model;
  private final ListProperty<Recipe> recipesList;
  private final ObjectProperty<Recipe> recipe;
  private final StringProperty error;
  private final StringProperty title;
  private final StringProperty description;
  private final PropertyChangeSupport support;

  public SearchFavouriteRecipesViewModel(Model model)
  {
    this.model = model;
    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.recipe = new SimpleObjectProperty<>();
    this.error = new SimpleStringProperty("");
    this.title = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    this.model.addPropertyChangeListener(this);
    this.support = new PropertyChangeSupport(this);
  }


  public void displayRecipe()
  {
    this.support.firePropertyChange(RECIPESELECTED, null, recipe.get());
  }

  public void bindError(StringProperty property)
  {
    property.bind(error);
  }

  public void bindRecipe(ReadOnlyObjectProperty<Recipe> property)
  {
    this.recipe.bind(property);
  }

  public void bindRecipeList(ObjectProperty<ObservableList<Recipe>> property)
  {
    property.bind(recipesList);
    resetRecipesList();
  }

  public void containRecipe(String text)
  {
    ArrayList<Recipe> temp = model.getFavouriteRecipesCopy();
    recipesList.clear();
    for (int i = 0; i < temp.size(); i++)
    {
      if (temp.get(i).getTitle().contains(text))
        recipesList.add(temp.get(i));
    }
  }
  public void reset()
  {
    title.set("");
    description.set("");
    error.set("");
  }

  private void resetRecipesList()
  {
    recipesList.setAll(model.getFavouriteRecipes());
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("ResetFavourites"))
        resetRecipesList();
    });
  }
}
