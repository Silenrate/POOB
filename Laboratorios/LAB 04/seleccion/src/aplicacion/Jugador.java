package aplicacion; 
 
/**
 * @version ECI 2019-1
 */

public class Jugador{
    private String nombres;
    private String apellidos;
    private int estatura;    
    private String posicion;
    private String premios;  

 
    public Jugador(String nombres, String apellidos,  String  estatura, String posicion,  String premios) throws SeleccionExcepcion{
        this.nombres = nombres.trim();
        this.apellidos = apellidos.trim();
		try{
                    this.estatura = Integer.parseInt(estatura.trim());
		}
        catch(Exception e){
			throw new SeleccionExcepcion(SeleccionExcepcion.PLAYER_HEIGHT);
		}
                int stature = Integer.parseInt(estatura.trim());
                if(stature<150||stature>210){throw new SeleccionExcepcion(SeleccionExcepcion.PLAYER_HEIGHT_LIMIT);}
		if((posicion.trim().equals("Defensa"))||(posicion.trim().equals("Delantero"))||(posicion.trim().equals("Arquero"))||(posicion.trim().equals("Mediocampista"))){this.posicion = posicion.trim();}
		else{throw new SeleccionExcepcion(SeleccionExcepcion.BAD_POSITION);}
        this.premios = premios.trim();        
    }
    
    /**
     * @return 
     */
    public String getNombres(){
        return nombres;
    }

    /**
     * @return 
     */
    public String getApellidos(){
        return apellidos;
    }

    /**
     * @return 
     */
    public int getEstatura(){
        return estatura;
    }    

    /**
     * @return 
     */
    public String getPosicion(){
        return posicion;
    }
    
    /**
     * @return 
     */
    public String getPremios(){
        return premios;
    }
    
    /**
     * @return 
     */
    public String toString(){
        return nombres +" "+apellidos+ " (" + posicion + ")" + "\n" + estatura + "\n" + premios  ;
    }

}
