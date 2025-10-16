package Pila;

import java.util.ArrayList;

public class Pila <T>{
    //Atributos de la clase Pila.
    int tope, valorMaximo;
    T[] pila;

    //Constructor de la clase Pila que recibe un valor de tamaño.
    public Pila(int valorMaximo){
        tope = -1;
        this.valorMaximo = valorMaximo;
        pila = (T[]) new Object[valorMaximo];
    }

    //Metodo que comprueba si la pila está llena por completo.
    public boolean pilaLlena(){
        return tope == valorMaximo-1;
    }

    //Metodo que comprueba si la pila no tiene ningun elemento.
    public boolean pilaVacia(){
        return tope == -1;
    }

    //Metodo que agrega un dato recibido como parámetro a la pila en caso de que haya espacio.
    public void push(T dato){
        if(pilaLlena()){
            System.out.println("Desbordamiento");
        }else{
            tope++;
            pila[tope] = dato;
        }
    }

    //Metodo que extrae el último elemento de la pila en caso de haya alguno.
    public T pop(){
        if(pilaVacia()){
            System.out.println("Subdesbordamiento");
        }else{
            T dato = pila[tope];
            tope--;
            return dato;
        }
        return null;
    }

    //Metodo que retorna el tamaño de la pila
    public int getTamaño(){
        return tope + 1;
    }

    //Metodo que checa en la pila el siguiente elemento.
    public T peek(){
        if(pilaVacia()){
            System.out.println("Subdesbordamiento");
            return null;
        }else{
            return pila[tope];
        }
    }

    //Metodo que vacia la pila por completo.
    public void clear(){
        while(!pilaVacia()){
            pop();
        }
    }

    //Metodo que convierte la pila a un ArrayList.
    public ArrayList<T> toList() {
        ArrayList<T> lista = new ArrayList<>();
        for (int i = 0; i <= tope; i++) {
            lista.add(pila[i]);
        }
        return lista;
    }
}