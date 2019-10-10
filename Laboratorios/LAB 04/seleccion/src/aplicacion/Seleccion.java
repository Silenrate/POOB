package aplicacion; 

import java.util.LinkedList;
import java.util.ArrayList;

/**
 * @version ECI 2019-1
 */

public class Seleccion{
    private static  LinkedList <Jugador> jugadores;

    public Seleccion(){
        jugadores = new LinkedList<Jugador>();
    }
    
    /**
     * Adiciona algunos jugadores
     */
    public void adicione()throws SeleccionExcepcion{
        Jugador ejemplos[] = {
	        new Jugador("James David","Rodr�guez Rubio", "181","Mediocampista",
	        "Recibi� en 2009, 2012 y 2014 el premio de jugador revelaci�n de la Primera Divisi�n de Argentina y en la Primeira Liga de Portugal"),        
	        new Jugador("Radamel Falcao","Garc�a Z�rate", "177","Delantero",
	        "(11�) mejor jugador del mundo, seg�n la votaci�n del FIFA Bal�n de Oro 2013"),                            
            new Jugador("David","Ospina Ram�rez", "183","Arquero",
	        "Jugador del partido contra Estados Unidos en la Copa Am�rica Centenario 2016"),   
        };
        for(Jugador informacion : ejemplos) {
            adicione(informacion);
        }
    }
    
   
    
    /**
     * Consulta la informaci�n de un jugador
     */
    public static Jugador getInformacion(String nombres,String apellidos){
    	Jugador c=null;
    	for(int i=0;i<jugadores.size() && c == null;i++){
    	    if (jugadores.get(i).getNombres().compareToIgnoreCase(nombres)==0 &&
                   (jugadores.get(i).getApellidos().compareToIgnoreCase(apellidos)==0)){
                   c=jugadores.get(i);
                }
    	}
    	return c;
    }


    /**
     * Adiciona un nuevo jugador
     */
    public static void adicione(String nombres, String apellidos, String  estatura, String posicion,  String premios)throws SeleccionExcepcion{
	   adicione(new Jugador(nombres, apellidos, estatura, posicion, premios));
    }

    /**
     * Adiciona un nuevo jugadores
     */
    public static void adicione(Jugador informacion) throws SeleccionExcepcion{
    	int i=0;
    	while ((i<jugadores.size()) && (jugadores.get(i).getApellidos().compareToIgnoreCase(informacion.getApellidos())<0)){
    	    i++;
    	}
		for(i=0 ; i<jugadores.size() ; i++){
			Jugador jugador = jugadores.get(i);
			if((jugador.getApellidos().equals(informacion.getApellidos())) && (jugador.getNombres().equals(informacion.getNombres()))){
				throw new SeleccionExcepcion(SeleccionExcepcion.PLAYER_ALREADY_EXISTS);
			}
		}
    	jugadores.add(i,informacion);
    }
    

    
    /**
     * Consulta las jugadores que inican con un prefijo
     * @param prefijo El prefijo a buscar
     * @return 
     */
    public ArrayList<Jugador> busque(String prefijo){
        ArrayList<Jugador> resultados = new ArrayList<>();
    	prefijo=prefijo.toUpperCase();
    	for(int i=0;i<jugadores.size();i++){
    	    if(jugadores.get(i).getNombres().toUpperCase().startsWith(prefijo)){
    	       resultados.add(jugadores.get(i));
    	    }	
    	}
        return resultados;
    }

    /**
     * Consulta el numero de jugadores
     * @return 
     */
    public int numeroJugadores(){
        return jugadores.size();
    }


    /**
     * Consulta todos los jugadores
     * @return 
     */
    public String toString(){
	StringBuffer allEntries=new StringBuffer();
        for(Jugador informacion : jugadores) {
            allEntries.append(informacion.toString().length()<=150? informacion: informacion.toString().substring(0,140)+"...");
            allEntries.append('\n');
            allEntries.append('\n');
        }
        return allEntries.toString();
    }
}
