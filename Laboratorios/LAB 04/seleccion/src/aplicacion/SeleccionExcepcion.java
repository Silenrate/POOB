package aplicacion;

public class SeleccionExcepcion extends Exception{
	public static final String EMPTY_BLOCK = "Existe un campo vacio";
	public static final String PLAYER_HEIGHT = "La altura debe ser un numero";
	public static final String BAD_POSITION = "Posicion invalida(las posiciones validas son Defensa,Delantero,Mediocampista y Arquero)";
	public static final String PLAYER_ALREADY_EXISTS = "El jugador ya existe en la seleccion";
        public static final String PLAYER_HEIGHT_LIMIT = "La altura tiene que estar entre 150 y 210";
	public SeleccionExcepcion(String message){
		super(message);
	}
}
