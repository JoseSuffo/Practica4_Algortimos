package Listas;

public class ListaCircular <T>{
    Nodo<T> inicio, fin;

    public ListaCircular(){
        this.inicio = null;
        this.fin = null;
    }

    public void insertaInicio(T dato){
        Nodo<T> n = new Nodo<>();
        n.setInfo(dato);
        if(inicio==null){
            inicio = fin = n;
            n.setSiguiente(inicio);
        }else{
            n.setSiguiente(inicio);
            inicio = n;
            fin.setSiguiente(inicio);
        }
    }

    public void insertaFin(T dato){
        Nodo<T> n = new Nodo<>();
        n.setInfo(dato);
        if(inicio==null){
            inicio = fin = n;
            n.setSiguiente(inicio);
        }else{
            n.setSiguiente(inicio);
            fin.setSiguiente(n);
            fin = n;
        }
    }

    public T eliminaInicio(){
        T dato = null;
        if(inicio==null){
            System.out.println("Lista Vacia");
        }else{
            if(inicio==fin){
                dato = inicio.getInfo();
                inicio = fin = null;
            }else{
                dato = inicio.getInfo();
                fin.setSiguiente(inicio.getSiguiente());
                inicio=inicio.getSiguiente();
            }
        }
        return dato;
    }

    public T eliminaFin(){
        T dato = null;
        if(inicio==null){
            System.out.println("Lista Vacia");
        }else{
            if(inicio==fin){
                dato = fin.getInfo();
                inicio=fin=null;
            }else{
                Nodo<T> r = inicio;
                while(r.getSiguiente()!=fin){
                    r = r.getSiguiente();
                }
                dato = fin.getInfo();
                fin = r;
                fin.setSiguiente(inicio);
            }
        }
        return dato;
    }

    public String recorrer(){
        String lista = "";
        if(inicio == null){
            System.out.println("Lista vacia");
        }else{
            Nodo<T> r = inicio;
            do{
                lista += r.getInfo()+"\n";
                r = r.getSiguiente();
            }while(r!=inicio);
        }
        return lista;
    }
}