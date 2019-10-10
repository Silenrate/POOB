package aplicacion;

public class Jugador {
	/**
	 * Clase que ejecuta un jugador del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/
	private Plataforma plataforma;
	private String nombre;
	private int score,lives;
	/**
     * Constructor para la clase jugador
	 * @param nombre El nombre del jugador.
    */
	public Jugador(String nombre) {
		this.nombre = nombre;
		score = 0;
		lives = 3;
	}
	/**
     * Cambia la plataforma por una nueva
     * @param newPlataforma La nueva plataforma del jugador.
    */
	public void setPlataforma(Plataforma newPlataforma) {
		plataforma = newPlataforma;
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
	public void muevaPlataformaALaDerecha(int i , int maxX) {
		plataforma.muevaseDerecha(i , maxX);
	}
	/**
     * Mueve la plataforma del jugador a la izquierda
     * @param i La cantidad de pixeles a mover.
	 * @param maxX El limite del tablero de juego.
    */
	public void muevaPlataformaALaIzquierda(int i , int minX) {
		plataforma.muevaseIzquierda(i , minX);
	}
}