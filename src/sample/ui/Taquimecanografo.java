package sample.ui;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> {

    // Bandera para detectar cambios de color en las teclas
    boolean banColor = false;

    //Arreglo para etiquetar el teclado
    private String arLblBtn1[] = {"ESC", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "PWR"};
    private String arLblBtn2[] = {"|", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "'", "¿", "BS"};


    //Elementos para el toolbar
    private ToolBar tlbMenu;
    private Button btnAbrir;

    //Elementos para la escritura
    private TextArea txtContenido, txtEscritura;

    //Elementos para el teclado
    private HBox[] arHBoxTeclas = new HBox[6];
    private VBox vBoxTeclado;
    private Button[] arBtnTeclado1 = new Button[14];
    private Button[] arBtnTeclado2 = new Button[14];

    //Elementos de agrupacion global
    private VBox vBoxPrincipal;

    private Scene escena;

    public Taquimecanografo() {
        CrearUI();
        this.setTitle("Tutor de Taquimecanografia");
        this.setScene(escena);
        this.show();

    }

    private void CrearUI() {
        CrearToolbar();
        CrearEscritura();
        CrearTeclado();

        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(tlbMenu, txtContenido, txtEscritura, vBoxTeclado);
        vBoxPrincipal.setSpacing(10);
        vBoxPrincipal.setPadding(new Insets(10));
        escena = new Scene(vBoxPrincipal, 800, 500);

    }

    private void CrearTeclado() {

        vBoxTeclado = new VBox();
        vBoxTeclado.setSpacing(7);

        for (int i = 0; i < arHBoxTeclas.length; i++) {
            arHBoxTeclas[i] = new HBox();
            arHBoxTeclas[i].setSpacing(7);
        }

        for (int i = 0; i < arBtnTeclado1.length; i++) {
            arBtnTeclado1[i] = new Button(arLblBtn1[i]);
            arBtnTeclado1[i].setStyle("-fx-background-color: #dcdcdc;");
            arBtnTeclado2[i] = new Button(arLblBtn2[i]);
            arBtnTeclado2[i].setStyle("-fx-background-color: #dcdcdc;");
            arHBoxTeclas[0].getChildren().add(arBtnTeclado1[i]);
            arHBoxTeclas[1].getChildren().add(arBtnTeclado2[i]);
        }
        vBoxTeclado.getChildren().addAll(arHBoxTeclas[0], arHBoxTeclas[1]);

    }

    private void CrearEscritura() {
        txtContenido = new TextArea();
        txtContenido.setEditable(false);
        txtContenido.setPrefColumnCount(6);
        txtEscritura = new TextArea();
        txtEscritura.setPrefRowCount(6);
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.setOnKeyReleased(this);
    }

    private void CrearToolbar() {
        tlbMenu = new ToolBar();
        btnAbrir = new Button();
        btnAbrir.setOnAction(event -> eventoTaqui(1));
        btnAbrir.setPrefSize(35, 35);

        //Asignamos la imagen al boton dentro del toolbar
        Image img = new Image("sample/assets/abrir.png");
        ImageView imv = new ImageView(img);
        imv.setFitHeight(25);
        imv.setPreserveRatio(true);
        btnAbrir.setGraphic(imv);

        tlbMenu.getItems().addAll(btnAbrir);
    }

    private void eventoTaqui(int opc) {
        switch (opc) {
            case 1:
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Abrir archivo... ");
                File file = fileChooser.showOpenDialog(this);
                break;
        }
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode().toString()) {
            case "BACK_SPACE":
                if (banColor == false)
                    arBtnTeclado2[13].setStyle("-fx-background-color:  #4169e1;");
                else
                    arBtnTeclado2[13].setStyle("-fx-background-color: #dcdcdc;");
                break;
        }
        banColor = !banColor;
    }
}
