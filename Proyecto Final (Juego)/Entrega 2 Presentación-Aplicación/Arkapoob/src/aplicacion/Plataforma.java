package aplicacion;

import java.awt.geom.Rectangle2D;

public class Plataforma {
	/**
	 * Clase que ejecuta una plataforma del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/
	private int x,y,ancho,alto;
	protected Rectangle2D.Double figura;
	/**
     * Constructor para la clase plataforma
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
    */
	public Plataforma(int x , int y) {
		this.x = x;
		this.y = y;
		ancho = 100;
		alto = 10;
		figura = new Rectangle2D.Double(x-ancho/2,y-20,ancho,alto);
	}
	/**
     * Obtiene la figura de la plataforma
	 * @return La figura de la plataforma.
    */
	public Rectangle2D.Double getFigura(){
		return figura;
	}
	/**
     * Cambia las dimensiones de la plataforma
     * @param newAncho El nuevo ancho de la plataforma.
	 * @param newAlto El nuevo alto de la plataforma.
    */
	public void changeSize(int newAncho , int newAlto) {
		ancho = newAncho;
		alto = newAlto;
		figura = new Rectangle2D.Double(x-ancho/2,y-20,ancho,alto);
	}
	/**
     * Cambia las coordenadas de la plataforma
     * @param newX La nueva coordenada horizontal de la plataforma.
	 * @param newY La nueva coordenada vertical de la plataforma.
    */
	public void setCoordinates(int newX , int newY) {
		x = newX;
		y = newY;
		figura = new Rectangle2D.Double(x-ancho/2,y-20,ancho,alto);
	}
	/**
     * Mueve la plataforma hacia la izquierda en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param minX El limite izquierdo del tablero.
    */
	public void muevaseIzquierda(int i , int minX) {
		//System.out.println(x);
		if (x-ancho/2>minX){
			x-=i;
			figura = new Rectangle2D.Double(x-ancho/2,y-20,ancho,alto);
		}
	}
	/**
     * Mueve la plataforma hacia la derecha en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param minX El limite derecho del tablero.
    */
	public void muevaseDerecha(int i , int maxX){
		//System.out.println(x-ancho/2);
		if(x-ancho/2<maxX){
			x+=i;
			figura = new Rectangle2D.Double(x-ancho/2,y-20,ancho,alto);
		}
		
	}
}