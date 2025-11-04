package Logica;

import Cartas.CartaInglesa;
import Cartas.Palo;
import Interfaz.HistorialTablero;

import java.util.ArrayList;
import java.util.Random;

public class EightOffGame {
    //Atributos de la clase EightOffGame
    ArrayList<TableauDeck> tableaus = new ArrayList<>();
    public ArrayList<EmptyCell> emptyCells = new ArrayList<>();
    public ArrayList<FoundationDeck> foundations = new ArrayList<>();
    FoundationDeck ultimoFoundationActualizado;
    Cartas.Mazo mazo = new Cartas.Mazo();

    //Constructor de la clase EightOffGame.
    public EightOffGame() {
        createTableaux();
        createFoundations();
        createEmptyCells();
    }

    //Creación de los 8 tableaus que se utilizan en el juego.
    private void createTableaux() {
        for (int i = 0; i < 8; i++) {
            TableauDeck tableauDeck = new TableauDeck();
            tableauDeck.inicializar(mazo.tomarNCartas(6));
            tableaus.add(tableauDeck);
        }
    }

    //Creación de las 4 foundations con su respectivo palo.
    private void createFoundations() {
        for (Palo palo : Palo.values()) {
            foundations.add(new FoundationDeck(palo));
        }
    }

    //Creación de las 8 empty cells, donde a las primeras 4 se le agregan cartas restantes.
    public void createEmptyCells() {
        for (int i = 0; i < 8; i++) {
            EmptyCell emptyCell = new EmptyCell();
            if (i < 4) {
                emptyCell.setCartaSiVacia(mazo.tomarUnaCarta());
            }
            emptyCells.add(emptyCell);
        }
    }

    //Getters de los atributos de la clase EightOff
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

    //Método que permite el movimiento de un tableau a otro tableau.
    public boolean moveTableauToTableau(int fuenteIndex, int destinoIndex) {
        TableauDeck fuente = tableaus.get(fuenteIndex);
        TableauDeck destino = tableaus.get(destinoIndex);
        if (fuente.isEmpty()) return false;
        ArrayList<CartaInglesa> bloque = fuente.obtenerBloqueMovible();
        if (bloque.isEmpty()) return false;
        CartaInglesa cartaInferior = bloque.getFirst();
        if (destino.sePuedeAgregarCarta(cartaInferior)) {
            fuente.removerBloque(bloque);
            destino.agregarBloqueDeCartas(bloque);
            return true;
        }
        if (destino.isEmpty() && cartaInferior.getValor() == 13) {
            fuente.removerBloque(bloque);
            destino.agregarBloqueDeCartas(bloque);
            return true;
        }
        return false;
    }

