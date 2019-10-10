package aplicacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;

public abstract class Sorpresa implements Serializable{
	/**
	 * Clase que ejecuta la sorpresa del juego arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private int x;
	private int y;
	protected Color color;
	protected Ellipse2D.Double figura;
	protected ImageIcon imagen;
	/**
     * Constructor para la clase sorpresa
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public Sorpresa(int x , int y) {
		color = Color.black;
		this.x=x;
		this.y=y;
		figura = new Ellipse2D.Double(x,y,20,20); 
	}
	/**
     * Muestra la figura de la sorpresa.
	 * @return La figura de la sorpresa.
    */
	public Ellipse2D.Double getFigura(){
		return figura;
	}
	/**
     * Muestra la coordenada horizontal de la sorpresa.
	 * @return La coordenada horizontal de la sorpresa.
    */
	public int getX() {
		return x;
	}
	/**
     * Muestra la coordenada vertical de la sorpresa.
	 * @return La coordenada vertical de la sorpresa.
    */
	public int getY() {
		return y;
	}
	/**
     * Cambiar el color de la sorpresa.
	 * @param newColor El nuevo color de la sorpresa.
    */
	public void setColor(Color newColor) {
		color = newColor;
	}
	/**
     * Muestra el color de la sorpresa.
	 * @return El color de la sorpresa.
    */
	public Color getColor() {
		return color;
	}
	/**
     * Desplazar la sorpresa a otras coordenadas en el tablero.
	 * @param newX La nueva coordenada horizontal de la sorpresa.
	 * @param newY La nueva coordenada vertical de la sorpresa.
    */
	public void goTo(int newX, int newY) {
		this.x = newX;
		this.y = newY;
		figura = new Ellipse2D.Double(newX,newY,20,20); 
	}
	/**
     * Reaccion de la sorpesa al ser tomada.
	 * @param bola La bola del juego arkanoid.
	 * @param jugador El jugador que tomo la sorpresa.
    */
	public abstract void reaccione(Bola bola, Jugador jugador);
	/**
     * Muestra la imagen de la sorpresa.
	 * @return La imagen de la sorpresa.
    */
	public Image getImage() {
		return imagen.getImage();
	}
}

