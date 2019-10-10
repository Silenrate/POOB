package aplicacion;
import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

public class Bola implements EnSalon,Serializable{  
    private Color color;
    private int y;
    private int x;
    private int size;
	private static final long serialVersionUID = 8799656478674716638L;
    /**
     * Constructor de la bola.
     * @param salon El salon donde esta la bola.
     * @param x La coordenada X de la bola.
     * @param y La coordenada Y de la bola.
     * @param size El tamaño de la bola.
     */
    public Bola(Salon salon , int x , int y , int size ){
        this.y=y;
        this.x=x;
        this.size = size;
        color = color.BLACK;
        salon.adicione(this);
    }
	public Bola(Salon salon ,Color color, int x , int y , int size){
        this(salon,x,y,size);
        this.color = color;
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
	public String toString(){
		return color.toString()+" "+x+" "+y+" "+size; 
	}
}
