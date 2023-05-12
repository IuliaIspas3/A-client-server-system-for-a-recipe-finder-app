package view.member;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewHandler;
import view.ViewLoader;
import viewmodel.ViewModelFactory;

public class SearchFavouriteRecipesMemberViewLoader extends ViewLoader
{
  private final ViewHandler viewHandler;
  private final ViewModelFactory viewModelFactory;

  public SearchFavouriteRecipesMemberViewLoader(String fxmlFile, ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    super(fxmlFile);
    this.viewHandler = viewHandler;
    this.viewModelFactory = viewModelFactory;
  }

  @Override protected ViewController createViewController(FXMLLoader loader, Region root)
  {
    SearchFavouriteRecipesMemberViewController searchRecipesMemberViewController = loader.getController();
    searchRecipesMemberViewController.init(viewHandler, viewModelFactory.getSearchFavouriteRecipesViewModel(), root);
    return searchRecipesMemberViewController;
  }
}
