import java.util.ArrayList;

public class Equipo{
    private ArrayList<Persona> personas;
    
    /**
     * Crea un equipo dado el nombre de sus miembros
     * @param nombres nombres de los miembros del equipo
     */    
    public Equipo(String [] nombres){
        personas= new ArrayList<Persona>();
        for (int i=0; i< nombres.length;i++){
            personas.add(new Persona(nombres[i]));
        }  
    }

    /**
     * Calcula el valor hora de un equipo
     */
    public int valorHora() throws EquipoExcepcion{
       int amount=0;
       for(int i=0;i<personas.size();i++){
           amount+=personas.get(i).valorHora();
       }
       if(amount==0){
           throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
       }
       return amount;
    }
    
    /**
     * Calcula el valor hora estimado de un equipo.
     * Más del 50% del equipo debe tener valores conocidos 
     * El valor de las personas a las que se desconoce su valor es el promedio entre el mínimo y el máximo de las conocidas
     * @return el valor hora del equipo
     * @throws EquipoException si no es posible calcular el valor o existe una persona desconocida
     */
    public int valorHoraEstimado() throws EquipoExcepcion{
       int valor=0; int amount=0;
       int cont=0; int max = -1;
       double min = Double.POSITIVE_INFINITY;
       if(personas.size()==0){
           throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
       }
       for(int i=0;i<personas.size();i++){
           try{
               valor=personas.get(i).valorHora(); amount+=valor;
               if(valor>max){
                   max=valor;
               }
               if(valor<min){
                   min=valor;
               }
               cont++;
           }
           catch(EquipoExcepcion e){
               if(EquipoExcepcion.PERSONA_DESCONOCIDA.equals(e.getMessage())){
                   throw new EquipoExcepcion(EquipoExcepcion.PERSONA_DESCONOCIDA);
               }
           }
       }
       if(cont>=((double)(personas.size()/2.0))){
           int avg = (max+(int)min)/2; amount+=(personas.size()-cont)*avg;
       }
       else{
           throw new EquipoExcepcion(EquipoExcepcion.VALOR_ESTIMADO_DESCONOCIDO);
       }
       return amount;
    }   
    
    /**
     * Calcula el valor hora asumido de un equipo.
     * El valor hora de los desconocidos es el valor de la primera persona conocida
     * El valor hora de los que no se conoce su valor es el valor de la última persona conocida
     * @return el valor hora del equipo
     * @throws EquipoException si no es posible calcular el valor 
     */
    public int valorHoraAsumido() throws EquipoExcepcion{
        int personaDesconocidas=0;
        int valorDesconocido=0;
        int valor; 
        int amount=0;
        int valorp=-1;
        int valoru=-1;
        if(personas.size()==0){
           throw new EquipoExcepcion(EquipoExcepcion.EQUIPO_VACIO);
        }
        for(int i=0;i<personas.size();i++){
           try{
               valor=personas.get(i).valorHora();
               amount+=valor;
               if(valorp==-1){valorp=valor;}
               valoru=valor;
           }
           catch(EquipoExcepcion e){
               if(EquipoExcepcion.PERSONA_DESCONOCIDA.equals(e.getMessage())){
                   personaDesconocidas++;
               }
               else{
                   valorDesconocido++;
               }
           }
        }
        if (valorp==-1 || valoru==-1){
            throw new EquipoExcepcion(EquipoExcepcion.VALOR_ASUMIDO_DESCONOCIDO);
        }
        amount=amount+(personaDesconocidas*valorp)+(valorDesconocido*valoru);
        return amount;
    }   
    
}
