package Interfaz;

import Logica.EightOffGame;
import Pila.Pila;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControladorTablero {
    EightOffGame eightOffGame;

    BorderPane ventana;
    VBox seccionIzquierda = new VBox(10);
    VBox seccionDerecha = new VBox(10);
    HBox seccionInferior = new HBox(10);
    HBox seccionSuperior = new HBox(10);
    TableroCentralGUI tablero = new TableroCentralGUI();
    Button undo = new Button("Undo");
    Button salir = new Button("Salir");
    Button reiniciar = new Button("Reiniciar");
    Button pista = new  Button("Pista");

    boolean seSeleccionoCarta;
    String seleccionOrigen;
    int tableauSeleccionado;
    StackPane cartaSeleccionada;

    Pila<HistorialTablero> historial = new Pila<HistorialTablero>(1000);

    public ControladorTablero(BorderPane ventana) {
        seSeleccionoCarta = false;
        seleccionOrigen = "";
        tableauSeleccionado = -1;
        cartaSeleccionada = new StackPane();
        eightOffGame = new EightOffGame();
        this.ventana = ventana;
        crearGUI();
    }

    public void crearGUI(){
        ventana.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, rgba(12,89,2,1) 0%, rgba(31,148,80,1) 50%, rgba(12,89,2,1) 100%);");
    }

    public void crearTablero(){
        StackPane[] tableaus = tablero.dibujar(eightOffGame.getTableaus());

        for (int i = 0; i < tableaus.length; i++) {
            final int index = i + 1;
            StackPane carta = tableaus[i];
            if (carta == null) {
                continue;
            }
            carta.setCursor(Cursor.HAND);

            carta.setOnMouseClicked(event -> {
                if (event.getButton() != MouseButton.PRIMARY) {
                    return;
                }
                seleccionarColumna(index);
                event.consume();
            });
        }
    }

    public void seleccionarColumna(int index){

    }
}
