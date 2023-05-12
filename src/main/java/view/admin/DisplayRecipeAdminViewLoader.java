package view.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import viewmodel.ViewModelFactory;

public class DisplayRecipeAdminViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public DisplayRecipeAdminViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    DisplayRecipeAdminViewController displayRecipeAdminViewController = loader.getController();
    displayRecipeAdminViewController.init(viewHandler, viewModelFactory.getDisplayRecipeViewModel(), root);
    return displayRecipeAdminViewController;
  }
}
