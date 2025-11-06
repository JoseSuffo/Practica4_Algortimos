package Interfaz;

import Cartas.CartaGUI;
import Cartas.CartaInglesa;
import Logica.FoundationDeck;
import Logica.TableauDeck;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class TableroCentralGUI {
    public HBox tablero = new HBox(16);
    public TableuGUI[] tableaus = new TableuGUI[8];

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
        StackPane[] pane = new StackPane[8];
        for(int i = 0; i < 8 && i < tableau.size(); i++){
            var cartas = tableau.get(i);
            pane[i] = tableaus[i].getTableu(cartas.getCards());
        }
        return pane;
    }

    //Metodo que regresa todo el tablero de juego
    public HBox getHBox() {
        return tablero;
    }

    public StackPane[] getAllTableauPanes() {
        StackPane[] panes = new StackPane[tableaus.length];
        for (int i = 0; i < tableaus.length; i++) {
            panes[i] = tableaus[i].obtenerTableu();
        }
        return panes;
    }

    public StackPane dibujarMini(ArrayList<TableauDeck> tableaus) {
        StackPane root = new StackPane();
        HBox h = new HBox(10);
        h.setAlignment(Pos.CENTER);

        StackPane[] columnas = dibujar(tableaus);
        for (StackPane c : columnas) {
            c.setOnMouseClicked(null);
            c.setCursor(null);
        }

        h.getChildren().addAll(columnas);
        root.getChildren().add(h);

        return root;
    }

    public BorderPane dibujarMiniCompleto(
            ArrayList<TableauDeck> tableaus,
            CartaInglesa[] emptyCells,
            FoundationDeck[] foundations
    ) {
        BorderPane mini = new BorderPane();

        HBox filaEmpty = new HBox(5);
        for (int i = 0; i < emptyCells.length; i++) {
            StackPane celda = new StackPane();
            celda.setPrefSize(40, 60);
            celda.setStyle("-fx-border-color: black; -fx-background-color: rgba(255,255,255,0.5);");

            if (emptyCells[i] != null) {
                CartaGUI carta = new CartaGUI(emptyCells[i]);
                StackPane cartaPane = carta.getPane();
                celda.getChildren().add(cartaPane);
            }

            filaEmpty.getChildren().add(celda);
        }
        mini.setTop(filaEmpty);

        VBox columnaFound = new VBox(5);
        for (int i = 0; i < foundations.length; i++) {
            StackPane celda = new StackPane();
            celda.setPrefSize(40, 60);
            celda.setStyle("-fx-border-color: black; -fx-background-color: rgba(255,255,255,0.5);");

            CartaInglesa ultima = foundations[i].getUltimaCarta();
            if (ultima != null) {
                CartaGUI carta = new CartaGUI(ultima);
                StackPane cartaPane = carta.getPane();
                celda.getChildren().add(cartaPane);
            }

            columnaFound.getChildren().add(celda);
        }
        mini.setRight(columnaFound);

        StackPane centro = dibujarMini(tableaus);
        mini.setCenter(centro);

        return mini;
    }
}