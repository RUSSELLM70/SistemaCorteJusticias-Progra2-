package sistemajuez;

import ds.desktop.notify.DesktopNotify;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorJuez;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FXMLFormularioJuezController {

    GestorJuez logica = new GestorJuez();

    @FXML
    private TextField txtCedulaJuez;

    @FXML
    private TextField txtPrimerApellidoJuez;

    @FXML
    private TextField txtTelefonoJuez;

    @FXML
    private TextField txtUsuarioRegistroJuez;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSegundoApellidoJuez;

    @FXML
    private TextField txtSala;

    @FXML
    private TextField txtclave;

    @FXML
    private Button btnRegistrarJuez;

    @FXML

    void handleButtonAction(ActionEvent event) throws Exception {
        String cedula, usuario, clave, nombre, apellidos, telefono, sala;
        //comienzo de las validaciones
        if (txtCedulaJuez.getText().isEmpty() || txtUsuarioRegistroJuez.getText().isEmpty() || txtclave.getText().isEmpty() || txtNombre.getText().isEmpty()
                || txtPrimerApellidoJuez.getText().isEmpty() || txtSegundoApellidoJuez.getText().isEmpty()
                || txtTelefonoJuez.getText().isEmpty() || txtSala.getText().isEmpty()) {
            //creo un css para los inputs para que salgan en rojo y se las pongo a cada input
            txtCedulaJuez.getStylesheets().add("/fxml/validaciones.css");
            txtUsuarioRegistroJuez.getStylesheets().add("/fxml/validaciones.css");
            txtclave.getStylesheets().add("/fxml/validaciones.css");
            txtNombre.getStylesheets().add("/fxml/validaciones.css");
            txtPrimerApellidoJuez.getStylesheets().add("/fxml/validaciones.css");
            txtSegundoApellidoJuez.getStylesheets().add("/fxml/validaciones.css");
            txtTelefonoJuez.getStylesheets().add("/fxml/validaciones.css");
            txtSala.getStylesheets().add("/fxml/validaciones.css");

            DesktopNotify.showDesktopMessage(
                    "Llenar campos vacios o revisar información introducida",
                    "",
                    DesktopNotify.WARNING, 3400L);

        } else {

            cedula = txtCedulaJuez.getText();

            try {
                if (logica.comprobarRegistroJuez(cedula)) {

                    txtCedulaJuez.getStylesheets().add("/fxml/validaciones.css");

                    DesktopNotify.showDesktopMessage(
                            "Cedula ya registrada, pruebe con otro diferente",
                            "",
                            DesktopNotify.ERROR, 3500L);
                } else {
                    usuario = txtUsuarioRegistroJuez.getText();
                    if (logica.comprobarRegistroUsuario(usuario)) {

                        txtUsuarioRegistroJuez.getStylesheets().add("/fxml/validaciones.css");

                        DesktopNotify.showDesktopMessage(
                                "Este usuario  ya se encuentra registrado, pruebe con otro diferente",
                                "",
                                DesktopNotify.ERROR, 3500L);
                    } else {

                        clave = txtclave.getText();
                        nombre = txtNombre.getText();
                        apellidos = txtPrimerApellidoJuez.getText() + " " + txtSegundoApellidoJuez.getText();
                        telefono = txtTelefonoJuez.getText();
                        sala = txtSala.getText();

                        //remuevo las validaciones del css y datos en el formulario
                        txtCedulaJuez.getStylesheets().remove("/fxml/validaciones.css");
                        txtUsuarioRegistroJuez.getStylesheets().remove("/fxml/validaciones.css");
                        txtclave.getStylesheets().remove("/fxml/validaciones.css");
                        txtNombre.getStylesheets().remove("/fxml/validaciones.css");
                        txtPrimerApellidoJuez.getStylesheets().remove("/fxml/validaciones.css");
                        txtSegundoApellidoJuez.getStylesheets().remove("/fxml/validaciones.css");
                        txtTelefonoJuez.getStylesheets().remove("/fxml/validaciones.css");
                        txtSala.getStylesheets().remove("/fxml/validaciones.css");

                        txtCedulaJuez.setText("");
                        txtUsuarioRegistroJuez.setText("");
                        txtclave.setText("");
                        txtNombre.setText("");
                        txtPrimerApellidoJuez.setText("");
                        txtSegundoApellidoJuez.setText("");
                        txtTelefonoJuez.setText("");
                        txtSala.setText("");

                        DesktopNotify.showDesktopMessage(
                                "Registro exitoso",
                                "",
                                DesktopNotify.SUCCESS, 3500L);

                        logica.agregarJuez(cedula, usuario, clave, nombre, apellidos, telefono, sala);

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
    }

    //video para validaciones de los campos(inputs)
    //https://youtu.be/zt0RlQiY3p8
    @FXML
    void validacionNombre(KeyEvent event) {
        txtNombre.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        txtPrimerApellidoJuez.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        txtSegundoApellidoJuez.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
        txtTelefonoJuez.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
    void validacionCedula(KeyEvent event) {
        txtCedulaJuez.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
    void initialize() {

    }

}
