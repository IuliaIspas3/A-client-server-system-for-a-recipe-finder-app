package viewmodel;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Model;
import model.Person;

import java.util.ArrayList;
import java.util.Optional;

public class ManageMemberProfileViewModel implements ManageProfilesInterface
{
  private final Model model;
  private final StringProperty username;
  private final StringProperty email;
  private final StringProperty password;
  private final StringProperty error;

  public ManageMemberProfileViewModel(Model model)
  {
    this.model = model;
    this.username = new SimpleStringProperty(model.getUsername());
    this.email = new SimpleStringProperty("");
    this.password = new SimpleStringProperty("");
    this.error = new SimpleStringProperty("");
  }

  @Override public void bindUsername(StringProperty property)
  {
    property.bind(this.username);
  }

  @Override public void bindEmail(StringProperty property)
  {
    property.bindBidirectional(this.email);
  }

  @Override public void bindPassword(StringProperty property)
  {
    property.bindBidirectional(this.password);
  }

  @Override public void bindError(StringProperty property)
  {
    property.bind(this.error);
  }

  @Override public void reset()
  {
    this.username.set(model.getUsername());
    this.email.set("");
    this.password.set("");
    this.error.set("");
  }

  @Override public void showProfile()
  {
    if (username.get() != null)
    {
      this.username.set(model.getUsername());
      this.email.set("");
      this.password.set("");
    }
  }

  public void editEmail()
  {
    try
    {
      if (username.get() != null && email.get() != null)
      {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to edit your email?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK)
          model.editEmail(username.get(), email.get());
      }
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public void editPassword()
  {
    try
    {
      if (username.get() != null && password.get() != null)
      {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to edit your password?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK)
          model.editPassword(username.get(), password.get());
      }
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  @Override public void deleteProfile()
  {
    try
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setHeaderText(null);
      alert.setContentText("Are you sure you want to delete your account?");
      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == ButtonType.OK)
        model.deleteProfile(username.get());
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }
}


