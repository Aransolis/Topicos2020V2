package sample.ui;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;


public class Memorama extends Stage implements EventHandler {

    private String[] arImagenes = {"horse.png", "lion.jpg", "monkey.png", "pajaro.png", "pig.png", "tigre.jpg", "turtle.png"};

    private Label lblTarjetas;
    private Label lblIntentos;
    private TextField txtNoTarjetas;
    private Button btnAceptar;
    private HBox hBox;
    private VBox vBox;
    private GridPane gdpMesa;

    private Button[][] arTarjetas;        //se puede realizar arreglo de botones
    private String[][] arAsignacion;
    private ArrayList<String> nombTarjetas = new ArrayList<>();
    private ArrayList<Integer> coordTarjetas = new ArrayList<>();


    private Scene escena;
    private int noPares;
    private Integer dimensionGdp[] = new Integer[2]; //arreglo para definir tamaño gdp
    int contador = 0;
    int numIntentos = 5;
    int validaTerminar = 0;



    public Memorama() {
        CrearUI();
        this.setTitle("Memorama : ");
        this.setScene(escena);
      //  this.setHeight(200);
        this.show();
    }

    private void CrearUI() {
        lblTarjetas = new Label("Numero de Tarjetas");
        txtNoTarjetas = new TextField();
        btnAceptar = new Button("Voltear y revolver");
        lblIntentos = new Label("Numero de intentos: " + numIntentos); //lbl para mostrar numero de intentos disponibles
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
        hBox.getChildren().addAll(lblTarjetas, txtNoTarjetas, btnAceptar);
        hBox.setSpacing(10);

        gdpMesa = new GridPane();
        vBox = new VBox();
        vBox.getChildren().addAll(hBox, lblIntentos, gdpMesa);

        escena = new Scene(vBox, 700, 480);
        escena.getStylesheets().add("sample/assets/css/Memorama_styles.css");

    }

