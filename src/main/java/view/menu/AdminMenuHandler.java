package view.menu;

import javafx.event.Event;
import view.ViewFactory;
import view.ViewHandler;

public class AdminMenuHandler implements MenuHandler
{
  private static MenuHandler instance;
  private ViewHandler viewHandler;

  private AdminMenuHandler(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }

  public static synchronized MenuHandler getInstance(ViewHandler viewHandler)
  {
    if (instance == null)
    {
      instance = new AdminMenuHandler(viewHandler);
    }
    return instance;
  }

  @Override public void handleMenu(Event event)
  {
    if (event.getSource().toString().contains(ViewFactory.MANAGERECIPESADMIN))
      viewHandler.openView(ViewFactory.MANAGERECIPESADMIN);
    else if (event.getSource().toString().contains(ViewFactory.SEARCHADMIN))
      viewHandler.openView(ViewFactory.SEARCHADMIN);
    else if (event.getSource().toString().contains(ViewFactory.MANAGEPROFILESADMIN))
      viewHandler.openView(ViewFactory.MANAGEPROFILESADMIN);
  }
}
