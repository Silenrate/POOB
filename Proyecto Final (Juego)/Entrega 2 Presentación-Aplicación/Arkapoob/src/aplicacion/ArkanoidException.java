package aplicacion;

public class ArkanoidException extends Exception{
	
	public static final String FILE_ERROR = "Hubo un error en el archivo del nivel, por favor verifique la carpeta levels";
	public static final String COLOR_ERROR = "Hubo un error en color de un bloque";
	
	public ArkanoidException(String message){
		super(message);
	}
}