import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;
/**
 * Class that solves and simulates the problem 'Getting Jump On a Crime' of ACM Finals 2018.
 *
 * @author (Nicolas Aguilera Contreras && Daniel Walteros)
 * @version (10/03/2019)
 */
public class CityOfHeroesContest{
    private CityOfHeroes city;
    private static int F; private static int C;
    private static double W;
    private static int V; 
    private static double vx; 
    private static double vy;
    private static double angulo; 
    private static int yi; 
    private static int xi;
    private static double diagonal;
    private static int[][] grid;
    private static int[][] rta;
    private static String[][] paths;
    private static double distancia;
    private static int deltaH;
    private static boolean visitados[][];
    private final static double gravedad = 9.80665;
    private static Queue <Pair> Q = new LinkedList<>();
    private static ArrayList<Pair> pairs = new ArrayList<>();
    private static ArrayList<CityOfHeroes> cities = new ArrayList<>();
    /**
     * Determinates if a hero can jump from one building to another. If yes , calculates his velocity and angle
     *
     */
    private boolean canJump(){
        double a = Math.pow(deltaH,2)+Math.pow(distancia,2);
        double b = (deltaH*gravedad*Math.pow(distancia,2))-(Math.pow(distancia,2)*Math.pow(V,2));
        double c = (Math.pow(gravedad,2)*Math.pow(distancia,4))/4;
        double determinante = Math.pow(b,2)-(4*a*c);
        if(determinante>=0){
            double temp1=(-b+Math.sqrt(determinante))/(2*a);
            double temp2=(-b-Math.sqrt(determinante))/(2*a);
            if(temp1<temp2){vx=Math.sqrt(temp1);}
            else{vx=Math.sqrt(temp2);}
            vy=Math.sqrt(Math.pow(V,2)-Math.pow(vx,2));
            angulo = Math.toDegrees(Math.atan(vy/vx));
            return true;
        }
        return false;
    }
    /**
     * Checks de horizontal jump of the hero from one building to another
     *
     * @param yi :  The inicial vertical coordinate of the hero in the city
     * @param xi :  The inicial horizontal coordinate of the hero in the city
     * @param yf :  The final vertical coordinate of the hero in the city
     * @param xi :  The final horizontal coordinate of the hero in the city
     * @return A boolean that knows if is a safe jump or not. 
     */
    private boolean checkHorizontal(int yi , int xi , int yf , int xf){
        double x; double trayectoria;
        double tangente = Math.tan(Math.toRadians(angulo));
        double coseno = Math.cos(Math.toRadians(angulo));
        if(xi<xf){
            double stepx=0;
            for(int i=xi+1 ; i<=xf ; i++){
                x = stepx+W/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[yi][i] || trayectoria<=grid[yi][i-1]){return false;}
                if(i==xf){break;}
                x = stepx+W/2+W;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[yi][i]){return false;}
                stepx+=W;
            }
        }
        else if(xi>xf){
            double stepx=0;
            for(int i=xi-1 ; i>=xf ; i--){
                x = stepx+W/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[yi][i] || trayectoria<=grid[yi][i+1] ){return false;}
                if(i==xf){break;}
                x = stepx+W/2+W;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[yi][i]){return false;}
                stepx+=W;
            }
        }
        return true;
    }
    /**
     * Checks the vertical jump of the hero from one building to another
     *
     * @param yi :  The inicial vertical coordinate of the hero in the city
     * @param xi :  The inicial horizontal coordinate of the hero in the city
     * @param yf :  The final vertical coordinate of the hero in the city
     * @param xi :  The final horizontal coordinate of the hero in the city
     * @return A boolean that knows if is a safe jump or not. 
     */
    private boolean checkVertical(int yi , int xi , int yf , int xf){
        double x; double trayectoria;
        double tangente = Math.tan(Math.toRadians(angulo));
        double coseno = Math.cos(Math.toRadians(angulo));
        if(yi<yf){
            double stepx=0;
            for(int i=yi+1 ; i<=yf ; i++){
                x = stepx+W/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[i][xi]|| trayectoria<=grid[i-1][xi]){return false;}
                if(i==yf){break;}
                x = stepx+W/2+W;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[i][xi]){return false;}
                stepx+=W;
            }
        }
        else if(yi>yf){
            double stepx=0;
            for(int i=yi-1 ; i>=yf ; i--){
                x = stepx+W/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[i][xi] || trayectoria<=grid[i+1][xi]  ){return false;}
                if(i==yf){break;}
                x = stepx+W/2+W;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                if(trayectoria<=grid[i][xi]){return false;}
                stepx+=W;
            }
        }
        return true;
    }
    /**
     * Checks the diagonal jump of the hero from one building to another
     *
     * @param yi :  The inicial vertical coordinate of the hero in the city
     * @param xi :  The inicial horizontal coordinate of the hero in the city
     * @param yf :  The final vertical coordinate of the hero in the city
     * @param xi :  The final horizontal coordinate of the hero in the city
     * @return A boolean that knows if is a safe jump or not. 
     */
    private boolean checkDiagonal(int yi,int xi,int yf,int xf){
        double x; double trayectoria;
        double tangente = Math.tan(Math.toRadians(angulo));
        double coseno = Math.cos(Math.toRadians(angulo));
        if(yi<yf && xi<xf){
            double stepx=0; int dx=xi+1; int [] posy = {-1,0}; int [] posx = {0,-1}; int maximo;
            for(int i=yi+1 ; i<=yf ; i++){
                x = stepx+diagonal/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                maximo = Math.max(grid[i+posy[0]][dx+posx[0]],grid[i+posy[1]][dx+posx[1]]);
                if(trayectoria<=maximo){return false;}
                if(trayectoria<=grid[i][dx] || trayectoria<=grid[i-1][dx-1]){return false;}
                stepx+=diagonal;
                dx+=1;
            }
        }
        if(yi<yf && xi>xf){
            double stepx=0; int dx=xi-1; int [] posy = {-1,0}; int [] posx = {0,1}; int maximo;
            for(int i=yi+1 ; i<=yf ; i++){
                x = stepx+diagonal/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                maximo = Math.max(grid[i+posy[0]][dx+posx[0]],grid[i+posy[1]][dx+posx[1]]);
                if(trayectoria<=maximo){return false;}
                if(trayectoria<=grid[i][dx] || trayectoria<=grid[i-1][dx+1]){return false;}
                stepx+=diagonal;
                dx-=1;
            }
        }
        if(yf<yi && xi>xf){
            double stepx=0; int dx=xi-1; int [] posy = {1,0}; int [] posx = {0,1}; int maximo;
            for(int i=yi-1 ; i>=yf ; i--){
                x = stepx+diagonal/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                maximo = Math.max(grid[i+posy[0]][dx+posx[0]],grid[i+posy[1]][dx+posx[1]]);
                if(trayectoria<=maximo){return false;}
                if(trayectoria<=grid[i][dx] || trayectoria<=grid[i+1][dx+1]){return false;}
                stepx+=diagonal;
                dx-=1;
            }
        }
        if(yf<yi && xi<xf){
            double stepx=0; int dx=xi+1; int [] posy = {1,0}; int [] posx = {0,-1}; int maximo;
            for(int i=yi-1 ; i>=yf ; i--){
                x = stepx+diagonal/2;
                trayectoria = grid[yi][xi]+(x*tangente)-((gravedad*x*x)/(2*V*V*coseno*coseno));
                maximo = Math.max(grid[i+posy[0]][dx+posx[0]],grid[i+posy[1]][dx+posx[1]]);
                if(trayectoria<=maximo){return false;}
                if(trayectoria<=grid[i][dx] || trayectoria<=grid[i+1][dx-1]){return false;}
                stepx+=diagonal;
                dx+=1;
            }
        }
        return true;
    }
    /**
     * Checks the random jump of the hero from one building to another
     *
     * @param yi :  The inicial vertical coordinate of the hero in the city
     * @param xi :  The inicial horizontal coordinate of the hero in the city
     * @param yf :  The final vertical coordinate of the hero in the city
     * @param xi :  The final horizontal coordinate of the hero in the city
     * @return A boolean that knows if is a safe jump or not. 
     */
    private boolean checkRandomJump(int yi,int xi,int yf,int xf){
        int stepy = (yf-yi)/Math.abs(yf-yi);
        int y = yi + stepy;
        double x = xi + .5 + (xf-xi)/(2.0*Math.abs(yf-yi));
        for (int i=0; i<Math.abs(yf-yi); i++) {
            int posx = (int)(x+1e-9);
            int maximo = grid[posx][y];
            maximo = Math.max(maximo, grid[posx][y-stepy]);
            if (x - posx< 1e-8) {
                maximo = Math.max(maximo, grid[posx-1][y]);
                maximo = Math.max(maximo, grid[posx-1][y-stepy]);
            }
            double disx = W*Math.sqrt((i+.5)*(i+.5)+(x-xi-.5)*(x-xi-.5));
            double tiempo = disx/(vx);
            double trayectoria = grid[xi][yi]+vy*tiempo - .5*gravedad*tiempo*tiempo;
            if (trayectoria < maximo)
                return false;
            y += stepy;
            x += (xf-xi)/(1.0*Math.abs(yf-yi));
	}
        return true;
    }
    /**
     * Knows if a jump of the hero from one building to another is safe
     *
     * @param yi :  The inicial vertical coordinate of the hero in the city
     * @param xi :  The inicial horizontal coordinate of the hero in the city
     * @param yf :  The final vertical coordinate of the hero in the city
     * @param xi :  The final horizontal coordinate of the hero in the city
     * @return A boolean that knows if is a safe jump or not. 
     */
    private boolean isValid(int yi,int xi,int yf,int xf){
        if(yi==yf){
            deltaH = grid[yf][xf]-grid[yi][xi];
            distancia = (xf*W-W/2)-(xi*W-W/2);
            if(!canJump()){return false;}
            return checkHorizontal(yi,xi,yf,xf);
        }
        else if(xi==xf){
            deltaH = grid[yf][xf]-grid[yi][xi];
            distancia = (yf*W-W/2)-(yi*W-W/2);
            if(!canJump()){return false;}
            return checkVertical(yi,xi,yf,xf);
        }
        else if((yi-xi)==(yf-xf) || (xi+yi) == (yf+xf)){
            deltaH = grid[yf][xf]-grid[yi][xi];
            distancia = Math.abs(yf-yi)*diagonal;
            if(!canJump()){return false;}
            return checkDiagonal(yi,xi,yf,xf);
        }
        else{
            deltaH = grid[yf][xf]-grid[yi][xi];
            distancia = Math.sqrt((xf-xi)*(xf-xi)+(yf-yi)*(yf-yi))*W;
            if(!canJump()){return false;}
            return checkRandomJump(xi,yi,xf,yf);
        }
    }
    /**
     * Search that finds the minimum number of jumps required by the hero
     *
     */
    private void bfs(){
        while(! Q.isEmpty()){
            Pair u = Q.remove();
            int y = u.getY();
            int x = u.getX();
            for(int i=0 ; i<F ; i++){
                for(int j=0 ; j<C ; j++){
                    Pair pair = new Pair(i,j);
                    if(i!=y || j!=x){
                        if(isValid(y,x,i,j) && visitados[i][j]==false){
                            rta[i][j]=rta[y][x]+1;
                            visitados[i][j]=true;
                            Q.add(pair);
                            pairs.add(pair);
                            pair.setAngulo(angulo);
                            pair.setBeforeBuilding(y,x);
                        }
                    }
                }
            }
        }
    }
    /**
     * Knows the minimum number of jumps that a hero needs for jump to all the buildings in the city.
     *
     * @param configuration : The configuration for the city(the number of buildings,the number of lines,the width of the buildings,the speed of the hero and the location of the hero in the city.) 
     * @param buildings : The heights of each building in the city.
     * @return A string matrix with the number of jumps for each building grouyp by the lines of the building.
     */
    public String[][] solve(int[] configuration,int[][] buildings){
        C  = configuration[0];
        F  = configuration[1];
        W  = configuration[2];
        V  = configuration[3];
        xi = configuration[4]-1;
        yi = configuration[5]-1;
        grid = new int [F][C]; 
        rta  = new int [F][C]; 
        rta[yi][xi]=0;
        visitados = new boolean [F][C];
        visitados[yi][xi]=true;
        Q = new LinkedList<>();
        Q.add(new Pair(yi,xi));
        diagonal = W*Math.sqrt(2);
        for(int i=0; i<F ; i++){
            for(int j=0 ; j<C ; j++){
                grid[i][j]=buildings[i][j];
            }
        }
        bfs();
        paths = new String[F][C];
        for(int i=0 ; i<F ; i++){
            for(int j=0 ; j<C ; j++){
                if((i!=yi || j!=xi) && rta[i][j]==0){
                    paths[i][j]="X";
                }
                else{
                    paths[i][j]=Integer.toString(rta[i][j]);
                }
            }
        }
        return paths;
    }
    /**
     * Simulate every minimum jump for the hero to every bulding in the city of hero
     *
     * @param configuration : The configuration for the city(the number of buildings,the number of lines,the width of the buildings,the speed of the hero and the location of the hero in the city.) 
     * @param buildings : The heights of each building in the city.
     * @param building : the building that have the hero.
     * @return A string matrix with the number of jumps for each building grouyp by the lines of the building.
     */
    public boolean simulate(int[] configuration,int[][] buildings,int building){
        pairs.clear();
        cities.clear();
        solve(configuration,buildings);
        city = new CityOfHeroes(C*(int)W,600);
        cities.add(city);
        if(paths[yi][building-1].equals("X")){
            return false;
        }
        for(int i=0 ; i<C ; i++){
            city.addBuilding(i*(int)W,(int)W,grid[yi][i],100);
        }
        city.makeVisible();
        city.addHeroe("blue",xi+1,100);
        getParameters(yi,building-1,rta[yi][building-1]);
        ArrayList <Double> angulos =getParameters(yi , building-1 , rta[yi][building-1]);
        for(int i = angulos.size()-1 ; i>-1 ; i--){
            Double angulo = angulos.get(i);
            if(building-1<xi){angulo=180.0-angulo;}
            city.jump("blue",V,angulo,true);
        }
        return true;
    }
    /**
     * Finds the angle that the hero needed to go to a building.
     *
     * @param y : The actual vertical coordinate of the hero
     * @param x : The actual horizontal coordinate of the hero
     * @param paths : The number of jumps that the hero needed to go from the inicial building to his actual building
     * @return An ArrayList of Double containing the angles of the jumps.
     */
    private ArrayList<Double> getParameters(int y , int x , int paths){
        ArrayList <Double> angulos =new ArrayList<>();
        boolean found = false;
        while(!found){
            for(int i =0 ; i<pairs.size() ; i++){
                Pair pair = pairs.get(i);
                if(pair.getY()==y && pair.getX() == x){
                    angulo=pair.getAngulo();
                    angulos.add(angulo);
                    int [] beforeBuild = pair.getBeforeBuilding();
                    y=beforeBuild[0];
                    x=beforeBuild[1];
                    break;
                }
            }
            if(y==yi && x==xi){found=true;}
        }
        return angulos;
    }
}

