package view.guest;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import viewmodel.ViewModelFactory;

public class DisplayRecipeGuestViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public DisplayRecipeGuestViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    DisplayRecipeGuestViewController displayRecipeGuestViewController = loader.getController();
    displayRecipeGuestViewController.init(viewHandler, viewModelFactory.getDisplayRecipeViewModel(), root);
    return displayRecipeGuestViewController;
  }
}
