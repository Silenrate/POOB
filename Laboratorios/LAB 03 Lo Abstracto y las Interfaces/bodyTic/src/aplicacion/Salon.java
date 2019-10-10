package aplicacion;
import java.util.*;


/**
 * @author ECI 2014
 * Salon de la aplicación egiptologos
 */
/**
 * @author ECI
 *
 */
public class Salon{
    public static final int MAXIMO = 500;
    private static Salon salon = null;
    private ArrayList<EnSalon> elementos;
    /**
     * Construye un salon.
     */
    private Salon() {
        elementos= new ArrayList<EnSalon>();
    }
    /**
     * Construye un salon para el usuario.
     * @return El salon para el usuario.
     */
    public static Salon demeSalon(){
        if (salon==null){
            salon=new Salon();
        }
        return salon;
    }
    /**
     * Crea un nuevo salon para el usuario.
     */
    public static void nuevoSalon() {
        salon=new Salon();
    }   
    /**
     * Cambia el salon para el usuario.
     * @param d El nuevo salon.
     */
    public static void cambieSalon(Salon d) {
        salon=d;
    }
    /**
     * Muestra un objeto del salon.
     * @param n El numero del objeto en el salon.
     * @return El objeto que hay en el salon.
     */
    public EnSalon deme(int n){
        EnSalon h=null;
        if (1<=n && n<=elementos.size()){
            h=elementos.get(n-1);
        }    
        return h; 
    }
    /**
     * Adiciona un nuevo elemento en el salon.
     * @param e El elemento a añadir.
     */
    public void adicione(EnSalon e){
        elementos.add(e);
    }
    /**
     * Muestra la cantidad de elementos en el salon.
     * @return El numero de elementos.
     */
    public int numeroEnSalon(){
        return elementos.size();
    }
    /**
     * Adiciona los elementos del salon.
     */
    public void entrada(){ 
        Deportista edward = new Deportista(this,"edward",100,100);
        Deportista bella = new Deportista(this,"bella",200,100);
        DeportistaAvanzado neo = new DeportistaAvanzado (this,"neo",250,300);
        DeportistaAvanzado trinity = new DeportistaAvanzado (this,"trinity",350,300);
        DeportistaHablador han = new DeportistaHablador(this,"han",100,300);
        DeportistaHablador leila = new DeportistaHablador(this,"leila",100,400);
        Bola suroeste = new Bola(this,0,-25,50);
        Bola noreste = new Bola(this,540,500,50);
        DeportistaVigorexo nicolas = new DeportistaVigorexo(this,"nicolas",100,200);
        DeportistaVigorexo daniel = new DeportistaVigorexo(this,"daniel",200,200);
        Pesa pesa = new Pesa(this,340,-25,200);
        Pesa pesa1 = new Pesa(this,0,490,200);
    }
    /**
     * Saca todos los elementos del salon.
     */
    public void salida(){ 
       elementos.clear();
    }
    /**
     * Inicia la rutina del salon para cada elemento en el salon.
     */
    public void inicio(){
       for (int i=0;i<elementos.size();i++){
           elementos.get(i).inicie();
       }
    }
    /**
     * Termina la rutina del salon para cada elemento en el salon.
     */
    public void parada(){
       for (int i=0;i<elementos.size();i++){
           elementos.get(i).pare();
       }
    }
    /**
     * Decide la proxima accion de cada elemento en el salon.
     */
    public void decision(){
       for (int i=0;i<elementos.size();i++){
           elementos.get(i).decida();
       }
    }
}
