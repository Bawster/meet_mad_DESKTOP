package sample;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerProfile implements MapComponentInitializedListener {

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

    @FXML
    public void initialize() {
        mapView.addMapInializedListener(this);
    }

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @Override
    public void mapInitialized() {
        LatLong CafeteríaDOSA = new LatLong(40.4304084,-3.6343015);



        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(40.4304084,-3.6343015))
                .mapType(MapTypeIdEnum.ROADMAP).streetViewControl(false).overviewMapControl(false)
                .zoom(17);

        map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(CafeteríaDOSA);
        markerOptions1.title("CAFETERIA");
        //markerOptions1.icon(MarkerImageFactory.createMarkerImage(getClass().getResource("tool.png").toString(),"png"));

        Marker DavidParra = new Marker(markerOptions1);

        map.addMarker( DavidParra );

    }



}
