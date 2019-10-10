package aplicacion;

import java.awt.Color;
import java.util.Random;

public class BloqueAzul extends Bloque {
	/**
	 * Clase que ejecuta el bloque azul del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	/**
     * Constructor para la clase bloqueAzul.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
    */
	public BloqueAzul(int x , int y , int ancho , int alto) {
		super(x, y, ancho, alto);
		puntaje = 300;
	}
	/**
     * Constructor para la clase bloqueAzul.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
	 * @param lives Las vidas del bloque.
    */
	public BloqueAzul(int x , int y , int ancho , int alto, int lives) {
		super(x,y,ancho,alto,lives);
		puntaje = 300;
	}
	/**
     * Obtiene el color del bloque.
     * @return El color del bloque.
    */
	public Color getColor() {
		return new Color(0,255,255);
	}
	/**
     * Reacciona el bloque si fue destruido.
	 * @param game El juego donde esta el bloque.
    */
	public void reaccione(Arkanoid game) {
		lives--;
		Random r = new Random();
		int signo = r.nextInt(3);
		if(signo==0){
			sorpresa = new SorpresaPlataforma((int) (getX()+figura.width/2),getY());
		}
		else if(signo==1){
			sorpresa = new SorpresaBola((int) (getX()+figura.width/2),getY());
		}
		else{
			sorpresa = new SorpresaPegante((int) (getX()+figura.width/2),getY());
		}
	}
}