package Interfaz;

public class HistorialAdaptador {
    private final ListaHistorial lista = new ListaHistorial();

    public void push(HistorialTablero estado) {
        lista.agregar(estado);
    }

    public boolean pilaVacia() {
        return lista.obtenerActual() == null;
    }

    public HistorialTablero pop() {
        return lista.deshacer();
    }

    public boolean puedeRehacer() { return lista.puedeRehacer(); }
    public HistorialTablero rehacer() { return lista.rehacer(); }
    public void truncarDesdeActual() { lista.truncarDesdeActual(); }
}