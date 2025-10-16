package Cartas;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CartaGUI {
    private CartaInglesa cartaInglesa;
    private ImageView imagenCarta;
    private StackPane contenedor;

    public CartaGUI(CartaInglesa carta) {
        this.cartaInglesa = carta;
        imagenCarta = new ImageView();
        imagenCarta.setFitWidth(50);
        imagenCarta.setFitHeight(100);
        imagenCarta.setPreserveRatio(false);
        contenedor = new StackPane(imagenCarta);
        actualizarImagen();
    }

    public StackPane getPane() {
        return contenedor;
    }


    //Se actualiza la imagen en base a una ruta qe se recibe.
    public void actualizarImagen() {
        String ruta = obtenerRuta();
        var recurso = getClass().getResource(ruta);
        if (recurso == null) {
            throw new IllegalArgumentException("Imagen no encontrada: " + ruta);
        }
        imagenCarta.setImage(new Image(recurso.toExternalForm()));
    }

    public String obtenerRuta() {
        if (!isFaceup()) {
            return "/ImagenesCartas/cartaVolteada.png";
        }
        String nombreArchivo = switch (getValor()) {
            case 11 -> "J" + getPalo() + ".png";
            case 12 -> "Q" + getPalo() + ".png";
            case 13 -> "K" + getPalo() + ".png";
            case 14 -> "A" + getPalo() + ".png";
            default -> Integer.toString(getValor()) + getPalo() + ".png";
        };
        return "/ImagenesCartas/" + nombreArchivo;
    }

    public boolean isFaceup() {
        return cartaInglesa.isFaceup();
    }

    public Palo getPalo() {
        return cartaInglesa.getPalo();
    }

    public int getValor() {
        return cartaInglesa.getValor();
    }

    //Metodo que voltea la carta y actualiza la imagen.
    public void voltearCarta() {
        if (cartaInglesa != null) {
            cartaInglesa.makeFaceDown();
            actualizarImagen();
        }
    }
}
