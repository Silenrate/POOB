package aplicacion;
import java.awt.Color;
import java.util.Random;

public class Pesa implements EnSalon{  
    private Color color;
    private int y;
    private int x;
    private int size;
    /**
     * Constructor de la pesa.
     * @param salon El salon donde esta la pesa.
     * @param x La coordenada X de la pesa.
     * @param y La coordenada Y de la pesa.
     * @param size El tamaño de la pesa.
     */
    public Pesa(Salon salon , int x , int y , int size){
        this.y=y;
        this.x=x;
        this.size = size;
        color = color.BLACK;
        salon.adicione(this);
    }
    /**
     * Conoce la forma de la pesa.
     * @return Un string con la forma de la pesa.
     */
    public String forma(){
        return EnSalon.FORMAS[4];
    }
    /**
     * Termina la rutina de la pesa.
     */
    public void pare(){
        color = color.BLACK;
    }
    /**
     * Inicia la rutina de la pesa.
     */
    public void inicie(){
        color = color.YELLOW;
    }
    /**
     * Conoce el color de la pesa.
     * @return El color de la pesa de tipo Color.
     */
    public Color getColor(){
        return color;
    }
    /**
     * Conoce la posicion vertical de la pesa.
     * @return La coordenada y de la pesa.
     */
    public int getPosicionY(){
        return y;
    }
    /**
     * Conoce la posicion horizontal de la pesa.
     * @return La coordenada x de la pesa.
     */
    public int getPosicionX(){
        return x;
    }
    /**
     * Conoce el peso de la pesa.
     * @return La coordenada x de la pesa.
     */
    public int getSize(){
        return size;
    }
}