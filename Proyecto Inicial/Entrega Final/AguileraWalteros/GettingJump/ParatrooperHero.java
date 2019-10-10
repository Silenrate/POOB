package GettingJump;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
/**
 * A hero that never goes out of a city of heroes.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (25/03/2019)
 */
public class ParatrooperHero extends Hero{
    /**
     * Constructor for objects of class ParatrooperHero
     */
    public ParatrooperHero(CityOfHeroes city,String color,int hidingBuilding,int strength) throws CityOfHeroesExcepcion{
        super(city,color,hidingBuilding,strength);
        circle.changeColor("yellow");
    }
    /**
     * Verifies if in a jump hero goes out of the city
     * 
     * @param xi the initial horizontal coordinate of the Hero
     * @param x the actual horizontal coordinate of the Hero
     * @param yi the initial vertical coordinate of the Hero
     * @param y the actual horizontal coordinate of the Hero
     * @param citySizeX the size of the Width of the City
     * @param citySize the size of the Height of the City
     * @return The boolean value that represents if the hero gets out of the city
     */
    protected boolean ifHeroOutOfLimits(BigDecimal xi,BigDecimal x,BigDecimal citySizeX,BigDecimal yi,BigDecimal y,BigDecimal citySize){
        BigDecimal cero = new BigDecimal(0);
        if(x.add(xi).compareTo(citySizeX)<0 && yi.subtract(y).compareTo(citySize)>0){
            heroInTheFloor(x.add(xi).intValue()); 
            return true;
        }
        else if(x.add(xi).compareTo(citySizeX)>0 || yi.subtract(y).compareTo(citySize)>0 || x.add(xi).compareTo(cero)<0 || yi.subtract(y).compareTo(cero)<0){
            if(jump){
               int auxY = yi.subtract(y).intValue();
               int auxX = x.add(xi).intValue();
               if(auxX>=citySizeX.intValue()){ auxX=citySizeX.intValue()-20;}
               if(auxX<=0){ auxX=0+20; }
               goDown(auxY , citySize , auxX);
            }
            return true;
        }
        else{
            if(jump){
                goTo(x.add(xi).intValue(),yi.subtract(y).intValue()-70);
            }
        }
        return false;
    }
    /**
     * Makes the Hero goes to the floor of the city
     * 
     * @param xi the initial horizontal coordinate of the Hero
     * @param x the actual horizontal coordinate of the Hero
     * @param yi the initial vertical coordinate of the Hero
     * @return The boolean value that represents if the hero gets out of the city
     */
    private void goDown(int auxY , BigDecimal citySize , int auxX){
        while(auxY<citySize.intValue()){
             goTo(auxX,auxY-65);
             for(int i=0 ; i<buildings.size() ; i++){
                int height = buildings.get(i).getHeight();
                Building building = buildings.get(i);
                int posX = buildings.get(i).getX();
                int width = buildings.get(i).getWidth();
                if(auxX >= posX && auxX <= posX+width && auxY==citySize.intValue()-height){
                   goTo(posX+width/2,(citySize.intValue()-height)-65);
                   setHidingBuilding(i+1);
                   return;
                }
             }
             auxY+=1;
        }
        heroInTheFloor(auxX);
        return;
    }
}
