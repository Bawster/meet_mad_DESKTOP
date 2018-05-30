package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ControllerRegistrer {

    @FXML public PasswordField ContrasINPUT;
    @FXML public TextField EmailINPUT;
    @FXML public Button EntrarBUTTON;
    @FXML public TextField NickINPUT;
    @FXML public TextField NombreINPUT;

        @FXML private void IntroducirDatos(ActionEvent event)
        {
            System.out.println("INSERT INTO Usuario VALUES (" + "'" + NombreINPUT.getText() + "'" + ","+  "'" + EmailINPUT.getText() + "'"+","
                    + "'" + ContrasINPUT.getText() + "'"+"," + "'" + NickINPUT.getText() + "'"+")");

            Connection c = null;

            java.sql.Statement stmt = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlite:meet_mad.db");

                System.out.println("Opened database successfully");
                stmt = c.createStatement();

                stmt.executeUpdate( "INSERT INTO Usuario VALUES (" + "'" + NombreINPUT.getText() + "'" + ","+  "'" + EmailINPUT.getText() + "'"+","
                         + "'" + ContrasINPUT.getText() + "'"+"," + "'" + NickINPUT.getText() + "'"+")");
                stmt.close();
                c.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Parent login = null;
            try {
                login = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene login_scene = new Scene(login);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.hide();
            app_stage.setScene(login_scene);
            app_stage.show();
        }
}
