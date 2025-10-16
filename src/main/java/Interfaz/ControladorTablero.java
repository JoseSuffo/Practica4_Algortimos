package Interfaz;

import Cartas.CartaGUI;
import Cartas.CartaInglesa;
import Cartas.Palo;
import Logica.EightOffGame;
import Pila.Pila;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;

public class ControladorTablero {
    EightOffGame eightOffGame;

    BorderPane ventana;
    VBox seccionIzquierda = new VBox(10);
    VBox seccionDerecha = new VBox(10);
    HBox seccionInferior = new HBox(10);
    HBox seccionSuperior = new HBox(10);
    StackPane[] foundations = new StackPane[4];
    StackPane[] emptyCells = new StackPane[8];
    TableroCentralGUI tablero = new TableroCentralGUI();
    Button undo = new Button("Undo");
    Button salir = new Button("Salir");
    Button reiniciar = new Button("Reiniciar");
    Button pista = new  Button("Pista");

    Seleccion seleccionActual;
    StackPane cartaSeleccionada;

    Pila<HistorialTablero> historial = new Pila<HistorialTablero>(1000);

    public ControladorTablero(BorderPane ventana) {
        seleccionActual = null;
        cartaSeleccionada = new StackPane();
        eightOffGame = new EightOffGame();
        this.ventana = ventana;
        crearGUI();
        actualizarGUI();
    }

    public void crearGUI(){
        ventana.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, rgba(12,89,2,1) 0%, rgba(31,148,80,1) 50%, rgba(12,89,2,1) 100%);");

        seccionIzquierda.setPadding(new Insets(16));
        seccionIzquierda.setAlignment(Pos.CENTER);
        seccionIzquierda.setFillWidth(false);

        seccionDerecha.setPadding(new Insets(16));
        seccionDerecha.setAlignment(Pos.TOP_RIGHT);
        seccionDerecha.setFillWidth(false);

        seccionInferior.setPadding(new Insets(16));
        seccionInferior.setAlignment(Pos.CENTER);
        seccionInferior.setFillHeight(false);

        seccionSuperior.setPadding(new Insets(16));
        seccionSuperior.setAlignment(Pos.CENTER);
        seccionSuperior.setFillHeight(false);

