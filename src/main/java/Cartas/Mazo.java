package Cartas;

import Listas.ListaCircularDoble;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ListaCircularDoble<CartaInglesa> cartas = new  ListaCircularDoble<>();

    public Mazo(){
        llenar();
        mezclar();
    }

    public ListaCircularDoble<CartaInglesa> getCartas() {
        return cartas;
    }

    public void llenar(){
        for (int i = 2; i <=14 ; i++) {
            for (Palo palo : Palo.values()) {
                CartaInglesa c = new CartaInglesa(i,palo, palo.getColor());
                cartas.insertaFin(c);
            }
        }
    }

    public void mezclar(){
        ArrayList<CartaInglesa> cartasTemporales = new ArrayList<>();
        while (!cartas.estaVacia()) {
            cartasTemporales.add(cartas.eliminaInicio());
        }
        Collections.shuffle(cartasTemporales);
        for (int i = cartasTemporales.size() - 1; i >= 0; i--) {
            cartas.insertaFin(cartasTemporales.get(i));
        }
    }

    public ArrayList<CartaInglesa> tomarNCartas(int N){
        ArrayList<CartaInglesa> cartasTomadas = new ArrayList<>();
        for(int i=0; i<N; i++){
            cartasTomadas.add(cartas.eliminaInicio());
        }
        return cartasTomadas;
    }
}