    @Override
    public void handle(Event event) {
        btnAceptar.setDisable(true);
        txtNoTarjetas.setDisable(true);

        numIntentos = 5;
        noPares = Integer.parseInt(txtNoTarjetas.getText());

        if (noPares < 8 && noPares > 1) {            //condicion para que solo acepte # 1-7
            vBox.getChildren().remove(gdpMesa);

            dimensionGdp = obtenerTamanio(noPares);  // mando llamar metodo para definir tamaño gdp
            gdpMesa = new GridPane();
            arAsignacion = new String[dimensionGdp[0]][dimensionGdp[1]]; //arreglo del gdp
            revolver();
            arTarjetas = new Button[dimensionGdp[0]][dimensionGdp[1]];


            for (int i = 0; i < dimensionGdp[0]; i++) {
                for (int j = 0; j < dimensionGdp[1]; j++) {
                    lblIntentos.setText("Numero de intentos: " + (numIntentos)); //modifico lbl cada ciclo
                    Image img = new Image("sample/assets/question.jpg");
                    ImageView imv = new ImageView(img);
                    imv.setFitHeight(100);
                    imv.setPreserveRatio(true);
                    arTarjetas[i][j] = new Button();
                    int finalI = i;
                    int finalJ = j;
                    arTarjetas[i][j].setGraphic(imv);
                    arTarjetas[i][j].setPrefSize(80, 100);

                    arTarjetas[i][j].setOnAction(event1 -> verTarjeta(finalI, finalJ));
                    gdpMesa.add(arTarjetas[i][j], j, i);

                }
            }
            vBox.getChildren().add(gdpMesa);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);

            alert.setTitle("Dialogo Informativo");
            alert.setHeaderText("Cantidad Erronea!");
            alert.setContentText("Ingrese un numero de 2 a 7");
            alert.showAndWait();
            btnAceptar.setDisable(false);
            txtNoTarjetas.setDisable(false);
        }
    }

    private Integer[] obtenerTamanio(Integer numeroPares) { //metodo para definir tamaño gdp
        Integer tamanio[] = new Integer[2];

        if (numeroPares == 2) {
            tamanio[0] = 2;
            tamanio[1] = 2;

        } else if (numeroPares == 3) {
            tamanio[0] = 3;
            tamanio[1] = 2;

        } else if (numeroPares == 4) {
            tamanio[0] = 2;
            tamanio[1] = 4;
        } else if (numeroPares == 5) {
            tamanio[0] = 2;
            tamanio[1] = 5;
        } else if (numeroPares == 6) {
            tamanio[0] = 3;
            tamanio[1] = 4;
        } else if (numeroPares == 7) {
            tamanio[0] = 2;
            tamanio[1] = 7;
        }

        return tamanio;

    }

    private void verTarjeta(int finalI, int finalJ) {
        Boolean bandera = null;

        if (contador < 2) {
            Image img = new Image("sample/assets/" + arAsignacion[finalI][finalJ]);
            ImageView imv = new ImageView(img);
            imv.setFitHeight(100);
            imv.setFitWidth(80);
            imv.setPreserveRatio(true);
            arTarjetas[finalI][finalJ].setGraphic(imv);

            coordTarjetas.add(finalI); //en este arraylist guardo posicion x y y de gdp
            coordTarjetas.add(finalJ);
            nombTarjetas.add(arAsignacion[finalI][finalJ]); // en este gdp guardo el nombre de targeta(nombre de imagen animal)
            contador++;
        } else {
            bandera = revisarTarjetas(nombTarjetas);// reviso si son las mismas targetas comparando nombre de imagen
            nombTarjetas.clear();
            if (bandera == false) { // si es falso, mando question image a las imagenes seleccionadas
                Image ima = new Image("sample/assets/question.jpg");
                ImageView im = new ImageView(ima);
                im.setFitHeight(100);
                im.setPreserveRatio(true);

                Image imagen = new Image("sample/assets/question.jpg");
                ImageView imgg = new ImageView(imagen);
                imgg.setFitHeight(100);
                imgg.setPreserveRatio(true);

                arTarjetas[coordTarjetas.get(0)][coordTarjetas.get(1)].setGraphic(im); //asigno question image a botones
                arTarjetas[coordTarjetas.get(0)][coordTarjetas.get(1)].setPrefSize(80, 100);
                arTarjetas[coordTarjetas.get(2)][coordTarjetas.get(3)].setGraphic(imgg);
                arTarjetas[coordTarjetas.get(2)][coordTarjetas.get(3)].setPrefSize(80, 100);
                numIntentos = numIntentos - 1; //decremento numero de intentos
                lblIntentos.setText("Numero de intentos: " + (numIntentos)); //modifico label
                coordTarjetas.removeAll(coordTarjetas); //vacio arraylist
                if (numIntentos == 0) { //si contador igual a 0, cierra juego y manda mensaje
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Dialogo Informativo");
                    alert.setHeaderText("¡Suerte a la proxima!");
                    alert.setContentText("Intente de nuevo");
                    alert.showAndWait();
                    Memorama.this.close();
                    new Memorama();
                }
            } else {
                //  System.out.println("SIGA");
            }
            contador = 0;
        }
    }

    private Boolean revisarTarjetas(ArrayList<String> comparar) { //metodo boolean que me verifica si ambos botones de gdp tienen misma imagen
        Boolean flag = null;
        if (comparar.get(0).contentEquals(comparar.get(1))) {
            coordTarjetas.removeAll(coordTarjetas);
            //System.out.println("Son las mismas tarjetas!");
            validaTerminar = validaTerminar + 1; //ayuda a terminar programa
            if (validaTerminar == Integer.parseInt(txtNoTarjetas.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dialogo Informativo");
                alert.setHeaderText("¡Felicidades!");
                alert.setContentText("Usted a Encontrado Todos Los Pares");
                alert.showAndWait();
                this.close();
                new Memorama();
            }
            flag = true;
        } else {
            flag = false;

        }
        return flag;
    }

    private void revolver() { //asignar nombre de imagen a cada objeto del gdp
        for (int i = 0; i < dimensionGdp[0]; i++)
            for (int j = 0; j < dimensionGdp[1]; j++) {
                arAsignacion[i][j] = new String(); //le da un valor "String" a cada espacio del gdp? para incializar
            }

        int posx, posy, cont = 0;
        List<String> stringList = Arrays.asList(arImagenes);
        Collections.shuffle(stringList);

        for (int i = 0; i < noPares; ) {
            posx = (int) (Math.random() * dimensionGdp[0]);
            posy = (int) (Math.random() * dimensionGdp[1]);
            if (arAsignacion[posx][posy].equals("")) {
                arAsignacion[posx][posy] = stringList.get(i);
                cont++;
            }
            if (cont == 2) { // Sirve para comprobar que la imagen se asignó 2 veces
                i++;
                cont = 0;
            }
        }
    }
}
