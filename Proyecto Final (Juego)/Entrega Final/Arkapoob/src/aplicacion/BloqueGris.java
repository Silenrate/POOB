package aplicacion;

import java.awt.Color;

public class BloqueGris extends Bloque {
	/**
	 * Clase que ejecuta el bloque gris del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	/**
     * Constructor para la clase bloqueGris.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
    */
	public BloqueGris(int x , int y , int ancho , int alto) {
		super(x, y, ancho, alto);
	}
	/**
     * Constructor para la clase bloqueGris.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
	 * @param lives Las vidas del bloque.
    */
	public BloqueGris(int x , int y , int ancho , int alto, int lives) {
		super(x,y,ancho,alto,lives);
	}
	/**
     * Obtiene el color del bloque
     * @return El color del bloque.
    */
	public Color getColor() {
		return Color.GRAY;
	}
	/**
     * Conoce si el bloque fue destruido
     * @return El valor booleano que determina si el bloque fue destruido.
    */
	public boolean esDestruido(){
		return false;
	}
	/**
     * Sabe si el bloque es destruible
     * @return El valor booleano que determina si el bloque es destruible.
    */
	public boolean esDestruible(){
		return false;
	}
	/**
     * Reacciona el bloque si fue destruido.
	 * @param game El juego donde esta el bloque.
    */
	@Override
	public void reaccione(Arkanoid game) {}
}