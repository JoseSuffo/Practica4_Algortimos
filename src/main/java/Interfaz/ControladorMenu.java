package Interfaz;

import Cartas.CartaInglesa;
import Cartas.Mazo;
import Listas.ListaCircularDoble;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ControladorMenu {
    @FXML private Button botonJugar;
    @FXML private Button botonSalir;
    @FXML private Button botonCreditos;

    //Creación del evento al presionar el boton Jugar
    @FXML
    public void botonJugar(ActionEvent actionEvent) {
        //Se crea la ventana y una instancia del controlador del tablero
        BorderPane ventana = new BorderPane();
        ControladorTablero controlador = new ControladorTablero(ventana);

        //Se crea la escena del tablero y se muestra
        Scene juego = new Scene(ventana, 800, 600);
        Stage stage = new Stage();
        stage.setScene(juego);
        stage.setResizable(false);
        stage.setTitle("Eight Off: Guasave Definitive Edition");
        stage.show();

        //Se cierra la ventana actual del menú
        Node node = (Node) actionEvent.getSource();
        Stage actual = (Stage) node.getScene().getWindow();
        actual.close();
    }

    //Creación del evento al presionar el boton Creditos
    @FXML
    public void botonCreditos(ActionEvent actionEvent){
        //Se envia una alerta de información que muestra mi nombre
        Alert creditos = new Alert(Alert.AlertType.INFORMATION);
        creditos.setTitle("Creditos del programa");
        creditos.setHeaderText("Creditos del programa");
        creditos.setContentText("Hecho por José Ramón Suffo Peimbert.");
        creditos.show();
    }

    //Creación del evento al presionar el boton Salir
    @FXML
    public void botonSalir(javafx.event.ActionEvent actionEvent) {
        //Salida del programa
        System.exit(0);
    }
}