    //Método que permite el movimiento de una celda vacía a un tableau
    public boolean moveEmptyCellToTableau(int indexEmptyCell, int indexTableau) {
        if (indexEmptyCell < 0 || indexEmptyCell >= emptyCells.size()) return false;
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

    //Método que permite el movimiento de un tableau a una celda vacía
    public boolean moveTableauToEmptyCell(int indexEmptyCell, int indexTableu) {
        if (indexEmptyCell < 0 || indexEmptyCell >= emptyCells.size()) return false;
        EmptyCell celda = emptyCells.get(indexEmptyCell);
        if (!celda.estaVacia()) return false;

        TableauDeck fuente = tableaus.get(indexTableu);
        if (fuente.isEmpty()) return false;

        CartaInglesa carta = fuente.removerUltimaCarta();
        celda.setCartaSiVacia(carta);
        return true;
    }

    //Método que permite el movimiento de una carta al foundation
    public boolean moveCartaToFoundation(CartaInglesa carta) {
        int cualFoundation = carta.getPalo().ordinal();
        FoundationDeck destino = foundations.get(cualFoundation);
        ultimoFoundationActualizado = destino;
        return destino.agregarCarta(carta);
    }

    //Método que permite el movimiento de un tableau a foundation
    public boolean moveTableauToFoundation(int indexTableau) {
        TableauDeck fuente = tableaus.get(indexTableau);
        if (fuente.isEmpty()) return false;
        CartaInglesa carta = fuente.verUltimaCarta();
        if (moveCartaToFoundation(carta)) {
            fuente.removerUltimaCarta();
            return true;
        }
        return false;
    }

    //Método que permite el movimiento de una celda vacía a un foundation
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

    //Método que permite la restauración de un movimiento realizado anteriormente
    public void restaurarEstado(HistorialTablero estado) {
        tableaus.clear();
        for (ArrayList<CartaInglesa> columna : estado.getTableaus()) {
            TableauDeck nuevo = new TableauDeck();
            ArrayList<CartaInglesa> cartasClonadas = new ArrayList<>();
            for (CartaInglesa carta : columna) {
                cartasClonadas.add(carta.clonar());
            }
            nuevo.setCards(cartasClonadas);
            tableaus.add(nuevo);
        }

        foundations.clear();
        for (int i = 0; i < estado.getFoundations().size(); i++) {
            FoundationDeck nuevo = new FoundationDeck(Palo.values()[i]);
            ArrayList<CartaInglesa> cartasClonadas = new ArrayList<>();
            for (CartaInglesa carta : estado.getFoundations().get(i)) {
                cartasClonadas.add(carta.clonar());
            }
            nuevo.setCartas(cartasClonadas);
            foundations.add(nuevo);
        }

        emptyCells.clear();
        for (int i = 0; i < estado.getEmptyCells().size(); i++) {
            EmptyCell nuevo = new EmptyCell();
            ArrayList<CartaInglesa> clonadas = estado.getEmptyCells().get(i);
            if (!clonadas.isEmpty() && clonadas.get(0) != null) {
                nuevo.setCartaSiVacia(clonadas.get(0));
            }
            emptyCells.add(nuevo);
        }
    }

    //Método que verifica si hay movimientos posibles dentro de la partida
    public boolean hayMovimientosPosibles() {
        for (int i = 0; i < tableaus.size(); i++) {
            TableauDeck tabla = tableaus.get(i);
            if (!tabla.isEmpty()) {
                CartaInglesa ultimaCarta = tabla.verUltimaCarta();
                for (FoundationDeck f : foundations) {
                    if (f.sePuedeAgregarCarta(ultimaCarta)) return true;
                }
                for (int j = 0; j < tableaus.size(); j++) {
                    if (i == j) continue;
                    if (tableaus.get(j).sePuedeAgregarCarta(ultimaCarta)) return true;
                }
                for (EmptyCell c : emptyCells) {
                    if (c.estaVacia()) return true;
                }
            }
        }
        for (EmptyCell c : emptyCells) {
            if (!c.estaVacia()) {
                CartaInglesa carta = c.getCarta();

                for (FoundationDeck f : foundations) {
                    if (f.sePuedeAgregarCarta(carta)) return true;
                }

                for (TableauDeck t : tableaus) {
                    if (t.sePuedeAgregarCarta(carta)) return true;
                }
            }
        }
        return false;
    }

    //Método que verifica si los foundations se han llenado de manera exitosa
    public boolean foundationsLlenos(){
        boolean foundationsLlenos = true;
        for (FoundationDeck foundation : foundations) {
            if (foundation.estaVacio()) {
                foundationsLlenos = false;
            } else {
                CartaInglesa ultimaCarta = foundation.getUltimaCarta();
                if (ultimaCarta.getValor() != 13) {
                    foundationsLlenos = false;
                }
            }
        }
        return foundationsLlenos;
    }

    //Método que verifica si la partida ha terminado en victoria o en derrota. O si la partida puede continuar.
    public String verificarFinDeJuego() {
        String condicion = "";
        if (foundationsLlenos()){
            condicion="VICTORIA";
        }else if(!hayMovimientosPosibles()){
            condicion="DERROTA";
        }else{
            condicion="CONTINUA";
        }
        return condicion;
    }

    //Método que obtiene una pista dentro del tablero y la retorna al usuario.
    public Pista obtenerUnaPista() {
        ArrayList<Pista> pistasFoundation = new ArrayList<>();
        ArrayList<Pista> pistasTableau = new ArrayList<>();
        ArrayList<Pista> pistasEmptyCell = new ArrayList<>();
        for (int i = 0; i < tableaus.size(); i++) {
            TableauDeck fuente = tableaus.get(i);

            if (fuente.isEmpty()) continue;
            CartaInglesa cartaSuperior = fuente.verUltimaCarta();

            for (int j = 0; j < foundations.size(); j++) {
                if (foundations.get(j).sePuedeAgregarCarta(cartaSuperior)) {
                    pistasFoundation.add(new Pista(cartaSuperior, Pista.TipoDestino.FOUNDATION, j));
                }
            }

            for (int j = 0; j < tableaus.size(); j++) {
                if (i == j) continue;
                TableauDeck destino = tableaus.get(j);
                if (destino.sePuedeAgregarCarta(cartaSuperior)) {
                    pistasTableau.add(new Pista(cartaSuperior, Pista.TipoDestino.TABLEAU, j));
                }
            }

            for (int j = 0; j < emptyCells.size(); j++) {
                if (emptyCells.get(j).estaVacia()) {
                    pistasEmptyCell.add(new Pista(cartaSuperior, Pista.TipoDestino.EMPTY_CELL, j));
                }
            }

            ArrayList<CartaInglesa> bloque = fuente.obtenerBloqueMovible();
            if (bloque.size() > 1) {
                CartaInglesa base = bloque.get(0);
                for (int j = 0; j < tableaus.size(); j++) {
                    if (i == j) continue;
                    TableauDeck destino = tableaus.get(j);
                    if (destino.sePuedeAgregarCarta(base)) {
                        pistasTableau.add(new Pista(base, Pista.TipoDestino.TABLEAU, j));
                    }
                }
            }
        }
        for (EmptyCell celda : emptyCells) {
            if (celda.estaVacia()) continue;

            CartaInglesa carta = celda.getCarta();
            for (int j = 0; j < foundations.size(); j++) {
                if (foundations.get(j).sePuedeAgregarCarta(carta)) {
                    pistasFoundation.add(new Pista(carta, Pista.TipoDestino.FOUNDATION, j));
                }
            }
            for (int j = 0; j < tableaus.size(); j++) {
                if (tableaus.get(j).sePuedeAgregarCarta(carta)) {
                    pistasTableau.add(new Pista(carta, Pista.TipoDestino.TABLEAU, j));
                }
            }
        }
        Random r = new Random();
        if (!pistasFoundation.isEmpty()) return pistasFoundation.get(r.nextInt(pistasFoundation.size()));
        if (!pistasTableau.isEmpty()) return pistasTableau.get(r.nextInt(pistasTableau.size()));
        if (!pistasEmptyCell.isEmpty()) return pistasEmptyCell.get(r.nextInt(pistasEmptyCell.size()));

        return null;
    }
}