package view.member;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Recipe;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import view.menu.MemberMenuHandler;
import view.menu.MenuHandler;
import viewmodel.SearchFavouriteRecipesViewModel;


public class SearchFavouriteRecipesMemberViewController implements ViewController
{
  @FXML private TextField searchRecipeTextField;
  @FXML private ListView<Recipe> recipeListView;
  private ReadOnlyObjectProperty<Recipe> recipe;
  private ViewHandler viewHandler;
  private MenuHandler menuHandler;
  private SearchFavouriteRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SearchFavouriteRecipesViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.menuHandler = MemberMenuHandler.getInstance(viewHandler);
    this.viewModel = viewModel;
    this.root = root;
    this.viewModel.bindRecipeList(recipeListView.itemsProperty());
    this.recipe = recipeListView.getSelectionModel().selectedItemProperty();
    this.viewModel.bindRecipe(recipe);
  }
  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }

  @FXML protected void recipeChangeListener()
  {
    searchRecipeTextField.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containRecipe(searchRecipeTextField.getText()));
  }

  @FXML protected void displayRecipeListener()
  {
    if (recipe.get() != null)
    {
      viewModel.displayRecipe();
      viewHandler.openView(ViewFactory.DISPLAYFAVOURITERECEPIEMEMBER);
    }
  }

  @Override public void reset()
  {
    viewModel.reset();
  }

  @Override public Region getRoot()
  {
    return root;
  }
}
