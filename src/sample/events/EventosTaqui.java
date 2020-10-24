package sample.events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import sample.ui.Taquimecanografo;
public class EventosTaqui implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent event) {

        switch (event.getCode().toString()) {
            case "DELETE":
                Taquimecanografo.arBtnTeclado1[13].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "ESCAPE":
                Taquimecanografo.arBtnTeclado1[0].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "BACK_SPACE":
                Taquimecanografo.arBtnTeclado2[13].setStyle("-fx-background-color: #48d1cc;");
                break;
            case "DIGIT1":
                Taquimecanografo.arBtnTeclado2[1].setStyle("-fx-background-color: #48d1cc;");
                break;
            case "DIGIT2":
                Taquimecanografo.arBtnTeclado2[2].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT3":
                Taquimecanografo.arBtnTeclado2[3].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT4":
                Taquimecanografo.arBtnTeclado2[4].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT5":
                Taquimecanografo.arBtnTeclado2[5].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT6":
                Taquimecanografo.arBtnTeclado2[6].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT7":
                Taquimecanografo.arBtnTeclado2[7].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT8":
                Taquimecanografo.arBtnTeclado2[8].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT9":
                Taquimecanografo.arBtnTeclado2[9].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DIGIT0":
                Taquimecanografo.arBtnTeclado2[10].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "QUOTE":
                Taquimecanografo.arBtnTeclado2[11].setStyle("-fx-background-color:  #48d1cc;");
                break;

            case "UNDEFINED":
                Taquimecanografo.arBtnTeclado2[12].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "TAB":
                Taquimecanografo.arBtnTeclado3[0].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "Q":
                Taquimecanografo.arBtnTeclado3[1].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "W":
                Taquimecanografo.arBtnTeclado3[2].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "E":
                Taquimecanografo.arBtnTeclado3[3].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "R":
                Taquimecanografo.arBtnTeclado3[4].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "T":
                Taquimecanografo.arBtnTeclado3[5].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "Y":
                Taquimecanografo.arBtnTeclado3[6].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "U":
                Taquimecanografo.arBtnTeclado3[7].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "I":
                Taquimecanografo.arBtnTeclado3[8].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "O":
                Taquimecanografo.arBtnTeclado3[9].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "P":
                Taquimecanografo.arBtnTeclado3[10].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "DEAD_ACUTE":
                Taquimecanografo.arBtnTeclado3[11].setStyle("-fx-background-color:  #48d1cc;");
                break;

            case "PLUS":
                Taquimecanografo.arBtnTeclado3[12].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "BRACERIGHT":
                Taquimecanografo.arBtnTeclado3[13].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "CAPS":
                Taquimecanografo.arBtnTeclado4[0].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "A":
                Taquimecanografo.arBtnTeclado4[1].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "S":
                Taquimecanografo.arBtnTeclado4[2].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "D":
                Taquimecanografo.arBtnTeclado4[3].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "F":
                Taquimecanografo.arBtnTeclado4[4].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "G":
                Taquimecanografo.arBtnTeclado4[5].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "H":
                Taquimecanografo.arBtnTeclado4[6].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "J":
                Taquimecanografo.arBtnTeclado4[7].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "K":
                Taquimecanografo.arBtnTeclado4[8].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "L":
                Taquimecanografo.arBtnTeclado4[9].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "Ñ":
                Taquimecanografo.arBtnTeclado4[10].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "BRACELEFT":
                Taquimecanografo.arBtnTeclado4[11].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "ENTER":
                Taquimecanografo.arBtnTeclado4[12].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "SHIFT":
                Taquimecanografo.arBtnTeclado5[0].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "Z":
                Taquimecanografo.arBtnTeclado5[1].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "X":
                Taquimecanografo.arBtnTeclado5[2].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "C":
                Taquimecanografo.arBtnTeclado5[3].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "V":
                Taquimecanografo.arBtnTeclado5[4].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "B":
                Taquimecanografo.arBtnTeclado5[5].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "N":
                Taquimecanografo.arBtnTeclado5[6].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "M":
                Taquimecanografo.arBtnTeclado5[7].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "COMMA":
                Taquimecanografo.arBtnTeclado5[8].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "PERIOD":
                Taquimecanografo.arBtnTeclado5[9].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "MINUS":
                Taquimecanografo.arBtnTeclado5[10].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "CONTROL":
                Taquimecanografo.arBtnTeclado6[0].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "ALT":
                Taquimecanografo.arBtnTeclado6[1].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "SPACE":
                Taquimecanografo.arBtnTeclado6[2].setStyle("-fx-background-color:  #48d1cc;");
                break;
            case "LESS":
                Taquimecanografo.arBtnTeclado6[3].setStyle("-fx-background-color:  #48d1cc;");
                break;

        }

    }
}