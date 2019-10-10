package presentacion;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;
import aplicacion.*;

public class Painter extends PanelFondo {
	private boolean isOn,right,left;
	private int speed , speedPlataforma;
	private String ruta;
	private Graphics2D g2;
    private Arkanoid game;
    private ArrayList<Bloque> bloques;
    private Timer timer,timerPlataforma;
    private Jugador jugador;
    private PantallaJuego pantallaJuego;
	public Painter(PantallaJuego pantalla){
		super();
		pantallaJuego = pantalla;
		right = false;
		left = false;
		speed=10;
		speedPlataforma = 5;
		ruta = "images/fondo.jpg";
		isOn=false;
		setBackground(ruta);
		reiniciarTimers();
		prepareJuego();
		prepareAcciones();
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.changeSize(getWidth(), getHeight());
		g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		if(!isOn) {
			prepareDibujos();
		}
		else{
			actualize();
		}
	}
	public void accionJugar(){
		isOn = true;
		activeTimers();
		game.changeSize((int)getBounds().getMaxX(),(int)getBounds().getMaxY());
		game.juegue();
		isGameOver();
		repaint();
	}
	public void detengase() {
		stopTimers();
	}
	public void reinicie(){
		isOn = false;
		stopTimers();
		reiniciarTimers();
		prepareJuego();
		repaint();
	}
	private void prepareAcciones() {
		addKeyListener(new KeyAdapter() {
			public void keyPressed( KeyEvent e ) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					right = true;
					accionMuevaPlataforma();
				}
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					left = true;
					accionMuevaPlataforma();
				}
			}
			public void keyReleased( KeyEvent e ) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					right = false;
				}
				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					left = false;
				}
			}
		});
	}
	private void isGameOver(){
		if(game.ganoNivel()) {
			if(game.ganoJuego()) {
				ImageIcon gana = new ImageIcon("images/carafeliz.png");
				JOptionPane.showMessageDialog(pantallaJuego, "Ganaste!!!","Victoria",JOptionPane.INFORMATION_MESSAGE,gana);
				int confirmado = JOptionPane.showConfirmDialog(null, "Desea jugar una nueva partida", "Guardar",JOptionPane.YES_NO_OPTION);
				if (confirmado == 0) {
					nuevoJuego();
				}
				else if (confirmado == 1 || confirmado == 2){
					pantallaJuego.setSaved(true);
					pantallaJuego.accionIrAlMenu();
					stopTimers();
				}
			}
			else {
				isOn = false;
				stopTimers();
				pantallaJuego.setTextPause("Play",true);
				updateStatistics();
			}
		}
		if(game.gameOver()){
			int lives = game.getJugador().getLives();
			if(lives>0) {
				pantallaJuego.setTextPause("Play",true);
				pantallaJuego.setLives(lives);
				newLife();
			}
			else {
				ImageIcon perdiste = new ImageIcon("images/caratriste.png");
				JOptionPane.showMessageDialog(pantallaJuego, "Perdiste!!!","Derrota",JOptionPane.INFORMATION_MESSAGE,perdiste);
				int confirmado = JOptionPane.showConfirmDialog(null, "Desea jugar una nueva partida", "Guardar",JOptionPane.YES_NO_OPTION);
				if (confirmado == 0) {
					nuevoJuego();
				}
				else if (confirmado == 1 || confirmado == 2){
					pantallaJuego.setSaved(true);
					pantallaJuego.accionIrAlMenu();
					stopTimers();
				}
			}
		}
	}
	private void newLife() {
		stopTimers();
		reiniciarTimers();
		game.setGameOver(false);
		Plataforma plataforma = game.getJugador().getPlataforma();
		plataforma.setCoordinates((int)getBounds().getMaxX()/2,(int)getBounds().getMaxY()-10);
		Bola bola = game.getBola();
		Rectangle2D.Double rectPlataforma = plataforma.getFigura();
		bola.goTo((int)(rectPlataforma.getX()+rectPlataforma.width/2-bola.getFigura().width/2),(int) (rectPlataforma.getY()-rectPlataforma.height));
		repaint();
	}
	private void nuevoJuego(){
		resetJugadores();
		pantallaJuego.resetGamebar();
		reinicie();
	}
	private void actualize() {
		bloques = game.getNivel();
		Rectangle2D.Double plataforma = game.getJugador().getPlataforma().getFigura();
		g2.fill(plataforma);
		g2.fill(game.getBola().getFigura());
		for(int i=0 ; i<bloques.size(); i++) {
			g2.setPaint(bloques.get(i).getColor());
			g2.fill(bloques.get(i).getFigura());
		}
		updateStatistics();
	}
	private void updateStatistics() {
		pantallaJuego.setScore(jugador.getScore());
		pantallaJuego.setLives(jugador.getLives());
		pantallaJuego.setNivel(game.getLevel()+1);
	}
	private void prepareDibujos(){
		try{
			game.addNivel();
			bloques = game.getNivel();
			game.getJugador().setPlataforma(new Plataforma((int)getBounds().getMaxX()/2,(int)getBounds().getMaxY()-10));
			Rectangle2D.Double plataforma = game.getJugador().getPlataforma().getFigura();
			Bola bola = game.getBola();
			g2.fill(plataforma);
			bola.goTo((int)(plataforma.getX()+plataforma.width/2-bola.getFigura().width/2),(int) (plataforma.getY()-plataforma.height));
			g2.fill(game.getBola().getFigura());
			for(int i=0 ; i<bloques.size(); i++) {
				g2.setPaint(bloques.get(i).getColor());
				g2.fill(bloques.get(i).getFigura());
			}
		}
		catch(ArkanoidException e){
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
			pantallaJuego.IrAlMenu();
			stopTimers();
		}	
	}
	private void accionMuevaPlataforma() {
		Jugador j = game.getJugador();
		if(right){
			j.muevaPlataformaALaDerecha(speedPlataforma , (int) (getBounds().getMaxX()-126));
		}
		if(left) {
			j.muevaPlataformaALaIzquierda(speedPlataforma , (int) (getBounds().getMinX()+27));
		}
	}
	private void resetJugadores() {
		jugador = null;
	}
	private void prepareJugadores() {
		if(jugador==null) {
			String nombre = JOptionPane.showInputDialog(this,"Ingresa tu nombre");
			jugador = new Jugador(nombre);
		}
		jugador.setScore(0);
		jugador.setLives(3);
		game.addJugador(jugador);
	}
	private void stopTimers() {
		timer.stop();
		timerPlataforma.stop();
	}
	private void activeTimers() {
		timer.start();
		timerPlataforma.start();
	}
	private void reiniciarTimers() {
		timer = new Timer(speed, new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				accionJugar();
			} 
		});
		timerPlataforma = new Timer(speedPlataforma, new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				setFocusable(true);
				requestFocusInWindow();
				accionMuevaPlataforma();
			} 
		}); 
	}
	private void prepareJuego(){
		game = new Arkanoid();
		try{
			game.addNivel();
			prepareJugadores();
		}
		catch(ArkanoidException e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);
			pantallaJuego.IrAlMenu();
			stopTimers();
		}
	}
}