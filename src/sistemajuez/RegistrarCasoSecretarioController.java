/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorCaso;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorQuerellante;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorSecretario;
import ds.desktop.notify.DesktopNotify;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Matthew Russell
 */
public class RegistrarCasoSecretarioController implements Initializable {

    @FXML
    private TextArea inputDescrip;
    @FXML
    private Label mssg;

    @FXML
    private TextField inputCedula;
    @FXML
    private Button btnGuardarCaso;

    @FXML

    public void registrarCasoGuardar(ActionEvent event) throws Exception {
        String cedula, descrip;

        GestorCaso logicaC = new GestorCaso();
        GestorQuerellante logicaQ = new GestorQuerellante();

        cedula = inputCedula.getText();
        descrip = inputDescrip.getText();

        if (inputDescrip.getText().isEmpty() || inputCedula.getText().isEmpty()) {
            mssg.setText("Revisar informacion introducida");

            DesktopNotify.showDesktopMessage(
                    "Llenar campos vacios o revisar información introducida",
                    "",
                    DesktopNotify.WARNING, 3400L);

        } else if (logicaQ.comprobarRegistroUsuario(cedula) && inputDescrip.getText().isEmpty() == false) {

            logicaC.registrarCaso(cedula, descrip);

            mssg.setText("");
            inputDescrip.setText("");
            inputCedula.setText("");
            DesktopNotify.showDesktopMessage(
                    "Registro exitoso",
                    "",
                    DesktopNotify.SUCCESS, 3500L);
        } else {

            mssg.setText("Revisar informacion introducida");

            DesktopNotify.showDesktopMessage(
                    "Esta cédula no se encuentra disponible",
                    "",
                    DesktopNotify.WARNING, 3400L);

        }

    }

    //validaciones
    @FXML
    void validacionCedula(KeyEvent event) {
        inputCedula.setOnKeyTyped(new EventHandler<KeyEvent>() {

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
