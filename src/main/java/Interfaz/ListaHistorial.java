package Interfaz;

import java.util.NoSuchElementException;

public class ListaHistorial {
    private NodoHistorial actual;

    private static class NodoHistorial {
        HistorialTablero estado;
        NodoHistorial anterior, siguiente;
        NodoHistorial(HistorialTablero estado) { this.estado = estado; }
    }

    public void agregar(HistorialTablero estado) {
        NodoHistorial nuevo = new NodoHistorial(estado);

        if (actual != null) {
            if (actual.siguiente != null) {
                actual.siguiente.anterior = null;
            }
            actual.siguiente = null;

            nuevo.anterior = actual;
            actual.siguiente = nuevo;
        }

        actual = nuevo;
    }

    public boolean puedeDeshacer() {
        return actual != null && actual.anterior != null;
    }

    public boolean puedeRehacer() {
        return actual != null && actual.siguiente != null;
    }

    public HistorialTablero deshacer() {
        if (!puedeDeshacer()) throw new NoSuchElementException();
        actual = actual.anterior;
        return actual.estado;
    }

    public HistorialTablero rehacer() {
        if (!puedeRehacer()) throw new NoSuchElementException();
        actual = actual.siguiente;
        return actual.estado;
    }

    public HistorialTablero obtenerActual() {
        return actual != null ? actual.estado : null;
    }

    public void truncarDesdeActual() {
        if (actual == null) return;
        actual.siguiente = null;
    }
}