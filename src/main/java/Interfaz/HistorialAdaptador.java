package Interfaz;

import java.util.NoSuchElementException;

public class HistorialAdaptador {
    private final ListaHistorial lista = new ListaHistorial();

    public void push(HistorialTablero estado) {
        lista.agregar(estado);
    }

    public boolean pilaVacia() {
        return lista.obtenerActual() == null;
    }

    public HistorialTablero pop() {
        if (!lista.puedeDeshacer())
            throw new NoSuchElementException("No se puede deshacer.");
        return lista.deshacer();
    }

    public boolean puedeRehacer() { return lista.puedeRehacer(); }
    public HistorialTablero rehacer() {
        if (!lista.puedeRehacer())
            throw new NoSuchElementException("No se puede rehacer.");
        return lista.rehacer();
    }

    public boolean puedeDeshacer() { return lista.puedeDeshacer(); }
    public void truncarDesdeActual() { lista.truncarDesdeActual(); }
}