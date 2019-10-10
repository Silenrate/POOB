package aplicacion;

import java.awt.Color;

public class BloqueRosado extends Bloque {
	/**
	 * Clase que ejecuta el bloque rosado del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	/**
     * Constructor para la clase bloque
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param anchoB El ancho del bloque.
	 * @param altoB La altura del bloque.
    */
	public BloqueRosado(int x , int y , int ancho , int alto) {
		super(x, y, ancho, alto);
		puntaje = 500;
	}
	/**
     * Obtiene el color del bloque
     * @return El color del bloque.
    */
	public Color getColor() {
		return Color.PINK;
	}
	/**
     * Sabe si el bloque es capaz de cambiar el todo el arkapoob
     * @return El valor booleano que determina  si el bloque es capaz de cambiar el todo el arkapoob.
    */
	public boolean changeGame(){
		return true;
	}
	/**
     * Reacciona el bloque si fue destruido.
	 * @param jugador El jugador que destruyo el bloque.
    */
	@Override
	public void reaccione(Jugador jugador) {
		
	}
}