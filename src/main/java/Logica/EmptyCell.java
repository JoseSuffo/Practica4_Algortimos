package Logica;

import Cartas.CartaInglesa;

public class EmptyCell {
    CartaInglesa carta;
    public EmptyCell() {
        this.carta = null;
    }

    public CartaInglesa getCarta() {
        return carta;
    }

    public void setCarta(CartaInglesa carta) {
        this.carta = carta;
    }

    public boolean setCartaSiVacia(CartaInglesa carta) {
        if (estaVacia()) {
            this.carta = carta;
            return true;
        }
        return false;
    }

    public CartaInglesa removerCarta() {
        CartaInglesa temp = carta;
        carta = null;
        return temp;
    }

    public boolean estaVacia() {
        return carta == null;
    }
}