        undo.setOnAction(E -> {
            if(!historial.pilaVacia()){
                HistorialTablero estadoAnterior = historial.pop();
                eightOffGame.restaurarEstado(estadoAnterior);
                actualizarGUI();
            }else{
                Alert noUndo = new Alert(Alert.AlertType.WARNING);
                noUndo.setTitle("Advertencia de Undo");
                noUndo.setHeaderText("Sin movimientos realizados!");
                noUndo.setContentText("No hay movimientos previos realizados, intenta nuevamente!");
                noUndo.showAndWait();
            }
        });
        undo.setTextFill(Color.WHITE);
        undo.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);");
        undo.setOnMouseEntered(e -> undo.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1976D2, #90CAF9);"));
        undo.setOnMouseExited(e -> undo.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);"));

        pista.setOnAction(E -> {

        });
        pista.setTextFill(Color.WHITE);
        pista.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);");
        pista.setOnMouseEntered(e -> pista.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1976D2, #90CAF9);"));
        pista.setOnMouseExited(e -> pista.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);"));

        salir.setOnAction(event -> {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Salir");
            confirmacion.setHeaderText("Confirmación de salida");
            confirmacion.setContentText("¿Estás seguro de salir?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK){
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }else if(resultado.get() == ButtonType.CANCEL){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("El juego sigue");
                mensaje.setHeaderText("¡Sigue con la partida!");
                mensaje.setContentText("La partida no ha sido reiniciada");
                mensaje.showAndWait();
            }
        });
        salir.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);");
        salir.setOnMouseEntered(e -> salir.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1976D2, #90CAF9);"));
        salir.setOnMouseExited(e -> salir.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);"));
        salir.setTextFill(Color.WHITE);

        reiniciar.setOnAction(event -> {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Reiniciar juego");
            confirmacion.setHeaderText("Confirmación de reinicio");
            confirmacion.setContentText("¿Estás seguro de reiniciar tu juego?");
            Optional<ButtonType> resultado = confirmacion.showAndWait();
            if(resultado.get() == ButtonType.OK){
                eightOffGame = new EightOffGame();
                reiniciarSeleccion();
                actualizarGUI();
                historial.clear();
            }else if(resultado.get() == ButtonType.CANCEL){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("El juego sigue");
                mensaje.setHeaderText("¡Sigue con la partida!");
                mensaje.setContentText("La partida no ha sido reiniciada");
                mensaje.showAndWait();
            }
        });
        reiniciar.setStyle("-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);");
        reiniciar.setOnMouseEntered(e -> reiniciar.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #1976D2, #90CAF9);"));
        reiniciar.setOnMouseExited(e -> reiniciar.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #90CAF9, #1976D2);"));
        reiniciar.setTextFill(Color.WHITE);

        for (int i = 0; i < foundations.length; i++) {
            final int indexFoundation = i;
            foundations[i] = new StackPane();
            generarEspacioTablero(foundations[i], Palo.values()[i].getFigura());

            foundations[i].setOnMouseClicked(event -> {
                if (event.getButton() != MouseButton.PRIMARY) {
                    return;
                }
                if (seleccionActual == null) {
                    return;
                }

                boolean seMovioCarta = false;
                HistorialTablero estadoPrevio = new HistorialTablero(eightOffGame);

                switch (seleccionActual.getTipo()) {
                    case EMPTY_CELL -> seMovioCarta = eightOffGame.moveEmptyCellToFoundation(seleccionActual.getIndice());
                    case TABLEAU -> seMovioCarta = eightOffGame.moveTableauToFoundation(seleccionActual.getIndice());
                }

                reiniciarSeleccion();
                if (seMovioCarta) {
                    historial.push(estadoPrevio);
                    actualizarGUI();
                    verificarFinDeJuego();
                }
            });
        }

        seccionIzquierda.getChildren().addAll(pista);
        seccionDerecha.getChildren().addAll(foundations[0], foundations[1]
                , foundations[2], foundations[3]);
        seccionInferior.getChildren().addAll(undo, salir, reiniciar);

        ventana.setTop(seccionSuperior);
        ventana.setBottom(seccionInferior);
        ventana.setLeft(seccionIzquierda);
        ventana.setRight(seccionDerecha);
        ventana.setCenter(tablero.getHBox());

        for(int i=0; i<8; i++){
            var tableu = tablero.getPane(i);
            int finalI = i;
            tableu.setOnMouseClicked(event -> {
                if(event.getButton() != MouseButton.PRIMARY){
                    return;
                }
                seleccionarColumna(finalI);
            });
            tableu.setCursor(Cursor.HAND);
        }
    }

    public void actualizarGUI(){
        crearTablero();
        crearFoundations();
        crearEmptyCells();
    }

    public void crearTablero(){
        StackPane[] tableaus = tablero.dibujar(eightOffGame.getTableaus());

        for (int i = 0; i < tableaus.length; i++) {
            final int index=i;
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

    public void crearFoundations() {
        for (int i = 0; i < foundations.length; i++) {
            StackPane carta = foundations[i];
            carta.getChildren().removeIf(n -> n.getUserData() != null);
            var foundation = eightOffGame.getFoundationDeck(i);
            if (foundation == null) {
                continue;
            }
            CartaInglesa cartaInglesa = foundation.getUltimaCarta();
            if (cartaInglesa != null) {
                CartaGUI cardGUI = new CartaGUI(cartaInglesa);
                StackPane card = cardGUI.getPane();
                card.setUserData("carta");
                carta.getChildren().add(card);
            }
        }
    }

    public void crearEmptyCells() {
        seccionSuperior.getChildren().clear();
        for (int i = 0; i < emptyCells.length; i++) {
            StackPane celda = new StackPane();
            generarEspacioTablero(celda, "FreeCell " + (i + 1));
            celda.getChildren().removeIf(n -> n.getUserData() != null);
            CartaInglesa carta = eightOffGame.getEmptyCell(i);
            if (carta != null) {
                CartaGUI cartaGUI = new CartaGUI(carta);
                StackPane cartaPane = cartaGUI.getPane();
                cartaPane.setUserData("carta");
                celda.getChildren().add(cartaPane);
            }
            int finalI = i;
            celda.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    seleccionarEmptyCell(finalI);
                    event.consume();
                }
            });
            seccionSuperior.getChildren().add(celda);
            emptyCells[i] = celda;
        }
    }


    public void generarEspacioTablero(StackPane tableu, String nombre){
        tableu.setPrefSize(50,100);
        Rectangle rectangle = new Rectangle(50,100);
        rectangle.setArcHeight(5);
        rectangle.setArcWidth(5);
        rectangle.setFill(Color.color(1, 0, 0, 0.12));
        rectangle.setStroke(Color.color(1, 0, 0, 0.35));
        rectangle.setStrokeWidth(1.5);
        tableu.getChildren().add(rectangle);

        Label nombreSeccion = new Label(nombre);
        nombreSeccion.setTextFill(Color.WHITE);
        StackPane.setAlignment(nombreSeccion, Pos.TOP_LEFT);
        StackPane.setMargin(nombreSeccion, new Insets(4, 0, 0, 6));
        tableu.getChildren().add(nombreSeccion);

        tableu.setCursor(Cursor.HAND);
    }

    public void seleccionarColumna(int index){
        if(seleccionActual == null){
            seleccionActual = new Seleccion(Seleccion.Tipo.TABLEAU, index);
        } else {
            HistorialTablero estadoPrevio = new HistorialTablero(eightOffGame);
            boolean seMovioCarta = false;
            switch (seleccionActual.getTipo()) {
                case EMPTY_CELL -> seMovioCarta = eightOffGame.moveEmptyCellToTableau(seleccionActual.getIndice(), index);
                case TABLEAU -> seMovioCarta = eightOffGame.moveTableauToTableau(seleccionActual.getIndice(), index);
            }
            reiniciarSeleccion();
            if (!seMovioCarta) {
                seleccionActual = new Seleccion(Seleccion.Tipo.TABLEAU, index);
            } else {
                historial.push(estadoPrevio);
                actualizarGUI();
                verificarFinDeJuego();
            }
        }
    }

    public void seleccionarEmptyCell(int index) {
        if (seleccionActual == null) {
            seleccionActual = new Seleccion(Seleccion.Tipo.EMPTY_CELL, index);
        } else {
            HistorialTablero estadoPrevio = new HistorialTablero(eightOffGame);
            boolean seMovio = false;
            switch (seleccionActual.getTipo()) {
                case EMPTY_CELL -> seMovio = eightOffGame.moveEmptyCellToTableau(seleccionActual.getIndice(), index);
                case TABLEAU -> seMovio = eightOffGame.moveTableauToEmptyCell(index, seleccionActual.getIndice());
            }
            reiniciarSeleccion();
            if (!seMovio) {
                seleccionActual = new Seleccion(Seleccion.Tipo.EMPTY_CELL, index);
            } else {
                historial.push(estadoPrevio);
                actualizarGUI();
                verificarFinDeJuego();
            }
        }
    }

    public void reiniciarSeleccion(){
        seleccionActual=null;
    }

    public void verificarFinDeJuego(){

    }
}