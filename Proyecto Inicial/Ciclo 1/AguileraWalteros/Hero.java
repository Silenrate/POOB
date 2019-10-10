import java.util.ArrayList;
/**
 * A hero that jumps from building to building.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (7 of February of 2019)
 */
public class Hero
{
    private String color;
    private int hidingBuilding;
    private int strength;
    private Circle circle;
    private Rectangle arms;
    private Rectangle chest;
    private Triangle legs;
    private Triangle legs2;
    private int noBuilding;
    private Rectangle strong;
    private Rectangle backgroundStrong;
    /**
     * Constructor for objects of class Heroes
     * 
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    public Hero(String color,int hidingBuilding,int strength){
        int strongsize;
        this.color = color;
        this.hidingBuilding = hidingBuilding;
        this.strength = strength ;
        noBuilding = 0;
        strongsize = (int)(this.strength*0.54);
        circle = new Circle();
        arms = new Rectangle();
        chest = new Rectangle();
        backgroundStrong = new Rectangle();
        strong = new Rectangle();
        legs = new Triangle();
        legs2 = new Triangle();
        legs2.changeColor("white");
        backgroundStrong.changeColor("black");
        circle.changeColor(color);
        arms.changeColor(color);
        chest.changeColor(color);
        legs.changeColor(color);
        strong.changeSize(11,strongsize);
        backgroundStrong.changeSize(15,60);
        chest.changeSize(55,11);
        arms.changeSize(12,55);
        legs2.changeSize(15,20);
        backgroundStrong.moveVertical(-34);
        strong.moveVertical(-32);
        chest.moveVertical(-5);
        legs.moveVertical(40);
        arms.moveVertical(20);
        legs2.moveVertical(55);
        backgroundStrong.moveHorizontal(-30);
        strong.moveHorizontal(-27);
        chest.moveHorizontal(10);
        arms.moveHorizontal(-12);
        legs.moveHorizontal(-55);
        legs2.moveHorizontal(-55);
    }
    /**
     * Makes Invisible the hero.
     * 
    */
    public void makeInvisible(){
        circle.makeInvisible();
        arms.makeInvisible();
        chest.makeInvisible();
        legs.makeInvisible();
        legs2.makeInvisible();
        strong.makeInvisible();
        backgroundStrong.makeInvisible();
    }
    /**
     * Makes Visible the hero.
     * 
    */
    public void makeVisible(){
        circle.makeVisible();
        arms.makeVisible();
        chest.makeVisible();
        legs.makeVisible();
        legs2.makeVisible();
        backgroundStrong.makeVisible();
        strong.makeVisible();
    }
    /**
     * Shows the hero in the city.
     * 
    */
    public void showHeroe(){
        makeInvisible();
        makeVisible();
    }
    /**
     * Puts the hero in the x and y coordinates.
     * 
     * @param x The coordinate x of the hero.
     * @param y The coordinate y of the hero.
    */
    public void goTo(int x,int y){
        makeInvisible();
        circle.goTo(x-15,y-20);
        arms.goTo(x-27,y+10);
        chest.goTo(x-5,y-15);
        legs.goTo(x,y+30);
        legs2.goTo(x,y+45);
        backgroundStrong.goTo(x-30,y-34);
        strong.goTo(x-27,y-32);
        makeVisible();
    }
    /**
     * Gets the hiding building of the hero.
     * 
     * @return  The number of the hiding building.
     */
    public int getHidingBuilding(){
        return hidingBuilding;
    }
    /**
     * Changes the hiding building of the hero.
     * 
     * @param The number of the new hiding building.
     */
    public void setHidingBuilding(int i){
        hidingBuilding=i;
    }
    /**
     * Gets the strength of the hero.
     * 
     * @return  The number of the strength of the hero.
     */
    public int getStrength(){
        return strength;
    }
    /**
     * Gets the color of the hero.
     * 
     * @return  The color of the hero.
     */
    public String getColor(){
        return color;
    }
    /**
     * Gets the building in that are the hero.
     * 
     * @return  The number of the building.
     */
    public int getNoBuilding(){
        return noBuilding;
    }
    /**
     * Changes the number of the building that have the hero.
     * 
     * @param The number of the new building.
    */
    public void setNoBuilding(int x){
        noBuilding = x;
    }
    /**
     * Changes the strength of the hero.
     * 
     * @param The number of the new strength.
     */
    public int setStrength(int x){
        strength = strength - x;
        return strength;
    }
    public String toString(){
        return "color = "+color+", strength = "+strength+", hidingbuilding = "+hidingBuilding+",nobuilding ="+noBuilding;
    }    
}
