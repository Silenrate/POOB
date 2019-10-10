package GettingJump;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
/**
 * A hero that builds an edification when lands on the earth..
 *
 * @author (Nicolas Aguilera y Daniel Walteros)
 * @version (2 of April of 2019)
 */
public class ConstructorHero extends Hero{
    /**
     * Constructor for objects of class ParatrooperHero
     */
    public ConstructorHero(CityOfHeroes city,String color,int hidingBuilding,int strength) throws CityOfHeroesExcepcion{
        super(city,color,hidingBuilding,strength);
        circle.changeColor("green");
    }
    
    /**
     * Knows if the hero is in the floor
     * @param x The X coordinate of the hero
    */
    protected void heroInTheFloor(int x){
        if(jump){
           buildings = city.getBuildings();
           int newWidth = 0;
           while(newWidth+x <= city.getCityWidth()){
               if(isABuildInThatX(newWidth+x)){break;}
               newWidth+=1;
           }
           int cont = x;
           while(cont <= x+newWidth/2){goTo(cont,city.getCityHeight()-60); cont+=1;}
           int newHeight = 0;
           Building building = new Building(city,x,newWidth,newHeight,strength);
           buildings.add(building); city.setBuildings(buildings);
           setHidingBuilding(city.getBuildingX(x)+1);
           while(newHeight<=city.getCityHeight()/2){
               building.changeSize(newHeight,newWidth);
               newHeight+=1;
               if(visibility){city.makeVisible();}
           }
           if(visibility){city.makeVisible();}
        }
    }
    
    /**
     * Knows if a building locates in the X coordinate.
     * @param x The X coordinate to evalue
     * @return The boolean expression that determines if a building locates in the X coordinate.
    */
    private boolean isABuildInThatX(int x){
        for(int i=0 ; i<buildings.size() ; i++){
            if(x==buildings.get(i).getX()){
                return true;
            }
        }
        return false;
    }
}
