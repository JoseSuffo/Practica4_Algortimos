package Logica;

import Cartas.Palo;

import java.util.ArrayList;

public class EightOffGame {
    ArrayList<TableauDeck> tableaus = new ArrayList<>();
    ArrayList<EmptyCell> emptyCells = new ArrayList<>();
    ArrayList <FoundationDeck> foundations = new ArrayList<>();
    FoundationDeck ultimoFoundationDeckActualizado;
    Cartas.Mazo mazo  = new Cartas.Mazo();

    public EightOffGame() {
        createTableaux();
        createFoundations();
    }

    private void createTableaux() {
        for (int i = 0; i < 8; i++) {
            TableauDeck tableauDeck = new TableauDeck();
            tableauDeck.inicializar(mazo.tomarNCartas(6));
            tableaus.add(tableauDeck);
        }
    }

    private void createFoundations() {
        for (Palo palo : Palo.values()) {
            foundations.add(new FoundationDeck(palo));
        }
    }

    public ArrayList<TableauDeck> getTableaus() {
        return tableaus;
    }
}
