package view.guest;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.IngredientAdapter;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import viewmodel.DisplayRecipeViewModel;

public class DisplayRecipeGuestViewController implements ViewController
{
  @FXML private Label title;
  @FXML private Label author;
  @FXML private TableView<IngredientAdapter> ingredientTableView;
  @FXML private TableColumn<IngredientAdapter, String> ingredientName;
  @FXML private TableColumn<IngredientAdapter, String> ingredientAmount;
  @FXML private TableColumn<IngredientAdapter, String> ingredientAmountType;
  @FXML private TextArea description;
  @FXML private Label error;
  @FXML private TextField multiplier;

  private ViewHandler viewHandler;
  private DisplayRecipeViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, DisplayRecipeViewModel displayRecipeViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = displayRecipeViewModel;
    this.root = root;

    this.viewModel.bindTitle(title.textProperty());
    this.viewModel.bindAuthor(author.textProperty());
    this.viewModel.bindIngredientsList(ingredientTableView.itemsProperty());
    this.ingredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.ingredientAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    this.ingredientAmountType.setCellValueFactory(new PropertyValueFactory<>("amountType"));
    this.viewModel.bindDescription(description.textProperty());
    this.viewModel.bindError(error.textProperty());
    this.viewModel.bindMultiplier(multiplier.textProperty());
  }

  @FXML protected void goBackButtonPressed()
  {
    reset();
    viewHandler.openView(ViewFactory.SEARCHGUEST);
  }

  @FXML protected void multiplyIngredients()
  {
    this.viewModel.multiplyIngredients();
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
