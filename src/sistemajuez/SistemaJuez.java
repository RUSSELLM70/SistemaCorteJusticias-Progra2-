package sistemajuez;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


/*Main: encargado de cargar la vista principl de la aplicacion,
agregando propiedades tales como:  el tamannio de la pantalla
de cada vista, asi tambien agregando estilos propios,
para una placentera experiencia para el usuario.
*/

public class SistemaJuez extends Application {
    
    public static Boolean isSplashLoaded = false;
    
    @Override
    public void start(Stage stage) throws Exception {
    VBox mainPane = (VBox) FXMLLoader.load( getClass().getResource("/fxml/FXMLDocument.fxml"));

      Image icon = new Image("/icons/LoginPage.png");
        
        stage.getIcons().add(icon);
        stage.setResizable(false);
        
        stage.setTitle("Casos pendientes del juzgado");
        Scene scene = new Scene (mainPane,1366,720);
        stage.setScene(scene);
        stage.show();
        
        
        
        
        Media someSound = new Media(getClass().getResource("/audio/Welcome.mp3").toString());    
        MediaPlayer audio = new MediaPlayer(someSound);
        audio.play();

    }

  
    public static void main(String[] args) {
        launch(args);
    }
    
}
