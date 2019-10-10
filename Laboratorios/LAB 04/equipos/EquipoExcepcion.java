

public class EquipoExcepcion extends Exception{

    public static final String  VALOR_DESCONOCIDO= "El valor no es conocido";
    public static final String  PERSONA_DESCONOCIDA="La persona no es conocida";
    public static final String  EQUIPO_VACIO="El equipo está vacio";
    public static final String  VALOR_ESTIMADO_DESCONOCIDO="Más del 50% del equipo debe tener valores conocidos";
    public static final String  VALOR_ASUMIDO_DESCONOCIDO="No existe ninguna persona con un valor conocido";
    public EquipoExcepcion(String message){
        super(message);
    }

}
