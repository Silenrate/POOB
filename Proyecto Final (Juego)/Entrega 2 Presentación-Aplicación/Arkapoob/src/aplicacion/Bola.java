package aplicacion;

import java.awt.geom.*;
import java.util.ArrayList;

public class Bola {
	/**
	 * Clase que ejecuta la bola del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/
	private Ellipse2D.Double figura;
	private int posX,posY;
	/**
     * Constructor para la clase bola
	 * @param x La coordenada horizontal de la bola.
	 * @param y La coordenada vertical de la bola.
    */
	public Bola(int x , int y) {
		posX = x;
		posY = y;
		figura = new Ellipse2D.Double(posX,posY,10,10);
	}
	/**
     * Obtiene la figura de la bola
     * @return La figura de la bola.
    */
	public Ellipse2D.Double getFigura(){
		return figura;
	}
	/**
     * Mover la bola
     * @param x La nueva coordenada horizontal de la bola.
	 * @param y La nueva coordenada vertical de la bola.
    */
	public void goTo(int x , int y) {
		posX=x;
		posY=y;
		figura = new Ellipse2D.Double(posX,posY,10,10);
	}
	/**
     * Obtiene la coordenada horizontal de la bola
     * @return La coordenada horizontal de la bola.
    */
	public int getX() {
		return posX;
	}
	/**
     * Obtiene la coordenada vertical de la bola
     * @return La coordenada vertical de la bola.
    */
	public int getY() {
		return posY;
	}
}