package sample.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.models.TipoPlatilloDAO;

public class FrmTipoPlatillo extends Stage {
    private TextField txtDescripcion;
    private Button btnGuardar;
    private VBox vBox;
    private Scene escena;
    private TipoPlatilloDAO objTipoPlatillos;
    private TableView<TipoPlatilloDAO> tbvTipoPlatillos;

    public FrmTipoPlatillo(TableView<TipoPlatilloDAO> tbvTipoPlatillos, TipoPlatilloDAO objTipoPlatillos) {
        if (objTipoPlatillos != null)
            this.objTipoPlatillos = objTipoPlatillos;
        else
            this.objTipoPlatillos = new TipoPlatilloDAO();

        CrearUI();
        this.setTitle("Gestion de Platillos");
        this.setScene(escena);
        this.show();
        this.tbvTipoPlatillos = tbvTipoPlatillos;

    }

    private void CrearUI() {
        txtDescripcion = new TextField();
        txtDescripcion.setText(objTipoPlatillos.getDsc_tipo());

        btnGuardar = new Button("Guardar Tipo Platillo");
        btnGuardar.setOnAction(event -> Guardar());
        vBox = new VBox();
        vBox.getChildren().addAll(txtDescripcion, btnGuardar);
        escena = new Scene(vBox, 300, 250);
    }

    private void Guardar() {
        objTipoPlatillos.setDsc_tipo(txtDescripcion.getText());

        if (objTipoPlatillos.getId_tipo()>=1) { //PROCESO DE ACTUALIZAR PLATILLO
            objTipoPlatillos.updTipo();
        } else {                    //PROCESO PARA NUEVO PLATILLO
            objTipoPlatillos.insTipo();
        }

        tbvTipoPlatillos.setItems(objTipoPlatillos.getAllTipo());
        tbvTipoPlatillos.refresh();
        this.close();
    }
}

