package aplicacion;

public class Destructor extends JugadorCPU {
	/**
     * Constructor para la clase Destructor
	 * @param nombre El nombre del Destructor.
    */
	public Destructor(String nombre) {
		super(nombre);
	}
	/**
     * Constructor para la clase Destructor
	 * @param nombre El nombre del Destructor.
	 * @param score El puntaje del Destructor.
	 * @param lives Las vidas del Destructor.
    */
	public Destructor(String nombre, int score, int lives) {
		super(nombre,score,lives);
	}
	/**
     * Mueve al jugador en el juego arkanoid.
     * @param game El juego arkanoid.
    */
	public void move(Arkanoid game) {
		int speed = 0;
		Jugador jugador = game.getJugador(1);
		Plataforma plataforma = jugador.getPlataforma();
		int x = plataforma.getX();
		int xBola = game.getBola().getX();
		if(x>xBola) {
			speed = 2*game.getBola().getSpeed();
			if(!jugador.estaInvertido()) {plataforma.muevaseIzquierda(-speed, game.getBola());}
			else {plataforma.muevaseDerecha(speed, game.getBola());}
		}
		else {
			speed = 2*game.getBola().getSpeed();
			if(!jugador.estaInvertido()) {plataforma.muevaseDerecha(speed, game.getBola());}
			else {plataforma.muevaseIzquierda(-speed, game.getBola());}
		}
	}
}