package aplicacion;

import java.awt.Color;

public class BloqueNegro extends Bloque {
	/**
	 * Clase que ejecuta el bloque negro del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	/**
     * Constructor para la clase bloqueNegro.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
    */
	public BloqueNegro(int x , int y , int ancho , int alto) {
		super(x, y, ancho, alto);
		puntaje = 600;
	}
	/**
     * Constructor para la clase bloqueNegro.
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param ancho El ancho del bloque.
	 * @param alto La altura del bloque.
	 * @param lives Las vidas del bloque.
    */
	public BloqueNegro(int x , int y , int ancho , int alto, int lives) {
		super(x,y,ancho,alto,lives);
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
     * Reacciona el bloque si fue destruido.
	 * @param game El juego donde esta el bloque.
    */
	@Override
	public void reaccione(Arkanoid game) {
		Bloque lastBloque =  game.getLastBloque();
		if(lastBloque != null){
			game.getLastPlayer().addScore(getPuntaje());
			lastBloque.setPosition(getX(),getY());
			lastBloque.resetLives();
			game.changesBlock(game.indexOf(this),lastBloque);
		}
		else{
			lives--;
		}
	}
}