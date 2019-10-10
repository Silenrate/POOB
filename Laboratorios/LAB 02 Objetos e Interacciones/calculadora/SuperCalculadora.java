import java.util.Stack;

/** 
 * Representa una calculadora de velocidades
 * @author Nicolas Aguilera Contreras && Daniel Walteros
 */
   

//No olvide documentar los métodos
public class SuperCalculadora{

    private Stack<Velocidad> operandos;
    boolean did;
    
    /**
     * Constructor de la SuperCalculadora
     * 
     */
    public SuperCalculadora(){
        did=false;
        this.operandos = new Stack<Velocidad>();
    }
    /**
     * Adiciona una velocidad al tope de la pila de Velocidades
     * @param longitud longitud de la velocidad
     * @param grados valor del angulo en grados de la velocidad
     */
    public void adicione(double longitud, double grados){
        if(longitud<0){grados=grados+180;}
        Angulo angulo = new Angulo (grados , 1);
        Velocidad velocidad = new Velocidad(Math.abs(longitud) , angulo);
        operandos.push(velocidad);
        did=true;
    }
    /**
     * Elimina una velocidad del tope de la pila de Velocidades
     *
     */
    public void elimine(){
        if(operandos.empty()==false){
            operandos.pop();
            did=true;
        }
        else{did=false;}
    }
    /**
     * Duplica la velocidad del tope de la pila de Velocidades
     *
     */
    public void duplique(){
        if(operandos.empty()==false){
            Velocidad velocidad = operandos.peek();
            operandos.push(velocidad);
            did=true;
        }
        else{did=false;}
    }
    /**
     * Elimina los dos primeros elementos de la pila de velocidades.
     * Realiza una operacion entre ellos e ingresa la nueva velocidad al tope de la pila de Velocidades
     * @param operacion Operacion a realizar(Los caracteres de las operaciones posibles son: +, -, x (producto vectorial),h (velocidad horizontal), v (velocidad vertical))
     */
    public void calcule(char operacion){
        if(operandos.size()>=2){
            if(String.valueOf(operacion).equals("+")){
                Velocidad v1 = operandos.pop();
                Velocidad v2 = operandos.pop();
                Velocidad v3 = v1.sume(v2);
                operandos.push(v3);
                did=true;
            }
             else if(String.valueOf(operacion).equals("-")){
                Velocidad v1 = operandos.pop();
                Velocidad v2 = operandos.pop();
                Velocidad v3 = v1.reste(v2);
                operandos.push(v3);
                did=true;
            }
             else if(String.valueOf(operacion).equals("x")){
                Velocidad v1=operandos.pop();
                Velocidad v2=operandos.pop();
                operandos.push(v1.vectorial(v2));
            }
        }
        if(operandos.size()>=1){
            if(String.valueOf(operacion).equals("h")){
                Velocidad v1 = operandos.pop();
                operandos.push(v1.velocidadH());
                did=true;
            }
            else if(String.valueOf(operacion).equals("v")){
                Velocidad v1 = operandos.pop();
                operandos.push(v1.velocidadV());
                did=true;
            }
            else{did=false;}
        }
        if(operandos.empty()){did=false;}
    }
    /**
     * Calcula la operacion deseada sobre el elemento tope de la pila de Velocidades
     * @param operacion la operacion a realizar (Los caracteres de las operaciones posibles son: * (producto escalar), t velocidad después de un tiempo)
     * @param parametro puede ser tiempo o un escalar dependiendo de la operacion a realizar
     */
    public void calcule(char operacion, double parametro){
        if(operandos.empty()==false){
            if(String.valueOf(operacion).equals("*")){
                Velocidad velocidad = operandos.pop();
                operandos.push(velocidad.escalar(parametro));
                did=true;
            }
            else if(String.valueOf(operacion).equals("t") && parametro>0){
                Velocidad velocidad = operandos.pop();
                operandos.push(velocidad.tiempo(parametro));
                did=true;
            }
            else{did=false;}
        }
        else{did=false;}
    }
    /**
     * Muestra la longitud y el angulo de la velocidad en forma de cadena de caracteres
     * 
     * @return Un string que indica la ultima velocidad que se puso en la pila
     */
    public String consulte(){
        if(operandos.empty()==false){
            Velocidad velocidad = operandos.peek();
            String sv = velocidad.toString();
            return sv;
        }
        else{did=false;}
        return null;
    }
    /**
     * Conoce si el ultimo metodo se realizó correctamente
     * 
     * @return Una expresion booleana que determina si la ultima operacion fue exitosa
     */
    public boolean ok(){
        return did;
    }
    
    public Velocidad velocidad(){
        if(operandos.empty()){return null;}
        return operandos.peek();
    }
}
    



