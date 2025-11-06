package Interfaz;

import Logica.EightOffGame;
import Logica.TableauDeck;
import Logica.FoundationDeck;
import Cartas.CartaGUI;
import Cartas.CartaInglesa;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;

public class TableroMini {

    private VBox root = new VBox(12);

    public TableroMini(EightOffGame estado) {
        HBox filaSuperior = new HBox(15);
        filaSuperior.setAlignment(Pos.CENTER);

        for (int i = 0; i < estado.emptyCells.size(); i++) {
            StackPane celda = new StackPane();
            celda.setPrefSize(50, 70);
            celda.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-border-color: white; -fx-border-width: 1.2;");
            CartaInglesa carta = estado.emptyCells.get(i).getCarta();
            if (carta != null) {
                CartaGUI g = new CartaGUI(carta);
                celda.getChildren().add(g.getPane());
            }
            filaSuperior.getChildren().add(celda);
        }

        for (int i = 0; i < estado.foundations.size(); i++) {
            StackPane f = new StackPane();
            f.setPrefSize(50, 70);
            f.setStyle("-fx-background-color: rgba(255,255,255,0.08); -fx-border-color: gold; -fx-border-width: 1.2;");

            FoundationDeck foundation = estado.getFoundationDeck(i);
            CartaInglesa carta = foundation.getUltimaCarta();

            if (carta != null) {
                CartaGUI g = new CartaGUI(carta);
                f.getChildren().add(g.getPane());
            }

            filaSuperior.getChildren().add(f);
        }

        HBox filaTableau = new HBox(5);
        filaTableau.setAlignment(Pos.CENTER);

        for (TableauDeck t : estado.getTableaus()) {
            VBox col = new VBox(12);
            col.setAlignment(Pos.TOP_CENTER);
            for (CartaInglesa carta : t.getCards()) {
                CartaGUI g = new CartaGUI(carta);
                col.getChildren().add(g.getPane());
            }
            filaTableau.getChildren().add(col);
        }

        root.getChildren().addAll(filaSuperior, filaTableau);
        root.setAlignment(Pos.CENTER);

        Scale s = new Scale(0.55, 0.55);
        root.getTransforms().add(s);

        root.setStyle("-fx-background-color: rgba(0,0,0,0.25); -fx-padding: 12; "
                + "-fx-border-color: white; -fx-border-width: 2;"
                + "-fx-background-radius: 8; -fx-border-radius: 8;");

        root.setMaxWidth(250);
        root.setPrefWidth(250);
        root.setMinWidth(250);

        root.setMaxHeight(350);
        root.setPrefHeight(350);
    }
}