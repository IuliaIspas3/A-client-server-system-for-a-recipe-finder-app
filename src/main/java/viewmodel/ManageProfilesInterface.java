package viewmodel;

import javafx.beans.property.StringProperty;

public interface ManageProfilesInterface
{
  void bindUsername(StringProperty property);
  void bindEmail(StringProperty property);
  void bindPassword(StringProperty property);
  void bindError(StringProperty property);
  void reset();
  void showProfile();
  void deleteProfile();
}
