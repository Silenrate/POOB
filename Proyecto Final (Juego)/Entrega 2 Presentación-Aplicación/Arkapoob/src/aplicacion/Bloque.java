package aplicacion;

import java.awt.Color;
import java.awt.geom.*;

public abstract class Bloque {
	/**
	 * Clase que ejecuta el bloque del juego arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/
	private int posX,posY,ancho,alto;
	protected int puntaje,lives;
	protected Rectangle2D.Double figura;
	/**
     * Constructor para la clase bloque
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param anchoB El ancho del bloque.
	 * @param altoB La altura del bloque.
    */
	public Bloque(int x , int y , int ancho , int alto) {
		puntaje = 0;
		lives=1;
		posX = x;
		posY = y;
		this.ancho = ancho;
		this.alto = alto;
		figura = new Rectangle2D.Double(posX,posY,ancho,alto);
	}
	/**
     * Obtiene el color del bloque
     * @return El color del bloque.
    */
	public Color getColor() {
		return Color.WHITE;
	}
	/**
     * Obtiene la figura del bloque
     * @return La figura del bloque.
    */
	public Rectangle2D.Double getFigura() {
		return figura;
	}
	/**
     * Obtiene el puntaje del bloque
     * @return El puntaje del bloque.
    */
	public int getPuntaje(){
		return puntaje;
	}
	/**
     * Colisionar el bloque
    */
	public void colision(){
		lives-=1;
	}
	/**
     * Conoce si el bloque fue destruido
     * @return El valor booleano que determina si el bloque fue destruido.
    */
	public boolean esDestruido(){
		return lives<1;
	}
}