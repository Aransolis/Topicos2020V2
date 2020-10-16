package sample.ui;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
    private Integer[] cordTarjetas;

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
    ;
    private ArrayList<Integer> coordTarjetas = new ArrayList<>();
    ;

    private Scene escena;
    private int noPares;
    private int dimensionGdp[] = new int[2];
    int contador = 0;
    int numIntentos = 5;
    int validaTerminar = 0;


    public Memorama() {
        CrearUI();
        this.setTitle("Memorama : ");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        lblTarjetas = new Label("Numero de Tarjetas");
        txtNoTarjetas = new TextField();
        btnAceptar = new Button("Voltear y revolver");
        lblIntentos = new Label("Numero de intentos: " + numIntentos);
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

        escena = new Scene(vBox, 600, 500);

    }

    @Override
    public void handle(Event event) {
        numIntentos = 5;
        noPares = Integer.parseInt(txtNoTarjetas.getText());
        if (noPares < 8 && noPares> 1) {
            vBox.getChildren().remove(gdpMesa);
            dimensionGdp = obtenerTamanio(noPares);

            gdpMesa = new GridPane();
            arAsignacion = new String[dimensionGdp[0]][dimensionGdp[1]]; //arreglo del gdp
            revolver();
            arTarjetas = new Button[dimensionGdp[0]][dimensionGdp[1]];

            for (int i = 0; i < dimensionGdp[0]; i++) {
                for (int j = 0; j < dimensionGdp[1]; j++) {
                    lblIntentos.setText("Numero de intentos: " + (numIntentos));
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
            System.out.println("INGRESAR NUMERO DE 1 AL 7");
        }
    }

    private int[] obtenerTamanio(Integer numeroPares) {
        int tamanio[] = new int[2];

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
            tamanio[0] = 4;
            tamanio[1] = 3;
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

            coordTarjetas.add(finalI);
            coordTarjetas.add(finalJ);
            nombTarjetas.add(arAsignacion[finalI][finalJ]);
            contador++;
        } else {
            bandera = revisarTarjetas(nombTarjetas);
            nombTarjetas.clear();
            if (bandera == false) {
                Image ima = new Image("sample/assets/question.jpg");
                ImageView im = new ImageView(ima);
                im.setFitHeight(100);
                im.setPreserveRatio(true);

                Image imagen = new Image("sample/assets/question.jpg");
                ImageView imgg = new ImageView(imagen);
                imgg.setFitHeight(100);
                imgg.setPreserveRatio(true);

                arTarjetas[coordTarjetas.get(0)][coordTarjetas.get(1)].setGraphic(im);
                arTarjetas[coordTarjetas.get(0)][coordTarjetas.get(1)].setPrefSize(80, 100);
                arTarjetas[coordTarjetas.get(2)][coordTarjetas.get(3)].setGraphic(imgg);
                arTarjetas[coordTarjetas.get(2)][coordTarjetas.get(3)].setPrefSize(80, 100);
                numIntentos = numIntentos - 1;
                lblIntentos.setText("Numero de intentos: " + (numIntentos));
                coordTarjetas.removeAll(coordTarjetas);
                if (numIntentos == 0) {
                    System.out.println("Mala suerte");
                    Memorama.this.close();
                    new Memorama();
                }
            } else {
                //  System.out.println("SIGA");
            }
            contador = 0;
        }
    }

    private Boolean revisarTarjetas(ArrayList<String> comparar) {
        Boolean flag = null;
        if (comparar.get(0).contentEquals(comparar.get(1))) {
            coordTarjetas.removeAll(coordTarjetas);
            System.out.println("Son las mismas tarjetas!");
            validaTerminar = validaTerminar + 1;
            if (validaTerminar == Integer.parseInt(txtNoTarjetas.getText())) {
                System.out.println("¡Felicidades ha terminado!");
                this.close();
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

        for (int i = 0; i < dimensionGdp[1]; ) {
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
