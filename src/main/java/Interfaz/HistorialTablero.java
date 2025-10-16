package Interfaz;

import Cartas.CartaInglesa;
import Logica.EightOffGame;
import Logica.EmptyCell;
import Logica.FoundationDeck;
import Logica.TableauDeck;

import java.util.ArrayList;

public class HistorialTablero {
    private ArrayList<ArrayList<CartaInglesa>> tableaus;
    private ArrayList<ArrayList<CartaInglesa>> foundations;
    private ArrayList<ArrayList<CartaInglesa>> emptyCells;

    public HistorialTablero(EightOffGame eightOffGame) {
        this.tableaus = clonarTableaux(eightOffGame.getTableaus());
        this.foundations = clonarFoundations(eightOffGame.foundations);
        this.emptyCells = clonarEmptyCells(eightOffGame.emptyCells);
    }

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

    private ArrayList<ArrayList<CartaInglesa>> clonarFoundations(ArrayList<FoundationDeck> originales) {
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (FoundationDeck deck : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            for(CartaInglesa carta : deck.getCartas()){
                clonadas.add(carta.clonar());
            }
            copia.add(clonadas);
        }
        return copia;
    }

    private ArrayList<ArrayList<CartaInglesa>> clonarEmptyCells(ArrayList<EmptyCell> originales){
        ArrayList<ArrayList<CartaInglesa>> copia = new ArrayList<>();
        for (EmptyCell cell : originales) {
            ArrayList<CartaInglesa> clonadas = new ArrayList<>();
            clonadas.add(cell.getCarta());
            copia.add(clonadas);
        }
        return copia;
    }

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