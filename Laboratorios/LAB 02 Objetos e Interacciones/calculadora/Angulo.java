/**
 * Esta clase sirve para manejar angulos. 
 * La medida de los ángulos podrá especificarse en grados o radianes(Se incorporo la opcion de gradianes)
 * y podrá solicitarse en cualquiera de estas unidades, independientemente de con cual hayan sido creados.
 * Internamente se harán las conversiones que sean necesarias para mantenerlos en grados
 * Lo trabajaremos mediante objetos inmutables, es decir, sin métodos modificadores. 
 * @author Nicolas Aguilera , Daniel Walteros 
 */
public class Angulo {

    /** Constantes para indicar que el argumento está en radianes */
    public static final int RADIANES = 0;
    /** Constantes para indicar que el argumento está en grados */
    public static final int GRADOS = 1;
    /** Constantes para indicar que el argumento está en gradianes */
    public static final int GRADIANES = 2;


    /** Constante para maximo error admitido al comparar dos angulos.  
     *  Recuerde que los cálculos en el computador con variables de plunto flotante
     *  tienen una precisión limitada, y se requiere un margen de tolerancia
     */
    public static final double MAXERROR = 0.00000000000001;

    private double grados;
    
    /** Crea un angulo a partir del valor dado en grados o en radianes
     * @param valor el valor de medida del angulo
     * @param tipo Tipo de medida del angulo: puede ser GRADOS, RADIANES, GRADIANES
     */
    public Angulo (double valor, int tipo){
        if(tipo==RADIANES){
            grados=Math.toDegrees(valor);
            grados = grados%360;
        }
        else if(tipo==GRADOS){
            grados = valor;
            grados = grados%360;
        }
        else if(tipo==GRADIANES){
            grados = (valor*0.9);
            grados = grados%360;
        }
        if(grados<0){
            grados+=360;
        }
    }
    
    
    /**Valor del angulo en grados
     * @return el valor del angulo en grados, 0 <= result < 360
     */
    public double grados () {
        return grados;
    }
    
    /**Valor del angulo en radianes
     * @return el valor del angulo en radianes, 0 <= result < 2*PI
     */
    public double radianes () {
        System.out.println(grados);
        return Math.toRadians(grados)%360;
    }
    
    
    /**
     * Suma este angulo con otro. Retorna un nuevo angulo
     * @param a El angulo a sumar
     * @return this + a
     */
    public Angulo sume (Angulo ang) {
        Angulo temporal = new Angulo((ang.grados()+grados),1);
        return temporal;
    }

    /**
     * Resta este angulo con otro. Retorna un nuevo angulo
     * @param a El angulo a sumar
     * @return this - a
     */
    public Angulo reste (Angulo a) {
        Angulo temporal = new Angulo(grados-a.grados(),1);
        return temporal;
    }

    /**
     * Multiplica este angulo con otro. Retorna un nuevo angulo
     * @param a El angulo a multiplicar
     * @return this * a
     */
    public Angulo multiplique (Angulo ang) {
        Angulo temporal = new Angulo(ang.grados()*grados,1);
        return temporal;
    }

    /**
     * Divide este angulo con otro. Retorna un nuevo angulo
     * @param a El angulo a dividir
     * @return this / a
     */
    public Angulo divida (Angulo a) {
        Angulo temporal = new Angulo(a.grados()/grados,1);
        return temporal;
    }
    
    
    /**
     * Multiplica esta angulo por un real
     * @param r real para hacer el producto 
     * @return r * this
     */
    public Angulo multiplique (double r) {
        Angulo temporal = new Angulo(r*grados,1);
        return temporal;
    }
    
    /**
     * Compara a este angulo con otro, para ver si son iguales, 
     * teniendo en cuenta el margen de error MAXERROR, dado que se trabaja con punto flotante
     * @param a angulo para compararse
     * @return |this - a| < MAXERROR
     */
    public boolean equals (Angulo a) {
        return Math.abs(grados-a.grados())<MAXERROR;
    }
    /** overrides Object.equals()
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals (Object o) {
        boolean equivalence;
        if(o.getClass()!=this.getClass()){
            equivalence = false;;
        }
        else{
            Angulo a = (Angulo) o;
            equivalence = equals (a) ;
        }
        return equivalence;
    }

    /**Calcula el seno
     * @return el seno de este angulo
     */
    public double seno () {
        return Math.sin(grados);
    }

    /**Calcula el coseno
     * @return el coseno de este angulo
     */
    public double coseno () {
        return Math.cos(grados);
    }

    /**
     * Retorna el valor del angulo en grados
     * @return the information of this object
     */
    public String toString() {
      return Double.toString(grados);
    }
}
