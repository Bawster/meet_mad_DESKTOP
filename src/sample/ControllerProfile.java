package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;

import javafx.event.ActionEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.scene.Node;

public class ControllerProfile {

    private FileChooser fileChooser;
    private File filepath;

    @FXML
    public void CambiarImagen (MouseEvent mouseEvent){

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir imagen");

        this.filepath = fileChooser.showOpenDialog(stage);
        if (filepath == null)
            return;
        try{
            BufferedImage bufferedImage = ImageIO.read(filepath);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage,null);
            ImageView view = (ImageView) mouseEvent.getSource();
            view.setPreserveRatio(false);
            view.setImage(image);


        } catch (Exception e){
            imagenMala();
        }
    }

    @FXML
    public void CambiarImagenPerfil (MouseEvent mouseEvent){

        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

        fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir imagen");

        this.filepath = fileChooser.showOpenDialog(stage);
        if (filepath == null)
            return;
        try{
            BufferedImage bufferedImage = ImageIO.read(filepath);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage,null);

            /*PixelReader reader = image.getPixelReader();
            WritableImage newImage = new WritableImage(reader, 0, 0, 337, 337);*/

            Circle circle = (Circle) mouseEvent.getSource();
            circle.setFill(new ImagePattern(image));


        } catch (Exception e){
            imagenMala();
        }
    }

    private void imagenMala() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Imagen");
        alert.setHeaderText("Esa imagen no es valida");
        alert.showAndWait();
    }


}
