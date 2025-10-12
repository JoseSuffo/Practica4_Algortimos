package Interfaz;

import javafx.scene.layout.BorderPane;

public class ControladorTablero {

    BorderPane ventana;

    public ControladorTablero(BorderPane ventana) {
        this.ventana = ventana;
        crearGUI();
    }

    public void crearGUI(){
        ventana.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, rgba(12,89,2,1) 0%, rgba(31,148,80,1) 50%, rgba(12,89,2,1) 100%);");
    }
}
