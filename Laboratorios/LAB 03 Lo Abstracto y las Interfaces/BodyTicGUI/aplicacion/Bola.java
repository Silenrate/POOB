package aplicacion;
import java.awt.Color;
import java.util.Random;

public class Bola implements EnSalon{  
    private Color color;
    private int y;
    private int x;
    private int size;
    /**
     * Constructor de la bola.
     */
    public Bola(Salon salon , int x , int y , int size ){
        this.y=y;
        this.x=x;
        this.size = size;
        color = color.BLACK;
        salon.adicione(this);
    }
    /**
     * Conoce la forma de la bola.
     * @return Un string con la forma de la bola.
     */
    public String forma(){
        return EnSalon.FORMAS[3];
    }
    /**
     * Termina la rutina de la bola.
     */
    public void pare(){
        color = color.BLACK;
    }
    /**
     * Inicia la rutina de la bola.
     */
    public void inicie(){
        color = color.BLUE;
    }
    /**
     * Conoce el color de la bola.
     * @return El color de la bola de tipo Color.
     */
    public Color getColor(){
        return color;
    }
    /**
     * Conoce la posicion vertical de la bola.
     * @return La coordenada y de la bola.
     */
    public int getPosicionY(){
        return y;
    }
    /**
     * Conoce la posicion horizontal de la bola.
     * @return La coordenada x de la bola.
     */
    public int getPosicionX(){
        return x;
    }
    /**
     * Conoce el tamaño de la bola.
     * @return El tamaño de la bola.
     */
    public int getSize(){
        return size;
    }
}
