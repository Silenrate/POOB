package aplicacion;

import java.io.Serializable;

public class Jugador implements Serializable{
	/**
	 * Clase que ejecuta un jugador del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private Plataforma plataforma;
	private String nombre;
	private int score,lives;
	private boolean controlInvertido;
	/**
     * Constructor para la clase jugador
	 * @param nombre El nombre del jugador.
    */
	public Jugador(String nombre) {
		this.nombre = nombre;
		controlInvertido = false;
		score = 0;
		lives = 3;
		plataforma = new Plataforma(0,0);
	}
	/**
     * Cambia la posicion de la plataforma
     * @param x La nueva coordenada horizontal de la plataforma.
	 * @param y La nueva coordenada vertical de la plataforma.
    */
	public void setPlataforma(int x, int y) {
		plataforma = new Plataforma(x,y);
	}
	/**
     * Cambia la plataforma del jugador
     * @param newPlataforma La nueva plataforma.
    */
	public void setPlataforma(Plataforma newPlataforma) {
		plataforma = newPlataforma;
	}
	/**
     * Invierte los controles de la plataforma.
    */
	public void controlesInvertidos() {
		if(controlInvertido){
			controlInvertido = false;
		}
		else {
			controlInvertido = true;
		}
	}
	/**
     * Determina si los controles de la plataforma estan invertidos.
	 * @return El valor booleano que determina si los controles de la plataforma estan invertidos.
    */
	public boolean estaInvertido() {
		return controlInvertido;
	}
	/**
     * Obtiene el nombre del jugador
     * @return El nombre del jugador.
    */
	public String getNombre(){
		return nombre;
	}
	/**
     * Incrementa el puntaje del jugador
     * @param newScore El nuevo puntaje a incrementar.
    */
	public void addScore(int newScore) {
		score += newScore;
	}
	/**
     * Obtiene el puntaje del jugador
     * @return El puntaje del jugador.
    */
	public int getScore() {
		return score;
	}
	/**
     * Resta una vida al jugador
    */
	public void subtractLive() {
		lives-=1;
	}
	/**
     * Suma una vida al jugador
    */
	public void addLive() {
		lives++;
	}
	/**
     * Obtiene las vidas del jugador
     * @return Las vidas del jugador.
    */
	public int getLives() {
		return lives;
	}
	/**
     * Cambia las vidas del jugador
     * @param newLives Las nuevas vidas del jugador.
    */
	public void setLives(int newLives) {
		lives = newLives;
	}
	/**
     * Cambia el puntaje del jugador
     * @param newScore El nuevo puntaje del jugador.
    */
	public void setScore(int newScore) {
		score = newScore;
	}
	/**
     * Obtiene la plataforma del jugador
     * @return La plataforma del jugador.
    */
	public Plataforma getPlataforma() {
		return plataforma;
	}
	/**
     * Mueve la plataforma del jugador a la derecha
     * @param i La cantidad de pixeles a mover.
	 * @param maxX El limite del tablero de juego.
    */
	public void muevaPlataformaALaDerecha(int i) {
		plataforma.muevaseDerecha(i);
	}
	/**
     * Mueve la plataforma del jugador a la izquierda
     * @param i La cantidad de pixeles a mover.
	 * @param maxX El limite del tablero de juego.
    */
	public void muevaPlataformaALaIzquierda(int i) {
		plataforma.muevaseIzquierda(i);
	}
	/**
     * Determina si el jugador tiene vidas.
     * @return El valor booleano que determina si el jugador tiene vidas.
    */
	public boolean estaVivo() {
		return getLives()>0;
	}
}





