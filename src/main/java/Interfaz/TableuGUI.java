package Interfaz;

import Cartas.CartaGUI;
import Cartas.CartaInglesa;
import javafx.scene.Cursor;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class TableuGUI {
    public StackPane tableu = new StackPane();
    public double anchoCarta, altoCarta;

    //Constructor de la clase TableuGUI
    public TableuGUI(){
        anchoCarta = 50;
        altoCarta = 100;

        tableu.setPrefSize(anchoCarta, altoCarta+6*30);
        tableu.setMinWidth(anchoCarta);
        tableu.setMaxWidth(anchoCarta);
        tableu.setMinSize(anchoCarta, altoCarta);
        tableu.setPickOnBounds(true);
        tableu.setCursor(Cursor.HAND);
    }

    //Metodo que se encarga de regresar la interfaz gráfica de las cartas en un StackPane
    //Recibe como argumento un arraylist de CartaInglesa para poder generar así su imagen
    public StackPane getTableu(ArrayList<CartaInglesa> cartas){
        tableu.getChildren().clear();
        int y = 0;
        StackPane stackCartas = new StackPane();

        for (CartaInglesa carta : cartas) {
            CartaGUI cartaGUI = new CartaGUI(carta);
            StackPane cartaPane = cartaGUI.getPane();
            cartaPane.setTranslateY(y);
            cartaPane.setUserData(carta);
            stackCartas.getChildren().add(cartaPane);
            y += carta.isFaceup() ? 25 : 30;
        }

        tableu.getChildren().add(stackCartas);
        return stackCartas;
    }

    //Este metodo nos regresa un solo tableu en su versión de Pane
    public StackPane obtenerTableu(){
        return tableu;
    }
}
