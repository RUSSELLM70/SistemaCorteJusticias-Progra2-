package sistemajuez;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnSalir;

    @FXML
    private TitledPane btnInicioSesion;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnRegisJuez;

    @FXML
    private Button btnRegisQuerell;

    @FXML
    private Button btnCaso;

    @FXML
    private VBox dataPane;

    @FXML
    private Button btnQuere;
   
         
   
   private static AnchorPane rootP;


    public void setDataPane(Node node) {
        // update VBox with new form(FXML) depends on which button is clicked
        dataPane.getChildren().setAll(node);
    }

    public VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }
       @FXML
    public void exit(ActionEvent event) {
        
        System.exit(0);
    }



    public void InicioSesion(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/fxml/InicioSesion.fxml"));
    }
      public void InicioSesionQuere(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/fxml/InicioSesionQuerellent.fxml"));
    }
         public void registrarSecre(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/fxml/RegistrarSecrePage.fxml"));
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        if (!SistemaJuez.isSplashLoaded) {
            loadSplashScreen();
        }

        rootP = root;

       
    }

    private void loadSplashScreen() {
        try {
            SistemaJuez.isSplashLoaded = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource(("/fxml/SplashFXML.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                
                try {
                       VBox mainPane = FXMLLoader.load(getClass().getResource(("/fxml/FXMLDocument.fxml")));
                    root.getChildren().setAll(mainPane);

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
}
