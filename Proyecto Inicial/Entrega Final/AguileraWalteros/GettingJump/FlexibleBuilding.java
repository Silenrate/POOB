package GettingJump;
import Figuras.*;
import java.math.BigDecimal; 
import java.util.ArrayList;
/**
 * A building thats changes their dimensions for dont get crush, it can be identified by his door.
 *
 * @author (Nicolas Aguilera y Daniel Walteros)
 * @version (24 of March of 2019)
 */

public class FlexibleBuilding extends Building{
    private Rectangle door;
    private boolean canHaveAHero;
    /**
     * Constructor for objects of class FlexibleBuilding
     */
    public FlexibleBuilding(CityOfHeroes city , int number , int width , int height , int hardness){
        super(city, number, width, height, hardness);
        door=new Rectangle();
        door.changeColor("black");
        door.changeSize(30,width/3);
        canHaveAHero=false;
    }
    
    /**
     * Verifies if it is posible to place a Building in a posicion in X
     * @param posX The position in X where the building is going to place in.
     * @param bWidth the width of the building that is going to place in.
     * @return The boolean value that represents if it is posible to place the building.
     */
    public boolean AlreadyBuildInCity(int posX , int bWidth){
        return moveBuilding(posX , bWidth);
    }
    
    /**
     * Verifies if it is a building in a deteminated position in X
     * @param posX The position in X where the building is going to place in.
     * @param bWidth the width of the building that is going to place in.
     * @return The boolean value that represents if it is posible to place the building.
     */
    private boolean BuildInThatPosition(int posX , int bWidth){
        if((posX==getX() && bWidth==getWidth())||((posX>getX() && posX<getX()+getWidth())||(posX+bWidth>getX() && posX+bWidth<getX()+getWidth())||(posX<getX() && posX+bWidth>getX()+getWidth()))){
            return true;
        }
        return false;
    }
    
    /**
     * Verifies if it is a building in a deteminated position in X
     * @param posX The position in X to check.
     * @return The boolean value that represents if it is a building there.
     */
    private boolean isABuildInThatX(int x){
        ArrayList<Building> buildings = city.getBuildings();
        for(int i=0 ; i<buildings.size() ; i++){
            if(!buildings.get(i).equals(this)){
                if(x==buildings.get(i).getX()){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Move a Flexible Building.
     * @param posX The original posicion in X of the building
     * @param posX The width of the building that we need to put.
     * @return The boolean value that represents if is possible to move our building to put another one.
     */
    private boolean moveBuilding(int posX , int bWidth){
        int tempX = getX();
        x = tempX;
        while(BuildInThatPosition(posX,bWidth)){
            if(x+getWidth()>=city.getCityWidth()){break;}
            if(isABuildInThatX(x+getWidth())){break;}
            x+=1;
            setX(x);
            city.makeVisible();
        }
        if(!BuildInThatPosition(posX,bWidth)){
            return false;
        }
        x = tempX;
        while(BuildInThatPosition(posX,bWidth)){
            if(x<=0){break;}
            if(isABuildInThatX(x)){break;}
            x-=1;
            setX(x);
            city.makeVisible();
        }
        if(!BuildInThatPosition(posX,bWidth)){
            return false;
        }
        return true;
    }
    
    /**
     * Verifies if a Hero smashes with a builing
     * @param hero the hero that is going to the building
     * @param jump a boolean that represents if the hero can jump or not
     * @param tempY the height that has the hero in the moment
     * @param actualY the vertical coordinate of the building
     * @return The boolean value that represents if the hero smashes or not the building
     */
    protected boolean isDamagedByHero(Hero hero , boolean jump , BigDecimal tempY , BigDecimal actualY){
       return false;
    }
    
    /**
     *Updates the size of the building if the hero can jump
     * @param jump a boolean that represents if the hero can jump or not
     * @param cityHeight the vertical size of the city
     * @param Y the vertical coordinate of a hero
     */
    protected void updateSize(boolean jump , int cityHeight , int y){
        int newSize = Math.abs(cityHeight-y);
        if(jump){
            if(Math.abs(y-(cityHeight-getHeight()))>((cityHeight-getHeight()*1.0))/100){
                this.changeSize(newSize-10,this.getWidth());
                goTo(getX(),cityHeight-getHeight());
                makeVisible();
            }
            else{
                canHaveAHero = true;
            }
        }
    }
    
    /**
     * Knows if a building can have a hero
     * 
     */
    protected boolean canHaveAHero(){
        return canHaveAHero;
    }
    
    /**
     * Moves the building in the city.
     * 
     * @param x The X coordinate in the city.
     * @param y The Y coordinate in the city.
    */
    public void goTo(int x , int y){
        super.goTo(x,y);
        door.goTo(x+getWidth()/3,y+getHeight()-30);
    }
    
    /**
     * Makes visible the building.
     * 
    */
    public void makeVisible(){
        super.makeVisible();
        door.makeVisible();
    }
    
    /**
     * Makes invisible the building.
     * 
    */
    public void makeInvisible(){
        super.makeInvisible();
        door.makeInvisible();
    }
    
    /**
     * Changes the size of the building.
     * 
     * @param newHeight The new height of the new hardness.
     * @param newWidth The new width of the new hardness.
    */
    public void changeSize(int newHeight , int newWidth){
        super.changeSize(newHeight, newWidth);
        door.changeSize(newHeight/4,newWidth/8);
    }
}