package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel
{
  private final Model model;
  private final StringProperty username;
  private final StringProperty password;
  private final StringProperty error;

  public LoginViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }

  public String login()
  {
    try
    {
      String usernameTemp = model.login(username.get(), password.get());
      reset();
      this.error.set("");
      return usernameTemp;
    }
    catch (Exception e)
    {
      this.error.set(e.getMessage());
      throw new IllegalArgumentException();
    }
  }

  public void bindUsername(StringProperty property)
  {
    this.username.bindBidirectional(property);
  }

  public void bindPassword(StringProperty property)
  {
    this.password.bindBidirectional(property);
  }

  public void bindError(StringProperty property)
  {
    property.bind(error);
  }

  public void reset()
  {
    this.username.set("");
    this.password.set("");
  }
}
