
/**
 * @author Nicolas Aguilera && Daniel Walteros
 *
 */
public class Velocidad{

    public static final double MAXERROR = 0.00000000000001;
    
    private double longitud;
    private Angulo angulo;
    private static double gravedad = 9.8;
    
    /**
     * Constructor de la velocidad, en componentes polares
     * @param l longitud de la velocidad
     * @param a angulo de la velocidad
     */
    public Velocidad (double l, Angulo a) {
        this.longitud=l;
        this.angulo = a;
        if(l<0){
            angulo = new Angulo (a.grados()+180 , 1);
        }
    }

    /**
     * Retorna el componente horizontal
     * @return componente horizontal de la velocidad
     */
    public double componenteH() {
        double compH=Math.abs(longitud)*Math.cos(Math.toRadians(angulo.grados()));
        compH=Math.round(compH * Math.pow(10, 4)) / Math.pow(10, 4);
        return compH;
    }

    /**
     * Retorna el componente vertical de la velocidad
     * @return componente vertical de la velocidad
     */
    public double componenteV() {
        double compV=Math.abs(longitud)*Math.sin(Math.toRadians(angulo.grados()));
        
        compV=Math.round(compV * Math.pow(10, 4)) / Math.pow(10, 4);
        return compV;
    }
    public Velocidad velocidadH(){
        Velocidad velocidad;
        Angulo angulo;
        double componenteH=this.componenteH();
        if(componenteH>=0){
            angulo=new Angulo(0,1);
        }
        else{
            angulo=new Angulo(180,1);
        }
        velocidad = new Velocidad(componenteH , angulo);
        return velocidad;
    }
     public Velocidad velocidadV(){
        Velocidad velocidad;
        Angulo angulo;
        double componenteV=this.componenteV();
        if(componenteV>=0){
            angulo=new Angulo(90,1);
        }
        else{
            angulo=new Angulo(-90,1);
        }
        velocidad = new Velocidad(componenteV , angulo);
        return velocidad;
    }
    /**
     * Retorna el angulo de la velocidad
     * @return angulo de la velocidad
     */
    public Angulo angulo () {
        return angulo;
    }
    /**
     * Retorna la longitud de la velocidad
     * @return la longitud del origen al velocidad
     */
    public double longitud() {
        return longitud;
    }
    /**
     * Compara este velocidad con otro. Serán iguales si la distancia entre ellos es menor que MAXERROR
     * @param v el velocidad a comparar con este
     */
    private boolean equals (Velocidad v) {
        boolean longitud = Math.abs(this.longitud()-v.longitud())<MAXERROR;
        boolean angulo = Math.abs(this.angulo().grados()-v.angulo().grados())<MAXERROR;
        return longitud&&angulo;
    }

    /** 
     * Compara si este velocidad es igual al parametro (debe ser tambien un velocidad)
     * (Es el método que usa JUnit)
     * @param o Objeto a comparar
     */
    @Override
    public boolean equals (Object o) {
        boolean equivalence;
        if(o.getClass()!=this.getClass()){
            equivalence = false;;
        }
        else{
            Velocidad a = (Velocidad) o;
            equivalence = equals (a) ;
        }
        return equivalence;
    }
    

