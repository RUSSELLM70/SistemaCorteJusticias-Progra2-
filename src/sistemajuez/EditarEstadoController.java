package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorCaso;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorEstado;
import ds.desktop.notify.DesktopNotify;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class EditarEstadoController implements Initializable {

    @FXML
    private Label welcome;

    @FXML
    private ComboBox<String> combox;

    @FXML
    private TextArea detalle;

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    ObservableList<String> options = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GestorEstado logicaEstado = new GestorEstado();
        ArrayList<String> listaEstados;

        try {
            listaEstados = logicaEstado.listarEstados();
            for (String estado : listaEstados) {
                options.add(estado);

            }

            combox.setItems(options);

        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getErrorCode());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

//    public void comboChanged(ActionEvent e) {
//        detalle.setText(combox.getValue());
//    }
    public void aceptarCambio(ActionEvent event) {

        GestorCaso logica = new GestorCaso();
        String estado, mdetalle;

        estado = combox.getValue();
        mdetalle = detalle.getText();

        try {
            if (estado.isEmpty() || detalle.getText().isEmpty()) {

            } else if (!estado.isEmpty() && !mdetalle.isEmpty()) {

                detalle.setText("");

                DesktopNotify.showDesktopMessage(
                        "Se ha guardado la información exitosamente",
                        "",
                        DesktopNotify.SUCCESS, 3400L);

                logica.actulizarCaso(estado, mdetalle);

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
