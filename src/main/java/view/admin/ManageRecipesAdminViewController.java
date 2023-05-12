package view.admin;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.IngredientAdapter;
import model.Recipe;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import view.menu.AdminMenuHandler;
import view.menu.MenuHandler;
import viewmodel.ManageRecipesViewModel;

public class ManageRecipesAdminViewController implements ViewController
{
  @FXML private TextField searchRecipeTextField;
  @FXML private ListView<Recipe> recipeListView;
  @FXML private TextField titleTextField;
  @FXML private TextField authorTextField;
  @FXML private TextField ingredientTextField;
  @FXML private TableView<IngredientAdapter> ingredientTableView;
  @FXML private TableColumn<IngredientAdapter, String> ingredientName;
  @FXML private TableColumn<IngredientAdapter, String> ingredientAmount;
  @FXML private TableColumn<IngredientAdapter, String> ingredientAmountType;
  @FXML private TextArea descriptionTextArea;
  @FXML private Label error;

  private ReadOnlyObjectProperty<IngredientAdapter> ingredient;
  private ReadOnlyObjectProperty<Recipe> recipe;

  private MenuHandler menuHandler;
  private ManageRecipesViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageRecipesViewModel manageRecipesViewModel, Region root)
  {
    this.menuHandler = AdminMenuHandler.getInstance(viewHandler);
    this.viewModel = manageRecipesViewModel;
    this.root = root;

    this.viewModel.bindRecipeList(recipeListView.itemsProperty());
    this.viewModel.bindTitle(titleTextField.textProperty());
    this.viewModel.bindAuthor(authorTextField.textProperty());
    this.viewModel.bindIngredientName(ingredientTextField.textProperty());

    this.ingredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.ingredientAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    this.ingredientAmountType.setCellValueFactory(new PropertyValueFactory<>("amountType"));
    this.viewModel.bindIngredientsList(ingredientTableView.itemsProperty());

    this.ingredient = ingredientTableView.getSelectionModel().selectedItemProperty();
    this.viewModel.bindIngredient(ingredient);

    this.viewModel.bindDescription(descriptionTextArea.textProperty());
    this.viewModel.bindError(error.textProperty());
    this.recipe = recipeListView.getSelectionModel().selectedItemProperty();
    this.viewModel.bindRecipe(recipe);
  }

  @FXML protected void addIngredientButtonPressed()
  {
    viewModel.addIngredient();
  }

  @FXML protected void removeIngredientButtonPressed()
  {
    viewModel.removeIngredient();
  }

  @FXML protected void addRecipeButtonPressed()
  {
    viewModel.addRecipe();
  }

  @FXML protected void editRecipeButtonPressed()
  {
    viewModel.editRecipe();
  }

  @FXML protected void removeRecipeButtonPressed()
  {
    viewModel.removeRecipe();
  }

  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }

  @FXML protected void recipeChangeListener()
  {
    searchRecipeTextField.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containRecipe(searchRecipeTextField.getText()));
  }

  @FXML protected void selectRecipeListener()
  {
    viewModel.showRecipe();
  }

  @Override public void reset()
  {
    this.viewModel.reset();
  }

  @Override public Region getRoot()
  {
    return root;
  }
}
