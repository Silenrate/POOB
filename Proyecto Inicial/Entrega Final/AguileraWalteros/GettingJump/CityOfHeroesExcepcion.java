package GettingJump;
/**
 * The exceptions in the city of heroes.
 *
 * @author (Nicolas Aguilera y Daniel Walteros)
 * @version (25 of March of 2019)
 */
public class CityOfHeroesExcepcion extends Exception{
    public static final String ALREADY_BUILD_IN_CITY = "Ya existe un edificio en esa posicion";
    public static final String ALREADY_HERO_IN_BUILD = "El heroe ya esta en ese edificio";
    public static final String ALREADY_HERO_IN_CITY = "Ya hay un heroe de ese color";
    public static final String ALREADY_OTHER_HERO_IN_BUILD = "Hay un heroe en ese edificio";
    public static final String BAD_BUILD_DIMENSIONS = "El edificio sale de los limites de la ciudad";
    public static final String BUILD_NOT_IN_CITY = "No existe ese edificio";
    public static final String HERO_NOT_IN_CITY = "No hay un heroe de ese color";
    public static final String NOT_ACTION = "No se ha realizado ninguna accion";
    public static final String STRENGTH_OUT_OF_RANGE = "La fuerza va entre 1 y 100";
    public static final String UNMAKEABLE_JUMP = "No es posible llegar";
    public static final String UNMAKEABLE_REDO = "No se puede rehacer";
    public static final String UNMAKEABLE_UNDO = "El ultimo metodo no se puede rehacer";
    public static final String UNMAKEABLE_ZOOM = "Caracter Equivocado (Solo se puede usar + o -)";
    public static final String UNREACHABLE_JUMP = "No es posible llegar a ese edificio";
    public static final String WRONG_BUILD_TYPE = "Los tipos validos son normal, radical, flexible y hard";
    public static final String WRONG_HERO_TYPE = "Los tipos validos son normal, careful, climber, paratrooper y constructor";
    public CityOfHeroesExcepcion(String mensaje){
        super(mensaje);
    }
}
