package Logica;

import Cartas.CartaInglesa;
import Cartas.Palo;
import Interfaz.HistorialTablero;

import java.util.ArrayList;

public class EightOffGame {
    ArrayList<TableauDeck> tableaus = new ArrayList<>();
    ArrayList<EmptyCell> emptyCells = new ArrayList<>();
    ArrayList<FoundationDeck> foundations = new ArrayList<>();
    FoundationDeck ultimoFoundationActualizado;
    Cartas.Mazo mazo = new Cartas.Mazo();

    public EightOffGame() {
        createTableaux();
        createFoundations();
        createEmptyCells();
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

    public void createEmptyCells() {
        for (int i = 0; i < 8; i++) {
            EmptyCell emptyCell = new EmptyCell();
            if (i < 4) {
                emptyCell.setCartaSiVacia(mazo.tomarUnaCarta());
            }
            emptyCells.add(emptyCell);
        }
    }

    public ArrayList<TableauDeck> getTableaus() {
        return tableaus;
    }

    public FoundationDeck getFoundationDeck(int index) {
        return foundations.get(index);
    }

    public CartaInglesa getEmptyCell(int index) {
        if (index < 0 || index >= emptyCells.size()) return null;
        return emptyCells.get(index).getCarta();
    }


    public boolean moveTableauToTableau(int tableauFuente, int tableauDestino) {
        boolean movimientoRealizado = false;
        TableauDeck fuente = tableaus.get(tableauFuente - 1);
        if (!fuente.isEmpty()) {
            TableauDeck destino = tableaus.get(tableauDestino - 1);

            int valorQueDebeTenerLaCartaInicialDeLaFuente;
            CartaInglesa cartaUltimaDelDestino;
            if (!destino.isEmpty()) {
                cartaUltimaDelDestino = destino.verUltimaCarta();
                valorQueDebeTenerLaCartaInicialDeLaFuente = cartaUltimaDelDestino.getValor() - 1;
            } else {
                valorQueDebeTenerLaCartaInicialDeLaFuente = 13;
            }
            CartaInglesa cartaInicialDePrueba = fuente.viewCardStartingAt(valorQueDebeTenerLaCartaInicialDeLaFuente);
            if (cartaInicialDePrueba != null && destino.sePuedeAgregarCarta(cartaInicialDePrueba)) {
                ArrayList<CartaInglesa> cartas = fuente.removeStartingAt(valorQueDebeTenerLaCartaInicialDeLaFuente);
                if (destino.agregarBloqueDeCartas(cartas)) {
                    if (!fuente.isEmpty()) {
                        fuente.verUltimaCarta().makeFaceUp();
                    }
                    movimientoRealizado = true;
                }
            }
        }
        return movimientoRealizado;
    }

    public boolean moveEmptyCellToTableau(int indexEmptyCell, int indexTableau) {
        EmptyCell cell = emptyCells.get(indexEmptyCell);
        if (cell.estaVacia()) return false;

        CartaInglesa carta = cell.getCarta();
        TableauDeck destino = tableaus.get(indexTableau);
        if (destino.sePuedeAgregarCarta(carta)) {
            destino.agregarCarta(cell.removerCarta());
            return true;
        }
        return false;
    }


    public boolean moveTableauToEmptyCell(int indexEmptyCell, int indexTableu) {
        if (indexEmptyCell < 0 || indexEmptyCell >= emptyCells.size()) return false;
        EmptyCell celda = emptyCells.get(indexEmptyCell);
        if (!celda.estaVacia()) return false;

        TableauDeck fuente = tableaus.get(indexTableu - 1);
        if (fuente.isEmpty()) return false;

        CartaInglesa carta = fuente.removerUltimaCarta();
        celda.setCartaSiVacia(carta);
        return true;
    }

    public boolean moveCartaToFoundation(CartaInglesa carta) {
        int cualFoundation = carta.getPalo().ordinal();
        FoundationDeck destino = foundations.get(cualFoundation);
        ultimoFoundationActualizado = destino;
        return destino.agregarCarta(carta);
    }

    public boolean moveTableauToFoundation(int indexTableau) {
        TableauDeck fuente = tableaus.get(indexTableau - 1);
        if (fuente.isEmpty()) return false;
        CartaInglesa carta = fuente.verUltimaCarta();
        if (moveCartaToFoundation(carta)) {
            fuente.removerUltimaCarta();
            return true;
        }
        return false;
    }

    public boolean moveEmptyCellToFoundation(int indexEmptyCell) {
        if (indexEmptyCell < 0 || indexEmptyCell >= emptyCells.size()) return false;

        EmptyCell celda = emptyCells.get(indexEmptyCell);
        if (celda.estaVacia()) return false;

        CartaInglesa carta = celda.getCarta();
        int indexFoundation = carta.getPalo().ordinal();
        FoundationDeck destino = foundations.get(indexFoundation);

        if (destino.sePuedeAgregarCarta(carta)) {
            destino.agregarCarta(carta);
            celda.setCarta(null);
            ultimoFoundationActualizado = destino;
            return true;
        }

        return false;
    }

    public void restaurarEstado(HistorialTablero estadoAnterior) {

    }
}
