package aplicacion;

import java.awt.geom.*;
import java.io.Serializable;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Bola implements Serializable{
	/**
	 * Clase que ejecuta la bola del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private Ellipse2D.Double figura;
	private int posX,posY,dx,dy;
	private transient Clip sonido;
	/**
     * Constructor para la clase bola
	 * @param x La coordenada horizontal de la bola.
	 * @param y La coordenada vertical de la bola.
    */
	public Bola(int x , int y) {
		posX = x;
		posY = y;
		dx=1;
		dy=1;
		figura = new Ellipse2D.Double(posX,posY,10,10);
	}
	/**
     * Obtiene la figura de la bola
     * @return La figura de la bola.
    */
	public Ellipse2D.Double getFigura(){
		return figura;
	}
	/**
     * Mover la bola
     * @param x La nueva coordenada horizontal de la bola.
	 * @param y La nueva coordenada vertical de la bola.
    */
	public void goTo(int x , int y) {
		posX=x;
		posY=y;
		figura = new Ellipse2D.Double(posX,posY,10,10);
	}
	/**
     * Obtiene la coordenada horizontal de la bola
     * @return La coordenada horizontal de la bola.
    */
	public int getX() {
		return posX;
	}
	/**
     * Obtiene la coordenada vertical de la bola
     * @return La coordenada vertical de la bola.
    */
	public int getY() {
		return posY;
	}
	/**
     * Cambia la velocidad horizontal de la bola.
     * @param newDx La nueva velocidad horizontal de la bola.
    */
	public void setDx(int newDx) {
		dx = newDx;
	}
	/**
     * Cambia la velocidad vertical de la bola.
     * @param newDy La nueva velocidad vertical de la bola.
    */
	public void setDy(int newDy) {
		dy = newDy;
	}
	/**
     * Cambia la direccion horizontal de la bola.
    */
	public void inverseDx() {
		dx = -dx;
	}
	/**
     * Cambia la direccion vertical de la bola.
    */
	public void inverseDy() {
		dy = -dy;
	}
	/**
     * Incrementa la velocidad de la bola.
    */
	public void increaseSpeed() {
		if(dx<0){dx--;}
		else{dx++;}
		if(dy<0){dy--;}
		else{dy++;}
		//System.out.println(dx+" "+dy);
	}
	/**
     * Disminuye la velocidad de la bola.
    */
	public void decreaseSpeed() {
		if(Math.abs(dy)!=1 && Math.abs(dx)!=1){
			if(dx<0){dx++;}
			else{dx--;}
			if(dy<0){dy++;}
			else{dy--;}
		}
	}
	/**
     * Determina si la bola se choco contra un jugador. 
	 * @param figure La figura de la plataforma del jugador.
	 * @param x La coordenada horizontal del tablero.
	 * @param y La coordenada vertical del tablero.
	 * @return El valor booleano que determina si la bola se choco contra un jugador.
	 * @throws ArkanoidException - SOUND_ERROR si ocurrio algun error al emitir el sonido.
    */
	public boolean colisionPlat(Rectangle2D.Double figure,int x,int y) throws ArkanoidException{
		if(figura.getBounds().intersects(figure)){
			choque();
			if(x+9<=figure.getX() || x+1>=figure.width+figure.getX()) {
				dx = -dx;
			}
			else{
				if (x<=figure.width+figure.getX() && (x>=figure.getX())){
					goTo(x,y-4);
					if(Math.abs(x-figure.width-figure.getX())<Math.abs(x-figure.getX())){
						dx = Math.min(-dx,dx);
					}
					else{
						dx = Math.max(-dx,dx);
					}
				}
				dy=Math.abs(dy);
			}
			return true;
		}
		return false;
	}
	/**
     * Determina si la bola se choco contra un bloque. 
	 * @param figure La figura del bloque.
	 * @param x La coordenada horizontal del tablero.
	 * @param y La coordenada vertical del tablero.
	 * @return El valor booleano que determina si la bola se choco contra un bloque.
	 * @throws ArkanoidException - SOUND_ERROR si ocurrio algun error al emitir el sonido.
    */
	public boolean colisionBlock(Rectangle2D.Double figure,int x,int y) throws ArkanoidException{
		if(figura.getBounds().intersects(figure)){
			choque();
			if(x+9<=figure.getX() || x+1>=figure.width+figure.getX()){dx = -dx;}
			else {dy=-dy;}
			return true;
		}
		return false;
	}
	/**
     * Emite un sonido si la bola choco. 
	 * @throws ArkanoidException - SOUND_ERROR si ocurrio algun error al emitir el sonido.
    */
	public void choque() throws ArkanoidException{
		try {
            sonido = AudioSystem.getClip();
            //Se carga el fichero wav y comienza a reproducirse
            sonido.open(AudioSystem.getAudioInputStream(new File("resources/choque.wav")));
            sonido.loop(0); 
        } catch (Exception e) {
			throw new ArkanoidException(ArkanoidException.SOUND_ERROR);
        }
	}
	/**
     * Desplaza la bola en el tablero respecto a las componentes de su velocidad. 
    */
	public void move(){
		posX=posX-dx;
		posY=posY-dy;
		figura = new Ellipse2D.Double(posX,posY,10,10);
	}
}