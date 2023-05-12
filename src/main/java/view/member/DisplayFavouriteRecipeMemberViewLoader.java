package view.member;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import viewmodel.ViewModelFactory;

public class DisplayFavouriteRecipeMemberViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public DisplayFavouriteRecipeMemberViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    DisplayFavouriteRecipeMemberViewController displayRecipeViewController = loader.getController();
    displayRecipeViewController.init(viewHandler, viewModelFactory.getDisplayFavouriteRecipeViewModel(), root);
    return displayRecipeViewController;
  }
}
