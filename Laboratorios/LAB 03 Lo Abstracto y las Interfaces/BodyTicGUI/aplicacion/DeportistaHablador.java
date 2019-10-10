package aplicacion;
import java.awt.Color;
/**
 * Un deportista que prefiere hablar a hacer ejercicio.
 *
 * @author (Nicolas Aguilera y Daniel Walteros)
 * @version (11 of March of 2019)
 */
public class DeportistaHablador extends Deportista{
    private String[] mensajes;
    /**
     * Constructor de un Deportista Hablador.
     */
    public DeportistaHablador(Salon salon,String nombre,int posicionx, int posiciony){
        super(salon,nombre,posicionx, posiciony);
        color=Color.ORANGE;
        mensajes = new String[4];
        mensajes[0] ="Entrenamiento de fuerza, trabajo cardiovascular y un plan de alimentación";
        mensajes[1] ="El dolor es la debilidad dejando el cuerpo.";
        mensajes[2] ="Solo los caminos duros llevan a la grandeza";
        mensajes[3] ="No pain No gain";
    }
    /**
     * Inicia la rutina de un deportista hablador.
     */
    public void inicie(){
        paso++;
        palabras = "";
        if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
            muevaBrazo('I','S');
            muevaBrazo('D','S'); 
            muevaPierna('I','S');
            muevaPierna('D','S');
        }
        if (getPosicionBrazo('I')==FRENTE && getPosicionBrazo('D')==FRENTE){
            palabras = mensajes[3];
            if(super.puedeMover('E')){
                muevaBrazo('I','S');
                muevaBrazo('D','S'); 
                muevaPierna('I','S');
                muevaPierna('D','S');
                muevase('E');
            }
            else{
                muevaPierna('I','B');
                muevaPierna('D','B');
            }
        }else if (getPosicionBrazo('I')==ARRIBA && getPosicionBrazo('D')==ARRIBA){
            muevaBrazo('I','B'); 
            muevaBrazo('D','B'); 
            muevaPierna('I','B');
            muevaPierna('D','B');
            muevaBrazo('I','B'); 
            muevaBrazo('D','B'); 
            muevaPierna('I','B');
            muevaPierna('D','B');
        }
    }
    /**
     * Termina la rutina de un deportista hablador.
     */
    public void pare(){
         super.pare();
         palabras = mensajes[paso%3];
    }
}

