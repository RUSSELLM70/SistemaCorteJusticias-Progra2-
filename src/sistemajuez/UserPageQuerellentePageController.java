/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemajuez;

import cr.ac.ucenfotec.corteJusticia.gestores.GestorCaso;
import cr.ac.ucenfotec.corteJusticia.objetos.Caso;
import ds.desktop.notify.DesktopNotify;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Matthew Russell
 */
public class UserPageQuerellentePageController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private TableView<Caso> tabla;
    ObservableList<Caso> listaCasoQuerellante;

    @FXML
    private TableColumn<Caso, Integer> tbId;
    @FXML
    private TableColumn<Caso, String> tbJuez;
    @FXML
    private TableColumn<Caso, String> tbEstado;
    @FXML
    private TableColumn<Caso, String> tbDescri;
    @FXML
    private TableColumn<Caso, String> tbDetalle;

    @FXML

    public void registrarCasos(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/RegistrarCasoPage.fxml"));
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

    @FXML

    public void VistaResolucion(ActionEvent event) throws SQLException, IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/VistaListadoCasosQuere.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            final ObservableList<Caso> lista = FXCollections.observableArrayList();

            GestorCaso logicaCasoLista = new GestorCaso();

            ArrayList<Caso> listaCasos;
            listaCasos = logicaCasoLista.listarCasosQuerellante();

            for (Caso tmpCaso : listaCasos) {
                lista.add(tmpCaso);

            }

            tbId.setCellValueFactory(new PropertyValueFactory<Caso, Integer>("numero"));
            tbJuez.setCellValueFactory(new PropertyValueFactory<Caso, String>("mJuez"));
            tbEstado.setCellValueFactory(new PropertyValueFactory<Caso, String>("mestado"));
            tbDetalle.setCellValueFactory(new PropertyValueFactory<Caso, String>("detalle"));
            tbDescri.setCellValueFactory(new PropertyValueFactory<Caso, String>("descripcion"));

            tabla.setItems(lista);

        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
