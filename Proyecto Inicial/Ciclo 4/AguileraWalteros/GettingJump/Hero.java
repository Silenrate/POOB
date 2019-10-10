package GettingJump;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import Figuras.*; 
/**
 * A hero that jumps from building to building.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (7 of February of 2019)
 */
public class Hero {
    protected String color;
    private static final BigDecimal ZERO = new BigDecimal(0);
    private static final BigDecimal gravity = new BigDecimal(9.80665);
    protected int hidingBuilding;
    protected int strength;
    private int targetBuilding;
    private int X;
    protected boolean visibility;
    protected boolean death;
    protected boolean finish;
    protected boolean jump;
    protected Circle circle;
    private Rectangle arms;
    private Rectangle chest;
    private Rectangle leg1;
    private Rectangle leg2;
    private Rectangle strong;
    private Rectangle backgroundStrong;
    protected CityOfHeroes city;
    protected ArrayList<Building> buildings;
    /**
     * Constructor for objects of class Heroes
     * 
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    public Hero(CityOfHeroes city,String color,int hidingBuilding,int strength) throws CityOfHeroesExcepcion{
        if (strength>100||strength<1){throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.STRENGTH_OUT_OF_RANGE);}
        this.color = color;
        this.hidingBuilding = hidingBuilding;
        this.city = city;
        this.strength = strength ;
        X = 0;
        targetBuilding = -1;
        visibility=false;
        death=false;
        int strongsize = (int)(this.strength*0.54);
        circle = new Circle();
        arms = new Rectangle();
        chest = new Rectangle();
        leg1 = new Rectangle(); leg2 = new Rectangle();
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
        leg1.changeSize(25,11); leg2.changeSize(25,11);
        backgroundStrong.moveVertical(-34);
        strong.moveVertical(-32);
        chest.moveVertical(-5);
        leg1.moveVertical(50); leg2.moveVertical(50);
        arms.moveVertical(20);
        backgroundStrong.moveHorizontal(-30);
        strong.moveHorizontal(-27);
        chest.moveHorizontal(10);
        leg1.moveHorizontal(-1); leg2.moveHorizontal(21);
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
        arms.makeVisible();
        chest.makeVisible();
        circle.makeVisible();
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
    public void goTo(int x,int y){
        if(visibility){
            makeInvisible();
            visibility=true;
        }
        circle.goTo(x-15,y-20);
        arms.goTo(x-27,y+10);
        chest.goTo(x-5,y-15);
        backgroundStrong.goTo(x-30,y-34);
        strong.goTo(x-27,y-32);
        leg1.goTo(x-16,y+40);
        leg2.goTo(x+6,y+40);
        if (visibility){makeVisible();}
    }
    
    /**
     * Makes jump a hero into the city.
     * 
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * @param slow A boolean indicating if the jumps sees slow or not.
     */
    public void jump(int velocity , double angle , boolean slow){
        jump=true;
        isSafeJump(velocity , angle);
        jump=false;
    }
    
    /**
     * Verifies if in a jump the hero get hurts or gets intact.
     * 
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * 
     * @return The boolean value that represents if the hero gets hurt or get intact.
     */
    public boolean isSafeJump(int velocity , double angle){
        BigDecimal vel = new BigDecimal(velocity);
        BigDecimal velY = vel.multiply(new BigDecimal(Math.sin(Math.toRadians(angle))));
        BigDecimal velX = vel.multiply(new BigDecimal(Math.cos(Math.toRadians(angle))));  
        BigDecimal yi=getInicialY(); BigDecimal xi=getInicialX();
        BigDecimal citySize = new BigDecimal(city.getCityHeight()); BigDecimal citySizeX = new BigDecimal(city.getCityWidth());
        death=false; finish=false;
        buildings = city.getBuildings();
        BigDecimal contTime=new BigDecimal(0);
        while(death==false){
            BigDecimal y= velY.multiply(contTime).subtract((gravity.multiply(contTime.multiply(contTime))).divide(new BigDecimal(2)));
            BigDecimal x= vel.multiply(new BigDecimal(Math.cos(Math.toRadians(angle)))).multiply(contTime);
            contTime=contTime.add(new BigDecimal(0.05));
            BigDecimal tempY = yi.subtract(y);
            for(int i=0 ; i<buildings.size(); i++){
                BigDecimal bHeight = new BigDecimal(buildings.get(i).getHeight());
                BigDecimal actualY = citySize.subtract(bHeight); 
                BigDecimal actualX = x.add(xi);
                BigDecimal posX = new BigDecimal(buildings.get(i).getX());
                BigDecimal bWidth = new BigDecimal(buildings.get(i).getWidth());
                if(actualX.compareTo(posX)>=0 && actualX.compareTo(posX.add(bWidth))<=0 && tempY.compareTo(actualY)==1){
                    death = heroSmashBuilding(tempY,actualY,actualX,i,angle);
                    if(i>=0 && i<buildings.size()){
                        finish = buildings.get(i).canHaveAHero();
                    }
                }
            }
            if(death || finish || ifHeroOutOfLimits(xi, x, citySizeX, yi, y,citySize)){break;}
        }
        return !death;
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
        Building building = buildings.get(i); boolean smash = false;
        if(existeHeroe(i+1) && hidingBuilding != i+1){smash = true;
             if(jump){
                heroDies(); showMessage("Ya habia un heroe en el edificio. El heroe que salto murio");
                return true;
             }
        }
        else{
            smash = building.isDamagedByHero(this , jump , tempY , actualY);
            if(getStrength()<=0 && jump){
                heroDies(); showMessage("El heroe murio con una fuerza de "+strength);
                return true;
            }
            if(!smash){setTargetBuilding(i+1);}
            if(jump && angle !=90){
                setHidingBuilding(i+1);
                goTo(actualX.intValue(),tempY.intValue());
            }
            building.updateSize(jump , city.getCityHeight(),tempY.intValue());
            if(visibility==true && finish){city.makeVisible();}
        }
        return smash;
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
        if(x.add(xi).compareTo(cero)>0 && x.add(xi).compareTo(citySizeX)<0 && yi.subtract(y).compareTo(citySize)>0){
            heroInTheFloor(x.add(xi).intValue()); 
            death = true;
            return true;
        }
        else if(x.add(xi).compareTo(citySizeX)>0 || yi.subtract(y).compareTo(citySize)>0 || x.add(xi).compareTo(cero)<0 || yi.subtract(y).compareTo(cero)<0){
            if(jump){
                heroDies();
                showMessage("El heroe salio de los limites de la ciudad");
                death=true;
            }
            return true;
        }
        else{
            if(jump){
                goTo(x.add(xi).intValue(),yi.subtract(y).intValue()-65);
            }
        }
        return false;
    }
    
