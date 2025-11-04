package Interfaz;

public class Seleccion {
    //Atributos de la clase Selección
    public enum Tipo { TABLEAU, EMPTY_CELL, FOUNDATION }
    private Tipo tipo;
    private int indice;

    //Constructor de la clase Seleccion
    public Seleccion(Tipo tipo, int indice) {
        this.tipo = tipo;
        this.indice = indice;
    }

    //Getters de la clase Seleccion
    public Tipo getTipo() { return tipo; }
    public int getIndice() { return indice; }
}