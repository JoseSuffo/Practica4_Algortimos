package Logica;

import Cartas.CartaInglesa;

public class Pista {
    public enum TipoDestino { TABLEAU, EMPTY_CELL, FOUNDATION }
    public CartaInglesa carta;
    public TipoDestino tipoDestino;
    public int indiceDestino;

    public Pista(CartaInglesa carta, TipoDestino tipoDestino, int indiceDestino) {
        this.carta = carta;
        this.tipoDestino = tipoDestino;
        this.indiceDestino = indiceDestino;
    }
}
