package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class ControllerLogin implements Initializable {

    @FXML public PasswordField ContrasINPUT;
    @FXML public TextField EmailINPUT;
    @FXML public Label CredencialesIncorrectos;
    @FXML public Button EntrarBUTTON;


    @FXML
    private void RedireccionPerfil(ActionEvent event) {
        Main.ex.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    ControllerLogin.this.login(event);
                } catch (IOException e) {
                }
            }
        });
    }

    /**
     * @author fquintana
     * Fquintana async call
     * @param event
     * @throws IOException
     */
    public void login(ActionEvent event) throws IOException {
        Parent profile = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene profile_scene = new Scene(profile);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Boolean corrrect =  CredencialesCorrectos();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(corrrect) {
                    app_stage.hide();
                    app_stage.setScene(profile_scene);
                    app_stage.show();
                } else{
                    EmailINPUT.clear();
                    ContrasINPUT.clear();
                    EmailINPUT.requestFocus();
                    CredencialesIncorrectos.setText("Datos Incorrectos");
                }
            }
        });

    }

    @FXML
    private void focusContra(ActionEvent event) throws IOException {
        ContrasINPUT.requestFocus();
    }
    private boolean CredencialesCorrectos()
    {
        boolean let_in = false;
        System.out.println( "SELECT * FROM Usuario WHERE Email= " + "'" + EmailINPUT.getText() + "'"
                + " AND Contraseña= " + "'" + ContrasINPUT.getText() + "'" );

        Connection c = null;

        java.sql.Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:meet_mad.db");
            c.setAutoCommit(false);

            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery( "SELECT * FROM Usuario WHERE Email= " + "'" + EmailINPUT.getText() + "'"
                    + " AND Contraseña= " + "'" + ContrasINPUT.getText() + "'");

            while ( rs.next() ) {
                if (rs.getString("Email") != null && rs.getString("Contraseña") != null) {

                    String  username = rs.getString("Email");
                    System.out.println( "Email = " + username );
                    String password = rs.getString("Contraseña");
                    System.out.println("Contraseña = " + password);
                    let_in = true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println(" CredencialesComprobacion Hecho correctamente");
        return let_in;

    }



    @FXML
    private void RedireccionRegistro(ActionEvent event) throws IOException {
        Parent profile = FXMLLoader.load(getClass().getResource("Registrer.fxml"));
        Scene profile_scene = new Scene(profile);
        Stage app_stage = (Stage) EntrarBUTTON.getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(profile_scene);
        app_stage.show();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
