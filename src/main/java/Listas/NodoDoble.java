package Listas;

public class NodoDoble <T>{
    private T info;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    public NodoDoble(){
        this.info = null;
        this.siguiente = null;
        this.anterior = null;
    }

    public NodoDoble(T info) {
        this.info = info;
        this.siguiente = null;
        this.anterior = null;
    }

    public NodoDoble(T info, NodoDoble<T> siguiente,  NodoDoble<T> anterior) {
        this.info = info;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDoble<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }

    public String toString() {
        return "Dato: "+info;
    }
}