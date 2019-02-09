package sistemajuez;

import ds.desktop.notify.DesktopNotify;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserPageJuezController implements Initializable {


    @FXML
    private TextArea msjPoderjudicial;

    String UserID, UserName;
    @FXML
    private Label welcome;
    @FXML
    private Label mssg1;
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void CerrarSesion(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLDocument.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        Scene scene = new Scene(root);


        Image icon = new Image("/icons/usericon4.png");
        stage.setTitle("Casos pendientes del juzgado");

        DesktopNotify.showDesktopMessage(
                    "Se ha cerrado sesión, muchas gracias por usar nuestro sistema...",
                    "",
                    DesktopNotify.TIP, 3500L);
        
        
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.sizeToScene();

  

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public void VistaCasos(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ListadoCasosJuez.fxml"));
        loader.load();
        Parent root = loader.getRoot();

     
        
        Scene scene = new Scene(root);
        
        scene.getStylesheets().add("/styles/AdminPage.css");

        Image icon = new Image("/icons/usericon4.png");

        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.sizeToScene();

        stage.setTitle("Lista de Casos");
        

        stage.setScene(scene);
        stage.show();
    
      
    }
    

    
    
    
    
    
    

    void Salir(ActionEvent event) throws IOException {
        System.exit(0);

     
    }
    
    
    
    
    

}







