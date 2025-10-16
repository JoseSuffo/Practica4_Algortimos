package Interfaz;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControladorTablero {

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

    public ControladorTablero(BorderPane ventana) {
        this.ventana = ventana;
        crearGUI();
    }

    public void crearGUI(){
        ventana.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, rgba(12,89,2,1) 0%, rgba(31,148,80,1) 50%, rgba(12,89,2,1) 100%);");
    }
}
