package aplicacion;

import java.awt.geom.Rectangle2D;

public class PlataformaPegante extends Plataforma{
	/**
	 * Clase que ejecuta una plataforma pegante del juego arkapoob (retiene la bola 3 veces)
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private int veces;
	/**
     * Constructor para la clase plataformaPegante
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
    */
	public PlataformaPegante(int x , int y) {
		super(x,y);
		veces = 3;
		figura = new Rectangle2D.Double(x,y,ancho,alto);
	}
	/**
     * Constructor para la clase plataformaPegante
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
	 * @param ancho El ancho de la plataforma.
	 * @param alto El alto de la plataforma.
	 * @param ruta La ruta de la imagen de la plataforma.
    */
	public PlataformaPegante(int x , int y, int ancho, int alto, String ruta) {
		super(x,y,ancho,alto,ruta);
		veces = 3;
		figura = new Rectangle2D.Double(x,y,ancho,alto);
	}
	/**
     * Reacciona cuando se mueve la plataforma.
	 * @param bola La bola con la que choco la plataforma.
    */
	public void reaccione(Bola bola) {
		if(veces>0) {
			bola.puedeMoverse(false);
			veces-=1;
		}
	}
	/**
     * Mueve la plataforma hacia la izquierda en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param bola La bola del juego arkanoid. 
    */
	public void muevaseIzquierda(int i, Bola bola){
		left = true;
		right = false;
		i=-i;
		if(figura.getX()-33>0){
			figura = new Rectangle2D.Double(figura.getX()-i,figura.getY(),ancho,alto);
			if(bola.puedeMoverse()==false) {
				bola.goTo(bola.getX()-i, bola.getY());
			}
		}
	}
	/**
     * Mueve la plataforma hacia la derecha en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param bola La bola del juego arkanoid.  
    */
	public void muevaseDerecha(int i, Bola bola){
		right = true;
		left = false;
		if(figura.getX()+33+figura.width<maxX){
			figura = new Rectangle2D.Double(figura.getX()+i,figura.getY(),ancho,alto);
			if(bola.puedeMoverse()==false) {
				bola.goTo(bola.getX()+i, bola.getY());
			}
		}
	}
}

