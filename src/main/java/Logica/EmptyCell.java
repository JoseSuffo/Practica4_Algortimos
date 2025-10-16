package Logica;

import Cartas.CartaInglesa;

public class EmptyCell {
    CartaInglesa carta = null;
    public EmptyCell(CartaInglesa carta) {
        this.carta = carta;
    }

    public CartaInglesa getCarta() {
        return carta;
    }

    public void setCarta(CartaInglesa carta) {
        this.carta = carta;
    }
}
