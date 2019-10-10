import java.util.HashMap;

public class Persona{
    private static HashMap <String,Integer> datos;
    
    static{
        datos=new HashMap<String,Integer>();
        datos.put("Pedro",10000);
        datos.put("Santiago",20000);
        datos.put("Marcos",30000);
        datos.put("Juan",40000);
        datos.put("Judas",50000); 
        
        datos.put("Garcia",null);
        datos.put("Ospina",null);
        datos.put("Guarin",null);
    }    
    
    private String nombre;

    /**
     * Crea una persona
     * 
     */
    public Persona(String nombre){
        this.nombre=nombre;
    }    

    /**
     * Retorna el valor hora de una persona
     * @return
     * @throws EquipoExcepcion si no se conoce la persona o su valor hora
     */
    public int valorHora() throws EquipoExcepcion{
       if (! datos.containsKey(nombre)) throw new EquipoExcepcion(EquipoExcepcion.PERSONA_DESCONOCIDA);
       if (datos.get(nombre)==null) throw new EquipoExcepcion(EquipoExcepcion.VALOR_DESCONOCIDO);
       return datos.get(nombre);
    }
}
