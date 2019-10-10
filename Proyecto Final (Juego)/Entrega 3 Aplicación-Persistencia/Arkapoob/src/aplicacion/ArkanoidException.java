package aplicacion;

public class ArkanoidException extends Exception{
	
	public static final String FILE_ERROR = "Hubo un error en el archivo del nivel, por favor verifique la carpeta levels";
	public static final String COLOR_ERROR = "Hubo un error en color de un bloque";
	public static final String TYPE_DAT_ERROR = "La extension del archivo debe ser .dat";
	public static final String TYPE_TXT_ERROR = "La extension del archivo debe ser .txt";
	public static final String FILE_NOT_FOUND_ERROR = "El arhivo no pudo ser encontrado";
	public static final String PLAYER_ALREADY_ERROR = "Ya existe ese jugador en el arkapoob";
	public static final String PLAYER_NAME_ERROR = "El nombre de ese jugador no es válido";
	public static final String SOUND_ERROR = "Ocurrio un error con un archivo de sonido";
	
	public ArkanoidException(String message){
		super(message);
	}
}