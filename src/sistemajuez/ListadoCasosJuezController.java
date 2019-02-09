package sistemajuez;

import accesobd.AccesoBD;
import accesobd.Conector;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorCaso;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorJuez;
import cr.ac.ucenfotec.corteJusticia.gestores.GestorQuerellante;
import cr.ac.ucenfotec.corteJusticia.objetos.Caso;
import cr.ac.ucenfotec.corteJusticia.objetos.Juez;
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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ListadoCasosJuezController implements Initializable {

    @FXML
    private Label welcome;

    @FXML
    private Button loadcusinfo;

    @FXML
    private TextField inputNumeroCasoBuscar;

    @FXML
    private Button btnHistorial;

    @FXML
    private ImageView adminimage;

    @FXML
    private Label adminname;

    @FXML
    private Label adminid;
    @FXML
    private Button btnEditar;

    @FXML
    private TableView<Caso> tabla;

    @FXML
    private TableColumn<Caso, Integer> tbId;

    @FXML
    private TableColumn<Caso, String> tbQuere;
    @FXML
    private TableColumn<Caso, String> tbDetalle;

    @FXML
    private TableColumn<Caso, String> tbEstado;

    @FXML
    private TableColumn<Caso, String> tbDescri;

    /**
     *
     * @param evet
     * @throws SQLException
     * @throws IOException
     * @throws Exception
     */
    @FXML
    public void cargarInfo(ActionEvent evet) throws SQLException, IOException, Exception {
        try {
            final ObservableList<Caso> lista = FXCollections.observableArrayList();

            GestorCaso logicaCasoLista = new GestorCaso();

            ArrayList<Caso> listaCasos;
            listaCasos = logicaCasoLista.listarCasosJuez();

            for (Caso tmpCaso : listaCasos) {
                lista.add(tmpCaso);

            }

            tbId.setCellValueFactory(new PropertyValueFactory<Caso, Integer>("numero"));
            tbQuere.setCellValueFactory(new PropertyValueFactory<Caso, String>("mQuerellante"));
            tbEstado.setCellValueFactory(new PropertyValueFactory<Caso, String>("mestado"));
            tbDetalle.setCellValueFactory(new PropertyValueFactory<Caso, String>("detalle"));
            tbDescri.setCellValueFactory(new PropertyValueFactory<Caso, String>("descripcion"));

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

    @FXML
    void VistaEditar(ActionEvent event) throws SQLException, IOException, Exception {

        GestorCaso logica = new GestorCaso();
        String numero;
        boolean existe;

        try {

            numero = inputNumeroCasoBuscar.getText();

            existe = logica.comprobarRegistro(Integer.parseInt(numero));

            if (!existe) {

                DesktopNotify.showDesktopMessage(
                        "Porfavor introducir un numero de la lista",
                        "",
                        DesktopNotify.WARNING, 3400L);

            } else if (existe) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/EditarEstado.fxml"));
                loader.load();
                Parent root = loader.getRoot();

                Scene scene = new Scene(root);

                scene.getStylesheets().add("/styles/AdminPage.css");

                Image icon = new Image("/icons/usericon4.png");

                stage.getIcons().add(icon);

                stage.setResizable(false);
                stage.sizeToScene();

                stage.setTitle("Editar  Casos");

                stage.setScene(scene);
                stage.show();

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
    public void VistaHistorial(ActionEvent event) throws SQLException, IOException {

        GestorCaso logica = new GestorCaso();
        String numero;
        boolean existe;

        try {
            numero = inputNumeroCasoBuscar.getText();
            existe = logica.comprobarRegistro(Integer.parseInt(numero));

            if (existe) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/VistaListadoCasosHistorial.fxml"));
                loader.load();
                Parent root = loader.getRoot();

                Scene scene = new Scene(root);

                scene.getStylesheets().add("/styles/AdminPage.css");

                Image icon = new Image("/icons/usericon4.png");

                stage.getIcons().add(icon);

                stage.setResizable(false);
                stage.sizeToScene();

                stage.setTitle("Historial de Casos");

                stage.setScene(scene);
                stage.show();

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GestorJuez logicaLista = new GestorJuez();

        try {
            Juez tmpJuez = logicaLista.buscar();

            adminname.setText(tmpJuez.getNombre() + " " + tmpJuez.getApellidos());
            adminid.setText(tmpJuez.getCedula());

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
