package presentacion;


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import aplicacion.Arkanoid;
import aplicacion.ArkanoidException;
import persistencia.ArkapoobDAO;

public class Painter extends PanelFondo {
	private boolean isOn,right,left,left2,right2,isRandom,puedeMover,cpu;
	private int speed , speedPlataforma;
	private Graphics2D g2;
	private String [] rutas = new String[2];
    private Arkanoid game;
    private Timer timer,timerPlataforma;
    private PantallaJuego pantallaJuego;
    private int jugadores;
	
	private Painter(PantallaJuego pantalla, boolean random , boolean CPU) {
		super("resources/fondo.jpg");
		cpu = CPU;
		pantallaJuego = pantalla;
		isRandom = random;
		right = false; left = false; left2 = false; right2 = false; puedeMover=false; isOn=false;
		speed=10; speedPlataforma = 5;
	}
	public Painter(PantallaJuego pantalla, boolean random , int jugadores , boolean CPU){
		this(pantalla,random,CPU);
		this.jugadores = jugadores;
		reiniciarTimers();
		prepareJuego();
		prepareAcciones();
		repaint();
	}
	public Painter(PantallaJuego pantalla, boolean random , int jugadores,String[] nombres , boolean CPU){
		this(pantalla,random,CPU);
		this.jugadores = jugadores;
		reiniciarTimers();
		prepareJuego(nombres);
		prepareAcciones();
		repaint();
	}
	
