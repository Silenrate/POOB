import javax.swing.JOptionPane;
import java.awt.Toolkit;
/**
 * A gestor thats manage certain group of ceilings.
 *
 * @author (Daniel Walteros y Nicolas Aguilera)
 * @version (31 of January of 2019)
 */
public class CeilingManager
{
    private Ceiling[] gestor;
    private String visualizacion;
    private int[] layers;
    private String[] ceilings;
    private int conCeilings;
    private static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};

    /**
     * Constructor for objects of class CelilingManager
     * 
     * @param visualizacion the way that represents the ceiling(the only ways acceptable is "tree" or "ceiling")
     */
    public CeilingManager(String visualizacion)
    {
        gestor = new Ceiling[10];
        ceilings = new String[10];
        conCeilings = 0;
        this.visualizacion=visualizacion;

    }

    /**
     * Adds a new ceiling to the ceiling gestor
     *
     * @param layers A integers array with the value of the resistances.
     * @param color The color of the ceiling(cannot be repeated)
     */
    public void add(int[] layers , String color){
        if (check(color)==true){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null, "Ya existe esa capa","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            Ceiling newCeiling = new Ceiling(layers, color);
            gestor[conCeilings]=newCeiling;
            ceilings[conCeilings]=color;
            conCeilings+=1;
            show();
        }
    }
    /**
     * Adds a new resistance in one ceiling of the gestor.
     *
     * @param color The color of the ceiling that is going to have a new layer.
     * @param layer The value of the resistance that have the new resistance.
     */
    public void addCapa(String color, int layer){
        if(check(color)==true){
            for(int i=0 ; i<conCeilings ; i++){
                if (ceilings[i].equals(color)){
                    gestor[i].add(layer);
                }
            }
            show();
        }
        else{
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"Esa capa no tiene un techo asignado","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Deletes the first resistance in one ceiling of the gestor.
     *
     * @param color The color of the ceiling that is going to have a new layer.
     * @param layer The value of the resistance that have the new resistance.
     */
    public void deleteCapa(String color){
        if(check(color)==true){
            for(int i=0 ; i<conCeilings ; i++){
                if (ceilings[i].equals(color)){
                    gestor[i].delete();
                }
            }
            show();
        }
        else{
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"Esa capa no tiene un techo asignado","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Delete a ceiling from the gestor.
     *
     * @param delColor The color of the ceiling that is going to be deleted.
     */
    public void deleteLayer(String delColor){
        Ceiling[] newGestor = new Ceiling[conCeilings-1];
        String[] newColors  = new String[conCeilings-1];
        if(check(delColor)==true){
            conCeilings-=1;
            for(int i=0 ; i<conCeilings ; i++){
                if (ceilings[i].equals(delColor)==true){
                    newGestor[i]=gestor[i];
                    newColors[i]=ceilings[i];
                }
            }
            gestor=newGestor;
            ceilings=newColors;
            show();
        }
        else{
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"Esa capa no tiene un techo asignado","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Adds a new resistance in all the ceilings of the gestor.
     *
     * @param layer The value of the resistance that have the new resistance.
     */
    public void addAll(int layer){
        for(int i=0 ; i<conCeilings ; i++){
                gestor[i].add(layer);
        }
        show();
    }
    /**
     * Deletes the first resistance in all the ceilings of the gestor.
     *
     */
    public void deleteAll(){
        for (int i=0; i<conCeilings ; i++){
            gestor[i].delete();
        }
        show();
    }
    /**
     * Comparates two ceilings for their tree form.
     *
     * @param tree1 The color of the first ceiling that is going to be compared.
     * @param tree2 The color of the second ceiling that is going to be compared.
     * @return The boolean value about if the two ceilings have the same tree form. 
     */
    public boolean equal(String tree1 , String tree2){
        if((check(tree1)==true)&&(check(tree2)==true)){
            Tree firstTree;
            Tree secondTree;
            for(int i=0 ; i<conCeilings ; i++){
                for (int j=0;j<conCeilings;j++){
                    if ((ceilings[i].equals(tree1)==true)&&(ceilings[j].equals(tree2)==true)){
                        firstTree=gestor[i].getTree();
                        secondTree=gestor[j].getTree();
                        gestor[i].showTree(0);
                        gestor[j].showTree(100);
                        return (firstTree.toString()).equals(secondTree.toString());
                    }
                }
            }
            return false;
        }
        else{
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"Alguno(s) de los techos no esta en el gestor.","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /**
     * Sets the visualization of the ceiling.
     *
     * @param newVisualizacion A string representing the showing form(tree or ceiling in double quotes).
     */
    public void setVisualizacion(String newVisualizacion){
        if((newVisualizacion=="tree")||(newVisualizacion == "ceiling")){
            visualizacion = newVisualizacion;
            show();
        }
        else{
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null, "Los metodos de visualizacion son tree o ceiling entre comillas dobles","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Function that checks if a ceiling is on the gestor based in their layer color.
     *
     * @param lcolor The color of the ceiling(cannot be repeated).
     * @return The boolean value of the presence o the ceiling in the gestor.
     */
    private boolean check(String lcolor){
        for(int i=0;i<conCeilings;i++){
            if (ceilings[i].equals(lcolor)){
                return true;
            }
        }
        return false;
    }
    /**
     * Shows the ceilings in the gestor in their visualization form.
     *
     */
    private void show(){
        for (int i=0;i<gestor.length;i++){
                if (gestor[i] != null){
                    gestor[i].makeGInvisible(visualizacion);
                }
        }
        int x=0;
        if(visualizacion=="tree"){
            for (int i=0;i<gestor.length;i++){
                if (gestor[i] != null){
                    gestor[i].showTree(x);
                    x+=300;
                }
            }
        }
        else if(visualizacion == "ceiling"){
            for (int i=0;i<gestor.length;i++){
                if (gestor[i] != null){
                    gestor[i].showCeiling();
                    gestor[i].move(x,0);
                    x+=300;
                }
            }
        }
        else{
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null, "Los metodos de visualizacion son tree o ceiling entre comillas dobles","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Gets the ceiling with more resistance.
     * 
     * @return The color of the ceiling with the max amount of resistance in the gestor.
     */
    public String getMaxCeiling(){
        int maxResistance=0;
        String maxCeiling="None";
        for (int i=0;i<gestor.length;i++){
            if (gestor[i].getResistance() > maxResistance){
                maxResistance = gestor[i].getResistance();
                maxCeiling = ceilings[i];
            }
        }
        return maxCeiling;
    }
}
        
              
