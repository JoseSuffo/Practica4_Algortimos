package Interfaz;

import Cartas.CartaInglesa;
import Logica.EightOffGame;
import Logica.EmptyCell;
import Logica.FoundationDeck;
import Logica.TableauDeck;

import java.util.ArrayList;

public class HistorialTablero {
    //Atributos de la clase HistorialTablero
    private ArrayList<ArrayList<CartaInglesa>> tableaus;
    private ArrayList<ArrayList<CartaInglesa>> foundations;
    private ArrayList<ArrayList<CartaInglesa>> emptyCells;

    //Constructor de la clase HistorialTablero
    public HistorialTablero(EightOffGame eightOffGame) {
        this.tableaus = clonarTableaux(eightOffGame.getTableaus());
        this.foundations = clonarFoundations(eightOffGame.foundations);
        this.emptyCells = clonarEmptyCells(eightOffGame.emptyCells);
    }

    //Método que clona lo que se recibió como tableaus en algún momento de la partida
    private ArrayList<ArrayList<CartaInglesa>> clonarTableaux(ArrayList<TableauDeck> originales) {
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (TableauDeck deck : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            for (CartaInglesa carta : deck.getCards()) {
                clonadas.add(carta.clonar());
            }
            copia.add(clonadas);
        }
        return copia;
    }

    //Método que clona lo que se recibió como foundations en algún momento de la partida
    private ArrayList<ArrayList<CartaInglesa>> clonarFoundations(ArrayList<FoundationDeck> originales) {
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (FoundationDeck deck : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            for (CartaInglesa carta : deck.getCartas()) {
                clonadas.add(carta.clonar());
            }
            copia.add(clonadas);
        }
        return copia;
    }

    //Método que clona lo que se recibió como empty cells en algún momento de la partida
    private ArrayList<ArrayList<CartaInglesa>> clonarEmptyCells(ArrayList<EmptyCell> originales){
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (EmptyCell cell : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            CartaInglesa carta = cell.getCarta();
            if (carta != null) {
                clonadas.add(carta.clonar());
            } else {
                clonadas.add(null);
            }
            copia.add(clonadas);
        }
        return copia;
    }

    //Getters de la clase HistorialTablero
    public ArrayList<ArrayList<CartaInglesa>> getEmptyCells() {
        return emptyCells;
    }
    public ArrayList<ArrayList<CartaInglesa>> getTableaus() {
        return tableaus;
    }
    public ArrayList<ArrayList<CartaInglesa>> getFoundations() {
        return foundations;
    }
}