	private void addNivel() {
		try {
			game.addNivel();
		} catch (ArkanoidException e) {
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(null,e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
			pantallaJuego.IrAlMenu();
			stopTimers();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		if(!isOn) {
			addNivel();
			if(jugadores==1) {
				prepareDibujos1Jugador(g);
			}
			else {
				prepareDibujos2Jugadores(g);
			}
		}
		else{
			actualize(g);
		}
	}
	public void accionJugar(){
		isOn = true;
		puedeMover=true;
		activeTimers();
		game.changeSize((int)getBounds().getMaxX(),(int)getBounds().getMaxY());
		try{
			game.juegue();
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
		}
		isGameOver();
		repaint();
	}
	public void detengase() {
		puedeMover=false;
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
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					game.puedeMoverBola();
				}
				else if (e.getKeyCode() == KeyEvent.VK_R) {
					pantallaJuego.reinicie();
				}
				else if (e.getKeyCode() == KeyEvent.VK_M) {
					pantallaJuego.accionIrAlMenu();
				}
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					left2 = true;
					accionMuevaPlataforma();
				}
				else if (e.getKeyCode() == KeyEvent.VK_D) {
					right2 = true;
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
				else if (e.getKeyCode() == KeyEvent.VK_A) {
					left2 = false;
				}
				else if (e.getKeyCode() == KeyEvent.VK_D) {
					right2 = false;
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					//el booleano para coordinar la plataforma especial
				}
			}
		});
	}
	private void isGameOver(){
		if(game.ganoNivel()) {
			if(game.ganoJuego()) {
				ImageIcon gana = new ImageIcon("resources/carafeliz.png");
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
			int lives;
			if(jugadores==1){lives = game.getPlayerLives(0);}
			else{lives = game.getPlayerLives(0)+game.getPlayerLives(1);}
			if(lives>0) {
				pantallaJuego.setTextPause("Play",true);
				pantallaJuego.setLives(lives);
				newLife();
			}
			else {
				ImageIcon perdiste = new ImageIcon("resources/caratriste.png");
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
		if(jugadores==1) {
			newLife1Jugador();
		}
		else {
			newLife2Jugadores();
		}
		game.setGameOver(false);
		game.setVelocity(1,1);
		repaint();
	}

	private void newLife1Jugador() {
		prepareDibujos1Jugador(getGraphics());
	}
	private void newLife2Jugadores() {
		prepareDibujos2Jugadores(getGraphics());
	}

	private void nuevoJuego(){
		resetJugadores();
		pantallaJuego.resetGamebar();
		reinicie();
	}
	private void actualize(Graphics g) {
		for(int i=0; i<jugadores ; i++) {
			if(game.getJugador(i).estaVivo()){
				g2.setPaint(game.getJugador(i).getPlataforma().getColor());
				Rectangle2D.Double plataforma = game.getPlayerPlataform(i);
				Image imagen = game.getJugador(i).getPlataforma().getImage();
				g.drawImage(imagen, (int)plataforma.getX(), (int)(plataforma.getY()), this);
			}
		}
		actualizeBloquesySorpresas(g);
		g2.setPaint(Color.white);
		g2.fill(game.getFigureBall());
		updateStatistics();
	}
	private void actualizeBloquesySorpresas(Graphics g) {
		for(int i=0 ; i<game.getBlocksAmount(); i++) {
			g2.setPaint(game.getBloque(i).getColor());
			g2.fill(game.getBloque(i).getFigura());
		}
		for(int i=0 ; i<game.getSorprisesAmount();i++) {
			g2.setPaint(game.getSorprise(i).getColor());
			Ellipse2D.Double figura = game.getSorprise(i).getFigura();
			Image imagen = game.getSorprise(i).getImage();
			g.drawImage(imagen,(int)figura.getBounds().getX(),(int)figura.getBounds().getY(),this);
		}
	}

	public void updateStatistics() {
		if(jugadores>1){
			if(game.getJugador(0).estaVivo()) {
				pantallaJuego.setScore(game.getPlayerScore(0));
				pantallaJuego.setLives(game.getPlayerLives(0));
			}
			else {
				pantallaJuego.setLives(0);
			}
			if(game.getJugador(1).estaVivo()) {
				pantallaJuego.setScore2(game.getPlayerScore(1));
				pantallaJuego.setLives2(game.getPlayerLives(1));
			}
			else {
				pantallaJuego.setLives2(0);
			}
		}
		else{
			pantallaJuego.setScore(game.getPlayerScore(0));
			pantallaJuego.setLives(game.getPlayerLives(0));
		}
		pantallaJuego.setNivel(game.getLevel()+1);
	}
	private void prepareDibujos1Jugador(Graphics g) {
		prepareDibujosJugador(g,0,(int)getBounds().getMaxX()/2,(int)getBounds().getMaxY()-30);
	}
	private void prepareDibujos2Jugadores(Graphics g) {
		if(game.getJugador(0).estaVivo()) {
			prepareDibujosJugador(g,0,(int)(getBounds().getMinX()+game.getPlayerPlataform(0).width),(int)getBounds().getMaxY()-30);
		}
		if(game.getJugador(1).estaVivo()) {
			prepareDibujosJugador(g,1,(int)(getBounds().getMaxX()-game.getPlayerPlataform(1).width),(int)getBounds().getMaxY()-30);
		}
	}
	private void prepareDibujosJugador(Graphics g, int player , int x , int y) {
		game.setPlayerPlataform(player,x,y);
		Rectangle2D.Double figura = game.getPlayerPlataform(player);
		game.getJugador(player).getPlataforma().setCoordinates((int)(figura.getX()),(int) figura.getY());
		game.getJugador(player).getPlataforma().setImage(rutas[player]);
		Image imagen = game.getJugador(player).getPlataforma().getImage();
		g.drawImage(imagen, (int)figura.getX(), (int)(figura.getY()), this);
		game.bolaGoTo((int)(getBounds().getMaxX()/2),(int)(getBounds().getMaxY()-50));
		g2.setPaint(Color.white);
		g2.fill(game.getFigureBall());
		actualizeBloquesySorpresas(g);
	}
	private void accionMuevaPlataforma() {
		if(puedeMover) {
			if(right){
				game.moveRightPlayerPlataform(0,speedPlataforma);
			}
			if(left) {
				game.moveLeftPlayerPlataform(0,-speedPlataforma);
			}
			if(!cpu) {
				if(right2){
					game.moveRightPlayerPlataform(1,speedPlataforma);
				}
				if(left2) {
					game.moveLeftPlayerPlataform(1,-speedPlataforma);
				}
			}
		}
	}

	private void resetJugadores() {
		game.resetPlayers();
	}
	
	private void prepareJugadores(){
		ArrayList<String> nombres = pantallaJuego.getNombres();
		for(int i=0 ; i<nombres.size(); i++) {
			try{
				if(i==1 && cpu) {
					game.addCPU(nombres.get(i), pantallaJuego.getCpuType());
				}
				else {
					game.addJugador(nombres.get(i));
				}
			}
			catch(ArkanoidException e){
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}
	}
	
	private void prepareJugadores(String [] nombres) {
		int contador = 0;
		while(contador<jugadores)  {
			try{
				if(contador==1 && cpu) {
					game.addCPU(nombres[contador], pantallaJuego.getCpuType());
				}
				else {
					game.addJugador(nombres[contador]);
				}
				contador+=1;
			}
			catch(ArkanoidException e){
				JOptionPane.showMessageDialog(this,e.getMessage());
			}
		}
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
		Arkanoid.nuevoArkanoid(isRandom,cpu);
		game = Arkanoid.deme(isRandom,cpu);
		try{
			game.addNivel();
			prepareJugadores();
			prepareColoresJugadores();
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);
			pantallaJuego.IrAlMenu();
			stopTimers();
		}
	}
	
	private void prepareJuego(String [] nombres){
		Arkanoid.nuevoArkanoid(isRandom,cpu);
		game = Arkanoid.deme(isRandom,cpu);
		try{
			game.addNivel();
			prepareJugadores(nombres);
			prepareColoresJugadores();
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.WARNING_MESSAGE);
			pantallaJuego.IrAlMenu();
			stopTimers();
		}
	}
	
	private void prepareColoresJugadores(){
		for(int i=0 ; i<jugadores ; i++) {
			ArrayList<String> colors = pantallaJuego.getColors();
			String color = colors.get(i);
			if(color.equals("Orange")){
				rutas[i]="resources/orange.png";
			}
			else if(color.equals("Green")){
				rutas[i]="resources/green.png";
			}
			if(color.equals("Yellow")){
				rutas[i]="resources/yellow.png";
			}
			else if(color.equals("Blue")){
				rutas[i]="resources/blue.png";
			}
			else if(color.equals("Red")){
				rutas[i]="resources/red.png";
			}
			else if(color.equals("Purple")){
				rutas[i]="resources/purpure.png";
			}
		}
	}
	
	public void salvar(File file) throws ArkanoidException{
		game.salvar(file);
	}
	public void exportar(File file) throws ArkanoidException{
		game.exportar(file);
	}
	public void abrir(File file) throws ArkanoidException{
		game = game.abrir(file);
	}
	public void setGame(Arkanoid game){
		this.game = game;
	}
}
