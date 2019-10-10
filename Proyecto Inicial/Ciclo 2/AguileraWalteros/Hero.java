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
    private int targetBuilding;
    private double X;
    private boolean visibility;
    private Circle circle;
    private Rectangle arms;
    private Rectangle chest;
    private Rectangle leg1;
    private Rectangle leg2;
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
        targetBuilding = -1;
        this.strength = strength ;
        X = 0;
        strongsize = (int)(this.strength*0.54);
        visibility=false;
        circle = new Circle();
        arms = new Rectangle();
        chest = new Rectangle();
        leg1 = new Rectangle();
        leg2 = new Rectangle();
        backgroundStrong = new Rectangle();
        strong = new Rectangle();
        backgroundStrong.changeColor("black");
        circle.changeColor(color);
        arms.changeColor(color);
        chest.changeColor(color);
        leg1.changeColor(color);
        leg2.changeColor(color);
        strong.changeSize(11,strongsize);
        backgroundStrong.changeSize(15,60);
        chest.changeSize(55,11);
        arms.changeSize(12,55);
        leg1.changeSize(25,11);
        leg2.changeSize(25,11);
        backgroundStrong.moveVertical(-34);
        strong.moveVertical(-32);
        chest.moveVertical(-5);
        leg1.moveVertical(50);
        leg2.moveVertical(50);
        arms.moveVertical(20);
        backgroundStrong.moveHorizontal(-30);
        strong.moveHorizontal(-27);
        chest.moveHorizontal(10);
        leg1.moveHorizontal(-1);
        leg2.moveHorizontal(21);
        arms.moveHorizontal(-12);
    }
    /**
     * Makes Invisible the hero.
     * 
    */
    public void makeInvisible(){
        visibility=false;
        circle.makeInvisible();
        arms.makeInvisible();
        chest.makeInvisible();
        strong.makeInvisible();
        leg1.makeInvisible();
        leg2.makeInvisible();
        backgroundStrong.makeInvisible();
    }
    /**
     * Makes Visible the hero.
     * 
    */
    public void makeVisible(){
        visibility=true;
        circle.makeVisible();
        arms.makeVisible();
        chest.makeVisible();
        leg1.makeVisible();
        leg2.makeVisible();
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
    public void goTo(double x,double y){
        makeInvisible();
        circle.goTo(x-15,y-20);
        arms.goTo(x-27,y+10);
        chest.goTo(x-5,y-15);
        backgroundStrong.goTo(x-30,y-34);
        strong.goTo(x-27,y-32);
        leg1.goTo(x-16,y+40);
        leg2.goTo(x+6,y+40);
        if (visibility==true){makeVisible();}
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
    public double getX(){
        return X;
    }
    /**
     * Changes the number of the building that have the hero.
     * 
     * @param The number of the new building.
    */
    public void setX(double x){
        X = x;
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
     /**
     * Changes the number of the building that the hero wants to go.
     * 
     * @param The number of the new target building
    */
    public void setTargetBuilding(int x){
        targetBuilding = x;
    }
     /**
     * Returns the number of the building that the hero wants to go.
     * 
    */
    public int getTargetBuilding(){
        return targetBuilding;
    }
    public String toString(){
        return "color = "+color+", strength = "+strength+", hidingbuilding = "+hidingBuilding;
    }    
}
