package Interfaz;

import java.util.ArrayList;

public class ListaHistorial {

    private final ArrayList<HistorialTablero> lista = new ArrayList<>();
    private int indice = -1;

    // Agrega un nuevo estado y borra los futuros si existen
    public void agregar(HistorialTablero estado) {
        // borrar estados adelante del índice
        if (indice < lista.size() - 1) {
            lista.subList(indice + 1, lista.size()).clear();
        }
        lista.add(estado);
        indice = lista.size() - 1;
    }

    public boolean puedeDeshacer() {
        return indice > 0;
    }

    public boolean puedeRehacer() {
        return indice < lista.size() - 1;
    }

    public HistorialTablero deshacer() {
        if (!puedeDeshacer()) return null;
        indice--;
        return lista.get(indice);
    }

    public HistorialTablero rehacer() {
        if (!puedeRehacer()) return null;
        indice++;
        return lista.get(indice);
    }

    public HistorialTablero obtenerActual() {
        if (indice < 0 || indice >= lista.size()) return null;
        return lista.get(indice);
    }

    public int indiceActual() {
        return indice;
    }

    public boolean existeIndice(int i) {
        return i >= 0 && i < lista.size();
    }

    public HistorialTablero obtenerPorIndice(int i) {
        if (!existeIndice(i)) return null;
        return lista.get(i);
    }

    public void truncarDesde(int limite) {
        if (!existeIndice(limite)) return;
        lista.subList(limite + 1, lista.size()).clear();
        indice = limite;
    }
}