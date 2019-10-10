package aplicacion;
import java.awt.Color;
/**
 * Un deportista que se ejercita con másfrecuencia que los deportistas comunes.
 *
 * @author (Nicolas Aguilera y Daniel Walteros)
 * @version (11 of March of 2019)
 */
public class DeportistaAvanzado extends Deportista{
    private int rutina;
    private int resistencia;
    /**
     * Constructor de un Deportista Avanzado.
     * @param salon El salon donde esta el deportista.
     * @param nombre El nombre del deportista.
     * @param posicionx La coordenada X del deportista.
     * @param posiciony La coordenada Y del deportista.
     */
    public DeportistaAvanzado(Salon salon,String nombre,int posicionx, int posiciony){
        super(salon,nombre,posicionx, posiciony);
        rutina=0;
        resistencia=2;
        color=Color.ORANGE;
    }
    /**
     * Inicia la rutina de un deportista avanzado.
     */
    public void inicie(){
       rutina++;
       super.inicie();
       char direccion=( (paso % 2 == 0)  ? 'E':'O');
       if (puedeMover(direccion)){
            muevase(direccion);
       }
    }
    /**
     * Aumenta la duracion de un deportista avanzado.
     */
    public void masResistencia(){
        resistencia++;
    }
    /**
     * Termina la rutina de un deportista avanzado.
     */
    public void pare(){
        if (rutina!=0 && rutina%resistencia==0){
            super.pare();
            masResistencia();
        }
    }
    /**
     * Le permite al deportista avnazdo decidir si seguir o no, por defecto el continuara eternamente.
     */
    public void decida(){
         inicie();
    }
}
