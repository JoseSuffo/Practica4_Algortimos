package Logica;

import Cartas.CartaInglesa;

import java.util.ArrayList;

/**
 * Modela un montículo donde se ponen las cartas
 * de por valor, alternando el color.
 *
 * @author Cecilia M. Curlango
 * @version 2025
 */
public class TableauDeck {
    ArrayList<CartaInglesa> cartas = new ArrayList<>();

    /**
     * Carga las cartas iniciales y voltea la última.
     *
     * @param cartas iniciales
     */
    public void inicializar(ArrayList<CartaInglesa> cartas) {
        this.cartas = cartas;
        CartaInglesa ultima = cartas.getLast();
        ultima.makeFaceUp();
    }

    /**
     * Remove cards starting from the one with a specified value.
     *
     * @param value of starting card to remove
     * @return removed cards or empty ArrayList if it is not possible to remove.
     */
    public ArrayList<CartaInglesa> removeStartingAt(int value) {
        ArrayList<CartaInglesa> bloque = new ArrayList<>();
        int inicio = -1;
        for (int i = 0; i < cartas.size(); i++) {
            CartaInglesa carta = cartas.get(i);
            if (carta.isFaceup() && carta.getValor() == value) {
                inicio = i;
                break;
            }
        }
        if (inicio == -1) return bloque;
        bloque.add(cartas.get(inicio));
        for (int i = inicio + 1; i < cartas.size(); i++) {
            CartaInglesa anterior = bloque.getLast();
            CartaInglesa actual = cartas.get(i);

            if (!actual.isFaceup()) break;
            if (actual.getValor() == anterior.getValor() - 1 &&
                    !actual.getColor().equals(anterior.getColor())) {
                bloque.add(actual);
            } else {
                break;
            }
        }

        if (!bloque.isEmpty()) {
            cartas.subList(inicio, inicio + bloque.size()).clear();
            if (!cartas.isEmpty()) {
                cartas.getLast().makeFaceUp();
            }
        }

        return bloque;
    }

    //Metodo que sive para ver si una carta empieza con un valor recibido como parámetro.
    public CartaInglesa viewCardStartingAt(int value) {
        for (int i = 0; i < cartas.size(); i++) {
            CartaInglesa carta = cartas.get(i);
            if (carta.isFaceup() && carta.getValor() == value) {
                if (i == cartas.size() - 1 || (
                        cartas.get(i + 1).isFaceup() &&
                                cartas.get(i + 1).getValor() == carta.getValor() - 1 &&
                                cartas.get(i + 1).getPalo() == carta.getPalo()
                )) {
                    return carta;
                }
            }
        }
        return null;
    }

    /**
     * Agrega una carta volteada al montículo. Sólo la agrega si:
     * A) es la siguiente carta en la secuencia
     * B) está vacio y la carta es un Rey
     *
     * @param carta que se intenta almancenar
     * @return true si se pudo guardar la carta, false si no
     */
    public boolean agregarCarta(CartaInglesa carta) {
        boolean agregado = false;

        if (sePuedeAgregarCarta(carta)) {
            carta.makeFaceUp();
            cartas.add(carta);
            agregado = true;
        }
        return agregado;
    }

    /**
     * Obtener la última carta del montículo sin removerla
     *
     * @return la carta que está al final, null si estaba vacio
     */
    CartaInglesa verUltimaCarta() {
        CartaInglesa ultimaCarta = null;
        if (!cartas.isEmpty()) {
            ultimaCarta = cartas.getLast();
        }
        return ultimaCarta;
    }

    /**
     * Remover la última carta del montículo.
     *
     * @return la carta que removió, null si estaba vacio
     */
    CartaInglesa removerUltimaCarta() {
        CartaInglesa ultimaCarta = null;
        if (!cartas.isEmpty()) {
            ultimaCarta = cartas.getLast();
            cartas.remove(ultimaCarta);
            if (!cartas.isEmpty()) {
                cartas.getLast().makeFaceUp();
            }
        }
        return ultimaCarta;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (cartas.isEmpty()) {
            builder.append("---");
        } else {
            for (CartaInglesa carta : cartas) {
                builder.append(carta.toString()).append(carta.isFaceup() ? "↑" : "↓");
            }
        }
        return builder.toString();
    }

    /**
     * Agrega un bloque de cartas al Tableau si la primera carta de las cartas recibidas
     * es de color alterno a la última carta del tableau y también es la siguiente.
     *
     * @param cartasRecibidas
     * @return true si se pudo agregar el bloque, false si no
     */
    public boolean agregarBloqueDeCartas(ArrayList<CartaInglesa> cartasRecibidas) {
        boolean resultado = false;

        if (!cartasRecibidas.isEmpty()) {
            CartaInglesa primera = cartasRecibidas.getFirst();
            if (sePuedeAgregarCarta(primera)) {
                cartas.addAll(cartasRecibidas);
                cartas.getLast().makeFaceUp();
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Indica si está vacío  el Tableau
     *
     * @return true si no tiene cartas restantes, false si tiene cartas.
     */
    public boolean isEmpty() {
        return cartas.isEmpty();
    }

    /**
     * Verifica si la carta que recibe puede ser la siguiente del tableau actual.
     *
     * @param cartaInicialDePrueba
     * @return true si puede ser la siguiente, false si no
     */
    public boolean sePuedeAgregarCarta(CartaInglesa cartaInicialDePrueba) {
        boolean resultado = false;
        if (!cartas.isEmpty()) {
            CartaInglesa ultima = cartas.getLast();
            if (!ultima.getColor().equals(cartaInicialDePrueba.getColor())) {
                if (ultima.getValor() == cartaInicialDePrueba.getValor() + 1) {
                    resultado = true;
                }
            }
        } else {
            if (cartaInicialDePrueba.getValor() == 13) {
                resultado = true;
            }
        }
        return resultado;
    }

    //Metodo que retorna las cartas del tableau guardadas en un ArrayList
    public ArrayList<CartaInglesa> getCards() {
        return cartas;
    }

    //Metodo que sobreescribe las cartas actuales por unas cartas nuevas (anteriores).
    public void setCards(ArrayList<CartaInglesa> cards) {
        cartas = new ArrayList<>();
        cartas.addAll(cards);
    }
}
