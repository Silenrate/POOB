package aplicacion;

import java.awt.Color;

public class BloqueVerde extends Bloque {
	/**
	 * Clase que ejecuta el bloque verde del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private static final Color VerdeOscuro = new Color(0,128,0);
	/**
     * Constructor para la clase bloqueVerde.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
    */
	public BloqueVerde(int x , int y , int ancho , int alto) {
		super(x, y, ancho, alto);
		puntaje = 200;
		lives=2;
		originalLives=2;
	}
	/**
     * Constructor para la clase bloqueVerde.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
	 * @param lives Las vidas del bloque.
    */
	public BloqueVerde(int x , int y , int ancho , int alto, int lives) {
		super(x,y,ancho,alto,lives);
		puntaje = 200;
		originalLives=2;
	}
	/**
     * Obtiene el color del bloque respecto ala vida que tiene
     * @return El color del bloque.
    */
	public Color getColor() {
		if (lives>1){
			return Color.GREEN;
		}
		else{
			return VerdeOscuro;
		}
	}
	/**
     * Reacciona el bloque si fue destruido.
	 * @param game El juego donde esta el bloque.
    */
	@Override
	public void reaccione(Arkanoid game) {
		lives--;
	}
}