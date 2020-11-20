package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.PlatillosDAO;
import sample.models.TipoPlatilloDAO;

public class FrmPlatillos extends Stage {

    private TextField txtPlatillos, txtPrecio;
    private ComboBox<TipoPlatilloDAO> cbxTipo;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private PlatillosDAO objPlatillo;
    private TableView<PlatillosDAO> tbvPlatillos;

    public FrmPlatillos(TableView<PlatillosDAO> tbvPlatillos, PlatillosDAO objPlatillo) {
        if (objPlatillo != null) {
            this.objPlatillo = objPlatillo;
        }
        else {
            this.objPlatillo = new PlatillosDAO();
        }
        CrearUI();
        this.setTitle("Gestion de Platillos");
        this.setScene(escena);
        this.show();
        this.tbvPlatillos = tbvPlatillos;

    }

    private void CrearUI() {
        txtPlatillos = new TextField();
        txtPlatillos.setText(objPlatillo.getNombre_platillo());
        txtPrecio = new TextField();
        txtPrecio.setText(objPlatillo.getPrecio()+"");

        cbxTipo = new ComboBox<>();
        cbxTipo.setItems(new TipoPlatilloDAO().getAllTipo());

        btnGuardar = new Button("Guardar Platillo");
        btnGuardar.setOnAction(event -> Guardar());
        vBox = new VBox();
        vBox.getChildren().addAll(txtPlatillos, txtPrecio, cbxTipo, btnGuardar);
        escena = new Scene(vBox, 300, 250);
    }

    private void Guardar() {
        objPlatillo.setNombre_platillo(txtPlatillos.getText());
        objPlatillo.setPrecio(Float.parseFloat(txtPrecio.getText()));
        objPlatillo.setId_tipo(1); //Valor fijo temporalmente

        if (objPlatillo.getId_platillo() >= 1) { //PROCESO DE NUEVO PLATILLO
            objPlatillo.updPlatillo();
        } else {                    //PROCESO PARA ACTUALIZAR PLATILLO
            objPlatillo.insPlatillo();
        }

        tbvPlatillos.setItems(objPlatillo.getAllPlatillo());
        tbvPlatillos.refresh();
        this.close();
    }
}
