package Listas;

public class ListaSimple <T>{
    private Nodo<T> inicio;

    public ListaSimple() {
        inicio = null;
    }

    public ListaSimple(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    public void insertaInicio(T dato){
        Nodo<T> n =  new Nodo<>();
        n.setInfo(dato);
        n.setSiguiente(inicio);
        inicio = n;
    }

    public T eliminaInicio(){
        if(inicio == null){
            System.out.println("La lista esta vacia!");
            return null;
        }else{
            inicio = inicio.getSiguiente();
            return inicio.getInfo();
        }
    }

    public void insertaFinal(T dato){
        Nodo<T> n = new Nodo<>();
        if(inicio.getSiguiente() == null){
            n.setSiguiente(inicio);
            inicio = n;
        }else{
            Nodo<T> r = inicio;
            while(r.getSiguiente() != null){
                r = r.getSiguiente();
            }
            r.setSiguiente(n);
            n.setSiguiente(null);
        }
    }

    public T eliminaFinal(){
        if(inicio==null){
            System.out.println("La lista esta vacia!");
            return null;
        }else if(inicio.getSiguiente()==null){
            inicio.setSiguiente(null);
            return inicio.getInfo();
        }else{
            Nodo<T> r = inicio.getSiguiente();
            Nodo<T> a = r.getSiguiente();
            while(r.getSiguiente() != null){
                a = r;
                r = r.getSiguiente();
            }
            a.setSiguiente(null);
            return a.getInfo();
        }
    }

    public String mostrarLista(){
        String cadena = "";
        if(inicio==null){
            cadena+="La lista esta vacia!";
        }else{
            Nodo<T> r = inicio.getSiguiente();
            do{
                cadena+=r.getInfo();
                r=r.getSiguiente();
            }while(r.getSiguiente() != null);
        }
        return cadena;
    }
}