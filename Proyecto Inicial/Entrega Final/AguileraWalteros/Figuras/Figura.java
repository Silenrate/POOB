package Figuras;
import java.awt.*;
/**
 * Write a description of class shape here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Figura{
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    private java.awt.Shape shape;
    /**
    * Make this triangle invisible. If it was already invisible, do nothing.
    */
   public void setXPosition(int x){
        this.xPosition = x;
    }
    /**
     * Dar posicion y inicial
     * @param y Posicion en y
     */
    public void setYPosition(int y){
        this.yPosition = y;
    }
    public void goTo(int x,int y){
        xPosition = x;
        yPosition = y;
    }
    /**
     * Dar color a la figura
     * @param color El color de la figura
     */
    public void setColor(String color){
        this.color = color.toLowerCase();
    }
    
    /**
     * Dar la figura que representa para representarlo visualmente
     * @param Figura de la clase awt.Shape de Java
     */
    public void setShape(java.awt.Shape shape){
        this.shape = shape;
    }
    
    public java.awt.Shape getShape(){
        return shape;
    }
    
    /**
     * Dar visibilidad a la figura
     * @param visible Valor verdadero o falso, el cual indica si la figura es visible
     */
    public void setVisible(boolean visible){
        this.isVisible = visible;
    }
    
    /**
     * Obtener la posicion en x de la figura
     * @return La posicion origen x de la figura 
     */
    public int getXPosition(){
        return xPosition;
    }
    
    /**
     * Obtener la posicion en y de la figura
     * @return La posicion origen y de la figura 
     */    
    public int getYPosition(){
        return yPosition;
    }   
    public void moveRight(){
        moveHorizontal(20);
    }
    public void moveLeft(){
        moveHorizontal(-20);
    }
    public void moveUp(){
        moveVertical(-20);
    }
    public void moveDown(){
        moveVertical(20);
    }
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
        delta = -1;
        distance = -distance;
        } else {
        delta = 1;
        }
    
        for(int i = 0; i < distance; i++){
        xPosition += delta;
        draw();
        }
    }
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
    * Make this triangle visible. If it was already visible, do nothing.
    */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    /**
    * Make this triangle visible. If it was already visible, do nothing.
    */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    public void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, shape);
            canvas.wait(10);
        }
    }
    public void draw(java.awt.Shape shapee){
        if (isVisible){
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this,color,shapee);
            canvas.wait(10);
        }
    }
    public void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}