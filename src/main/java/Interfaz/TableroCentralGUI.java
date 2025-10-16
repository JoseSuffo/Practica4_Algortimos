package Interfaz;

import Logica.TableauDeck;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class TableroCentralGUI {
    public HBox tablero = new HBox(16);
    public TableuGUI[] tableaus = new TableuGUI[7];

    //Constructor de la clase TableroCentralGUI
    public TableroCentralGUI() {
        for(int i = 0; i < tableaus.length; i++){
            TableuGUI tableu = new TableuGUI();
            tableaus[i] = tableu;
            tablero.getChildren().add(tableu.obtenerTableu());
        }

        tablero.setAlignment(Pos.TOP_CENTER);
        tablero.setFillHeight(false);
        tablero.setPadding(new Insets(10,16,16,16));
    }

    //Metodo que regresa un tableu en especifico del tablero
    public Pane getPane(int index){
        return tableaus[index].obtenerTableu();
    }

    //Metodo que dibuja todos los tableus en GUI y los regresa como un ArrayList de StackPane
    public StackPane[] dibujar(ArrayList<TableauDeck> tableau){
        StackPane[] pane = new StackPane[7];
        for(int i = 0; i < 7 && i < tableau.size(); i++){
            var cartas = tableau.get(i);
            pane[i] = tableaus[i].getTableu(cartas.getCards());
        }
        return pane;
    }

    //Metodo que regresa todo el tablero de juego
    public HBox getHBox() {
        return tablero;
    }
}