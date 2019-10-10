package aplicacion;


/**
 * Write a description of class KalahException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class KalahException extends Exception{
    public static final String BAD_DIMENSIONS = "La cantidad de casas y semillas deben ser mayor que cero.";
    public static final String BAD_HOUSE = "La casa seleccionada debe estar entre 1 y la cantidad de casas por fila.";
    /**
     * Constructor for objects of class KalahException
     */
    public KalahException(String message){
        super(message);
    }
}
