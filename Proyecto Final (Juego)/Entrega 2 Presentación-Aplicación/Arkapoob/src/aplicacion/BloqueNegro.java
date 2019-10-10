package aplicacion;

import java.awt.Color;
import java.awt.geom.*;

public class BloqueNegro extends Bloque {
	/**
	 * Clase que ejecuta el bloque negro del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
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
}