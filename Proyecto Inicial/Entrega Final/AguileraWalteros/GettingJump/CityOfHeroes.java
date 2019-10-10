package GettingJump;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal; 
import java.util.Stack;
import Figuras.*; 
/**
 * A city composed of buildings in which the heroes jumps from roof to roof.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (18/03/2019)
 */
public class CityOfHeroes{
    public static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};
    public static final double GRAVEDAD = 9.80665;
    private static int cityWidth;
    private static int cityHeight;
    private boolean jump;
    private boolean jump2;
    private boolean jump3;
    private boolean jumpPlan;
    private boolean did;
    private boolean visibility;
    private boolean undo;
    private boolean death;
    private Rectangle city;
    private ArrayList<Building> buildings;
    private ArrayList<Building> copyOfBuilding;
    private ArrayList<Hero> copyOfHero;
    private ArrayList<Hero> heroes;
    private ArrayList<String> deads;
    private Stack<String>methods;
    private Stack<Object>parameters;
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
        methods=new Stack<String>();
        parameters=new Stack<Object>();
        city.changeSize(cityHeight,cityWidth);
        city.changeColor("darkGray");
        city.goTo(0,0);
        jump=false;
        jump2=false;
        jump3=false;
        jumpPlan=false;
        setDid(true);
        visibility=false;
        undo=false;
        death=false;
    }
    
    /**
     * Knows if the previous method was executed correctly.
     * @return A boolean value that determines if the previous method was executed correctly.
    */
    public boolean ok(){
        return did;
    }
    
    /**
     * Knows the strength of certain hero.
     * 
     * @param heroe The color of the hero.
     * @return The stregnth of the hero.
    */
    public int strength(String heroe) throws CityOfHeroesExcepcion{
        heroe=heroe.toLowerCase();
        for(int i=0 ; i< heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(heroe)){
                setDid(true);
                methods.push("strength");
                parameters.push(heroe);
                return heroes.get(i).getStrength();
            }
        }
        setDid(false);
        throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.HERO_NOT_IN_CITY);
    }
    
    /**
     * Adds a building into the city, if any dimension of the building gets out of the city or clash with other building doesn´t add it.
     * 
     * @param x The origin X coordinate of the building.
     * @param width The width of the building.
     * @param height The height of the building.
     * @param hardness The hardness of the building.
     */
    public void addBuilding(int x, int width , int height , int hardness)throws CityOfHeroesExcepcion{
        checkAddBuild(x, width, height, hardness);
        Building building = new Building(this,x,width,height,hardness);
        Rectangle imageBuilding = building.getBuild();
        buildings.add(building);
        imageBuilding.changeColor(colors[buildings.size()-1]);
        methods.push("addBuilding");
        parameters.push(x);
        setDid(true);
        if(visibility==true){
            makeVisible();
        }
        sortCity();
    }
    
    /**
     * Knows if any dimension of the building gets out of the city or clash with other building doesn´t add it.
     * 
     * @param x The origin X coordinate of the building.
     * @param width The width of the building.
     * @param height The height of the building.
     * @param hardness The hardness of the building.
     */
    private void checkAddBuild(int x, int width , int height , int hardness)throws CityOfHeroesExcepcion{
        if(x<0 || x>cityWidth || x+width>cityWidth || height>cityHeight || height<0){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.BAD_BUILD_DIMENSIONS);
        }
        for(int i=0;i<buildings.size();i++){
            Building building  = buildings.get(i);
            int currentX = building.getX();
            int currentWidth = building.getWidth();
            if(building.AlreadyBuildInCity(x,width)){
                setDid(false);
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.ALREADY_BUILD_IN_CITY);
            }
        }
    }
    
    /**
     * Adds a building into the city, if any dimension of the building gets out of the city or clash with other building doesn´t add it.
     * 
     * @param type The type of the building(normal, radical, flexible or hard).
     * @param x The origin X coordinate of the building.
     * @param width The width of the building.
     * @param height The height of the building.
     * @param hardness The hardness of the building.
     */
    public void addBuilding(String type, int x, int width , int height , int hardness)throws CityOfHeroesExcepcion{
        type=type.toLowerCase();
        checkAddBuild(x, width, height, hardness);
        Building building;
        if(type.equals("radical")){building = new RadicalBuilding(this,x,width,height,hardness);}
        else if(type.equals("flexible")){building = new FlexibleBuilding(this,x,width,height,hardness);}
        else if(type.equals("normal")){building = new Building(this,x,width,height,hardness);}
        else if(type.equals("hard")){building = new HardBuilding(this,x,width,height,hardness);}
        else{throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.WRONG_BUILD_TYPE);}
        Rectangle imageBuilding = building.getBuild();
        buildings.add(building);
        imageBuilding.changeColor(colors[buildings.size()-1]);
        methods.push("addBuilding");
        parameters.push(x);
        setDid(true);
        if(visibility==true){
            makeVisible();
        }
        sortCity();
    }
    
    /**
     * Removes a building into the city if the building exists.
     * 
     * @param x The origin X coordinate of the building.
    */
    public void removeBuilding(int x) throws CityOfHeroesExcepcion{
        sortCity();
        for(int i=0; i<heroes.size() ;i++){
            if(heroes.get(i).getHidingBuilding()==x){
                setDid(false);
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.ALREADY_OTHER_HERO_IN_BUILD);
            }
        }
        if(x>buildings.size()){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.BUILD_NOT_IN_CITY);
        }
        methods.push("removeBuilding");
        Building building = buildings.get(x-1);
        ArrayList<Object> temp = new ArrayList<>();
        temp.add(building.getHardness());
        temp.add(building.getHeight());
        temp.add(building.getWidth());
        temp.add(building.getX());
        parameters.push(temp);
        parameters.push(x);
        buildings.remove(x-1);
        sortCity();
        if(visibility==true){
            makeVisible();
        }
        setDid(true);
    }
    
    /**
     * Shows the colors of the heroes that are dead.
     * 
     * @return An arrayList showing the colors of the dead heroes.
     */
    public String[] deads(){
        methods.push("deads");
        String [] tempDeads = new String[deads.size()];
        for(int i=0;i<deads.size();i++){
            tempDeads[i]=deads.get(i);
        }
        setDid(true);
        return tempDeads;
    }
    
    /**
     * Adds a new hero in the city if the building exists and the color of the hero is not already in the city.
     * 
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    public void addHeroe(String color ,int hidingBuilding , int strength) throws CityOfHeroesExcepcion{
        color=color.toLowerCase();
        checkAddHero(color, hidingBuilding, strength);
        try{
            Hero heroe = new Hero(this, color , hidingBuilding , strength);
            heroes.add(heroe);
            methods.push("addHero");
            parameters.push(color);
            sortCity();
            setDid(true);
            if(visibility==true){
                makeVisible();
            }
        }
        catch(CityOfHeroesExcepcion e){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.STRENGTH_OUT_OF_RANGE);
        }
    }
    
    /**
     * Knows if the building exists and the color of the hero is not already in the city.
     * 
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    private void checkAddHero(String color ,int hidingBuilding , int strength) throws CityOfHeroesExcepcion{
        if(hidingBuilding>buildings.size()||hidingBuilding<1){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.BUILD_NOT_IN_CITY);
        }
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(color)){
                setDid(false);
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.ALREADY_HERO_IN_CITY);
            }
        }
    }
    
    /**
     * Adds a new hero in the city if the building exists and the color of the hero is not already in the city.
     * 
     * @param type The type of the hero(normal, careful, climber, paratrooper or constructor):
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    public void addHeroe(String type, String color ,int hidingBuilding , int strength) throws CityOfHeroesExcepcion{
        color=color.toLowerCase(); type=type.toLowerCase();
        checkAddHero(color, hidingBuilding, strength);
        try{
            Hero heroe;
            if(type.equals("normal")){heroe = new Hero(this, color , hidingBuilding , strength);}
            else if(type.equals("careful")){heroe = new CarefulHero(this, color , hidingBuilding , strength);}
            else if(type.equals("paratrooper")){heroe = new ParatrooperHero(this, color , hidingBuilding , strength);}
            else if(type.equals("climber")){heroe = new ClimberHero(this, color , hidingBuilding , strength);}
            else if(type.equals("constructor")){heroe = new ConstructorHero(this, color , hidingBuilding , strength);}
            else{throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.WRONG_HERO_TYPE);}
            heroes.add(heroe);
            methods.push("addHero");
            parameters.push(color);
            sortCity();
            setDid(true);
            if(visibility==true){
                makeVisible();
            }
        }
        catch(CityOfHeroesExcepcion e){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.STRENGTH_OUT_OF_RANGE);
        }
    }
    
    /**
     * Removes a hero in the city if the color of the hero is in the city.
     * 
     * @param color A string that represents the color of the hero.
     */
    public void removeHeroe(String color) throws CityOfHeroesExcepcion{
        sortCity();
        color=color.toLowerCase();
        int tamao = heroes.size();
        for(int i=0;i<tamao;i++){
            Hero heroe = heroes.get(i);
            if (heroe.getColor().equals(color)){
                heroe.makeInvisible();
                heroes.remove(i);
                sortCity();
                setDid(true);
                methods.push("removeHeroe");
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(heroe.getStrength());
                temp.add(heroe.getHidingBuilding());
                temp.add(heroe.getColor());
                parameters.push(temp);
                if(visibility==true){
                    makeVisible();
                }
                return;
            }
        }
        setDid(false);
        throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.HERO_NOT_IN_CITY);
    }
    
    /**
     * Makes jump a hero into the city.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * @param slow A boolean indicating if the jumps sees slow or not.
     */
    public void jump(String heroe , int velocity , double angle , boolean slow) throws CityOfHeroesExcepcion{
        if(findHeroeIndex(heroe)==-1){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.HERO_NOT_IN_CITY);
        }
        heroe=heroe.toLowerCase();
        jump=true;
        if(jump3!=true && jump2!=true && jumpPlan!=true){
            methods.push("jump");
            parameters.push(heroe);
            parameters.push(findHidingBuilding(heroe));
        }
        setDid(true);
        isSafeJump(heroe , velocity , angle);
        if(visibility){makeVisible();}
        jump=false;
    }
    
    /**
     * Verifies if in a jump the hero get hurts or gets intact.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * @param slow A boolean indicating if the jumps sees slow or not.
     * 
     * @return The boolean value that represents if the hero gets hurt or get intact.
     */
    public boolean isSafeJump(String heroe , int velocity , double angle){
        heroe=heroe.toLowerCase(); int hidingBuilding=0; int heroeIndex = 0;
        boolean found = false;
        death=false;
        if(findHidingBuilding(heroe)!=-1){
            heroeIndex=findHeroeIndex(heroe); found=true;
        }
        if(found==false){ setDid(false); return false; }
        Hero hero = heroes.get(heroeIndex);
        if (jump){
            hero.jump(velocity,angle,true);
        }
        else{
            death=hero.isSafeJump(velocity,angle);
        }
        return death;
    }
    
    /**
     * Makes the hero dies in the city.
     * 
     * @param heroe The hero that is going to die.
     */
    public void heroDies(Hero heroe){
        int heroeIndex = findHeroeIndex(heroe.getColor());
        deads.add(heroes.get(heroeIndex).getColor());
        heroes.remove(heroeIndex);
    }
    
    /**
     * Designs a jump in the city.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param building A number that represents the number of the building
     */
    public int[] jumpPlan(String heroe , int building)throws CityOfHeroesExcepcion{
        jumpPlan=true;
        int angulo=0; int [] valores = new int[2];
        if(checkJumpPlan2(heroe, building)){return null;}
        BigDecimal posHeroe = getInicialX(findHeroeIndex(heroe) , findHidingBuilding(heroe));
        int xi = getInicialX(findHeroeIndex(heroe),findHidingBuilding(heroe)).intValue();
        int posX = buildings.get(building-1).getX()+(buildings.get(building-1).getWidth()/2);
        int Y = buildings.get(building-1).getHeight();
        int yi = getInicialY(findHeroeIndex(heroe),findHidingBuilding(heroe)).intValue();
        if(posHeroe.compareTo(new BigDecimal(posX))>0){
            angulo=-180;
        }
        boolean found = checkJumpPlan(angulo, building,heroe,valores , xi ,Y ,yi);
        if(!found){
            setDid(false);
            if(jump3!=true && jumpPlan!=true && jump2==false){
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.UNMAKEABLE_JUMP);
            }
            return null;
        }
        setDid(true);
        if(jump3!=true && jump2!=true){
            methods.push("jumpPlan");
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(building); temp.add(heroe);
            parameters.push(temp);
        }
        return valores;
    }
    
    /**
     * Checks if the hero can reach a building with the parameters.
     * 
     * @param angulo The angle that the hero is going to jump.
     * @param building A number that represents the number of the building
     * @param heroe A string that represents the color of the hero.
     * @param valores An array with the optimum velocity and angle.
     * @param xi The X coordinate of the hero.
     * @param Y The Y coordinate that represents where the hero goes.
     * @param yi The Y coordinate of the hero.
     */
    private boolean checkJumpPlan(int angulo, int building,String heroe,int [] valores , int xi , int Y , int yi)throws CityOfHeroesExcepcion{
        boolean found=false;
        for(int i=1 ; i<90 ; i++){
            angulo=angulo+1; int temp=Math.abs(angulo);
            double tangente = Math.tan(Math.toRadians(temp));
            double coseno = Math.pow(Math.cos(Math.toRadians(temp)),2);
            int targetX = Math.abs(xi-buildings.get(building-1).getX());           
            for(int j=0 ; j<buildings.get(building-1).getWidth()/2 ; j=j+2){
                int velocidad = getInicialVelocity(tangente,coseno,targetX,Y,yi);
                if(isSafeJump(heroe,velocidad,temp) && heroes.get(findHeroeIndex(heroe)).getTargetBuilding()==building){
                    found=true;
                    if(jump2){jump(heroe,velocidad,temp,true);}
                    valores[0]=velocidad; valores[1]=temp;
                    break;
                }
                targetX+=j;
            }
            if(found){break;}
        }
        return found;
    }
    
    /**
     * Knows if the hero nad the building are capable of are in the jump.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param building A number that represents the number of the building
     */
    private boolean checkJumpPlan2(String heroe, int building) throws CityOfHeroesExcepcion{
        if(findHidingBuilding(heroe)==building){
            setDid(false);
            if(jump3!=true && jumpPlan!=true && jump2==false){
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.ALREADY_HERO_IN_BUILD);
            }
            return true;
        }
        else if(findHeroeIndex(heroe)==-1){
            setDid(false);
            if(jump3!=true && jumpPlan!=true && jump2==false){
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.HERO_NOT_IN_CITY);
            }
            return true;
        }
        else if(building>buildings.size() || building<=0){
            setDid(false);
            if(jump3!=true && jumpPlan!=true && jump2==false){
                throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.BUILD_NOT_IN_CITY);
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Jumps the heroe to a building
     * 
     * @param heroe A string that represents the color of the hero.
     * @param building A number that represents the number of the building
     */
    public void jump(String heroe , int building)throws CityOfHeroesExcepcion{
        jump2=true;
        int actualBuilding = findHidingBuilding(heroe);
        if(jumpPlan(heroe,building)==null){
            jump2=false;
            setDid(false);
            return;
        }
        else{
            jumpPlan(heroe,building);
            setDid(true);
            if(jump3!=true){
                methods.push("jump2");
                ArrayList<Object> temp = new ArrayList<>();
                temp.add(actualBuilding);
                temp.add(heroe);
                parameters.push(temp);
            }
        }
        jump2=false;
    }
    
    /**
     * Makes the "better" jump of the heroe
     * 
     * @param heroe A string that represents the color of the hero.
     */
    public void jump(String heroe)throws CityOfHeroesExcepcion{
        int maximo = -1;
        int index = -1;
        jump3=true;
        int hidingBuilding=findHidingBuilding(heroe);
        for(int i=0 ; i<buildings.size() ; i++){
            if(isSafeJump(heroe,i+1)){
                int comp = i+1;
                int temp = Math.abs(hidingBuilding-comp);
                if(temp>maximo){
                    maximo=temp;
                    index=comp;
                }
            }
        }
        if(maximo==-1){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.UNREACHABLE_JUMP);
        }
        else{
            parameters.push(heroe);
            jump(heroe,index);
            setDid(true);
            methods.push("jump3");
        }
        jump3=false;
    }
    
    /**
     * Knows if a jump to a building is safe or not.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param building A number that represents the number of the building
     */
    public boolean isSafeJump(String heroe , int building)throws CityOfHeroesExcepcion{
        if(jumpPlan(heroe,building)==null){
            setDid(false);
            return false;
        }
        if(jump3!=true){
            setDid(true);
            methods.push("isSafeJump2");
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(building);
            temp.add(heroe);
            parameters.push(temp);
        }
        return true;
    }
    
    /**
     * Returns two lists with the information of the buildings and heroes organized by coordenate of X.
     * 
     */
    public int[][] city(){
        int [][] arreglo = new int [2][buildings.size()*4];
        arreglo[0] = new int [buildings.size()*4];
        arreglo[1] = new int [heroes.size()*2];
        int cont=0;
        for(int i = 0 ; i<buildings.size() ; i++){
            arreglo[0][cont]=buildings.get(i).getX();
            arreglo[0][cont+1]=buildings.get(i).getWidth();
            arreglo[0][cont+2]=buildings.get(i).getHeight();
            arreglo[0][cont+3]=buildings.get(i).getHardness();
            cont+=4;
        }
        cont=0;
        for(int i = 0 ; i<heroes.size() ; i++){
            arreglo[1][cont]=heroes.get(i).getHidingBuilding();
            arreglo[1][cont+1]=heroes.get(i).getStrength();
            cont+=2;
        }
        cont+=1;
        setDid(true);
        methods.push("city");
        return arreglo;
    }
    
    /**
     * Makes Visible the city, their buildings and the heroes.
     * 
    */
    public void makeVisible(){
        makeInvisible();
        city.makeVisible();
        for(int i=0;i<buildings.size();i++){
            Building building = buildings.get(i);
            int movX = building.getX();
            int bWidth = building.getWidth();
            int bHeight = building.getHeight();
            building.goTo(movX,cityHeight-bHeight);
            building.makeVisible();
        }
        for(int i=0 ; i<heroes.size();i++){
            if(heroes.get(i).getHidingBuilding()==0){
                heroes.get(i).goTo(heroes.get(i).getX(),cityHeight-60);
                heroes.get(i).showHeroe();
            }
            else{
                Hero heroe = heroes.get(i);
                int hidingBuilding = heroe.getHidingBuilding();
                Building building = buildings.get(hidingBuilding-1);
                heroe.goTo(building.getX()+(building.getWidth()/2),(cityHeight-building.getHeight())-65);
                heroe.showHeroe();
            }
        }
        visibility=true;
    }
    
    /**
     * Makes Invisible the city, their buildings and the heroes.
     * 
    */
    public void makeInvisible(){
        visibility=false;
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
        if(x>buildings.size()){
            setDid(false);
            return false;
        }
        methods.push("isDamaged");
        parameters.push(x);
        if(buildings.get(x-1).isDamaged()){
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
     * Redo an action that was did before
     * 
     */
    public void redo() throws CityOfHeroesExcepcion{
        if (undo==true){
            undo();
            undo = false;
        }
        else{
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.UNMAKEABLE_REDO);
        }
    }
    
    /**
     * Undo an action that was did before
     * 
     */
    public void undo() throws CityOfHeroesExcepcion{
        if(methods.isEmpty()){
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.NOT_ACTION);
        }
        String method = methods.pop();
        if(method.equals("addBuilding")){
            int x = getBuildingX((int)parameters.pop());
            removeBuilding(x+1);
            setDid(true);
        }
        if(method.equals("addHero")){
            setDid(true);
            String x = (String)parameters.pop();
            removeHeroe(x);
        }
        else if((method.equals("removeBuilding"))){
            parameters.pop();
            ArrayList<Object> temp = (ArrayList)parameters.pop();
            addBuilding((int)temp.get(3),(int)temp.get(2),(int)temp.get(1),(int)temp.get(0));
            setDid(true);
        }
        else if((method.equals("removeHeroe"))){
            ArrayList<Object> temp = (ArrayList)parameters.pop();
            addHeroe((String)temp.get(2),(int)temp.get(1),(int)temp.get(0));
            setDid(true);
        }
        else if((method.equals("jump"))){
            int building2 = (int)parameters.pop(); 
            int building1 = (int)parameters.pop(); 
            jump((String)parameters.pop(),building1);
            buildings.get(building2).restore();
            setDid(true);
        }
        else if((method.equals("jump2"))){
            ArrayList<Object> temp;
            temp=(ArrayList)parameters.pop();
            jump((String)temp.get(1),(int)temp.get(0));
            setDid(true);
        }
        else if((method.equals("jump3"))){
            jump((String)parameters.pop());
            setDid(true);
        }
        else if((method.equals("zoom"))){
            char signo = (char)parameters.pop(); 
            if(signo=='+'){
                zoom('-');
            }
            else{
                zoom('+');
            }
            setDid(true);
        }
        else{
            setDid(false);
            throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.UNMAKEABLE_UNDO);
        }
        undo=true;
    }
    
    /**
     * Increase or decreases the size of the city
     * 
     */
    public void zoom(char signo) throws CityOfHeroesExcepcion{
       Canvas canvas = null;
       if(visibility==true){canvas = Canvas.getCanvas();}
       if((signo=='+')||(signo=='-')){
           if(visibility==true){
               canvas.zoom(signo);
               setDid(true);
               methods.push("zoom");
               parameters.push(signo);
            }
       }
       else{
           setDid(false);
           throw new CityOfHeroesExcepcion(CityOfHeroesExcepcion.UNMAKEABLE_ZOOM);
       }
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
            if(heroes.get(i).getHidingBuilding()==x){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * Returns the index of a City based on a coordinate X
     * 
     * @param x X coordinate
     */
    public int getBuildingX(int x){
        int buildingIndex=-1;
        for(int i=0;i<buildings.size();i++){
            Building building = buildings.get(i);
            if(building.getX()==x){
                buildingIndex=i;
            }
        }
        return buildingIndex;
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
            if(heroes.get(i).getHidingBuilding()!=0){
                if(heroes.get(i).getHidingBuilding()==newPosicions[i]-1 && heroes.get(i).getHidingBuilding()!=0){
                    heroes.get(i).setHidingBuilding(i+1);
                }
            }
        }
        for(int i=0 ; i<newBuildings.size() ; i++){
            buildings.set(i,newBuildings.get(i));
        }
    }
    
    /**
     * Returns a index of a City based on the color of a hero
     * 
     * @param heroe : A string containing the name of the hero
     */
    private int findHidingBuilding(String heroe){
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(heroe)){
                return heroes.get(i).getHidingBuilding();
            }
        }
        return -1;
    }
    
    /**
    * Returns a index of a Hero based on the color of a hero
    * 
    * @param heroe : A string containing the name of the hero
    */
    private int findHeroeIndex(String heroe){
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(heroe)){
                return i;
            }
        }
        return -1;
    }
    
    /**
    * Returns the X coordinate where the hero is
    * 
    * @param heroeIndex : The heroe Index in the ArrayList of heros
    * @param hidingBuilding : The number of the building where the hero is
    * 
    */
    private BigDecimal getInicialX(int heroeIndex , int hidingBuilding){
        BigDecimal xi = new BigDecimal(0);
        if(hidingBuilding==0){
            xi=xi.add(new BigDecimal(heroes.get(heroeIndex).getX()));
        }
        else{
            xi = xi.add(new BigDecimal(buildings.get(hidingBuilding-1).getX()).add(new BigDecimal(buildings.get(hidingBuilding-1).getWidth()/2)));
        }
        return xi;
    }
    
    /**
    * Returns the Height of the city.
    * @return the Height of the city
    */
    public int getCityHeight(){
        return cityHeight;
    }
    
    /**
    * Returns the Width of the city.
    * @return the Width of the city
    */
    public int getCityWidth(){
        return cityWidth;
    }
    
    /**
    * Returns the buildings of the city.
    * @return the buildings of the city
    */
    public ArrayList<Building> getBuildings(){
        return buildings;
    }
    
    /**
    * Changes the buildings in the city.
    * @param newBuildings The new buildings of the city.
    */
    public void setBuildings(ArrayList <Building> newBuildings){
        buildings=newBuildings;
    }
    
    /**
    * Returns the heroes of the city.
    * @return the heroes of the city
    */
    public ArrayList<Hero> getHeroes(){
        return heroes;
    }
    
    /**
    * Returns the X coordinate where the hero is
    * 
    * @param tangente : The tangent of the angle which the velocity has
    * @param coseno : The cosine of the angle which the velocity has
    * @param X : The x Coordinate where the hero wants to go
    * @param Y : The y Coordinate where the hero wants to go
    * @param yi : The inicial Y coordinate of the hero
    * 
    */
    private int getInicialVelocity(double tangente , double coseno,  int X , int Y , int yi){
        double numerador = GRAVEDAD*Math.pow(X,2);
        int altura = Y-yi;
        double resta = (X*tangente)-altura;
        double denominador = (2*Math.pow(coseno,2))*(resta);
        return (int) Math.sqrt(numerador/denominador);
    }
    
    /**
    * Returns the Y coordinate where the hero is
    * 
    * @param heroeIndex : The heroe Index in the ArrayList of heros
    * @param hidingBuilding : The number of the building where the hero is
    * 
    */
    private BigDecimal getInicialY(int heroeIndex , int hidingBuilding){
        BigDecimal citySize = new BigDecimal(cityHeight);
        BigDecimal yi = new BigDecimal(0);
        if(hidingBuilding==0){
            yi=citySize.subtract(new BigDecimal(60));
        }
        else{
            yi = citySize.subtract(new BigDecimal(buildings.get(hidingBuilding-1).getHeight()));
        }
        return yi;
    }
    
    /**
    * A proof that the previous method was executed correctly
    * @param newDid : boolean that knows the state of the previous method
    */
    public void setDid(boolean newDid){
        did=newDid;
    }
}