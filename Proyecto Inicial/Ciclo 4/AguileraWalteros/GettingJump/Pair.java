package GettingJump;
/**
 * Class that represents a Building through a pair of coordiantes
 * 
 * @author (Nicolas Aguilera Contreras && Daniel Walteros) 
 * @version (10/03/2019)
 */
 public class Pair{
     int y; int x;  
     double angulo;
     int[] beforeBuilding;
     /**
     * Constructor for objects of class Pair
     * 
     * @param y : The vertical coordinate of the building.
     * @param x : The horizontal coordinate of the building.
     */
     Pair(int y , int x){
         this.y=y; this.x=x;
     }
     
     /**
     * Gets the horizontal coordinate of the building.
     * 
     * @return  The horizontal coordinate of the building.
     */
     public int getY(){
         return y;
     }
     
     /**
     * Gets the vertical coordinate of the building.
     * 
     * @return  The horizontal coordinate of the building.
     */
     public int getX(){
         return x;
     }
     
     /**
     * Sets the angle to go to this building
     * 
     */
     public void setAngulo(double angulo){
         this.angulo=angulo;
     }
     
     /**
     * Gets the angle to go to this building
     * 
     * @return  The angle to go to this building
     */
     public double getAngulo(){
         return angulo;
     }
     
     /**
     * Sets the building to go from that building to this building
     * 
     */
     public void setBeforeBuilding(int i , int j){
         beforeBuilding=new int[2];
         beforeBuilding[0]=i;
         beforeBuilding[1]=j;
     }
     
     /**
     * Gets the building to go from that building to this building
     * 
     * @return  An array of int cointaining the vertical and horizontal coordinates of the building
     */
     public int[] getBeforeBuilding(){
         return beforeBuilding;
     }
}