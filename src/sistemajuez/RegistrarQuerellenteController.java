package sistemajuez;

import ds.desktop.notify.DesktopNotify;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorQuerellante;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class RegistrarQuerellenteController implements Initializable {

    GestorQuerellante logica = new GestorQuerellante();
    @FXML
    private TextField txtCedulaQuere;

    @FXML
    private TextField txtPrimerApellidoQuere;

    @FXML
    private TextField txtTelefonoQuere;

    @FXML
    private TextField txtNomQuere;

    @FXML
    private TextField txtSegundoApellidoQuere;

    @FXML
    private Button btnRegistrarJuez;

    @FXML
    private TextArea txtDirecQuere;

    @FXML
    void registrarQuere(ActionEvent event) throws Exception {
        String cedula, nombre, apellidos, telefono, direccion;
        //comienzo de las validaciones

        if (txtCedulaQuere.getText().isEmpty() || txtPrimerApellidoQuere.getText().isEmpty() || txtTelefonoQuere.getText().isEmpty() || txtSegundoApellidoQuere.getText().isEmpty()
                || txtNomQuere.getText().isEmpty() || txtDirecQuere.getText().isEmpty()) {
            //creo un css para los inputs para que salgan en rojo y se las pongo a cada input
            txtCedulaQuere.getStylesheets().add("/fxml/validaciones.css");
            txtPrimerApellidoQuere.getStylesheets().add("/fxml/validaciones.css");
            txtTelefonoQuere.getStylesheets().add("/fxml/validaciones.css");
            txtSegundoApellidoQuere.getStylesheets().add("/fxml/validaciones.css");
            txtNomQuere.getStylesheets().add("/fxml/validaciones.css");
            txtDirecQuere.getStylesheets().add("/fxml/validaciones.css");

            DesktopNotify.showDesktopMessage(
                    "Llenar campos vacios o revisar información introducida",
                    "",
                    DesktopNotify.WARNING, 3400L);

        } else {

            cedula = txtCedulaQuere.getText();

            try {

                if (logica.comprobarRegistroUsuario(cedula)) {

                    txtCedulaQuere.getStylesheets().add("/fxml/validaciones.css");

                    DesktopNotify.showDesktopMessage(
                            "Cedula ya registrada, pruebe con otro diferente",
                            "",
                            DesktopNotify.ERROR, 3500L);

                } else {

                    direccion = txtDirecQuere.getText();
                    nombre = txtNomQuere.getText();
                    apellidos = txtPrimerApellidoQuere.getText() + " " + txtSegundoApellidoQuere.getText();
                    telefono = txtTelefonoQuere.getText();

                    //remuevo las validaciones del css y datos en el formulario
                    txtCedulaQuere.getStylesheets().remove("/fxml/validaciones.css");
                    txtDirecQuere.getStylesheets().remove("/fxml/validaciones.css");
                    txtNomQuere.getStylesheets().remove("/fxml/validaciones.css");
                    txtPrimerApellidoQuere.getStylesheets().remove("/fxml/validaciones.css");
                    txtSegundoApellidoQuere.getStylesheets().remove("/fxml/validaciones.css");
                    txtTelefonoQuere.getStylesheets().remove("/fxml/validaciones.css");

                    txtCedulaQuere.setText("");
                    txtDirecQuere.setText("");
                    txtNomQuere.setText("");
                    txtPrimerApellidoQuere.setText("");
                    txtSegundoApellidoQuere.setText("");
                    txtTelefonoQuere.setText("");

                    DesktopNotify.showDesktopMessage(
                            "Registro exitoso",
                            "",
                            DesktopNotify.SUCCESS, 3500L);

                    logica.agregarQuerellente(cedula, direccion, nombre, apellidos, telefono);
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

//validaciones
    @FXML
    void validacionCedula(KeyEvent event) {
        txtCedulaQuere.setOnKeyTyped(new EventHandler<KeyEvent>() {

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

    @FXML
    void validacionNombre(KeyEvent event) {
        txtNomQuere.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        txtPrimerApellidoQuere.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        txtSegundoApellidoQuere.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        txtTelefonoQuere.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
