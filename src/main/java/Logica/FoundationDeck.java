package Logica;

import Cartas.CartaInglesa;
import Cartas.Palo;

import java.util.ArrayList;

/**
 * Modela un monículo donde se ponen las cartas
 * de un solo palo.
 *
 * @author Cecilia M. Curlango
 * @version 2025
 */
public class FoundationDeck {
    Palo palo;
    ArrayList<CartaInglesa> cartas = new ArrayList<>();

    public FoundationDeck(Palo palo) {
        this.palo = palo;
    }

    /**
     * Agrega una carta al montículo. Sólo la agrega si
     * la carta es del palo del montículo y el la siguiente
     * carta en la secuencia.
     *
     * @param carta que se intenta almancenar
     * @return true si se pudo guardar la carta, false si no
     */
    public boolean agregarCarta(CartaInglesa carta) {
        boolean agregado = false;
        if (carta.tieneElMismoPalo(palo)) {
            if (cartas.isEmpty()) {
                if (carta.getValorBajo() == 1) {
                    // si no hay cartas entonces la carta debe ser un A
                    cartas.add(carta);
                    agregado = true;
                }
            } else {
                // si hay cartas entonces debe haber secuencia
                CartaInglesa ultimaCarta = cartas.getLast();
                if (ultimaCarta.getValorBajo() + 1 == carta.getValorBajo()) {
                    // agregar la carta si el la siguiente a la última
                    cartas.add(carta);
                    agregado = true;
                }
            }
        }
        return agregado;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (cartas.isEmpty()) {
            builder.append("---");
        } else {
            for (CartaInglesa carta : cartas) {
                builder.append(carta.toString());
            }
        }
        return builder.toString();
    }

    /**
     * Determina si hay cartas en el Foundation.
     * @return true hay al menos una carta, false no hay cartas
     */
    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    /**
     * Obtiene la última carta del Foundation sin removerla.
     * @return última carta, null si no hay cartas
     */
    public CartaInglesa getUltimaCarta() {
        CartaInglesa ultimaCarta = null;
        if (!cartas.isEmpty()) {
            ultimaCarta = cartas.getLast();
        }
        return ultimaCarta;
    }

    //Metodo que retorna las cartas de la foundation guardadas en un ArrayList.
    public ArrayList<CartaInglesa> getCartas() {
        return cartas;
    }

    //Metodo que sobreescribe las cartas actuales por unas cartas nuevas (anteriores).
    public void setCartas(ArrayList<CartaInglesa> cartas) {
        this.cartas = new ArrayList<>(cartas);
    }
}
