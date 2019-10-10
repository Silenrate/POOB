package aplicacion;

public class Mimo extends JugadorCPU {
	/**
     * Constructor para la clase Mimo
	 * @param nombre El nombre del Mimo.
    */
	public Mimo(String nombre) {
		super(nombre);
		puedeMoverse = false;
	}
	/**
     * Constructor para la clase Mimo
	 * @param nombre El nombre del Mimo.
	 * @param score El puntaje del Mimo.
	 * @param lives Las vidas del Mimo.
    */
	public Mimo(String nombre, int score, int lives) {
		super(nombre,score,lives);
		puedeMoverse = false;
	}
	/**
     * Mueve al jugador en el juego arkanoid.
     * @param game El juego arkanoid.
    */
	public void move(Arkanoid game) {
		game.copie();
	}
}
