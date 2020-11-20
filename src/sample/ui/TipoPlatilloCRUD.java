package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.components.ButtonCustomeTP;
import sample.models.TipoPlatilloDAO;

public class TipoPlatilloCRUD extends Stage {

    private VBox vBox;
    private TableView<TipoPlatilloDAO> tbvTipoPlatillos;
    private Button btnNuevo;

    private Scene escena;
    private TipoPlatilloDAO objTPDAO;

    public TipoPlatilloCRUD(){
        objTPDAO = new TipoPlatilloDAO();
        CrearUI();
        this.setTitle("Administracion de Tipo de Platillos");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        tbvTipoPlatillos = new TableView<>();
        CrearTabla();
        btnNuevo = new Button("Nuevo Tipo de Platillo");
       btnNuevo.setOnAction(event -> {new FrmTipoPlatillo(tbvTipoPlatillos, null);});
        vBox = new VBox();
        vBox.getChildren().addAll(tbvTipoPlatillos,btnNuevo);
        escena = new Scene(vBox,400,300);
    }

    private void CrearTabla() {
        TableColumn<TipoPlatilloDAO, Integer> tbcIdTipoP = new TableColumn<>("ID");
        tbcIdTipoP.setCellValueFactory(new PropertyValueFactory<>("id_tipo"));

        TableColumn<TipoPlatilloDAO, String> tbcDscTipo = new TableColumn<>("Descripcion Tipo");
        tbcDscTipo.setCellValueFactory(new PropertyValueFactory<>("dsc_tipo"));

        TableColumn<TipoPlatilloDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>() {
                    @Override
                    public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param) {
                        return new ButtonCustomeTP(1);
                    }
                }
        );
        TableColumn<TipoPlatilloDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<TipoPlatilloDAO, String>, TableCell<TipoPlatilloDAO, String>>() {
                    @Override
                    public TableCell<TipoPlatilloDAO, String> call(TableColumn<TipoPlatilloDAO, String> param) {
                        return new ButtonCustomeTP(2);

                    }
                }
        );

        tbvTipoPlatillos.getColumns().addAll(tbcIdTipoP, tbcDscTipo, tbcEditar, tbcBorrar);
        tbvTipoPlatillos.setItems(objTPDAO.getAllTipo());
    }


}
