package GettingJump;
import java.util.ArrayList;
import java.math.BigDecimal;
import Figuras.*; 
/**
 * A construction in the city of Heroes.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (24 of March of 2019)
 */
public class Building{
    private int width;
    private int height;
    protected int hardness;
    protected int x;
    protected CityOfHeroes city;
    protected Rectangle build;
    private ArrayList<Integer> state;
    /**
     * Constructor for objects of class Buildings
     * 
     * @param x The origin X coordinate of the building.
     * @param width The width of the building.
     * @param height The height of the building.
     * @param hardness The hardness of the building.
    */
    public Building(CityOfHeroes city , int x , int width , int height , int hardness){
        this.width=width;
        this.height=height;
        this.hardness=hardness;
        this.x=x;
        this.city=city;
        state = new ArrayList<>();
        state.add(hardness);
        build = new Rectangle();
        build.changeSize(height,width);
    }
    
    /**
     * Gets the width of the building.
     * 
     * @return  The number of the width.
    */
    public int getWidth(){
        return width; 
    }
    
    /**
     * Set the hardness of the building
     * 
     * @param  newHardness the new hardness of the building.
    */
    public void setHardness(int newHardness){
        hardness = newHardness;
    }
    
    /**
     * Knows if the building was damaged by a hero.
     * 
     * @param  hero The hero that crash with the building.
     * @param  jump The boolean value that determines if the hero are jumping. 
     * @param  tempY The Y coordinate in the hero smash the building.
     * @param  actaulY The Y coordinate of the hero.
    */
    protected boolean isDamagedByHero(Hero hero , boolean jump , BigDecimal tempY , BigDecimal actualY){
       int strength = hero.getStrength();
       if(Math.abs(tempY.subtract(actualY).doubleValue())>(actualY.doubleValue()*1)/100){
           if(hardness>0 && jump){
               hero.setStrength(strength-hardness);
               this.changeHardness(strength);
           }
           return true;
       }
       return false;
    }
    
    /**
     * Knows if there is another building in certain coordinates of the city.
     * 
     * @param  posX The X coordinate in the city
     * @param  bWidth The width of the actual building.
     * @return The boolean expression that determines if there is another building in that coordinates 
    */
    protected boolean  AlreadyBuildInCity(int posX , int bWidth){
        if((posX==getX() && bWidth==getWidth())||((posX>getX() && posX<getX()+getWidth())||(posX+bWidth>getX() && posX+bWidth<getX()+getWidth())||(posX<getX() && posX+bWidth>getX()+getWidth()))){
            return true;
        }
        return false;
    }
    
    /**
     * Sets the X coordinate of the building
     * 
     * @param  posX The new X coordinate of the building.
    */
    protected void setX(int posX){
        x = posX;
    }
    
    /**
     * Updates the size of the building when gets damaged.
     * 
     * @param  jump The boolean value that determines if the hero are jumping. 
     * @param  cityHeight The height of the city.
     * @param  y The Y coordinate in the hero smash the building.
    */
    protected void updateSize(boolean jump , int cityHeight , int y){
       if(jump){
           this.changeSize(Math.abs(y-cityHeight),this.getWidth());
       }
    }
    
    /**
     * Knows if the building can have a hero.
     * 
    */
    protected boolean canHaveAHero(){
        return true;
    }
    
    /**
     * Gets the width of the building.
     * 
     * @return  The number of the width.
    */
    public int getHardness(){
        return hardness;
    }
    
    /**
     * Changes the hardness of the building.
     * 
     * @param The number of the new hardness.
     * 
     * @return The new hardness of the building.
    */
    public void changeHardness(int x){
        hardness = hardness - x;
        state.add(hardness);
    }
    
    /**
     * Gets the height of the building.
     * 
     * @return  The number of the height.
    */
    public int getHeight(){
        return height;
    }
    
    /**
     * Gets the X coordinate of the building.
     * 
     * @return  The number of the X coordinate.
    */
    public int getX(){
        return x;
    }
    
    /**
     * Knows if the building is damaged
     * 
     * @return  The boolean expression of the existence of the damage.
    */
    public boolean isDamaged(){
        return state.size()>1;
    }
    
    /**
     * Restore the building to the previous state after a hit.
     * 
    */
    public void restore(){
        state.remove(state.size()-1);
        hardness = state.get(state.size()-1);
    }
    
    /**
     * Gets the rectangle of the building.
     * 
     * @return  The number of the rectangle.
    */
    public Rectangle getBuild(){
        return build;
    }
    
    /**
     * Moves the building in the city.
     * 
     * @param x The X coordinate in the city.
     * @param y The Y coordinate in the city.
    */
    public void goTo(int x , int y){
        build.goTo(x,y);
    }
    
    /**
     * Makes visible the building.
     * 
    */
    public void makeVisible(){
        build.makeVisible();
    }
    
    /**
     * Makes invisible the building.
     * 
    */
    public void makeInvisible(){
        build.makeInvisible();
    }
    
    /**
     * Changes the size of the building.
     * 
     * @param newHeight The new height of the new hardness.
     * @param newWidth The new width of the new hardness.
    */
    public void changeSize(int newHeight , int newWidth){
        height = newHeight;
        width = newWidth;
        build.changeSize(newHeight,newWidth);
    }
}
