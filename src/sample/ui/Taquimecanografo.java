package sample.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.events.EventosTaqui;
import javafx.scene.layout.Region;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;


public class Taquimecanografo extends Stage implements EventHandler<KeyEvent> , Initializable {
    //VARIABLES
    private String archivo = "";
    private Label lblContaErrores;
    private Label lblbErroes;
    private Label lblNumeroPalabras, lblpalabras;
    private Label lblSegundos, lblTiempo;
    Integer contador = 0;
    String texto;
    private static final int STARTTIME = 0;
    private Timeline timeline;
    private final IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);

    //Arreglo para etiquetar el teclado
    private String arLblBtn1[] = {"ESC", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", "SUPR"};
    private String arLblBtn2[] = {"|", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "?", "¿", "BACKSPACE"};
    private String arLblBtn3[] = {"TAB", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "´´", "+", "}"};
    private String arLblBtn4[] = {"BLOQ MAYUS", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ñ", "{", "ENTER"};
    private String arLblBtn5[] = {"SHIFT", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "-"}; //11
    private String arLblBtn6[] = {"CTRL", "ALT", "                     ESPACIO                      ", "<>"};


    //Elementos para el toolbar
    private ToolBar tlbMenu;
    private Button btnAbrir;


    //Elementos para la escritura
    private TextArea txtContenido, txtEscritura;

    //Elementos para el teclado
    private HBox[] arHBoxTeclas = new HBox[6];
    private HBox hBoxContador;
    private VBox vBoxTeclado;
    public static Button[] arBtnTeclado1 = new Button[14];
    public static Button[] arBtnTeclado2 = new Button[14];
    public static Button[] arBtnTeclado3 = new Button[14];
    public static Button[] arBtnTeclado4 = new Button[13];
    public static Button[] arBtnTeclado5 = new Button[11];
    public static Button[] arBtnTeclado6 = new Button[4];

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
        crearhBoxIntermedio();

        txtContenido.setId("txt1");
        txtEscritura.setId("txt2");
        vBoxPrincipal = new VBox();
        vBoxPrincipal.getChildren().addAll(tlbMenu, txtContenido, hBoxContador, txtEscritura, vBoxTeclado);
        vBoxPrincipal.setSpacing(10);
        vBoxPrincipal.setPadding(new Insets(10));
        escena = new Scene(vBoxPrincipal, 800, 700);

        escena.getStylesheets().add("sample/assets/css/Taquimecanografo_styles.css");
    }

    private void crearhBoxIntermedio() {
        lblContaErrores = new Label("Numero De Errores: ");
        lblbErroes = new Label();
        lblNumeroPalabras = new Label("Numero De Palabras: ");
        lblpalabras = new Label();
        lblTiempo = new Label("Tiempo: ");
        lblSegundos = new Label();

        lblContaErrores.setId("lbl1");
        lblNumeroPalabras.setId("lbl2");
        lblSegundos.setId("lbl3");
        lblTiempo.setId("lbl4");
        lblbErroes.setId("lbl5");
        lblpalabras.setId("lbl6");

        hBoxContador = new HBox();
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        hBoxContador.getChildren().addAll(lblContaErrores, lblbErroes, region1, lblTiempo, lblSegundos, region2, lblNumeroPalabras, lblpalabras);
        hBoxContador.setPadding(new Insets(0,20,0,20));
    }

    private void CrearTeclado() {

        vBoxTeclado = new VBox();
        vBoxTeclado.setSpacing(7);

        for (int i = 0; i < arHBoxTeclas.length; i++) {
            arHBoxTeclas[i] = new HBox();
            arHBoxTeclas[i].setSpacing(7);
            arHBoxTeclas[i].setAlignment(Pos.CENTER);
        }
        for (int i = 0; i < arBtnTeclado1.length; i++) {
            arBtnTeclado1[i] = new Button(arLblBtn1[i]);
            arBtnTeclado1[i].setStyle("-fx-background-color: #48d1cc;");
            arBtnTeclado1[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            arBtnTeclado2[i] = new Button(arLblBtn2[i]);
            arBtnTeclado2[i].setStyle("-fx-background-color: #48d1cc;");
            arBtnTeclado2[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            arBtnTeclado3[i] = new Button(arLblBtn3[i]);
            arBtnTeclado3[i].setStyle("-fx-background-color: #48d1cc;");
            arBtnTeclado3[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            arHBoxTeclas[0].getChildren().add(arBtnTeclado1[i]);
            arHBoxTeclas[0].setHgrow(arBtnTeclado1[i], Priority.ALWAYS);

            arHBoxTeclas[1].getChildren().add(arBtnTeclado2[i]);
            arHBoxTeclas[1].setHgrow(arBtnTeclado2[i], Priority.ALWAYS);

            arHBoxTeclas[2].getChildren().add(arBtnTeclado3[i]);
            arHBoxTeclas[2].setHgrow(arBtnTeclado3[i], Priority.ALWAYS);
        }

        for (int i = 0; i < arBtnTeclado4.length; i++) {
            arBtnTeclado4[i] = new Button(arLblBtn4[i]);
            arBtnTeclado4[i].setStyle("-fx-background-color: #48d1cc;");
            arBtnTeclado4[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            arHBoxTeclas[3].getChildren().add(arBtnTeclado4[i]);
            arHBoxTeclas[3].setHgrow(arBtnTeclado4[i], Priority.ALWAYS);


        }
        for (int i = 0; i < arBtnTeclado5.length; i++) {
            arBtnTeclado5[i] = new Button(arLblBtn5[i]);
            arBtnTeclado5[i].setStyle("-fx-background-color: #48d1cc;");
            arBtnTeclado5[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            arHBoxTeclas[4].getChildren().add(arBtnTeclado5[i]);
            arHBoxTeclas[4].setHgrow(arBtnTeclado5[i], Priority.ALWAYS);
        }


        for (int i = 0; i < arBtnTeclado6.length; i++) {
            arBtnTeclado6[i] = new Button(arLblBtn6[i]);
            arBtnTeclado6[i].setStyle("-fx-background-color: #48d1cc;");
            arBtnTeclado6[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            arHBoxTeclas[5].getChildren().add(arBtnTeclado6[i]);
            arHBoxTeclas[5].setHgrow(arBtnTeclado6[i], Priority.ALWAYS);

        }

        vBoxTeclado.getChildren().addAll(arHBoxTeclas[0], arHBoxTeclas[1], arHBoxTeclas[2], arHBoxTeclas[3], arHBoxTeclas[4], arHBoxTeclas[5]);
        vBoxTeclado.setAlignment(Pos.CENTER);
    }

    private void CrearEscritura() {
        texto = "";
        txtContenido = new TextArea();
        txtContenido.setEditable(false);
        txtContenido.setPrefRowCount(11);
        txtEscritura = new TextArea();
        txtEscritura.setVisible(false);
        txtEscritura.setPrefRowCount(9);
        txtEscritura.setOnKeyPressed(this);
        txtEscritura.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(lblSegundos.getText().equals("")) {
                    btnAbrir.setDisable(true);
                    timeline = new Timeline(new KeyFrame(Duration.seconds(1), evt -> actualizarTiempo()));
                    timeline.setCycleCount(Animation.INDEFINITE); // repeat over and over again
                    timeSeconds.set(STARTTIME);
                    timeline.play();
                    lblSegundos.textProperty().bind(timeSeconds.asString());
                }
                else {
                   // System.out.println("hoaaa");
                }
            }
        });
        txtEscritura.addEventHandler(KeyEvent.KEY_RELEASED, new EventosTaqui());


    }

    private void verificarFinal() {
        String txt1, txt2;
        txt1 = txtEscritura.getText().replaceAll("\n", "");
        txt2 = txtContenido.getText().replaceAll("\n", "");

        if (txt1.contentEquals(txt2)) {
            timeline.stop();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Dialogo Informativo");
            alert.setHeaderText("Ha Finalizado La Escritura Del Texto");
            alert.setContentText("Cantidad Errores: " + contador + "\nDuracion De Tiempo: " + lblSegundos.getText() + " segundos \nCantidad De Palabras: " +
                lblpalabras.getText());
            alert.showAndWait();
            this.close();
        }
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
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setTitle("Abrir archivo... ");
                File file = fileChooser.showOpenDialog(this);
                archivo = file.getAbsoluteFile().toString();
                if (archivo != null) {
                    fileToTextfield(archivo);
                    txtEscritura.setVisible(true);
                }
                break;
        }
    }

    private void fileToTextfield(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null)
                if (txtContenido.getText().equals("")) {
                    txtContenido.setText(linea);
                } else {
                    txtContenido.setText(txtContenido.getText() + "\n" + linea);
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void actualizarTiempo() {
        // incrementa segundos
        int seconds = timeSeconds.get();
        timeSeconds.set(seconds+1);
    }

    public void initialize(URL url, ResourceBundle rb) {

       // lblSegundos.textProperty().bind(timeSeconds.asString());
    }
    @Override
    public void handle(KeyEvent event) {

        switch (event.getCode().toString()) {
            case "DELETE":
                arBtnTeclado1[13].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "ESCAPE":
                arBtnTeclado1[0].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "BACK_SPACE":
                arBtnTeclado2[13].setStyle("-fx-background-color:  #4169e1;");
                contador++;
                lblbErroes.setText(contador.toString());
                lblContaErrores.setText("Numero De Errores: " );
                texto = txtEscritura.getText();
                StringTokenizer st = new StringTokenizer(texto);
                lblpalabras.setText(Integer.toString(st.countTokens()));
                break;
            case "DIGIT1":
                arBtnTeclado2[1].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT2":
                arBtnTeclado2[2].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT3":
                arBtnTeclado2[3].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT4":
                arBtnTeclado2[4].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT5":
                arBtnTeclado2[5].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT6":
                arBtnTeclado2[6].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT7":
                arBtnTeclado2[7].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT8":
                arBtnTeclado2[8].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT9":
                arBtnTeclado2[9].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DIGIT0":
                arBtnTeclado2[10].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "QUOTE":
                arBtnTeclado2[11].setStyle("-fx-background-color:  #4169e1;");
                break;

            case "UNDEFINED":
                arBtnTeclado2[12].setStyle("-fx-background-color:  #4169e1;");
                break;
            //
            case "TAB":
                arBtnTeclado3[0].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "Q":
                arBtnTeclado3[1].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "W":
                arBtnTeclado3[2].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "E":
                arBtnTeclado3[3].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "R":
                arBtnTeclado3[4].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "T":
                arBtnTeclado3[5].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "Y":
                arBtnTeclado3[6].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "U":
                arBtnTeclado3[7].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "I":
                arBtnTeclado3[8].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "O":
                arBtnTeclado3[9].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "P":
                arBtnTeclado3[10].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "DEAD_ACUTE":
                arBtnTeclado3[11].setStyle("-fx-background-color:  #4169e1;");
                break;

            case "PLUS":
                arBtnTeclado3[12].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "BRACERIGHT":
                arBtnTeclado3[13].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "CAPS":
                arBtnTeclado4[0].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "A":
                arBtnTeclado4[1].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "S":
                arBtnTeclado4[2].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "D":
                arBtnTeclado4[3].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "F":
                arBtnTeclado4[4].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "G":
                arBtnTeclado4[5].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "H":
                arBtnTeclado4[6].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "J":
                arBtnTeclado4[7].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "K":
                arBtnTeclado4[8].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "L":
                arBtnTeclado4[9].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "Ñ":
                arBtnTeclado4[10].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "BRACELEFT":
                arBtnTeclado4[11].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "ENTER":
                arBtnTeclado4[12].setStyle("-fx-background-color:  #4169e1;");
                verificarFinal();
                texto = txtEscritura.getText();
                StringTokenizer tr = new StringTokenizer(texto);
                lblpalabras.setText(Integer.toString(tr.countTokens()));

                break;
            case "SHIFT":
                arBtnTeclado5[0].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "Z":
                arBtnTeclado5[1].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "X":
                arBtnTeclado5[2].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "C":
                arBtnTeclado5[3].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "V":
                arBtnTeclado5[4].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "B":
                arBtnTeclado5[5].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "N":
                arBtnTeclado5[6].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "M":
                arBtnTeclado5[7].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "COMMA":
                arBtnTeclado5[8].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "PERIOD":
                arBtnTeclado5[9].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "MINUS":
                arBtnTeclado5[10].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "CONTROL":
                arBtnTeclado6[0].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "ALT":
                arBtnTeclado6[1].setStyle("-fx-background-color:  #4169e1;");
                break;
            case "SPACE":
                arBtnTeclado6[2].setStyle("-fx-background-color:  #4169e1;");
                texto = txtEscritura.getText();
                StringTokenizer ts = new StringTokenizer(texto);
                lblpalabras.setText(Integer.toString(ts.countTokens()));
                break;
            case "LESS":
                arBtnTeclado6[3].setStyle("-fx-background-color:  #4169e1;");
                break;
        }

    }
}
