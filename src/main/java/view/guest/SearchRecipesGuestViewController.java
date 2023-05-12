package view.guest;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.IngredientAdapter;
import model.Recipe;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import viewmodel.SearchRecipesViewModel;

public class SearchRecipesGuestViewController implements ViewController
{
  @FXML private TextField searchRecipeTextField;
  @FXML private TextField searchIngredientTextField;
  @FXML private TableView<IngredientAdapter> ingredientTable;
  @FXML private TableColumn<IngredientAdapter, String> nameCell;
  @FXML private TableColumn<IngredientAdapter, CheckBox> selectCell;
  @FXML private ListView<Recipe> recipeListView;
  private SimpleListProperty<IngredientAdapter> selectedIngredientList;
  private ReadOnlyObjectProperty<Recipe> recipe;

  private ViewHandler viewHandler;
  private SearchRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, SearchRecipesViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.selectedIngredientList = new SimpleListProperty<>(FXCollections.observableArrayList());
    this.nameCell.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.selectCell.setCellValueFactory(new PropertyValueFactory<>("select"));
    this.viewModel.bindRecipeList(recipeListView.itemsProperty());
    this.viewModel.bindIngredientList(ingredientTable.itemsProperty());
    this.viewModel.bindSelectedIngredientList(selectedIngredientList);
    this.recipe = recipeListView.getSelectionModel().selectedItemProperty();
    this.viewModel.bindRecipe(recipe);
  }

  @FXML protected void selectButtonPressed()
  {
    selectedIngredientList.clear();
    for (int i = 0; i < ingredientTable.getItems().size(); i++)
    {
      if (ingredientTable.getItems().get(i).getSelect().isSelected())
        selectedIngredientList.add(ingredientTable.getItems().get(i));
    }
    viewModel.getRecipeByIngredients();
  }

  @FXML protected void goBackButtonPressed()
  {
    viewHandler.openView(ViewFactory.LOGIN);
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
      viewHandler.openView(ViewFactory.DISPLAYRECIPEGUEST);
    }
  }

  @FXML protected void ingredientChangeListener()
  {
    searchIngredientTextField.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containIngredient(searchIngredientTextField.getText()));
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
