package Listas;

public class ListaCircularDoble <T>{
    private NodoDoble<T> inicio, fin;

    public ListaCircularDoble(){
        this.inicio = null;
        this.fin = null;
    }

    public void insertaInicio(T dato){
        NodoDoble<T> n = new  NodoDoble<>(dato);
        if(inicio==null){
            inicio=fin=n;
            n.setSiguiente(n);
            n.setAnterior(n);
        }else{
            n.setSiguiente(inicio);
            inicio.setAnterior(n);
            inicio=n;
            fin.setSiguiente(inicio);
            n.setAnterior(fin);
        }
    }

    public void insertaFin(T dato){
        NodoDoble<T> n = new  NodoDoble<>(dato);
        if(inicio==null){
            inicio=fin=n;
            n.setSiguiente(inicio);
            n.setAnterior(inicio);
        }else{
            n.setSiguiente(inicio);
            inicio.setAnterior(n);
            fin.setSiguiente(n);
            n.setAnterior(fin);
            fin=n;
        }
    }

    public T eliminaInicio(){
        T dato = null;
        if(inicio==null){
            System.out.println("Lista Vacia");
            return dato;
        }else{
            if(inicio==fin){
                dato=inicio.getInfo();
                inicio=fin=null;
                return dato;
            }else{
                dato=inicio.getInfo();
                fin.setSiguiente(inicio.getSiguiente());
                inicio=inicio.getSiguiente();
                inicio.setAnterior(fin);
                return dato;
            }
        }
    }

    public T eliminaFin(){
        T dato = null;
        if(inicio==null){
            System.out.println("Lista Vacia");
            return dato;
        }else{
            if(inicio==fin){
                dato=fin.getInfo();
                fin=inicio=null;
                return dato;
            }else{
                dato=fin.getInfo();
                NodoDoble<T> r=fin.getAnterior();
                r.setSiguiente(inicio);
                inicio.setAnterior(r);
                fin=r;
                return dato;
            }
        }
    }

    public String recorrer(){
        String datos="";
        if(inicio==null){
            System.out.println("Lista Vacia");
        }else{
            NodoDoble<T> r=inicio;
            do{
                datos+=r.getInfo()+"\n";
                r=r.getSiguiente();
            }while(r!=inicio);
        }
        return datos;
    }

    public boolean estaVacia(){
        return inicio == null;
    }

    public int getSize() {
        if (inicio == null) return 0;

        int size = 1;
        NodoDoble<T> actual = inicio.getSiguiente();

        while (actual != null && actual != inicio) {
            size++;
            actual = actual.getSiguiente();
        }

        return size;
    }

}