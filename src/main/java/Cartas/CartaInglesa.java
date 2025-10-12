package Cartas;

public class CartaInglesa extends Carta {

    public CartaInglesa(int valor, Palo figura, String color) {
        super(valor, figura, color);
    }

    @Override
    public int compareTo(Carta o) {
        if (getValor() == o.getValor() ) {
            if (getColor().equals(o.getColor())) {
                return 0;
            } else {
                return palo.getPeso() - o.palo.getPeso();
            }
        }
        return getValor() - o.getValor();
    }

    //Metodo que clona la carta actual y devuelve la copia para usos del undo.
    public CartaInglesa clonar() {
        CartaInglesa copia = new CartaInglesa(getValor(), getPalo(), getColor());
        if (isFaceup()) {
            copia.makeFaceUp();
        } else {
            copia.makeFaceDown();
        }
        return copia;
    }

}
