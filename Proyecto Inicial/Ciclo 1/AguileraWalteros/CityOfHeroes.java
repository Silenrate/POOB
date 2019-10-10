import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Toolkit; 
/**
 * A city composed of buildings in which the heroes jumps from roof to roof.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (7 of February of 2019)
 */
public class CityOfHeroes
{
    private static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};
    private int cityWidth;
    private int cityHeight;
    private Rectangle city;
    private ArrayList<Building> buildings;
    private ArrayList<Hero> heroes;
    private ArrayList<String> deads;
    private static final double gravedad = 9.80665;
    private boolean jump;
    private boolean did;
    /**
     * Constructor for objects of class CityOfHeroes
     * 
     * @param cityWidth The width of the city.
     * @param cityHeight The height of the city.
     */
    public CityOfHeroes(int cityWidth, int cityHeight){
        this.cityWidth = cityWidth;
        this.cityHeight = cityHeight;
        deads=new ArrayList<>();
        city=new Rectangle();
        buildings = new ArrayList<>();
        heroes=new ArrayList<>();
        city.changeSize(cityHeight,cityWidth);
        city.changeColor("darkGray");
        city.goTo(0,0);
        jump=false;
        did=true;
    }
    /**
     * Knows if the previous method was executed correctly
     * 
    */
    public boolean ok(){
        return did;
    }
    /**
     * Returns the strength of a hero
     * 
     * @param heroe The color oh the hero
    */
    public int strength(String heroe){
        heroe=heroe.toLowerCase();
        for(int i=0 ; i< heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(heroe)){
                did=true;
                return heroes.get(i).getStrength();
            }
        }
        Toolkit.getDefaultToolkit().beep(); 
        JOptionPane.showMessageDialog(null,"El edificio sale de los limites de la ciudad","Error",JOptionPane.ERROR_MESSAGE);
        did=false;
        return 0;
    }
    /**
     * Adds a building into the city, if any dimension of the building gets out of the city or clash with other building doesn´t add it.
     * 
     * @param x The origin X coordinate of the building.
     * @param width The width of the building.
     * @param height The height of the building.
     * @param hardness The hardness of the building.
     */
    public void addBuilding(int x, int width , int height , int hardness){
        if(x<0 || x>cityWidth || x+width>cityWidth || height>cityHeight){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"El edificio sale de los limites de la ciudad","Error",JOptionPane.ERROR_MESSAGE);
            did=false;
        }
        else{
            boolean flag = true;
            for(int i=0;i<buildings.size();i++){
                int currentX = buildings.get(i).getX();
                int currentWidth = buildings.get(i).getWidth();
                if((x>currentX && x<currentX+currentWidth)||(x+width>currentX && x+width<currentX+currentWidth)||x<currentX && x+width>currentX+currentWidth){
                    Toolkit.getDefaultToolkit().beep(); 
                    JOptionPane.showMessageDialog(null,"Ya existe un edificio en esa posicion","Error",JOptionPane.ERROR_MESSAGE);
                    flag=false;
                    did=false;
                    break;
                }
            }
            if (flag == true){
                Building building = new Building(x,width,height,hardness);
                Rectangle imageBuilding = building.getBuild();
                buildings.add(building);
                imageBuilding.changeColor(colors[buildings.size()-1]);
                did=true;
            }
        }
        sortCity();
    }
    /**
     * Removes a building into the city if the building exists.
     * 
     * @param x The origin X coordinate of the building.
    */
    public void removeBuilding(int x){
        if(x>buildings.size()){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"No existe ese edificio","Error",JOptionPane.ERROR_MESSAGE);
            did=false;
            return;
        }
        did=true;
        buildings.remove(x-1);
        sortCity();
    }
    /**
     * Shows the colors of the heroes that are dead.
     * 
     * @return An arrayList showing the colors of the dead heroes.
     */
    public String[] deads(){
        did=true;
        String [] tempDeads = new String[deads.size()];
        for(int i=0;i<deads.size();i++){
            tempDeads[i]=deads.get(i);
        }
        return tempDeads;
    }
    /**
     * Adds a new hero in the city, a hero of a color that is already in the city cannot be adds.
     * 
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    public void addHeroe(String color ,int hidingBuilding , int strength){
        color=color.toLowerCase();
        if(strength>100){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"La fuerza maxima es 100","Error",JOptionPane.ERROR_MESSAGE);
            did=false;
            return;
        }
        if(hidingBuilding>buildings.size()){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,"No existe el edificio numero:"+hidingBuilding,"Error",JOptionPane.ERROR_MESSAGE);
            did=false;
            return;
        }
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(color)){
                Toolkit.getDefaultToolkit().beep(); 
                JOptionPane.showMessageDialog(null,"Ya hay un heroe de ese color","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        Hero heroe = new Hero(color , hidingBuilding , strength);
        heroes.add(heroe);
        sortCity();
        makeVisible();
        did=true;
    }
    /**
     * Removes a hero in the cit if the color of the hero is in the city.
     * 
     * @param color A string that represents the color of the hero.
     */
    public void removeHeroe(String color){
        color=color.toLowerCase();
        for(int i=0;i<heroes.size();i++){
            Hero heroe = heroes.get(i);
            if (heroe.getColor().equals(color)){
                heroes.remove(i);
                sortCity();
                did=true;
                return;
            }
        }
        did=false;
        Toolkit.getDefaultToolkit().beep(); 
        JOptionPane.showMessageDialog(null,"No hay un heroe de ese color","Error",JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Verifies if in a jump the hero get hurts or gets intact.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * @param slow A boolean indicating if the jumps sees slow or not.
     */
    public void jump(String heroe , int velocity , int angle , boolean slow){
        heroe=heroe.toLowerCase();
        jump=true;
        isSafeJump(heroe , velocity , angle , slow);
        jump=false;
        did=true;
    }
    /**
     * Makes jump a hero into the city.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * @param slow A boolean indicating if the jumps sees slow or not.
     * 
     * @return The boolean value that represents if the hero gets hurt or get intact.
     */
    public boolean isSafeJump(String heroe , int velocity , int angle , boolean slow){
        heroe=heroe.toLowerCase();
        int yi=0;
        int xi=0;
        double tMax=0;
        int hidingBuilding=0;
        int heroeIndex = 0;
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(heroe)){
                hidingBuilding = heroes.get(i).getHidingBuilding();
                heroeIndex=i;
                if(hidingBuilding==0){
                    yi=cityHeight-60;
                    xi=heroes.get(i).getNoBuilding();
                }
                else{
                yi = cityHeight-buildings.get(hidingBuilding-1).getHeight();
                xi = buildings.get(hidingBuilding-1).getX()+buildings.get(hidingBuilding-1).getWidth()/2;
                }
            }
        }
        double tMax1 = (velocity*Math.sin(Math.toRadians(angle))+Math.sqrt((Math.pow(-velocity*Math.sin(Math.toRadians(angle)),2))-(4*(gravedad/2)*(-yi))))/gravedad;
        tMax1 =  tMax1;
        double tMax2 = (velocity*Math.sin(Math.toRadians(angle))-Math.sqrt((Math.pow(-velocity*Math.sin(Math.toRadians(angle)),2))-(4*(gravedad/2)*(-yi))))/gravedad;
        tMax2 =  tMax2;
        if(tMax1<=0){
            tMax=tMax2;
        }
        else{
            tMax=tMax1;
        }
        boolean death=false;
        int contTime=0;
        while(contTime<tMax && death==false){
            double y= (int) (velocity*Math.sin(Math.toRadians(angle))*contTime)-((gravedad*Math.pow(contTime,2))/2);
            double x= (int)  velocity*Math.cos(Math.toRadians(angle))*contTime;
            contTime+=1;
            for(int i=0 ; i<buildings.size() ; i++){
                Building building = buildings.get(i);
                if(x+xi>=building.getX() && x+xi<=building.getX()+building.getWidth() && yi-y>=cityHeight-building.getHeight()){
                    if(jump){
                        if(y>0){
                            death=true;
                        }
                        double tempy = (int) yi-y;
                        double size = (int) cityHeight-tempy;
                        int strength = heroes.get(heroeIndex).getStrength();
                        int hardness = building.getHardness();
                        if(i!=hidingBuilding-1){
                            if(hardness>0){
                                heroes.get(heroeIndex).setStrength(hardness);
                            }
                            hardness = building.setHardness(strength);
                            strength = heroes.get(heroeIndex).getStrength();
                        }
                        if(strength<=0){
                            deads.add(heroes.get(heroeIndex).getColor());
                            heroes.remove(heroeIndex);
                            death=true;
                            Toolkit.getDefaultToolkit().beep(); 
                            JOptionPane.showMessageDialog(null,"El heroe murio con una fuerza de "+strength,"Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            if(existeHeroe(i) && i!=hidingBuilding-1){
                                deads.add(heroes.get(heroeIndex).getColor());
                                heroes.remove(heroeIndex);
                                death=true;
                                Toolkit.getDefaultToolkit().beep(); 
                                JOptionPane.showMessageDialog(null,"Ya habia un heroe en el edificio. El heroe que saltó murió","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            else{
                                building.changeSize((int)size,building.getWidth());
                                heroes.get(heroeIndex).setHidingBuilding(i+1);
                                sortCity();
                                makeVisible();
                            }
                        }
                        
                    }
                }
            }
            if(death){
                break;
            }
            if(angle<90){
               if (x>=velocity*Math.cos(Math.toRadians(angle))*tMax){
                   if(jump){
                       heroes.get(heroeIndex).goTo((int) x ,0);
                   }
                   break;
               }
            }
            else if (x<=velocity*Math.cos(Math.toRadians(angle))*tMax){
                if(jump){
                    heroes.get(heroeIndex).goTo((int) x ,0);
                }
                break;
            }
            if(x+xi>cityWidth && yi-y>cityHeight){
                if(jump){
                    double tempY = (int) yi-y;
                    double tempX = (int) x+xi;
                    heroes.get(heroeIndex).goTo((int) tempX,(int)tempY);
                    Toolkit.getDefaultToolkit().beep(); 
                    JOptionPane.showMessageDialog(null,"El heroe salio de los limites de la ciudad","Error",JOptionPane.ERROR_MESSAGE);
                    deads.add(heroes.get(heroeIndex).getColor());
                    heroes.remove(heroeIndex);
                    death=true;
                }
                break;
            }
            else if(x+xi<cityWidth && yi-y>cityHeight){
                if(jump){
                    double tempX = (int) x+xi;
                    heroes.get(heroeIndex).goTo((int) tempX,cityHeight-60);
                    heroes.get(heroeIndex).setNoBuilding((int) tempX);
                    heroes.get(heroeIndex).setHidingBuilding(0);
                }
                break;
            }
            else{
                if(jump){
                    double tempY = (int) yi-y;
                    double tempX = (int) x+xi;
                    heroes.get(heroeIndex).goTo((int) tempX,(int)tempY);
                }
            }
       }
       return death;
    }
    /**
     * Makes Visible the city, their buildings and the heroes.
     * 
    */
    public void makeVisible(){
        sortCity();
        makeInvisible();
        city.makeVisible();
        for(int i=0;i<buildings.size();i++){
            Building building = buildings.get(i);
            Rectangle imageBuilding = building.getBuild();
            int movX = building.getX();
            int bWidth = building.getWidth();
            int bHeight = building.getHeight();
            imageBuilding.goTo(movX,cityHeight-bHeight);
            imageBuilding.makeVisible();
        }
        for(int i=0 ; i<heroes.size();i++){
            if(heroes.get(i).getHidingBuilding()==0){
                heroes.get(i).goTo(heroes.get(i).getNoBuilding(),cityHeight-60);
                heroes.get(i).showHeroe();
            }
            else{
                Hero heroe = heroes.get(i);
                int hidingBuilding = heroe.getHidingBuilding();
                Building building = buildings.get(hidingBuilding-1);
                heroe.goTo(building.getX()+(building.getWidth()/2),(cityHeight-building.getHeight())-55);
                heroe.showHeroe();
            }
        }
    }
    /**
     * Makes Invisible the city, their buildings and the heroes.
     * 
    */
    public void makeInvisible(){
        city.makeInvisible();
        for(int i=0;i<buildings.size();i++){
            buildings.get(i).getBuild().makeInvisible();
        }
        for(int i=0;i<heroes.size();i++){
            heroes.get(i).makeInvisible();
        }
    }
    /**
     * Knows if the hero get hurt at the jump in one building.
     * 
     * @param x The coordinate of the building in which the hero are jumping.
     * 
     * return A boolean expresion that determines if the hero gets hurt.
    */
    public boolean isDamaged(int x){
        if(buildings.get(x-1).getHardness()<=0){
            return true;
        }
        return false;
    }
    /**
     * Finishes the execution of the class
     * 
    */
    public void finish(){
        System.exit(0);
    }
    /**
     * Knows if the hero are in the city.
     * 
     * @param x The coordinate of the building in which the herois going tho be verified.
     * 
     * return A boolean expresion that determines if the hero are in the city.
    */
    private boolean existeHeroe(int x){
        for(int i=0;i<heroes.size();i++){
            if(heroes.get(i).getHidingBuilding()-1==x){
                return true;
            }
        }
        return false;
    }
    /**
     * Sorts the buildings by their coordinate X from left to right.
     * 
    */
    private void sortCity(){
        ArrayList <Building> newBuildings = new ArrayList<>();
        int [] newPosicions = new int [buildings.size()];
        int [] sortPosicion = new int [buildings.size()];
        for(int i=0 ; i<buildings.size() ; i++){
            sortPosicion[i]=buildings.get(i).getX();
        }
        Arrays.sort(sortPosicion);
        for(int c=0 ; c<sortPosicion.length ; c++){
            for(int i=0 ; i<buildings.size() ; i++){
                if(buildings.get(i).getX()==sortPosicion[c]){
                    newBuildings.add(buildings.get(i));
                    newPosicions[c]=i;
                }
            }
        }
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getHidingBuilding()==newPosicions[i]-1 && heroes.get(i).getHidingBuilding()!=0){
                heroes.get(i).setHidingBuilding(i+1);
            }
        }
        for(int i=0 ; i<newBuildings.size() ; i++){
            buildings.set(i,newBuildings.get(i));
        }
    }
}