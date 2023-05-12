package view.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import viewmodel.ViewModelFactory;

public class ManageRecipesAdminViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public ManageRecipesAdminViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    ManageRecipesAdminViewController manageRecipesAdminViewController = loader.getController();
    manageRecipesAdminViewController.init(viewHandler, viewModelFactory.getManageRecipesViewModel(), root);
    return manageRecipesAdminViewController;
  }
}
