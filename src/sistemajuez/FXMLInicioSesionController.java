package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorJuez;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorQuerellante;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorSecretario;
import ds.desktop.notify.DesktopNotify;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class FXMLInicioSesionController implements Initializable {

    GestorJuez logicaJ = new GestorJuez();
    GestorSecretario logicaS = new GestorSecretario();
    GestorQuerellante logicaQ = new GestorQuerellante();

    @FXML
    private TextField useridtf;

    @FXML
    private Label mssg;

    @FXML
    private ImageView imgUsua;

    @FXML
    private ImageView imgContra;
    @FXML
    private TextField inputCedulaQuere1;

    @FXML
    private Button loginb;

    @FXML
    private PasswordField passwordtf;

    @FXML
    private RadioButton Juezb;

    @FXML
    private ToggleGroup UserOrAdmin;

    @FXML
    private RadioButton Secreb;

    @FXML
    private void Login(ActionEvent event) throws IOException, Exception {

        if (Juezb.isSelected()) {
            String usuario = useridtf.getText(), clave = passwordtf.getText();

            if (logicaJ.verificarDatosLogin(usuario, clave)) {

                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/UserPageJuez.fxml"));
                loader.load();
                Parent root = loader.getRoot();

                stage.setTitle("Pagina del Juez");
                Image icon = new Image("/icons/juez.png");
                stage.getIcons().add(icon);

                stage.setMinHeight(710);
                stage.setMinWidth(1020);
                stage.setMaximized(true);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/UserPage.css");
                stage.setScene(scene);
                stage.show();

                mssg.setText("");
                useridtf.setText("");
                passwordtf.setText("");

                DesktopNotify.showDesktopMessage(
                        "Se ha iniciado sesión como juez",
                        "",
                        DesktopNotify.SUCCESS, 3000L);

                Media someSound = new Media(getClass().getResource("/audio/UserLogin.mp3").toString());
                MediaPlayer mp = new MediaPlayer(someSound);
                mp.play();

            } else {

                mssg.setText("Revisar informacion introducida del juez");

                DesktopNotify.showDesktopMessage(
                        "Llenar campos vacios o revisar información introducida del juez",
                        "",
                        DesktopNotify.WARNING, 3400L);

            }

        } else if (Secreb.isSelected()) {
            String usuario = useridtf.getText(), clave = passwordtf.getText();

            if (logicaS.verificarDatosLogin(usuario, clave)) {

                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/UserPageSecre.fxml"));
                loader.load();
                Parent root = loader.getRoot();

                stage.setTitle("Inicio Secretario");
                Image icon = new Image("/icons/UserPage.png");
                stage.getIcons().add(icon);

                Scene scene = new Scene(root);
                scene.getStylesheets().add("/styles/UserPage.css");
                stage.setScene(scene);
                stage.show();

                mssg.setText("");
                useridtf.setText("");
                passwordtf.setText("");
                DesktopNotify.showDesktopMessage(
                        "Se ha iniciado sesión como secretario",
                        "",
                        DesktopNotify.SUCCESS, 3000L);

                Media someSound = new Media(getClass().getResource("/audio/AdminLogin.mp3").toString());
                MediaPlayer mp = new MediaPlayer(someSound);
                mp.play();
            } else {

                mssg.setText("Revisar informacion introducida del secretario");

                DesktopNotify.showDesktopMessage(
                        "Llenar campos vacios o revisar información introducida del secretario",
                        "",
                        DesktopNotify.WARNING, 3400L);

            }

        }

    }

    @FXML
    public void loginQuere(ActionEvent event) throws SQLException, IOException, Exception {
        String cedula = inputCedulaQuere1.getText();
        try {
            if (inputCedulaQuere1.getText().isEmpty()) {
                mssg.setText("Revisar informacion introducida del querellante");

                DesktopNotify.showDesktopMessage(
                        "Llenar campos vacios o revisar información introducida del querellante",
                        "",
                        DesktopNotify.WARNING, 3400L);

            } else {

                if (logicaQ.verificarDatosLogin(cedula)) {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/fxml/UserPageQuerellente.fxml"));
                    loader.load();
                    Parent root = loader.getRoot();

                    Scene scene = new Scene(root);

                    scene.getStylesheets().add("/styles/UserPage.css");

                    Image icon = new Image("/icons/usericon4.png");

                    stage.getIcons().add(icon);

                    stage.setResizable(false);
                    stage.sizeToScene();

                    stage.setTitle("Pagina Querellante");

                    stage.setScene(scene);
                    stage.show();

                    DesktopNotify.showDesktopMessage(
                            "Se ha iniciado sesión como querellante",
                            "",
                            DesktopNotify.SUCCESS, 3000L);
                    inputCedulaQuere1.setText("");

                } else {

                    mssg.setText("Revisar informacion introducida del querellante");

                    DesktopNotify.showDesktopMessage(
                            "Llenar campos vacios o revisar información introducida del querellante",
                            "",
                            DesktopNotify.WARNING, 3400L);

                }

            }

        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void validacionCedula(KeyEvent event) {
        inputCedulaQuere1.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                char caracter = event.getCharacter().charAt(0);
                //consume quieredecir que no lo admita
                //consume si es distinto a una letra eso se encarga el consume
                if (Character.isLetter(caracter)) {
                    event.consume();
                }
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
