package sample;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class ControllerLogin implements Initializable {

    @FXML public PasswordField ContrasINPUT;
    @FXML public TextField EmailINPUT;
    @FXML public Button EntrarBUTTON;


    @FXML
    private void RedireccionPerfil(ActionEvent event) throws IOException {
        Parent profile = FXMLLoader.load(getClass().getResource("profile.fxml"));
        Scene profile_scene = new Scene(profile);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(profile_scene);
        app_stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
