package aplicacion;
import java.awt.Color;
/**
 * Un deportista que solo piensa en hacer ejercicio hasta que no puede mas.
 *
 * @author (Nicolas Aguilera y Daniel Walteros)
 * @version (11 of March of 2019)
 */
public class DeportistaVigorexo extends Deportista{
    private int energia;
    /**
     * Constructor de un  Deportista Vigorexo.
     */
    public DeportistaVigorexo(Salon salon,String nombre,int posicionx, int posiciony){
        super(salon,nombre,posicionx, posiciony);
        color=Color.GREEN;
        energia=10;
    }
    /**
     * Inicia la rutina de un deportista vigorexo.
     */
    public void inicie(){
       if(energia>0){
           super.inicie();
           char direccion=( (paso % 2 == 0)  ? 'E':'O');
           if (puedeMover(direccion)){
               muevase(direccion);
               muevase(direccion);
           }
           energia--;
       }
       else{
           pare();
       }
    }
    /**
     * Termina la rutina de un deportista vigorexo.
     */
    public void pare(){
        if (energia>0){
            inicie();
            palabras = "Aun no voy a parar";
        }
        else{
            color=Color.RED;
            super.pare();
            palabras = "Ya me canse";
        }
    }
    /**
     * Decide si el deportista vigorexo sigue ejercitandose, en base a su energia.
     */
    public void decida(){
        if(energia>0){
            inicie();
        }
        else{
            pare();
        }
    }
}
