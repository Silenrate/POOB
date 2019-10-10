package aplicacion;

import java.awt.Color;

public class BloqueNegro extends Bloque {
	/**
	 * Clase que ejecuta el bloque negro del juego arkapoob
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
	public BloqueNegro(int x , int y , int ancho , int alto) {
		super(x, y, ancho, alto);
		puntaje = 600;
	}
	/**
     * Obtiene el color del bloque
     * @return El color del bloque.
    */
	public Color getColor() {
		return Color.BLACK;
	}
	/**
     * Sabe si el bloque es capaz de cambiar por el ultimo bloque destruido
     * @return El valor booleano que determina si el bloque es capaz de cambiar por el ultimo bloque destruido.
    */
	public boolean changesToLastBlock(){
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