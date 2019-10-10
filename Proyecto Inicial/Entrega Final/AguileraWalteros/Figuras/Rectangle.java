package Figuras;

import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */
public class Rectangle extends Figura{
    private int height;
    private int width;
    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "magenta";
        isVisible = false;
        update();
    }
    /**
     * Move the rectangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        update();
        draw();
    }
    private void update(){
         setShape(new java.awt.Rectangle(xPosition, yPosition, width, height));
    }
    /**
     * Move the rectangle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        update();
        draw();
    }
    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        update();
        draw();
    }
    public void goTo(int x,int y){
        xPosition = x;
        yPosition = y;
        update();
    }
    
    public void blink(){
        int tempH = height;
        int tempW = width;
        height=tempW;
        width=tempH;
        update();
    }
}