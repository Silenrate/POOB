package aplicacion;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.util.Random;

public class SorpresaPlataforma extends Sorpresa {
	/**
	 * Clase que ejecuta la sorpresa de la plataforma del juego arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private int signo;
	private String[] rutas = {"resources/poderreducir.png","resources/poderaumentar.png","resources/poderespecial.png"};
	/**
     * Constructor para la clase sorpresaPlataforma
	 * @param x La coordenada horizontal de la sorpresa.
	 * @param y La coordenada vertical de la sorpresa.
    */
	public SorpresaPlataforma(int x , int y) {
		super(x,y);
		Random r = new Random();
		signo = r.nextInt(3);
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
		else if(signo==1) {
			return new Color(0,255,255);
		}
		return Color.YELLOW;
	}
	/**
     * Reaccion de la sorpresa al ser tomada.
	 * @param bola La bola del juego arkanoid.
	 * @param jugador El jugador que tomo la sorpresa.
    */
	public void reaccione(Bola bola, Jugador jugador) {
		Plataforma plataforma = jugador.getPlataforma();
		//System.out.println(signo);
		int ancho = (int) plataforma.getFigura().width;
		int alto  = (int) plataforma.getFigura().height;
		int menos = (int) (plataforma.getFigura().width-((ancho*20)/100));
		int mas =   (int) (plataforma.getFigura().width+((ancho*20)/100));
		if(signo==0) {
			if(menos>0) {
				plataforma.changeSize(menos,alto);
			}
		}
		else if (signo ==1) {
			if(mas<3*ancho){
				plataforma.changeSize(mas,alto);
			}
		}
		else {
			nuevaPlataforma(jugador,plataforma);
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
     * Alternar la plataforma del jugador entre una plataforma especial y una plataforma normal.
	 * @param jugador El jugador que tomo la sorpresa.
	 * @param plataforma La plataforma del jugador.
    */
	private void nuevaPlataforma(Jugador jugador , Plataforma plataforma) {
		if(!jugador.estaInvertido()) {
			Plataforma newPlataforma = new PlataformaEspecial((int)(plataforma.getFigura().getX()),(int)(plataforma.getFigura().getY()));
			jugador.setPlataforma(newPlataforma);
			newPlataforma.setImage(plataforma.getRuta());
			newPlataforma.changeSize(plataforma.getWidth(), plataforma.getHeight());
			
		}
		else {
			Plataforma newPlataforma = new Plataforma((int)(plataforma.getFigura().getX()),(int)(plataforma.getFigura().getY()));
			jugador.setPlataforma(newPlataforma);
			newPlataforma.setImage(plataforma.getRuta());
			newPlataforma.changeSize(plataforma.getWidth(), plataforma.getHeight());
		}
		jugador.controlesInvertidos();
	}
}
