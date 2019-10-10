package aplicacion;

import java.awt.geom.*;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Arkanoid {
	/**
	 * Clase que ejecuta el juego arkapoob, el cual consiste en destruir bloques con una bola desde una plataforma.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/
	private Bola bola;
	private Nivel nivel;
	private Jugador jugador;
	private boolean gameOver;
	private int puntaje,ancho,alto,dx,dy,level;
	private ArrayList<Bloque> bloques;
	/**
     * Constructor para la clase arkanoid
    */
	public Arkanoid(){
		puntaje = 0;
		gameOver = false;
		bola = new Bola(0,0);
		this.dx = 1;
		this.dy = 1;
		bloques = new ArrayList<>();
		level = 0;
	}
	/**
     * Adicionar un nuevo jugador al juego arkanoid
     * @param newJugador El nuevo jugador que va a ser adicionado.
    */
	public void addJugador(Jugador newJugador) {
		jugador = newJugador;
	}
	/**
     * Obtiene el jugador que esta jugando arkanoid
     * @return El jugador que esta jugando el arkanoid.
    */
	public Jugador getJugador() {
		return jugador;
	}
	/**
     * Cambia las dimensiones del juego
     * @param newAncho El nuevo ancho del juego.
	 * @param newAlto El nuevo alto del juego.
    */
	public void changeSize(int newAncho , int newAlto) {
		alto = newAlto;
		ancho = newAncho;
	}
	/**
     * Obtiene la bola con la que esta jugando arkanoid
     * @return la bola con la que esta jugando arkanoid.
    */
	public Bola getBola() {
		return bola;
	}
	/**
     * Adicionar un nuevo bloque en el juego arkanoid
     * @param color El color del nuevo bloque.
	 * @param x La coordenada horizontal del nuevo bloque.
	 * @param y La coordenada vertical del nuevo bloque.
	 * @param anchoB El ancho del nuevo bloque.
	 * @param altoB La altura del nuevo bloque.
    */
	private void addBloque(String color , int x , int y , int anchoB , int altoB) throws ArkanoidException{
		color = color.toLowerCase();
		if(color.equals("red")) {
			bloques.add(new BloqueRojo(x,y,anchoB,altoB));
		}
		else if(color.equals("blue")) {
			bloques.add(new BloqueAzul(x,y,anchoB,altoB));
		}
		else if(color.equals("green")) {
			bloques.add(new BloqueVerde(x,y,anchoB,altoB));
		}
		else if(color.equals("gray")) {
			bloques.add(new BloqueGris(x,y,anchoB,altoB));
		}
		else if(color.equals("pink")) {
			bloques.add(new BloqueRosado(x,y,anchoB,altoB));
		}
		else if(color.equals("yellow")) {
			bloques.add(new BloqueAmarillo(x,y,anchoB,altoB));
		}
		else if(color.equals("orange")) {
			bloques.add(new BloqueNaranja(x,y,anchoB,altoB));
		}
		else if(color.equals("black")) {
			bloques.add(new BloqueNegro(x,y,anchoB,altoB));
		}
		else{
			throw new ArkanoidException(ArkanoidException.COLOR_ERROR);
		}
	}
	/**
     * Adicionar un nuevo nivel al juego arkanoid
     * @throws ArkanoidException - FILE_ERROR Si no se encuentra el archivo con el nivel.
    */
	public void addNivel() throws ArkanoidException{
		bloques = new ArrayList<>();
		nivel = new Nivel(level);
		String configuracion[][] = nivel.getNivel();
		int altoPlataformas = (alto/2-alto/4)/configuracion.length;
		int anchoPlataformas = (ancho-60)/configuracion[0].length;
		int y = alto/4;
		int x = 27;
		for(int i=0 ; i<configuracion.length ; i++) {
			x=27;
			for(int j=0 ; j<configuracion[0].length ; j++){
				addBloque(configuracion[i][j],x,y,anchoPlataformas,altoPlataformas);
				x+=anchoPlataformas+1;
			}
			y+=altoPlataformas+1;
		}
	}
	/**
     * Conoce si el juego fue terminado
     * @return El valor booleano que determina si el juego acabo.
    */
	public boolean ganoJuego() {
		return bloques.size()==0 && level==2;
	}
	/**
     * Conoce si el turno fue terminado
     * @return El valor booleano que determina si el turno acabo.
    */
	public boolean gameOver() {
		return gameOver;
	}
	/**
     * Cambia el estado finalizado del juego
     * @param state El nuevo estado del juego.
    */
	public void setGameOver(boolean state) {
		gameOver = state;
	}
	/**
     * Conoce si el jugador paso el nivel
     * @return El valor booleano que determina si el jugador paso el nivel.
    */
	public boolean ganoNivel() {
		if(bloques.size()==0) {
			level+=1;
			return true;
		}
		return false;
	}
	public int getLevel() {
		return level;
	}
	/**
     * Obtiene los bloques del nivel que se esta jugando
     * @return Los bloques del nivel que se esta jugando.
    */
	public ArrayList<Bloque> getNivel() {
		return bloques;
	}
	/**
     * Determina si la bola colisiono
     * @return El valor booleano que determina si hubo una colision para la bola.
    */
	public boolean colision(int x , int y) {
		/*choque contra la plataforma*/
		Rectangle2D.Double plataform = jugador.getPlataforma().getFigura();
		if(bola.getFigura().getBounds().intersects(plataform)) {
			if(x+9<=plataform.getX() || x+1>=plataform.width+plataform.getX()) {
				dx = -dx;
				return true;
			}
			else {
				if (x<=plataform.width+plataform.getX() && (x>=plataform.getX())){
					bola.goTo(x,y-4);
					if(Math.abs(x-plataform.width-plataform.getX())<Math.abs(x-plataform.getX())){
						dx = Math.min(-dx,dx);
					}
					else{
						dx = Math.max(-dx,dx);
					}
				}
				dy=Math.abs(dy);
				return true;
			}
		}
		/*choque contra bloques*/
		for(int i=0 ; i<bloques.size(); i++) {
			Rectangle2D.Double bloque = bloques.get(i).getFigura();
			if(bola.getFigura().getBounds().intersects(bloque)){
				if(x+9<=bloque.getX() || x+1>=bloque.width+bloque.getX()){
					dx = -dx;
					bloques.get(i).colision();
					if(bloques.get(i).esDestruido()){
						jugador.addScore(bloques.get(i).getPuntaje());
						bloques.remove(i);
					}
					return true;
				}
				else {
					dy=-dy;
					bloques.get(i).colision();
					if(bloques.get(i).esDestruido()){
						jugador.addScore(bloques.get(i).getPuntaje());
						bloques.remove(i);
					}
					return true;
				}
			}
		}
		return false;
	}
	/**
     * Obtiene el puntaje del jugador
     * @return El puntaje del jugador.
    */
	public int getPuntaje(){
		return puntaje;
	}
	/**
     * Juega un ciclo en el juego arkanoid
    */
	public void juegue() {
		int x = (int) bola.getFigura().getBounds().getX();
		int y = (int) bola.getFigura().getBounds().getY();
		if(colision(x,y)) {
			bola.goTo(bola.getX()-dx,bola.getY()-dy);
		}
		else {
			if(bola.getX()-30<=0 || bola.getX()+30>=ancho){
				dx = -dx;
			}
			else if(bola.getY()-12<=0) {
				dy = -dy;
			}
			else if (bola.getY()+10>=alto) {
				gameOver = true;
				jugador.subtractLive();
			}
			else if(bola.getY()==0 && bola.getX()==0) {
				dx = -dx;
				dy = -dy;
			}
			bola.goTo(bola.getX()-dx,bola.getY()-dy);
		}
	}
}