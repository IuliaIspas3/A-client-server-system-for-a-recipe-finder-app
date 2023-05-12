package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.CreateAccountViewModel;

public class CreateAccountViewController implements ViewController
{
  @FXML private TextField emailTextField;
  @FXML private TextField usernameTextField;
  @FXML private TextField passwordTextField;
  @FXML private TextField repeatPasswordTextField;
  @FXML private Label error;

  private ViewHandler viewHandler;
  private CreateAccountViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, CreateAccountViewModel createAccountViewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = createAccountViewModel;
    this.root = root;

    this.viewModel.bindEmail(emailTextField.textProperty());
    this.viewModel.bindUsername(usernameTextField.textProperty());
    this.viewModel.bindPassword(passwordTextField.textProperty());
    this.viewModel.bindRepeatPassword(repeatPasswordTextField.textProperty());
    this.viewModel.bindError(error.textProperty());
  }

  @FXML protected void createAccountButtonPressed()
  {
    try
    {
      this.viewModel.createAccount();
      viewHandler.openView(ViewFactory.SEARCHMEMBER);
    }
    catch (Exception e)
    {
      reset();
    }
  }

  @FXML protected void continueAsGuestButtonPressed()
  {
    viewHandler.openView(ViewFactory.SEARCHGUEST);
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
