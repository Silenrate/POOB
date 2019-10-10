package aplicacion;

public abstract class JugadorCPU extends Jugador{
	/**
     * Constructor para la clase jugadorCpu
	 * @param nombre El nombre del jugadorCpu.
    */
	public JugadorCPU(String nombre) {
		super(nombre);
	}
	/**
     * Constructor para la clase jugadorCpu
	 * @param nombre El nombre del jugadorCpu.
	 * @param score El puntaje del jugadorCpu.
	 * @param lives Las vidas del jugadorCpu.
    */
	public JugadorCPU(String nombre, int score, int lives) {
		super(nombre,score,lives);
	}
}