    /**
     * Knows if the hero are in the city.
     * 
     * @param x The coordinate of the building in which the herois going tho be verified.
     * 
     * return A boolean expresion that determines if the hero are in the city.
    */
    protected boolean existeHeroe(int x){
        ArrayList<Hero>heroes = city.getHeroes();
        for(int i=0;i<heroes.size();i++){
            if(heroes.get(i).getHidingBuilding()==x){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Updates the position of the hero in the floor of the city
     * 
     * @param x The horizontal coordinate of the hero.
     * 
    */
    protected void heroInTheFloor(int x){
        if(jump){
           goTo(x,city.getCityHeight()-60);
           setX(x);
           setHidingBuilding(0);
           if(visibility){city.makeVisible();}
        }
    }
    
    /**
     * Adds the hero in the list of dead heroes
     * 
     * @param x The horizontal coordinate of the hero.
     * 
    */
    protected void heroDies(){
       makeInvisible();
       city.heroDies(this);
       city = null;
    }
    
    /**
    * Returns the X coordinate where the hero is before jump.
    * @return The x coordinate of the hero in the city.
    */
    private BigDecimal getInicialX(){
        BigDecimal xi = new BigDecimal(0);
        if(hidingBuilding==0){
            xi=xi.add(new BigDecimal(X));
        }
        else{
            Building building = city.getBuildings().get(hidingBuilding-1);
            xi = xi.add(new BigDecimal(building.getX()).add(new BigDecimal(building.getWidth()/2)));
        }
        return xi;
    }
    
    /**
    * Returns the Y coordinate where the hero is before jump.
    * @return The y coordinate of the hero in the city.
    */
    private BigDecimal getInicialY(){
        BigDecimal citySize = new BigDecimal(city.getCityHeight());
        BigDecimal yi = new BigDecimal(0);
        if(hidingBuilding==0){
            yi=citySize.subtract(new BigDecimal(60));
        }
        else{
            Building building = city.getBuildings().get(hidingBuilding-1);
            yi = citySize.subtract(new BigDecimal(building.getHeight()));
        }
        return yi;
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
    public int getX(){
        return X;
    }
    
    /**
     * Changes the number of the building that have the hero.
     * 
     * @param x : The number of the new building.
    */
    public void setX(int x){
        X = x;
    }
    
    /**
     * Sets the strength of the hero
     * 
     * @param newStrength : The new strength of the hero
    */
    public void setStrength(int newStrength){
        strength=newStrength;
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
     * Shows a message to the user.
     * 
     * @param message The message for the user.
    */
    protected void showMessage(String message){
        if(visibility==true){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);    
        }
    }
    
    /**
     * Returns the number of the building that the hero wants to go.
     * 
    */
    public int getTargetBuilding(){
        return targetBuilding;
    }
    
    /**
     * Reads the hero like a string.
     * 
    */
    public String toString(){
        return "color = "+color+", strength = "+strength+", hidingbuilding = "+hidingBuilding;
    }    
}
