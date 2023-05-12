package view.member;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import view.member.ManageRecipesMemberViewController;
import viewmodel.ViewModelFactory;

public class ManageRecipesMemberViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public ManageRecipesMemberViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    ManageRecipesMemberViewController manageRecipesMemberViewController = loader.getController();
    manageRecipesMemberViewController.init(viewHandler, viewModelFactory.getManageRecipesViewModel(), root);
    return manageRecipesMemberViewController;
  }
}