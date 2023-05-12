module com.example.sep2 {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires remoteobserver;
  requires java.rmi;
  requires org.postgresql.jdbc;
  requires java.sql;

  opens model to javafx.fxml, javafx.base;
  opens viewmodel to javafx.fxml;
  opens view to javafx.fxml;

  opens view.guest to javafx.fxml;
  opens view.member to javafx.fxml;
  opens view.admin to javafx.fxml;

  opens shared to remoteobserver, java.rmi;
  opens client to java.rmi;

  opens dao to org.postgresql.jdbc, java.sql;

  exports application;
}