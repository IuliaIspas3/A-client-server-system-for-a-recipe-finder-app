package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class CreateAccountViewModel
{
  private final Model model;
  private final StringProperty email;
  private final StringProperty username;
  private final StringProperty password;
  private final StringProperty repeatPassword;
  private final StringProperty error;

  public CreateAccountViewModel(Model model)
  {
    this.model = model;
    this.email = new SimpleStringProperty("");
    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.repeatPassword = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }

  public void createAccount()
  {
    try
    {
      if (!password.get().equals(repeatPassword.get()))
        throw new IllegalArgumentException("Passwords are not the same.");
      model.createAccount(email.get(), username.get(), password.get());
      reset();
      this.error.set("");
    }
    catch (Exception e)
    {
      this.error.set(e.getMessage());
      throw new IllegalArgumentException();
    }
  }

  public void bindEmail(StringProperty property)
  {
    this.email.bindBidirectional(property);
  }

  public void bindUsername(StringProperty property)
  {
    this.username.bindBidirectional(property);
  }

  public void bindPassword(StringProperty property)
  {
    this.password.bindBidirectional(property);
  }

  public void bindRepeatPassword(StringProperty property)
  {
    this.repeatPassword.bindBidirectional(property);
  }

  public void bindError(StringProperty property)
  {
    property.bind(error);
  }

  public void reset()
  {
    this.email.set("");
    this.username.set("");
    this.password.set("");
    this.repeatPassword.set("");
  }
}
