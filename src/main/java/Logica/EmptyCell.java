package Logica;

import Cartas.CartaInglesa;

public class EmptyCell {
    //Atributo de la clase EmptyCell
    CartaInglesa carta;
    public EmptyCell() {
        this.carta = null;
    }

    //Método que remueve una carta de la celda y la devuelve como parámetro.
    public CartaInglesa removerCarta() {
        CartaInglesa temp = carta;
        carta = null;
        return temp;
    }

    //Método que verifica si la celda está vacía (no cuenta con carta)
    public boolean estaVacia() {
        return carta == null;
    }

    //Getters y setters de la clase EmptyCell
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
}
