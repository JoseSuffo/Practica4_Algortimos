package Listas;

public class Nodo <T>{
    private T info;
    private Nodo <T> siguiente;

    public Nodo() {
    }

    public Nodo(T info) {
        this.info = info;
    }

    public Nodo(T info, Nodo <T> siguiente) {
        this.info = info;
        this.siguiente = siguiente;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Nodo <T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo <T> siguiente) {
        this.siguiente = siguiente;
    }
}