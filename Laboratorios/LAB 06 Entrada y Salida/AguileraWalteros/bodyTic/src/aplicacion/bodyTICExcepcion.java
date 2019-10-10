package aplicacion;

import java.io.Serializable;

public class bodyTICExcepcion extends Exception implements Serializable {
	
	public static final String TYPE_DAT_ERROR = "La extension no es .dat";
	public static final String TYPE_TXT_ERROR = "La extension no es .txt";
	public static final String FILE_NOT_FOUND_ERROR = "Ocurrio un error al encontrar la clase";
	public static final String NO_TEXT_FOUND = "El archivo de texto esta vacio";
	public static final String SIZE_ERROR = "La cantidad de datos ingresados no es correcta";
	public static final String COLOR_ERROR = "El formato del color es incorrecto (java.awt.Color[r=#,g=#,b=#])";
	public static final String NUMBER_FORMAT_EXCEPTION = "No es posible convertir a entero";
	
	public bodyTICExcepcion(String mensaje) {
		super(mensaje);
	}
}
