package GettingJump;
/**
 * A hero that always does secure jumps
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (25/03/2019)
 */
public class CarefulHero extends Hero{
 /**
 * Constructor of a careful hero.
 */
 public CarefulHero(CityOfHeroes city,String color,int hidingBuilding,int strength) throws CityOfHeroesExcepcion{
        super(city,color, hidingBuilding, strength);
        circle.changeColor("red");
 }
 
 /**
 * Makes jump a hero into the city.
 * @param velocity The speed that the hero jumps.
 * @param angle The angle that the hero jumps.
 * @param slow A boolean indicating if the jumps sees slow or not.
 */
 public void jump(int velocity , double angle , boolean slow){
        
        if(isSafeJump(velocity , angle)){
            jump=true;
            super.jump(velocity,angle,slow);
        }
        else{
            city.setDid(false);
        }
        jump=false;
 }
}
