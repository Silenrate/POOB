package aplicacion;
import java.awt.Color;
import java.util.Random;

public interface EnSalon{
    public static String[] FORMAS = new String[]{"Persona", "Circulo", "Cuadrado","Bola","Pesa"};
    Random r = new Random(1);
    /**
     * Devuelve la coordenada horizontal.
     * @return La posicion x del objeto.
     */
    int getPosicionX();
    /**
     * Devuelve la coordenada vertical.
     * @return La posicion y del objeto.
     */
    int getPosicionY();
    /**
     * Devuelve el color del objeto.
     * @return El color del objeto en tipo Color.
     */
    Color getColor();
    /**
     * Hace que el objeto inicie su rutina.
     */
    void inicie();
    /**
     * Hace que el objeto termine su rutina.
     */
    void pare();
    /**
     * Devuelve forma del objeto.
     * @return El string "circulo".
     */
    default String forma(){
       return FORMAS[1];
    }
    /**
     * Da un mensaje vacio.
     * @return Elmensaje vacio.
     */
    default String mensaje(){
       return "";
    }
    /**
     * Decide aleatoriamente la acción a tomar.
     */
    default void decida(){
        if (r.nextBoolean()){
            inicie();
        }
        else{
            pare();
        }
    }
}
