/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorCaso;
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
public class RegistrarCasoPageController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inputCedula;

    @FXML
    private Button btnGuardarCaso;

    @FXML
    private TextArea inputDescrip;

    @FXML
    private TextArea quotedisai;

    @FXML
    private Label mssg;

    @FXML
    void registrarCaso(ActionEvent event) throws Exception {
        String cedula, descrip;

        GestorCaso logica = new GestorCaso();

        if (inputDescrip.getText().isEmpty()) {
            mssg.setText("Revisar informacion introducida");

            DesktopNotify.showDesktopMessage(
                    "Llenar campos vacios o revisar información introducida",
                    "",
                    DesktopNotify.WARNING, 3400L);
        } else {
            descrip = inputDescrip.getText();

            mssg.setText("");

            inputDescrip.setText("");

            DesktopNotify.showDesktopMessage(
                    "Registro exitoso",
                    "",
                    DesktopNotify.SUCCESS, 3500L);

            logica.ingresarCaso(descrip);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
