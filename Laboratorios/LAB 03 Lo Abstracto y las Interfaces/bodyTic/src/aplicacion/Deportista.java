package aplicacion;
import java.awt.Color;

public class Deportista extends Persona implements EnSalon{
    private Salon salon;   
    protected String palabras;
    protected int paso;
    /**
     * Constructor de un deportista.
     * @param salon El salon donde esta el deportista.
     * @param nombre El nombre del deportista.
     * @param posicionx La coordenada X del deportista.
     * @param posiciony La coordenada Y del deportista.
     */
    public Deportista(Salon salon,String nombre,int posicionx, int posiciony){
        super(nombre,posicionx,posiciony);
        this.salon=salon;
        color=Color.BLACK;
        palabras="Soy"+nombre;
        salon.adicione(this);
        paso=0;
    }
    /**
     * Muestra si es posible que el deportista se mueva en una direccion.
     * @param direccion La direccion a la que se va a mover: (N)orte, (S)ur, (E)ste, (O)este.
     * @return La posibildad de que el deportista se mueva.
     */
    protected boolean puedeMover(char direccion) {
        boolean puede=false;
        int posX = getPosicionX();
        int posY = getPosicionY();
        switch(direccion){
            case 'N' : puede = (posY+PASO < salon.MAXIMO);
            break;
            case 'E' : puede = (posX+PASO < salon.MAXIMO);
            break;
            case 'S' : puede = (posY-PASO >= 0);
            break;
            case 'O': puede = (posX-PASO >= 0);
            break; 
        } 
        return puede;
    }
    /**
     * Detiene la rutina de ejercicio del deportista.
     */
    public void pare(){
        muevaBrazo('I','B'); 
        muevaPierna('I','P');
        muevaBrazo('D','B'); 
        muevaPierna('D','P');       
        palabras="¡Uff!";
    }
    /**
     * Inicia la rutina de ejercicio del deportista.
     */
    public void inicie(){
        palabras="";
        paso++;
        if (getPosicionBrazo('I')==ABAJO && getPosicionBrazo('D')==ABAJO){
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } else if  (getPosicionBrazo('I')==FRENTE){
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } else if (getPosicionBrazo('I')==ARRIBA){
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
        }else if (getPosicionBrazo('D')==FRENTE){
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
            muevaBrazo('D','S'); 
            muevaPierna('D','S');
            muevaBrazo('I','B'); 
            muevaPierna('I','B');
        }else if (getPosicionBrazo('D')==ARRIBA){
            muevaBrazo('D','B'); 
            muevaPierna('D','B');
            muevaBrazo('D','B'); 
            muevaPierna('D','B');
            muevaBrazo('I','S'); 
            muevaPierna('I','S');
        } 
        char direccion;
        if (paso%2==0){
            direccion = 'E';
        }
        else{
            direccion = 'O';
        }
        if (puedeMover(direccion)){
            muevase(direccion);
        }
    }
    /**
     * Muestra la forma del objeto deportista.
     * @return Un string con la palabra "persona".
     */
    public String forma(){
        return EnSalon.FORMAS[0];
    }
    /**
     * Conoce lo que quiere decir el deportista.
     * @return El mensaje del deportista.
     */
    public String mensaje(){
        return palabras;
    }
}

