package view.member;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import model.IngredientAdapter;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import view.menu.MemberMenuHandler;
import view.menu.MenuHandler;
import viewmodel.DisplayFavouriteRecipeViewModel;


public class DisplayFavouriteRecipeMemberViewController implements ViewController {
  @FXML private Label title;
  @FXML private Label author;
  @FXML private TableView<IngredientAdapter> ingredientTableView;
  @FXML private TableColumn<IngredientAdapter, String> ingredientName;
  @FXML private TableColumn<IngredientAdapter, String> ingredientAmount;
  @FXML private TableColumn<IngredientAdapter, String> ingredientAmountType;
  @FXML private TextArea description;
  @FXML private Label error;
  @FXML private TextField multiplier;
  @FXML private ComboBox<String> ratings;
  private ReadOnlyObjectProperty<String> rate;

  private ViewHandler viewHandler;
  private MenuHandler menuHandler;
  private DisplayFavouriteRecipeViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, DisplayFavouriteRecipeViewModel displayRecipeViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.menuHandler = MemberMenuHandler.getInstance(viewHandler);
    this.viewModel = displayRecipeViewModel;
    this.root = root;
    this.ratings.getItems().setAll("1", "2", "3", "4", "5");
    this.rate = ratings.getSelectionModel().selectedItemProperty();
    this.viewModel.bindTitle(title.textProperty());
    this.viewModel.bindAuthor(author.textProperty());
    this.viewModel.bindIngredientsList(ingredientTableView.itemsProperty());
    this.ingredientName.setCellValueFactory(new PropertyValueFactory<>("name"));
    this.ingredientAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    this.ingredientAmountType.setCellValueFactory(new PropertyValueFactory<>("amountType"));
    this.viewModel.bindDescription(description.textProperty());
    this.viewModel.bindRate(rate);
    this.viewModel.bindError(error.textProperty());
    this.viewModel.bindMultiplier(multiplier.textProperty());
  }

  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }
  @FXML protected void removeFromFavourites(){
    this.viewModel.removeFromFavourites();
  }

  @FXML protected void goBackButtonPressed()
  {
    reset();
    viewHandler.openView(ViewFactory.SEARCHFAVOURITESMEMBER);
  }
  @FXML protected void multiplyIngredients()
  {
    this.viewModel.multiplyIngredients();
  }

  @FXML protected void rateButtonClicked()
  {
    this.viewModel.rate();
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
