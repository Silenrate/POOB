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
     * Constructor para la clase plataformaEspecial
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
    */
	public PlataformaPegante(int x , int y) {
		super(x,y);
		figura = new Rectangle2D.Double(x,y,ancho,alto);
		veces=3;
	}
}

