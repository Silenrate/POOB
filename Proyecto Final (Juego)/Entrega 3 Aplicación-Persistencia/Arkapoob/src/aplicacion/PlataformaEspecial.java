package aplicacion;

import java.awt.geom.Rectangle2D;

public class PlataformaEspecial extends Plataforma{
	/**
	 * Clase que ejecuta una plataforma especial del juego arkapoob (controles invertidos)
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	/**
     * Constructor para la clase plataformaEspecial
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
    */
	public PlataformaEspecial(int x , int y) {
		super(x,y);
		figura = new Rectangle2D.Double(x,y,ancho,alto);
	}
	/**
     * Mueve la plataforma hacia la izquierda en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param minX El limite izquierdo del tablero.
    */
	public void muevaseDerecha(int i){
		left = true;
		right = false;
		if(figura.getX()-33>0){
			figura = new Rectangle2D.Double(figura.getX()-i,figura.getY(),ancho,alto);
		}
	}
	/**
     * Mueve la plataforma hacia la derecha en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param minX El limite derecho del tablero.
    */
	public void muevaseIzquierda(int i){
		right = true;
		left = false;
		i = -i;
		if(figura.getX()+33+figura.width<maxX){
			figura = new Rectangle2D.Double(figura.getX()+i,figura.getY(),ancho,alto);
		}
	}
	/**
     * Determina si la plataforma esta invertida.
	 * @return El valor booleano que determina si la plataforma esta invertida.
    */
	public boolean estaInvertida() {
		return true;
	}
}

