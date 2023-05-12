package view;

import javafx.scene.layout.Region;
import view.admin.*;
import view.guest.DisplayRecipeGuestViewLoader;
import view.guest.SearchRecipesGuestViewLoader;
import view.member.*;
import viewmodel.ViewModelFactory;

public class ViewFactory
{
  public static final String SEARCHMEMBER = "SEARCHMEMBER";
  public static final String MANAGERECIPESMEMBER = "MANAGERECIPESMEMBER";
  public static final String LOGIN = "LOGIN";
  public static final String SIGNUP = "SIGNUP";
  public static final String DISPLAYRECIPEMEMBER = "DISPLAYRECIPEMEMBER";
  public static final String SEARCHGUEST = "SEARCHGUEST";
  public static final String DISPLAYRECIPEGUEST = "DISPLAYRECIPEGUEST";
  public static final String SEARCHADMIN = "SEARCHADMIN";
  public static final String DISPLAYRECIPEADMIN = "DISPLAYRECIPEADMIN";
  public static final String DISPLAYFAVOURITERECEPIEMEMBER = "DISPLAYFAVOURITERECEPIEMEMBER";
  public static final String MANAGERECIPESADMIN = "MANAGERECIPESADMIN";
  public static final String MANAGEPROFILESADMIN = "MANAGEPROFILESADMIN";
  public static final String MANAGEPROFILEMEMBER = "MANAGEPROFILEMEMBER";
  public static final String SEARCHFAVOURITESMEMBER = "SEARCHFAVOURITESMEMBER";


  private final SearchRecipesMemberViewLoader searchRecipesMemberViewLoader;
  private final ManageRecipesMemberViewLoader manageRecipesMemberViewLoader;
  private final LoginViewLoader loginViewLoader;
  private final CreateAccountViewLoader createAccountViewLoader;
  private final DisplayRecipeMemberViewLoader displayRecipeMemberViewLoader;
  private final SearchRecipesGuestViewLoader searchRecipesGuestViewLoader;
  private final DisplayRecipeGuestViewLoader displayRecipeGuestViewLoader;
  private final SearchRecipesAdminViewLoader searchRecipesAdminViewLoader;
  private final DisplayRecipeAdminViewLoader displayRecipeAdminViewLoader;
  private final DisplayFavouriteRecipeMemberViewLoader displayFavouriteRecipeMemberViewLoader;
  private final ManageRecipesAdminViewLoader manageRecipesAdminViewLoader;
  private final ManageProfilesAdminViewLoader manageProfilesAdminViewLoader;
  private final ManageProfileMemberViewLoader manageProfileMemberViewLoader;
  private final SearchFavouriteRecipesMemberViewLoader searchFavouriteRecipesMemberViewLoader;


  public ViewFactory(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    searchRecipesMemberViewLoader = new SearchRecipesMemberViewLoader(
        "SearchRecipesMember.fxml", viewHandler, viewModelFactory);
    manageRecipesMemberViewLoader = new ManageRecipesMemberViewLoader("ManageRecipesMember.fxml", viewHandler, viewModelFactory);
    loginViewLoader = new LoginViewLoader("Login.fxml", viewHandler, viewModelFactory);
    createAccountViewLoader = new CreateAccountViewLoader("CreateAccount.fxml", viewHandler, viewModelFactory);
    displayRecipeMemberViewLoader = new DisplayRecipeMemberViewLoader("DisplayRecipeMember.fxml", viewHandler, viewModelFactory);
    searchRecipesGuestViewLoader = new SearchRecipesGuestViewLoader("SearchRecipesGuest.fxml", viewHandler, viewModelFactory);
    displayRecipeGuestViewLoader = new DisplayRecipeGuestViewLoader("DisplayRecipeGuest.fxml", viewHandler, viewModelFactory);
    searchRecipesAdminViewLoader = new SearchRecipesAdminViewLoader("SearchRecipesAdmin.fxml", viewHandler, viewModelFactory);
    displayRecipeAdminViewLoader = new DisplayRecipeAdminViewLoader("DisplayRecipeAdmin.fxml", viewHandler, viewModelFactory);
    displayFavouriteRecipeMemberViewLoader = new DisplayFavouriteRecipeMemberViewLoader("DisplayFavouriteRecipeMember.fxml", viewHandler, viewModelFactory);
    manageRecipesAdminViewLoader = new ManageRecipesAdminViewLoader("ManageRecipesAdmin.fxml", viewHandler, viewModelFactory);
    manageProfilesAdminViewLoader = new ManageProfilesAdminViewLoader("ManageProfilesAdmin.fxml", viewHandler, viewModelFactory);
    manageProfileMemberViewLoader = new ManageProfileMemberViewLoader("ManageProfileMember.fxml", viewHandler, viewModelFactory);
    searchFavouriteRecipesMemberViewLoader = new SearchFavouriteRecipesMemberViewLoader("SearchFavouritesRecipes.fxml", viewHandler,viewModelFactory);
  }

  public Region load(String id)
  {
    return switch (id) {
      case SEARCHMEMBER -> searchRecipesMemberViewLoader.loadView();
      case MANAGERECIPESMEMBER -> manageRecipesMemberViewLoader.loadView();
      case LOGIN -> loginViewLoader.loadView();
      case SIGNUP -> createAccountViewLoader.loadView();
      case DISPLAYRECIPEMEMBER -> displayRecipeMemberViewLoader.loadView();
      case SEARCHGUEST -> searchRecipesGuestViewLoader.loadView();
      case DISPLAYRECIPEGUEST -> displayRecipeGuestViewLoader.loadView();
      case SEARCHADMIN -> searchRecipesAdminViewLoader.loadView();
      case DISPLAYRECIPEADMIN -> displayRecipeAdminViewLoader.loadView();
      case DISPLAYFAVOURITERECEPIEMEMBER -> displayFavouriteRecipeMemberViewLoader.loadView();
      case MANAGERECIPESADMIN -> manageRecipesAdminViewLoader.loadView();
      case MANAGEPROFILESADMIN -> manageProfilesAdminViewLoader.loadView();
      case MANAGEPROFILEMEMBER -> manageProfileMemberViewLoader.loadView();
      case SEARCHFAVOURITESMEMBER -> searchFavouriteRecipesMemberViewLoader.loadView();
      default -> throw new IllegalArgumentException("Unknown id: " + id);
    };
  }
}
