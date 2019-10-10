package aplicacion;

import java.awt.Color;
import java.awt.geom.*;
import java.io.Serializable;
import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public abstract class Bloque implements Serializable{
	/**
	 * Clase que ejecuta el bloque del juego arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	protected int posX,posY,ancho,alto;
	protected int puntaje,lives,originalLives;
	protected Rectangle2D.Double figura;
	protected Sorpresa sorpresa;
	private transient Clip sonido;
	/**
     * Constructor para la clase bloque
	 * @param x La coordenada horizontal del bloque.
	 * @param y La coordenada vertical del bloque.
	 * @param anchoB El ancho del bloque.
	 * @param altoB La altura del bloque.
    */
	public Bloque(int x , int y , int ancho , int alto) {
		sorpresa=null;
		puntaje = 0;
		lives=1;
		originalLives=1;
		posX = x;
		posY = y;
		this.ancho = ancho;
		this.alto = alto;
		figura = new Rectangle2D.Double(posX,posY,ancho,alto);
	}
	/**
     * Obtiene el color del bloque
     * @return El color del bloque.
    */
	public Color getColor() {
		return Color.WHITE;
	}
	/**
     * Obtiene la figura del bloque
     * @return La figura del bloque.
    */
	public Rectangle2D.Double getFigura() {
		return figura;
	}
	/**
     * Obtiene la altura del bloque
     * @return La altura del bloque.
    */
	public int getAlto() {
		return alto;
	}
	/**
     * Obtiene la coordenada horizontal del bloque.
     * @return La coordenada horizontal del bloque.
    */
	public int getX() {
		return (int) figura.getX();
	}
	/**
     * Obtiene la coordenada vertical del bloque.
     * @return La coordenada vertical del bloque.
    */
	public int getY() {
		return (int) figura.getY();
	}
	/**
     * Cambia las coordenadas del bloque.
     * @param x La nueva coordenada horizontal del bloque.
	 * @param y La nueva coordenada vertical del bloque.
    */
	public void setPosition(int x,int y){
		posX = x;
		posY = y;
		figura = new Rectangle2D.Double(posX,posY,ancho,alto);
	}
	/**
     * Restaura las vidas del bloque.
    */
	public void resetLives(){
		lives=originalLives;
	}
	/**
     * Obtiene el puntaje del bloque
     * @return El puntaje del bloque.
    */
	public int getPuntaje(){
		return puntaje;
	}
	/**
     * Colisiona el bloque y puede emitir un sonido por eso.
	 * @throws ArkanoidException - SOUND_ERROR si ocurrio algun error al emitir el sonido.
    */
	public void colision() throws ArkanoidException{
		lives--;
		if(esDestruible()&&esDestruido()){destruye();}
	}
	/**
     * Emite un sonido al destuir el bloque.
	 * @throws ArkanoidException - SOUND_ERROR si ocurrio algun error al emitir el sonido.
    */
	private void destruye() throws ArkanoidException{
		try {
            sonido = AudioSystem.getClip();
            //Se carga el fichero wav y comienza a reproducirse
            sonido.open(AudioSystem.getAudioInputStream(new File("resources/destruir.wav")));
            sonido.loop(0); 
        } catch (Exception e) {
            throw new ArkanoidException(ArkanoidException.SOUND_ERROR);
        }
	}
	/**
     * Conoce si el bloque fue destruido
     * @return El valor booleano que determina si el bloque fue destruido.
    */
	public boolean esDestruido(){
		return lives<1;
	}
	/**
     * Muestra la sorpresa que tiene el bloque.
     * @return La sorpresa que tiene el bloque.
    */
	public Sorpresa getSorpresa() {
		return sorpresa;
	}
	/**
     * Sabe si el bloque es destruible
     * @return El valor booleano que determina si el bloque es destruible.
    */
	public boolean esDestruible(){
		return true;
	}
	/**
     * Sabe si el bloque es capaz de cambiar el todo el arkapoob
     * @return El valor booleano que determina si el bloque es capaz de cambiar el todo el arkapoob.
    */
	public boolean changeGame(){
		return false;
	}
	/**
     * Sabe si el bloque es capaz de cambiar por el ultimo bloque destruido
     * @return El valor booleano que determina si el bloque es capaz de cambiar por el ultimo bloque destruido.
    */
	public boolean changesToLastBlock(){
		return false;
	}
	/**
     * Sabe si el bloque es capaz de moverse hacia arriba
     * @return El valor booleano que determina si el bloque es capaz de moverse hacia arriba.
    */
	public boolean movesUp(){
		return false;
	}
	/**
     * Desplaza el bloque hacia arriba en el tablero. 
    */
	public void moveUp(){
		posY = posY-alto-1;
		figura = new Rectangle2D.Double(posX,posY,ancho,alto);
	}
	/**
     * Reacciona el bloque si fue destruido.
	 * @param jugador El jugador que destruyo el bloque.
    */
	public abstract void reaccione(Jugador jugador);
}