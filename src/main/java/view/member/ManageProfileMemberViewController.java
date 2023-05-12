package view.member;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import view.ViewController;
import view.ViewFactory;
import view.ViewHandler;
import view.menu.MemberMenuHandler;
import view.menu.MenuHandler;
import viewmodel.ManageMemberProfileViewModel;

public class ManageProfileMemberViewController implements ViewController
{
  @FXML private TextField username;
  @FXML private TextField email;
  @FXML private TextField password;
  @FXML private Label error;

  private ViewHandler viewHandler;
  private MenuHandler menuHandler;
  private ManageMemberProfileViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler,
      ManageMemberProfileViewModel manageMemberProfileViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.menuHandler = MemberMenuHandler.getInstance(viewHandler);
    this.viewModel = manageMemberProfileViewModel;
    this.root = root;
    this.viewModel.bindUsername(username.textProperty());
    this.viewModel.bindEmail(email.textProperty());
    this.viewModel.bindPassword(password.textProperty());
    this.viewModel.bindError(error.textProperty());
  }

  @FXML protected void handleMenu(Event event)
  {
    menuHandler.handleMenu(event);
  }

  @FXML protected void editEmailButtonClicked()
  {
    viewModel.editEmail();
    reset();
  }

  @FXML protected void editPasswordButtonClicked()
  {
    viewModel.editPassword();
    reset();
  }

  @FXML protected void deleteProfileButtonClicked()
  {
    viewModel.deleteProfile();
    viewHandler.openView(ViewFactory.LOGIN);
    reset();
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