    /**
     * Rota el velocidad el angulo dado, con respecto al origen. 
     * Es decir que el angulo resultante, es la suma del angulo dado con el angulo inicial de la velocidad, 
     * y la distancia es la misma.
     * @param a Angulo a rotar
     */
    public Velocidad rote(Angulo a) {
        Angulo ang = angulo.sume(a);
        Velocidad velocidad = new Velocidad(longitud,ang);
        return velocidad;
    }
    /** 
     * Suma esta velocidad con otra
     * @param v una velocidad
     * @return la nueva velocidad
     */
    public  Velocidad sume(Velocidad v){
        Velocidad velocidad;
        Angulo a;
        double compH = this.componenteH()+v.componenteH();
        compH=Math.round(compH * Math.pow(10, 4)) / Math.pow(10, 4);
        double compV = this.componenteV()+v.componenteV();
        compV=Math.round(compV * Math.pow(10, 4)) / Math.pow(10, 4);
        double l = Math.sqrt(Math.pow(compH,2)+Math.pow(compV,2));
        l=Math.round(l * Math.pow(10, 4)) / Math.pow(10, 4);
        if(compH==0 && compV==0){a = new Angulo(0,1);}
        else if(compH==0 && compV>=0){a = new Angulo(90,1);}
        else if (compH==0 && compV<0){a = new Angulo(-90,1);}
        else if (compV==0 && compH<0){a = new Angulo(180,1);}
        else if (compV==0 && compH>=0){a = new Angulo(0,1);}
        else{
            double temp = Math.toDegrees(Math.atan(compV/compH));
            temp =Math.round(temp * Math.pow(10, 4)) / Math.pow(10, 4);
            a = new Angulo(temp,1);
        }
        velocidad = new Velocidad(l,a);
        return velocidad;
    }
    /** 
     * Resta esta velocidad con otra
     * @param v una velocidad
     * @return la nueva velocidad
     */
    public Velocidad reste(Velocidad v){
        Velocidad velocidad;
        v=v.rote(new Angulo(180,1));
        Angulo a;
        double compH = this.componenteH()+v.componenteH();
        compH=Math.round(compH * Math.pow(10, 4)) / Math.pow(10, 4);
        double compV = this.componenteV()+v.componenteV();
        compV=Math.round(compV * Math.pow(10, 4)) / Math.pow(10, 4);
        double l = Math.sqrt(Math.pow(compH,2)+Math.pow(compV,2));
        l=Math.round(l * Math.pow(10, 4)) / Math.pow(10, 4);
        if(compH==0 && compV==0){a = new Angulo(0,1);}
        else if(compH==0 && compV>=0){a = new Angulo(90,1);}
        else if (compH==0 && compV<0){a = new Angulo(-90,1);}
        else if (compV==0 && compH<0){a = new Angulo(180,1);}
        else if (compV==0 && compH>=0){a = new Angulo(0,1);}
        else{
            double temp = Math.toDegrees(Math.atan(compV/compH));
            temp =Math.round(temp * Math.pow(10, 4)) / Math.pow(10, 4);
            a = new Angulo(temp,1);}
        velocidad = new Velocidad(l,a);
        return velocidad;
    }
    /** 
     * Multiplica esta velocidad por un escalar
     * @param operando, escalar por el que se multiplicara la velocidad
     * @return la nueva velocidad
     */
    public Velocidad escalar(double operando){
        double grados=angulo.grados();
        if(operando<0){grados=grados+180;}
        Velocidad velocidad;
        double l = longitud*Math.abs(operando);
        velocidad = new Velocidad(l,new Angulo(grados,1));
        return velocidad;
    }    
    /** 
     * Realiza el producto velocidad entre esta velocidad y otra
     * @param v, una velocidad
     * @return la nueva velocidad
     */
    public Velocidad vectorial(Velocidad v){
        Velocidad velocidad = new Velocidad (0, new Angulo(0,1));
        return velocidad;
    }
    /** 
     * Halla la velocidad despues de un tiempo t
     * @param t, tiempo
     * @return la nueva velocidad
     */
    public Velocidad tiempo(double t){
        Velocidad velocidad;
        Angulo angulo;
        double compV = this.componenteV()+gravedad*t;
        compV=Math.round(compV * Math.pow(10, 4)) / Math.pow(10, 4);
        double compH = this.componenteH();
        compH=Math.round(compH * Math.pow(10, 4)) / Math.pow(10, 4);
        double magnitud = Math.sqrt(Math.pow(compV,2)+Math.pow(compH,2));
        magnitud=Math.round(magnitud * Math.pow(10, 4)) / Math.pow(10, 4);
        if(compH==0 && compV==0){angulo = new Angulo(0,1);}
        else if(compH==0 && compV>=0){angulo = new Angulo(90,1);}
        else if (compH==0 && compV<0){angulo = new Angulo(-90,1);}
        else if (compV==0 && compH<=0){angulo = new Angulo(180,1);}
        else if (compV==0 && compH>=0){angulo = new Angulo(0,1);}
        else{
            double temp = Math.toDegrees(Math.atan(compV/compH));
            temp =Math.round(temp * Math.pow(10, 4)) / Math.pow(10, 4);
            angulo = new Angulo(temp,1);}
        velocidad = new Velocidad (magnitud , angulo);
        return velocidad;
    }
    /** 
     * Retorna una cadena que describe a este velocidad (en componentes polares)
     */
    @Override
    public String toString () {
          String s = "longitud: "+longitud+" angulo: "+angulo;
          return s;
    }

}
