package view.admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import viewmodel.ViewModelFactory;

public class ManageProfilesAdminViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public ManageProfilesAdminViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    ManageProfilesAdminViewController manageProfilesAdminViewController = loader.getController();
    manageProfilesAdminViewController.init(viewHandler, viewModelFactory.getManageProfilesAdminViewModel(), root);
    return manageProfilesAdminViewController;
  }
}
