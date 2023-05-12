package view.menu;

import javafx.event.Event;
import view.ViewFactory;
import view.ViewHandler;

public class MemberMenuHandler implements MenuHandler
{
  private static MenuHandler instance;
  private ViewHandler viewHandler;

  private MemberMenuHandler(ViewHandler viewHandler)
  {
    this.viewHandler = viewHandler;
  }

  public static synchronized MenuHandler getInstance(ViewHandler viewHandler)
  {
    if (instance == null)
    {
      instance = new MemberMenuHandler(viewHandler);
    }
    return instance;
  }

  @Override public void handleMenu(Event event)
  {
    if (event.getSource().toString().contains(ViewFactory.MANAGERECIPESMEMBER))
      viewHandler.openView(ViewFactory.MANAGERECIPESMEMBER);
    else if (event.getSource().toString().contains(ViewFactory.SEARCHMEMBER))
      viewHandler.openView(ViewFactory.SEARCHMEMBER);
    else if(event.getSource().toString().contains(ViewFactory.MANAGEPROFILEMEMBER))
      viewHandler.openView(ViewFactory.MANAGEPROFILEMEMBER);
    else if (event.getSource().toString().contains(ViewFactory.SEARCHFAVOURITESMEMBER))
      viewHandler.openView(ViewFactory.SEARCHFAVOURITESMEMBER);
  }
}
