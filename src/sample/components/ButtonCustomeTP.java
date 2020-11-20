package sample.components;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import sample.models.TipoPlatilloDAO;
import sample.ui.FrmTipoPlatillo;

import java.util.Optional;

public class ButtonCustomeTP extends TableCell<TipoPlatilloDAO, String> {
    private TipoPlatilloDAO objTPDAO;
    private Button btnCelda;

    public ButtonCustomeTP(int opc) {
        if (opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objTPDAO = ButtonCustomeTP.this.getTableView().getItems().get(ButtonCustomeTP.this.getIndex());
                new FrmTipoPlatillo(ButtonCustomeTP.this.getTableView(), objTPDAO);
            });
        } else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Mensajes del sistema: ");
                alerta.setHeaderText("Confirmando Accion");
                alerta.setContentText("¿Realmente deseas borrar el registro?");
                Optional<ButtonType> result = alerta.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //Obtenemos el objeto de tipo Platillos  de acuerdo al renglon seleccionado
                    objTPDAO = ButtonCustomeTP.this.getTableView().getItems().get(ButtonCustomeTP.this.getIndex());
                    objTPDAO.delTipo();

                    //Actualizamos eñ TableView
                    ButtonCustomeTP.this.getTableView().setItems(objTPDAO.getAllTipo());
                    ButtonCustomeTP.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty)
            setGraphic(btnCelda);
    }
}

