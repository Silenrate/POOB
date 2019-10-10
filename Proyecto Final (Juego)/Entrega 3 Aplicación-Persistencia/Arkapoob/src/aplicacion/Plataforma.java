package aplicacion;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.ImageIcon;

public class Plataforma implements Serializable{
	/**
	 * Clase que ejecuta una plataforma del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	protected int x;
	protected int y;
	protected int ancho;
	protected int alto;
	protected int maxX = 900;
	protected int maxY = 700;
	protected int speed;
	protected Color color;
	protected boolean right;
	protected boolean left;
	protected Rectangle2D.Double figura;
	protected String ruta;
	protected ImageIcon imagen;
	protected transient Image temporal;
	/**
     * Constructor para la clase plataforma
	 * @param x La coordenada horizontal de la plataforma.
	 * @param y La coordenada vertical de la plataforma.
    */
	public Plataforma(int x , int y) {
		this.x = x;
		this.y = y;
		right = false;
		left = false;
		speed = 0;
		ancho = 100;
		alto = 10;
		figura = new Rectangle2D.Double(x-ancho/2,y,ancho,alto);
	}
	/**
     * Obtiene la figura de la plataforma
	 * @return La figura de la plataforma.
    */
	public Rectangle2D.Double getFigura(){
		return figura;
	}
	/**
     * Obtiene la coordenada horizontal de la plataforma.
	 * @return La coordenada horizontal de la plataforma.
    */
	public int getX() {
		return (int) figura.getX();
	}
	/**
     * Obtiene la coordenada vertical de la plataforma.
	 * @return La coordenada vertical de la plataforma.
    */
	public int getY() {
		return (int) figura.getY();
	}
	/**
     * Obtiene el ancho de la plataforma.
	 * @return El ancho de la plataforma.
    */
	public int getWidth() {
		return (int) figura.getWidth();
	}
	/**
     * Obtiene el alto de la plataforma.
	 * @return El alto de la plataforma.
    */
	public int getHeight() {
		return (int) figura.getHeight();
	}
	/**
     * Cambia las dimensiones de la plataforma
     * @param newAncho El nuevo ancho de la plataforma.
	 * @param newAlto El nuevo alto de la plataforma.
    */
	public void changeSize(int newAncho , int newAlto) {
		ancho = newAncho;
		alto = newAlto;
		figura = new Rectangle2D.Double(figura.getX(),figura.getY(),ancho,alto);
	}
	/**
     * Cambia las coordenadas de la plataforma
     * @param newX La nueva coordenada horizontal de la plataforma.
	 * @param newY La nueva coordenada vertical de la plataforma.
    */
	public void setCoordinates(int newX , int newY) {
		x = newX;
		y = newY;
		figura = new Rectangle2D.Double(x,y,ancho,alto);
	}
	/**
     * Mueve la plataforma hacia la izquierda en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param minX El limite izquierdo del tablero.
    */
	public void muevaseIzquierda(int i){
		left = true;
		right = false;
		i = -i;
		if(figura.getX()-33>0){
			figura = new Rectangle2D.Double(figura.getX()-i,figura.getY(),ancho,alto);
		}
	}
	/**
     * Mueve la plataforma hacia la derecha en el tablero.
     * @param i La cantidad de pixeles a mover.
	 * @param minX El limite derecho del tablero.
    */
	public void muevaseDerecha(int i){
		right = true;
		left = false;
		if(figura.getX()+33+figura.width<maxX){
			figura = new Rectangle2D.Double(figura.getX()+i,figura.getY(),ancho,alto);
		}
	}
	/**
     * Determina si la plataforma choco hacia la derecha con otra plataforma.
     * @param i La cantidad de pixeles a mover.
	 * @param p La otra plataforma del juego.
	 * @return El valor booleano que determina si la plataforma choco hacia la derecha con otra plataforma.
    */
	public boolean chocaALaDerechaCon(Plataforma p , int i) {
		Rectangle2D.Double temporal1 = getFigura();
		Rectangle2D.Double temporal2 = p.getFigura();
		int x = (int) (temporal1.getMaxX()+i);
		if(x>=temporal2.getMinX() && x<=temporal2.getMaxX()) {
			int x1 = getX();
			int y1 = getY();
			setCoordinates(p.getX(),p.getY());
			p.setCoordinates(x1,y1);
			return true;
		}
		return false;
	}
	/**
     * Determina si la plataforma choco hacia la izquierda con otra plataforma.
     * @param i La cantidad de pixeles a mover.
	 * @param p La otra plataforma del juego.
	 * @return El valor booleano que determina si la plataforma choco hacia la izquierda con otra plataforma.
    */
	public boolean chocaALaIzquierdaCon(Plataforma p , int i) {
		Rectangle2D.Double temporal1 = getFigura();
		Rectangle2D.Double temporal2 = p.getFigura();
		int x = (int) (temporal1.getMinX()-i);
		if(x>=temporal2.getMinX() && x<=temporal2.getMaxX()) {
			int x1 = getX();
			int y1 = getY();
			setCoordinates(p.getX(),p.getY());
			p.setCoordinates(x1,y1);
			return true;
		}
		return false;
	}
	/**
     * Determina si la plataforma se esta moviendo hacia la derecha.
	 * @return El valor booleano que determina si la plataforma se esta moviendo hacia la derecha.
    */
	public boolean isMovingRight() {
		return right;
	}
	/**
     * Determina si la plataforma se esta moviendo hacia la izquierda.
	 * @return El valor booleano que determina si la plataforma se esta moviendo hacia la izquierda.
    */
	public boolean isMovingLeft() {
		return left;
	}
	/**
     * Cambia el color de la plataforma.
	 * @param newColor El nuevo color de la plataforma.
    */
	public void setColor(Color newColor) {
		color = newColor;
	}
	/**
     * Muestra el color de la plataforma.
	 * @return El color de la plataforma.
    */
	public Color getColor() {
		return color;
	}
	/**
     * Cambia la imagen de la plataforma.
	 * @param newRuta La nueva imagen de la plataforma.
    */
	public void setImage(String newRuta) {
		ruta = newRuta;
		imagen = new ImageIcon(ruta);
	}
	/**
     * Muestra la ruta de la imagen de la plataforma.
	 * @return La ruta de la imagen de la plataforma.
    */
	public String getRuta() {
		return ruta;
	}
	/**
     * Muestra la imagen de la plataforma.
	 * @return La imagen de la plataforma.
    */
	public Image getImage() {
		temporal = imagen.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
		ImageIcon fin = new ImageIcon(temporal);
		return fin.getImage();
	}
	/**
     * Determina si la plataforma esta invertida.
	 * @return El valor booleano que determina si la plataforma esta invertida.
    */
	public boolean estaInvertida() {
		return false;
	}
}
