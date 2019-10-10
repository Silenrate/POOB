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
     * Constructor para la clase plataformaEspecial
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
	 * @param ancho El ancho de la plataforma.
	 * @param alto El alto de la plataforma.
	 * @param ruta La ruta de la imagen de la plataforma.
    */
	public PlataformaEspecial(int x , int y, int ancho, int alto, String ruta) {
		super(x,y,ancho,alto,ruta);
		figura = new Rectangle2D.Double(x,y,ancho,alto);
	}
	/**
     * Mueve la plataforma hacia la derecha en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param bola La bola del juego arkanoid.  
    */
	public void muevaseDerecha(int i , Bola bola){
		right = false;
		if(figura.getX()-33>0){
			left = true;
			figura = new Rectangle2D.Double(figura.getX()-i,figura.getY(),ancho,alto);
		}
		else {
			left = false;
		}
	}
	/**
     * Mueve la plataforma hacia la izquierda en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param bola La bola del juego arkanoid. 
    */
	public void muevaseIzquierda(int i , Bola bola){
		left = false;
		i = -i;
		if(figura.getX()+33+figura.width<maxX){
			right = true;
			figura = new Rectangle2D.Double(figura.getX()+i,figura.getY(),ancho,alto);
		}
		else {
			right = false;
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

