package GettingJump;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
/**
 * A hero that climbs the buildings of a city of heroes.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (25/03/2019)
 */
public class ClimberHero extends Hero{
    /**
     * Constructor for objects of class ClimberHero extends Hero
     */
    public ClimberHero(CityOfHeroes city,String color,int hidingBuilding,int strength) throws CityOfHeroesExcepcion{
        super(city,color,hidingBuilding,strength);
        circle.changeColor("red");
    }
    /**
     * Verifies if in a jump smashes with a building
     * 
     * @param tempY the height that has the hero in the moment
     * @param actualY the vertical coordinate of the building
     * @param actualX the horizontal coordinate of the building
     * @param i the index of the building
     * @param angle the angle of the jump
     * @return The boolean value that represents if the hero smashes or not the building
     */
    protected boolean heroSmashBuilding(BigDecimal tempY, BigDecimal actualY, BigDecimal actualX,int i,double angle){
        BigDecimal bWidth = new BigDecimal(buildings.get(i).getWidth());
        Building building = buildings.get(i);
        boolean smash = false;
        if(existeHeroe(i+1) && hidingBuilding != i+1){ smash = true;
             if(jump){
                   heroDies(); showMessage("Ya habia un heroe en el edificio. El heroe que salto murio");
                   return true;
             }
        }
        else{
            setTargetBuilding(i+1);
            if(jump){
                if(angle!=90){
                    setHidingBuilding(i+1);
                    climb(tempY.intValue(),actualY.intValue(),actualX.intValue(),bWidth.intValue(),building);
                }
            }
            if(visibility==true){city.makeVisible();}
        }
        return smash;
    }
    /**
     * Makes the hero climbs a building
     * 
     * @param tempY the height that has the hero in the moment
     * @param actualY the vertical coordinate of the building
     * @param actualX the horizontal coordinate of the building
     * @param bWidth the width of the building
     * @param building the building that the hero is going to climb
     */
    private void climb(int tempY , int actualY , int actualX , int bWidth , Building building){
        while(tempY>actualY){
            tempY = tempY-1;
            goTo(actualX-15,tempY-65);
        }
        goTo(actualX-10,tempY-65);
        while(actualX < bWidth/2){
            actualX=actualX+1;
            goTo(actualX,(city.getCityHeight()-building.getHeight())-65);
        }
    }
}
