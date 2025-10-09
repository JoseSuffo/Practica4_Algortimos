package Listas;

public class ListaDoble <T>{
    private NodoDoble<T> inicio;

    public ListaDoble(){
        this.inicio = null;
    }

    public ListaDoble(NodoDoble<T> inicio){
        this.inicio = inicio;
    }

    public void insertaInicio(T dato){
        NodoDoble<T> n = new NodoDoble<>();
        n.setInfo(dato);
        n.setSiguiente(inicio);
        n.setAnterior(null);
        if(inicio != null){
            inicio.setAnterior(n);
        }
        this.inicio = n;
    }

    public void insertaFinal(T dato){
        NodoDoble<T> n = new NodoDoble<>();
        n.setInfo(dato);
        n.setSiguiente(null);
        if(inicio == null){
            n.setAnterior(inicio);
            this.inicio=n;
        }else{
            NodoDoble<T> r = inicio;
            while(r.getSiguiente() != null){
                r = r.getSiguiente();
            }
            r.setSiguiente(n);
            n.setAnterior(r);
        }
    }

    public T eliminaInicio(){
        T dato = null;
        if(inicio == null){
            System.out.println("Lista vacia");
            return dato;
        }else{
            if(inicio.getSiguiente() == null){
                dato = inicio.getInfo();
                inicio=null;
                return dato;
            }else{
                dato = inicio.getInfo();
                inicio=inicio.getSiguiente();
                inicio.setAnterior(null);
                return dato;
            }
        }
    }

    public T eliminaFinal(){
        T dato = null;
        if(inicio == null){
            System.out.println("Lista vacia");
            return dato;
        }else{
            if(inicio.getSiguiente() == null){
                dato = inicio.getInfo();
                inicio=null;
                return dato;
            }else{
                NodoDoble<T> r = inicio;
                while(r.getAnterior() != null){
                    r = r.getSiguiente();
                }
                dato = r.getInfo();
                r.getAnterior().setSiguiente(null);
                return dato;
            }
        }
    }

    public String recorrer(){
        String lista = "";
        if(inicio == null){
            System.out.println("Lista vacia");
            return lista;
        }else{
            NodoDoble<T> r = inicio;
            while(r != null){
                lista +=  r.getInfo() + "\n";
                r = r.getSiguiente();
            }
            return lista;
        }
    }
}