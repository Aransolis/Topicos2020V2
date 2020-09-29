package sample.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import sample.events.EventosMemorama;

import javax.swing.*;


public class Memorama extends Stage implements EventHandler {

    private Label lblTarjetas;
    private TextField txtNoTarjetas;
    private Button btnAceptar;
    private HBox hBox;
    private VBox vBox;
    private GridPane gdpMesa;

    private Button[][] arTarjetas;        //se puede realizar arreglo de botones

    private Scene escena;
    private int noPares;

    public Memorama(){
        CrearUI();
        this.setTitle("Memorama : ");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        lblTarjetas = new Label("Numero de Tarjetas");
        txtNoTarjetas = new TextField();
        btnAceptar = new Button("Voltear y revolver");
      btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
      //----Para mandar diferentes mensajes opcion 2 en una clase diferente mandando un argumento int----

      /*btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventosMemorama()); // el evento es llamado de otro paquete/clase
        btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Tercer evento desde una clase anonima");
            }
        });
*/
        hBox = new HBox();
        hBox.getChildren().addAll(lblTarjetas, txtNoTarjetas,btnAceptar);
        hBox.setSpacing(10);

        gdpMesa = new GridPane();
        vBox = new VBox();
        vBox.getChildren().addAll(hBox,gdpMesa);

        escena = new Scene(vBox,500,500);
    }

    @Override
    public void handle(Event event) {
        noPares = Integer.parseInt(txtNoTarjetas.getText());
        vBox.getChildren().remove(gdpMesa);

        gdpMesa = new GridPane();
        arTarjetas = new Button[2][noPares];
        for (int i = 0; i<2; i++){
            for(int j =0; j<noPares; j++){
                arTarjetas[i][j] = new Button("X");
                gdpMesa.add(arTarjetas[i][j],j,i);
            }
        }
        vBox.getChildren().add(gdpMesa);
    }
}
