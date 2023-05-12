package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Optional;

public class SearchRecipesViewModel implements PropertyChangeListener
{
  public final static String RECIPESELECTED = "RECIPESELECTED";
  private final Model model;
  private final ListProperty<Recipe> recipesList;
  private final ObjectProperty<Recipe> recipe;
  private final ListProperty<IngredientAdapter> ingredientList;
  private final ListProperty<IngredientAdapter> selectedIngredientList;
  private final StringProperty error;
  private final StringProperty title;
  private final StringProperty description;
  private final PropertyChangeSupport support;

  public SearchRecipesViewModel(Model model)
  {
    this.model = model;
    this.recipesList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.recipe = new SimpleObjectProperty<>();
    this.ingredientList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.selectedIngredientList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.error = new SimpleStringProperty("");
    this.title = new SimpleStringProperty("");
    this.description = new SimpleStringProperty("");
    this.model.addPropertyChangeListener(this);
    this.support = new PropertyChangeSupport(this);
  }

  public void getRecipeByIngredients()
  {
    if (!selectedIngredientList.isEmpty())
    {
      ArrayList<IngredientAdapter> ingredientsTemp = new ArrayList<>();
      ingredientsTemp.addAll(selectedIngredientList);

      ArrayList<Ingredient> temp1 = new ArrayList<>();

      ArrayList<Recipe> temp2 = model.getRecipesCopy();
      recipesList.clear();

      for (int i = 0; i < ingredientsTemp.size(); i++)
      {
        temp1.add(ingredientsTemp.get(i).getSubject());
      }

      for (int i = 0; i < temp2.size(); i++)
      {
        if (temp2.get(i).ifRecipeContainsIngredients(temp1))
          recipesList.add(temp2.get(i));
      }
    }
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

  public void bindIngredientList(ObjectProperty<ObservableList<IngredientAdapter>> property)
  {
    property.bind(ingredientList);
    resetIngredientList();
  }

  public void bindSelectedIngredientList(SimpleListProperty<IngredientAdapter> property)
  {
    this.selectedIngredientList.bind(property);
  }

  public void containRecipe(String text)
  {
    ArrayList<Recipe> temp = model.getRecipesCopy();
    recipesList.clear();
    for (int i = 0; i < temp.size(); i++)
    {
      if (temp.get(i).getTitle().contains(text))
        recipesList.add(temp.get(i));
    }
  }

  public void containIngredient(String text)
  {
    ArrayList<Ingredient> temp = model.getIngredientsCopy();
    ingredientList.clear();
    for (int i = 0; i < temp.size(); i++)
    {
      if (temp.get(i).getName().contains(text))
        ingredientList.add(new IngredientAdapter(temp.get(i)));
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
    recipesList.setAll(model.getAllRecipes());
  }

  private void resetIngredientList()
  {
    ArrayList<Ingredient> ingredients = model.getAllIngredients();
    ArrayList<IngredientAdapter> ingredientAdapters = new ArrayList<>();
    for (int i = 0; i < ingredients.size(); i++)
    {
      ingredientAdapters.add(new IngredientAdapter(ingredients.get(i)));
    }
    ingredientList.setAll(ingredientAdapters);
  }

  public void addPropertyChangeListener(PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      if (evt.getPropertyName().equals("ResetIngredients"))
        resetIngredientList();
      else if (evt.getPropertyName().equals("ResetRecipes"))
      {
        resetRecipesList();
        resetIngredientList();
      }
      else if(evt.getPropertyName().equals("YourAccountRemoved"))
      {
        this.support.firePropertyChange("YourAccountRemoved", false, true);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Your account has been removed by the administrator.");
        alert.show();
      }
    });
  }
}