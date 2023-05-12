package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import viewmodel.CreateAccountViewModel;
import viewmodel.ViewModelFactory;

public class CreateAccountViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public CreateAccountViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    CreateAccountViewController createAccountViewController = loader.getController();
    createAccountViewController.init(viewHandler, viewModelFactory.getCreateAccountViewModel(), root);
    return createAccountViewController;
  }
}
