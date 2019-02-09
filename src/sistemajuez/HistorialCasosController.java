package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorCaso;
import cr.ac.ucenfotec.corteJusticia.objetos.HistorialCaso;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HistorialCasosController implements Initializable {

    @FXML
    private Label welcome;

    @FXML
    private TableView<HistorialCaso> tabla;

    @FXML
    private TableColumn<HistorialCaso, Integer> tbId;

    @FXML
    private TableColumn<HistorialCaso, ArrayList<String>> tbEstado;

    @FXML
    private TableColumn<HistorialCaso, ArrayList<LocalDateTime>> tbFechaHora;

    ObservableList<HistorialCaso> lista = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GestorCaso logica = new GestorCaso();
        ArrayList<HistorialCaso> listaHistorial;

        try {
            listaHistorial = logica.listarHistorialCaso();
            for (HistorialCaso tmpHistorial : listaHistorial) {
                lista.add(tmpHistorial);

            }

            tbId.setCellValueFactory(new PropertyValueFactory<HistorialCaso, Integer>("numeroCaso"));
            tbEstado.setCellValueFactory(new PropertyValueFactory<HistorialCaso, ArrayList<String>>("estados"));
            tbFechaHora.setCellValueFactory(new PropertyValueFactory<HistorialCaso, ArrayList<LocalDateTime>>("fechas"));

            tabla.setItems(lista);

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
