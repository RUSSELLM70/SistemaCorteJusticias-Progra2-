package sistemajuez;

import ds.desktop.notify.DesktopNotify;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UserPageSecreController implements Initializable {

    @FXML
    private Button btnCerrar;
    @FXML
    private Label welcome;

    @FXML
    private Button btnRegistroJuez;

    @FXML
    private Button btnRegistrarQuere;

    @FXML
    private Button btnRegistrarCaso;

    @FXML
    private TextArea msjPoderjudicial;

    @FXML
    public void registrarJuez(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/FXMLFormularioJuez.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

//        scene.getStylesheets().add("/styles/AdminPage.css");
        Image icon = new Image("/icons/usericon4.png");

        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.sizeToScene();

        stage.setTitle("Registro Juez");

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void registrarQuere(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/RegistrarQuerellentePage.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        Image icon = new Image("/icons/usericon4.png");

        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.sizeToScene();

        stage.setTitle("Registro Querellante");

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void registrarCasosSecretario(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/RegistrarCasoSecretario.fxml"));
        loader.load();
        Parent root = loader.getRoot();

        Scene scene = new Scene(root);

        scene.getStylesheets().add("/styles/AdminPage.css");

        Image icon = new Image("/icons/usericon4.png");

        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.sizeToScene();

        stage.setTitle("Registrar casos");

        stage.setScene(scene);
        stage.show();

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
