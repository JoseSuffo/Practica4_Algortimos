package Interfaz;

public class Seleccion {
    public enum Tipo { TABLEAU, EMPTY_CELL, FOUNDATION }

    private Tipo tipo;
    private int indice;

    public Seleccion(Tipo tipo, int indice) {
        this.tipo = tipo;
        this.indice = indice;
    }

    public Tipo getTipo() { return tipo; }
    public int getIndice() { return indice; }
}