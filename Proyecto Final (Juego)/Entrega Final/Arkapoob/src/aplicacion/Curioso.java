package aplicacion;

public class Curioso extends JugadorCPU{
	/**
     * Constructor para la clase Curioso
	 * @param nombre El nombre del Curioso.
    */
	public Curioso(String nombre) {
		super(nombre);
	}
	/**
     * Constructor para la clase Curioso
	 * @param nombre El nombre del Curioso.
	 * @param score El puntaje del Curioso.
	 * @param lives Las vidas del Curioso.
    */
	public Curioso(String nombre, int score, int lives) {
		super(nombre,score,lives);
	}
	/**
     * Mueve al jugador en el juego arkanoid.
     * @param game El juego arkanoid.
    */
	public void move(Arkanoid game) {
		int minimo = 90000;
		int speed = 0;
		Jugador jugador = game.getJugador(1);
		Plataforma plataforma = jugador.getPlataforma();
		int x = plataforma.getX();
		for(int i=0 ; i<game.getSorprisesAmount(); i++){
			int xSorpresa = game.getSorprise(i).getX();
			int temporal = Math.abs(x-xSorpresa);
			if(temporal<minimo) {
				minimo = temporal;
				if(x<=xSorpresa) {speed = 5;}
				else {speed = -5;}
			}
		}
		if(speed<0) {
			if(!jugador.estaInvertido()) {plataforma.muevaseIzquierda(speed, game.getBola());}
			else {plataforma.muevaseDerecha(-speed, game.getBola());}
		}
		else if(speed>0) {
			if(!jugador.estaInvertido()) {plataforma.muevaseDerecha(speed, game.getBola());}
			else {plataforma.muevaseIzquierda(-speed, game.getBola());}
		}
	}

}
