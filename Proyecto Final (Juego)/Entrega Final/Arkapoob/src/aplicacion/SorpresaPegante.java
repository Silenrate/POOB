package aplicacion;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

public class SorpresaPegante extends Sorpresa {
	private String[] rutas = {"resources/poderpegante.png"};
	/**
	 * Clase que ejecuta la sorpresa pegante del juego arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	/**
     * Constructor para la clase sorpresaPegante
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public SorpresaPegante(int x , int y) {
		super(x,y);
		setImage(0);
	}
	/**
     * Reaccion de la sorpesa al ser tomada.
	 * @param bola La bola del juego arkanoid.
	 * @param jugador El jugador que tomo la sorpresa.
    */
	@Override
	public void reaccione(Bola bola, Jugador jugador) {
		Plataforma p = jugador.getPlataforma();
		Plataforma newPlataforma = new PlataformaPegante(p.getX(),p.getY());
		newPlataforma.setImage(jugador.getPlataforma().getRuta());
		jugador.setPlataforma(newPlataforma);
	}
	/**
     * Cambia la imagen de la sorpesa.
	 * @param ruta El indice de la ruta en la lista de rutas.
    */
	public void setImage(int ruta) {
		imagen = new ImageIcon(rutas[ruta]);
	}
}
