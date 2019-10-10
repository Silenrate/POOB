package aplicacion;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.util.Random;

public class SorpresaBola extends Sorpresa{
	/**
	 * Clase que ejecuta la sorpresa de la bola del juego arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private int signo;
	private String[] rutas = {"resources/bolareducir.png","resources/bolaaumentar.png"};
	/**
     * Constructor para la clase sorpresaBola
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public SorpresaBola(int x , int y) {
		super(x,y);
		Random r = new Random();
		signo = r.nextInt(2);
		setImage(signo);
	}
	/**
     * Muestra el color de la sorpresa.
	 * @return El color de la sorpresa.
    */
	public Color getColor() {
		if(signo==0) {
			return Color.red;
		}
		else{
			return new Color(0,255,255);
		}
	}
	/**
     * Cambiar la imagen de la sorpresa.
	 * @param ruta La ruta de la nueva imagen de la sorpresa.
    */
	public void setImage(int ruta) {
		imagen = new ImageIcon(rutas[ruta]);
	}
	/**
     * Reaccion de la sorpresa al ser tomada.
	 * @param bola La bola del juego arkanoid.
	 * @param jugador El jugador que tomo la sorpresa.
    */
	public void reaccione(Bola bola, Jugador jugador) {
		if(signo==0) {
			bola.decreaseSpeed();
		}
		else{
			bola.increaseSpeed();
		}
	}
}
