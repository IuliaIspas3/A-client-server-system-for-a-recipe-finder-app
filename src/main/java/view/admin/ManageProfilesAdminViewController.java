package view.admin;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Person;
import view.ViewController;
import view.ViewHandler;
import view.menu.AdminMenuHandler;
import view.menu.MenuHandler;
import viewmodel.ManageMemberProfileViewModel;
import viewmodel.ManageProfilesAdminViewModel;

public class ManageProfilesAdminViewController implements ViewController
{
  @FXML private ListView<Person> profiles;
  @FXML private TextField search;
  @FXML private TextField username;
  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private Label error;
  private ReadOnlyObjectProperty<Person> profile;


  private MenuHandler menuHandler;
  private ManageProfilesAdminViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, ManageProfilesAdminViewModel manageProfilesAdminViewModel, Region root)
  {
    this.menuHandler = AdminMenuHandler.getInstance(viewHandler);
    this.viewModel = manageProfilesAdminViewModel;
    this.root = root;

    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindEmail(email.textProperty());
    this.viewModel.bindPassword(password.textProperty());
    this.viewModel.bindError(error.textProperty());
    this.viewModel.bindProfiles(profiles.itemsProperty());
    this.profile = profiles.getSelectionModel().selectedItemProperty();
    this.viewModel.bindProfile(profile);
  }

  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }

  @FXML protected void searchChangeListener()
  {
    search.textProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> viewModel.containProfile(search.getText()));
  }

  @FXML protected void deleteProfileButtonClicked()
  {
    viewModel.deleteProfile();
    reset();
  }

  @FXML protected void selectedProfileListener()
  {
    viewModel.showProfile();
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
