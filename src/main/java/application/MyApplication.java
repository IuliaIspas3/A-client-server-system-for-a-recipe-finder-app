package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import shared.Connector;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MyApplication extends Application
{
  @Override public void start(Stage primaryStage) throws RemoteException, NotBoundException
  {
    Registry registry = LocateRegistry.getRegistry("10.154.204.58",1099);
    Connector connector = (Connector) registry.lookup("rmiServer");
    Model model = new ModelManager(connector);
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler viewHandler = new ViewHandler(viewModelFactory);
    viewHandler.start(primaryStage);
  }
}
