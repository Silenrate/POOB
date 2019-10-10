import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.math.BigDecimal; 
import java.util.Stack; 
/**
 * A city composed of buildings in which the heroes jumps from roof to roof.
 * 
 * @author (Nicolas Aguilera y Daniel Walteros) 
 * @version (7 of February of 2019)
 */
public class CityOfHeroes
{
    private static final String[] colors = {"red", "yellow", "blue", "green", "magenta", "black", "darkGray", "lightGray", "orange", "cyan"};
    private static final double GRAVEDAD = 9.80665;
    private int cityWidth;
    private int cityHeight;
    private boolean jump;
    private boolean jump2;
    private boolean jump3;
    private boolean jumpPlan;
    private boolean did;
    private boolean visibility;
    private boolean undo;
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
    }
    /**
     * Knows if the previous method was executed correctly
     * 
    */
    public boolean ok(){
        methods.push("ok");
        return did;
    }
    /**
     * Returns the strength of a hero
     * 
     * @param heroe The color oh the hero
    */
    public int strength(String heroe){
        heroe=heroe.toLowerCase();
        for(int i=0 ; i< heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(heroe)){
                setDid(true);
                methods.push("strength");
                parameters.push(heroe);
                return heroes.get(i).getStrength();
            }
        }
        showMessage("Ese heroe no existe");
        setDid(false);
        return 0;
    }
    /**
     * Adds a building into the city, if any dimension of the building gets out of the city or clash with other building doesn´t add it.
     * 
     * @param x The origin X coordinate of the building.
     * @param width The width of the building.
     * @param height The height of the building.
     * @param hardness The hardness of the building.
     */
    public void addBuilding(int x, int width , int height , int hardness){
        if(x<0 || x>cityWidth || x+width>cityWidth || height>cityHeight || height<0){
            showMessage("El edificio sale de los limites de la ciudad");
            setDid(false);
        }
        else{
            boolean flag = true;
            for(int i=0;i<buildings.size();i++){
                int currentX = buildings.get(i).getX();
                int currentWidth = buildings.get(i).getWidth();
                if(x==currentX && width==currentWidth){flag=false;setDid(false);break;}
                if((x>currentX && x<currentX+currentWidth)||(x+width>currentX && x+width<currentX+currentWidth)||x<currentX && x+width>currentX+currentWidth){
                    showMessage("Ya existe un edificio en esa posicion");
                    flag=false;
                    setDid(false);
                    break;
                }
            }
            if (flag == true){
                Building building = new Building(x,width,height,hardness);
                Rectangle imageBuilding = building.getBuild();
                buildings.add(building);
                imageBuilding.changeColor(colors[buildings.size()-1]);
                methods.push("addBuilding");
                parameters.push(x);
                setDid(true);
            }
        }
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
    public void removeBuilding(int x){
        sortCity();
        for(int i=0; i<heroes.size() ;i++){
            if(heroes.get(i).getHidingBuilding()==x){
                showMessage("Ya hay un heroe en el edificio");
                setDid(false);
                return;
            }
        }
        if(x>buildings.size()){
            showMessage("No existe ese edificio");
            setDid(false);
            return;
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
     * Adds a new hero in the city, a hero of a color that is already in the city cannot be adds.
     * 
     * @param color A string that represents the color of the hero.
     * @param hidingBuilding The number of the building in which the hero lives.
     * @param strength A number that represents the strength of the hero(the maximum strength is 100).
     */
    public void addHeroe(String color ,int hidingBuilding , int strength){
        color=color.toLowerCase();
        if(strength>100 || strength<1){
            showMessage("La fuerza esta entre 1 y 100");
            setDid(false);
            return;
        }
        if(hidingBuilding>buildings.size()){
            showMessage("No existe el edificio numero: "+hidingBuilding);
            setDid(false);
            return;
        }
        for(int i=0 ; i<heroes.size() ; i++){
            if(heroes.get(i).getColor().equals(color)){
                showMessage("Ya hay un heroe de ese color");
                return;
            }
        }
        Hero heroe = new Hero(color , hidingBuilding , strength);
        heroes.add(heroe);
        methods.push("addHeroe");
        parameters.push(color);
        sortCity();
        if(visibility==true){
            makeVisible();
        }
        setDid(true);

    }
    /**
     * Removes a hero in the cit if the color of the hero is in the city.
     * 
     * @param color A string that represents the color of the hero.
     */
    public void removeHeroe(String color){
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
        showMessage("No hay un heroe de ese color");
        setDid(false);
    }
    /**
     * Makes jump a hero into the city.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param velocity The speed that the hero jumps.
     * @param angle The angle that the hero jumps.
     * @param slow A boolean indicating if the jumps sees slow or not.
     */
    public void jump(String heroe , int velocity , int angle , boolean slow){
        if(findHeroeIndex(heroe)==-1){
            showMessage("El heroe no existe");
            setDid(false);
            return;
        }
        heroe=heroe.toLowerCase();
        jump=true;
        if(jump3!=true && jump2!=true && jumpPlan!=true){
            methods.push("jump");
            parameters.push(heroe);
            parameters.push(findHidingBuilding(heroe));
        }
        isSafeJump(heroe , velocity , angle);
        jump=false;
        setDid(true);
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
    public boolean isSafeJump(String heroe , int velocity , int angle){
        heroe=heroe.toLowerCase();
        int hidingBuilding=0; int heroeIndex = 0;
        boolean found = false;
        BigDecimal cero = new BigDecimal(0); BigDecimal vel = new BigDecimal(velocity);
        BigDecimal velY = vel.multiply(new BigDecimal(Math.sin(Math.toRadians(angle))));
        BigDecimal velX = vel.multiply(new BigDecimal(Math.cos(Math.toRadians(angle))));
        BigDecimal gravity = new BigDecimal(GRAVEDAD);
        BigDecimal yi; BigDecimal xi;
        BigDecimal citySize = new BigDecimal(cityHeight); BigDecimal citySizeX = new BigDecimal(cityWidth);
        if(findHidingBuilding(heroe)!=-1){
            hidingBuilding=findHidingBuilding(heroe);
            heroeIndex=findHeroeIndex(heroe);
            found=true;
        }
        xi=getInicialX(heroeIndex,hidingBuilding);
        yi=getInicialY(heroeIndex,hidingBuilding);
        if(found==false){
            setDid(false);
            return false;
        }
        if(jump3!=true && jump2!=true && jumpPlan!=true && jump!=true){
            methods.push("isSafeJump");
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(angle); temp.add(velocity) ; temp.add(heroe);
            parameters.push(temp);
        }
        boolean death=false ; boolean finish = false;
        BigDecimal contTime=new BigDecimal(0);
        while(death==false){
            BigDecimal y= velY.multiply(contTime).subtract((gravity.multiply(contTime.multiply(contTime))).divide(new BigDecimal(2)));
            BigDecimal x= vel.multiply(new BigDecimal(Math.cos(Math.toRadians(angle)))).multiply(contTime);
            contTime=contTime.add(new BigDecimal(0.05));
            BigDecimal tempX = x.add(xi); BigDecimal tempY = yi.subtract(y);
            for(int i=0 ; i<buildings.size() ; i++){
                Building building = buildings.get(i);
                BigDecimal posX = new BigDecimal(building.getX());
                BigDecimal bWidth = new BigDecimal(building.getWidth());
                BigDecimal bHeight = new BigDecimal(building.getHeight());
                BigDecimal actualY = citySize.subtract(bHeight);
                if(tempX.compareTo(posX)>=0 && tempX.compareTo(posX.add(bWidth))<=0 && tempY.compareTo(actualY)==1){
                    int strength = heroes.get(heroeIndex).getStrength();
                    int hardness = building.getHardness();
                    if(existeHeroe(i+1) && i+1!=hidingBuilding){death=true;
                        if(jump){
                            deads.add(heroes.get(heroeIndex).getColor());
                            heroes.get(heroeIndex).makeInvisible();
                            heroes.remove(heroeIndex);
                            showMessage("Ya habia un heroe en el edificio. El heroe que salto murio");
                            break;
                        }
                    }
                    else{
                        if(Math.abs(tempY.subtract(actualY).doubleValue())>(actualY.doubleValue()*1)/100){
                            death=true;
                            if(hardness>0){
                                if(jump){
                                    heroes.get(heroeIndex).setStrength(hardness);
                                    building.setHardness(strength);
                                }
                            }
                        }
                        if(strength<=0){
                            if(jump){
                                deads.add(heroes.get(heroeIndex).getColor());
                                heroes.get(heroeIndex).makeInvisible();
                                heroes.remove(heroeIndex);
                                death=true;
                                showMessage("El heroe murio con una fuerza de "+strength);
                                break;
                            }
                        }
                        if(!death){heroes.get(heroeIndex).setTargetBuilding(i+1);}
                        if(jump){
                            if(angle!=90){
                                heroes.get(heroeIndex).setHidingBuilding(i+1);
                                if(death){
                                    building.changeSize(Math.abs(cityHeight-tempY.intValue()),building.getWidth());
                                    heroes.get(heroeIndex).goTo(x.add(xi).intValue(),tempY.intValue());
                                }
                                else{heroes.get(heroeIndex).goTo(x.add(xi).intValue(),actualY.doubleValue());}
                            }
                            if(visibility==true){makeVisible();}
                        }
                        finish=true;
                        break;
                    }
                }
            }
            if(death || finish){break;}
            if(x.add(xi).compareTo(citySizeX)<0 && yi.subtract(y).compareTo(citySize)>0){
                if(jump){
                    heroes.get(heroeIndex).goTo(x.add(xi).intValue(),cityHeight-60);
                    if(visibility){heroes.get(heroeIndex).makeVisible();}
                    heroes.get(heroeIndex).setX(x.add(xi).doubleValue());
                    heroes.get(heroeIndex).setHidingBuilding(0);
                }
                break;
            }
            else if(x.add(xi).compareTo(citySizeX)>0 || yi.subtract(y).compareTo(citySize)>0 || x.add(xi).compareTo(cero)<0 || yi.subtract(y).compareTo(cero)<0){
                if(jump){
                    heroes.get(heroeIndex).goTo(x.add(xi).doubleValue(),yi.subtract(y).doubleValue());
                    showMessage("El heroe salio de los limites de la ciudad");
                    deads.add(heroes.get(heroeIndex).getColor());
                    heroes.get(heroeIndex).makeInvisible();
                    heroes.remove(heroeIndex);
                    death=true;
                }
                break;
            }
            else{
                if(jump){
                    heroes.get(heroeIndex).goTo(x.add(xi).doubleValue(),yi.subtract(y).doubleValue()-60);
                    if(visibility){heroes.get(heroeIndex).makeVisible();}
                }
            }
       }
       return !death;
  }
    /**
     * Plans a jump in the city.
     * 
     * @param heroe A string that represents the color of the hero.
     * @param building A number that represents the number of the building
     */
    public int[] jumpPlan(String heroe , int building){
        jumpPlan=true;
        int angulo=0;
        int [] valores = new int[2];
        if(findHidingBuilding(heroe)==building){
            if(jump3!=true && jumpPlan!=true && jump2==false){
                showMessage("El heroe está en ese edificio");
            }
            setDid(false);
            return null;
        }
        if(findHeroeIndex(heroe)==-1){
            if(jump3!=true && jumpPlan!=true && jump2==false){
                showMessage("El heroe no existe");
            }
            setDid(false);
            return null;
        }
        if(building>buildings.size() || building<=0){
            if(jump3!=true && jumpPlan!=true && jump2==false){
                showMessage("El edificio no existe");
            }
            setDid(false);
            return null;
        }
        BigDecimal posHeroe = getInicialX(findHeroeIndex(heroe) , findHidingBuilding(heroe));
        int xi = getInicialX(findHeroeIndex(heroe),findHidingBuilding(heroe)).intValue();
        int posX = buildings.get(building-1).getX()+(buildings.get(building-1).getWidth()/2);
        int targetX = Math.abs(xi-buildings.get(building-1).getX()+(buildings.get(building-1).getWidth()/2));
        int Y = buildings.get(building-1).getHeight();
        int yi = getInicialY(findHeroeIndex(heroe),findHidingBuilding(heroe)).intValue();
        if(posHeroe.compareTo(new BigDecimal(posX))>0){
            angulo=-180;
        }
        boolean found=false;
        for(int i=1 ; i<90 ; i++){
            angulo=angulo+1; int temp=Math.abs(angulo);
            double tangente = Math.tan(Math.toRadians(temp));
            double coseno = Math.pow(Math.cos(Math.toRadians(temp)),2);
            int velocidad = getInicialVelocity(tangente,coseno,targetX,Y,yi);
            if(isSafeJump(heroe,velocidad,temp) && heroes.get(findHeroeIndex(heroe)).getTargetBuilding()==building){
                found=true;
                if(jump2){jump(heroe,velocidad,temp,true);}
                valores[0]=velocidad;
                valores[1]=temp;
                break;
            }
        }
        if(!found){
            if(jump3!=true && jumpPlan!=true && jump2==false){
                showMessage("No es posible llegar");
            }
            setDid(false);
            return null;
        }
        setDid(true);
        if(jump3!=true && jump2!=true){
            methods.push("jumpPlan");
            ArrayList<Object> temp = new ArrayList<>();
            temp.add(building);
            temp.add(heroe);
            parameters.push(temp);
        }
        return valores;
    }
    /**
     * Jumps the heroe to a building
     * 
     * @param heroe A string that represents the color of the hero.
     * @param building A number that represents the number of the building
     */
    public void jump(String heroe , int building){
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
    public void jump(String heroe){
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
            showMessage("No existe un edificio al cual saltar");
            setDid(false);
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
    public boolean isSafeJump(String heroe , int building){
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
        for(int j=cont ; j<buildings.size()*4 ;j++){
            arreglo[1][j]=-1;
        }
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
            Rectangle imageBuilding = building.getBuild();
            int movX = building.getX();
            int bWidth = building.getWidth();
            int bHeight = building.getHeight();
            imageBuilding.goTo(movX,cityHeight-bHeight);
            imageBuilding.makeVisible();
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
                heroe.goTo(building.getX()+(building.getWidth()/2),(cityHeight-building.getHeight())-55);
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
    public void redo(){
        if (undo==true){
            undo();
            undo = false;
        }
        else{
            showMessage("No de puede rehacer");
        }
    }
    /**
     * Undo an action that was did before
     * 
     */
    public void undo(){
        if(methods.isEmpty()){
            showMessage("No se ha realizado ninguna accion");
            setDid(false);
            return;
        }
        String method = methods.pop();
        if(method.equals("addBuilding")){
            int x = getBuildingX((int)parameters.pop());
            removeBuilding(x+1);
            setDid(true);
        }
        if(method.equals("addHero")){
            String x = (String)parameters.pop();
            removeHeroe(x);
            setDid(true);
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
            showMessage("El ultimo metodo no se puede rehacer");
            setDid(false);
        }
        undo=true;
    }
    /**
     * Increase or decreases the size of the city
     * 
     */
    public void zoom(char signo){      
       Canvas canvas = Canvas.getCanvas();
       if((signo=='+')||(signo=='-')){
           canvas.zoom(signo);
           setDid(true);
           methods.push("zoom");
           parameters.push(signo);
       }
       else{
           showMessage("Caracter Equivocado");
           setDid(false);
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
    private int getBuildingX(int x){
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
     * Show an error message if a method wasnt did correctly
     * 
     * @param message : A string containing the message to show
     */
    private void showMessage(String message){
        if(visibility==true){
            Toolkit.getDefaultToolkit().beep(); 
            JOptionPane.showMessageDialog(null,message,"Error",JOptionPane.ERROR_MESSAGE);    
        }
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
    * A Proof if the previous method was executed correctly
    * @param newDid : boolean that knows the state of the previous method
    */
    private void setDid(boolean newDid){
        did=newDid;
    }
}