package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorJuez;
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

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXMLRegistrarSecreController implements Initializable {

    GestorSecretario logica = new GestorSecretario();

    @FXML
    private TextField txtNomSecre;

    @FXML
    private TextField txtPrimerApellidoSecre;

    @FXML
    private TextField txtTelefonoSecre;

    @FXML
    private TextField txtUusarioSecre;

    @FXML
    private TextField txtSegundoApellidoSecre;

    @FXML
    private Button btnRegistrarSecre;

    @FXML
    private TextField txtClaveSecre;

    public void registroLogica() throws Exception {

        String usuario = "", clave, nombre, apellidos, telefono;

        //comienzo de las validaciones
        if (txtNomSecre.getText().isEmpty() || txtPrimerApellidoSecre.getText().isEmpty() || txtSegundoApellidoSecre.getText().isEmpty()
                || txtTelefonoSecre.getText().isEmpty() || txtUusarioSecre.getText().isEmpty()
                || txtClaveSecre.getText().isEmpty()) {
            //creo un css para los inputs para que salgan en rojo y se las pongo a cada input
            txtNomSecre.getStylesheets().add("/fxml/validaciones.css");
            txtTelefonoSecre.getStylesheets().add("/fxml/validaciones.css");
            txtClaveSecre.getStylesheets().add("/fxml/validaciones.css");
            txtPrimerApellidoSecre.getStylesheets().add("/fxml/validaciones.css");
            txtSegundoApellidoSecre.getStylesheets().add("/fxml/validaciones.css");
            txtUusarioSecre.getStylesheets().add("/fxml/validaciones.css");

            DesktopNotify.showDesktopMessage(
                    "Llenar campos vacios o revisar información introducida",
                    "",
                    DesktopNotify.WARNING, 3400L);

        } else {
            usuario = txtUusarioSecre.getText();

            try {
                if (logica.comprobarRegistroUsuario(usuario)) {

                    txtUusarioSecre.getStylesheets().add("/fxml/validaciones.css");

                    DesktopNotify.showDesktopMessage(
                            "Usuario ya registrado, pruebe con otro diferente",
                            "",
                            DesktopNotify.ERROR, 3500L);

                } else {

                    clave = txtClaveSecre.getText();
                    nombre = txtNomSecre.getText();
                    apellidos = txtPrimerApellidoSecre.getText() + " " + txtSegundoApellidoSecre.getText();
                    telefono = txtTelefonoSecre.getText();

                    //remuevo las validaciones del css y datos en el formulario
                    txtUusarioSecre.getStylesheets().remove("/fxml/validaciones.css");
                    txtClaveSecre.getStylesheets().remove("/fxml/validaciones.css");
                    txtNomSecre.getStylesheets().remove("/fxml/validaciones.css");
                    txtPrimerApellidoSecre.getStylesheets().remove("/fxml/validaciones.css");
                    txtSegundoApellidoSecre.getStylesheets().remove("/fxml/validaciones.css");
                    txtTelefonoSecre.getStylesheets().remove("/fxml/validaciones.css");

                    txtUusarioSecre.setText("");
                    txtClaveSecre.setText("");
                    txtNomSecre.setText("");
                    txtSegundoApellidoSecre.setText("");
                    txtPrimerApellidoSecre.setText("");
                    txtTelefonoSecre.setText("");

                    DesktopNotify.showDesktopMessage(
                            "Registro exitoso",
                            "",
                            DesktopNotify.SUCCESS, 3500L);

                    logica.agregarSecretario(clave, usuario, nombre, apellidos, telefono);

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

    }

    @FXML
    void registrarSecretario(ActionEvent event) throws Exception {
        registroLogica();

    }

    //video para validaciones de los campos(inputs)
    //https://youtu.be/zt0RlQiY3p8
    @FXML
    void validacionNombre(KeyEvent event) {
        txtNomSecre.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                char caracter = event.getCharacter().charAt(0);
                //consume quieredecir que no lo admita
                //consume si es distinto a una letra eso se encarga el consume
                if (!Character.isLetter(caracter)) {
                    event.consume();
                }
            }
        });

    }

    @FXML
    void validacionApellido1(KeyEvent event) {
        txtPrimerApellidoSecre.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                char caracter = event.getCharacter().charAt(0);
                //consume quieredecir que no lo admita
                //consume si es distinto a una letra eso se encarga el consume
                if (!Character.isLetter(caracter)) {
                    event.consume();
                }
            }
        });

    }

    @FXML
    void validacionApellido2(KeyEvent event) {
        txtSegundoApellidoSecre.setOnKeyTyped(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                char caracter = event.getCharacter().charAt(0);
                //consume quieredecir que no lo admita
                //consume si es distinto a una letra eso se encarga el consume
                if (!Character.isLetter(caracter)) {
                    event.consume();
                }
            }
        });

    }

    @FXML
    void validacionTelefono(KeyEvent event) {
        txtTelefonoSecre.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        // TODO
    }
}
