package aplicacion;

import java.awt.geom.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.io.*;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import persistencia.*;

public class Arkanoid implements Serializable{
	/**
	 * Clase que ejecuta el juego arkapoob, el cual consiste en destruir bloques con una bola desde una plataforma.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/
	private Bola bola;
	private Nivel nivel;
	private ArrayList<Jugador> jugadores;
	private boolean gameOver,isRandom,cpu;
	private int level,lastJugador;
	private static int ancho = 900;
	private static int alto = 700;
	private ArrayList<Bloque> bloques;
	private ArrayList<Sorpresa> sorpresas;
	private Bloque lastBloque;
	private static transient ArkapoobDAO arkaDAO;
	private static Arkanoid arkanoid=null;
	private static final long serialVersionUID = 8799656478674716638L;
	private transient Clip sonido; 
	private String cpuType;
	/**
     * Constructor privado para la clase arkanoid
	 * @param random El valor booleano que determina si el juego esta generando los niveles de forma aleatoria. 
	 * @param cpu El valor booleano que determina si el juego esta juagndo con una cpu. 
    */
	private Arkanoid(boolean random, boolean cpu){
		lastJugador = 0;
		isRandom = random;
		gameOver = false;
		bola = new Bola(0,0);
		this.cpu = cpu;
		bloques = new ArrayList<>();
		jugadores = new ArrayList<>();
		sorpresas = new ArrayList<>();
		level = 0;
	}
	/**
     * Consigue un nuevo arkanoid
	 * @param random El valor booleano que determina si el juego esta generando los niveles de forma aleatoria. 
	 * @param cpu El valor booleano que determina si el juego esta juagndo con una cpu. 
	 * @return El nuevo Arkanoid 
    */
	public static Arkanoid deme(boolean random, boolean cpu){
		if(arkanoid==null){
			arkanoid = new Arkanoid(random,cpu);
		}
		return arkanoid;
	}
	/**
     * Construye un nuevo arkanoid, borrando el anterior
	 * @param random El valor booleano que determina si el juego esta generando los niveles de forma aleatoria. 
	 * @param cpu El valor booleano que determina si el juego esta juagndo con una cpu. 
    */
	public static void nuevoArkanoid(boolean random, boolean cpu){
		arkanoid = new Arkanoid(random,cpu);
	}
	/**
     * Cambia las dimensiones del tablero del juego arkanoid
	 * @param newAncho El nuevo ancho del tablero de arkanoid.
	 * @param newAlto El nuevo alto del tablero de arkanoid.	 
    */
	public void changeSize(int newAncho,int newAlto){
		ancho = newAncho;
		alto = newAlto;
	}
	/**
     * Adicionar un nuevo jugador al juego arkanoid
     * @param newJugador El nombre del nuevo jugador que va a ser adicionado.
	 * @throws ArkanoidException - PLAYER_ALREADY_ERROR si ya existe un jugador con ese nombre en el juego arkanoid.
	 * @throws ArkanoidException - PLAYER_NAME_ERROR si el nombre del jugador no es valido.
    */
	public void addJugador(String newJugador) throws ArkanoidException{
		if(newJugador.equals("")){throw new ArkanoidException(ArkanoidException.PLAYER_NAME_ERROR);}
		for(Jugador j: arkanoid.getPlayers()){
			if (j.getNombre().equals(newJugador)){
				throw new ArkanoidException(ArkanoidException.PLAYER_ALREADY_ERROR);
			}
		}
		arkanoid.getPlayers().add(new Jugador(newJugador));
	}
	/**
     * Adicionar un nuevo jugador al juego arkanoid
     * @param newJugador El nuevo jugador que va a ser adicionado.
	 * @throws ArkanoidException - PLAYER_ALREADY_ERROR si ya existe un jugador con ese nombre en el juego arkanoid.
	 * @throws ArkanoidException - PLAYER_NAME_ERROR si el nombre del jugador no es valido.
    */
	public void addJugador(Jugador newJugador) throws ArkanoidException{
		if(newJugador.getNombre().equals("")){throw new ArkanoidException(ArkanoidException.PLAYER_NAME_ERROR);}
		for(Jugador j: arkanoid.getPlayers()){
			if (j.getNombre().equals(newJugador.getNombre())){
				throw new ArkanoidException(ArkanoidException.PLAYER_ALREADY_ERROR);
			}
		}
		arkanoid.getPlayers().add(newJugador);
	}
	/**
     * Obtiene el jugador que esta jugando arkanoid, si el indice no esta dentro de la lista retorna un jugador por defecto.
	 * @param index El indice del jugador dentro del juego arkanoid
     * @return El jugador que esta jugando el arkanoid.
    */
	public Jugador getJugador(int index) {
		if(index<jugadores.size() && index>-1){
			return jugadores.get(index);
		}
		else{
			return new Jugador("Default");
		}
	}
	/**
     * Cambia la velocidad de la bola en el juego arkanoid.
	 * @param newDx La nueva velocidad horizontal de la bola.
     * @param newDy La nueva velocidad vertical de la bola.
    */
	public void setVelocity(int newDx, int newDy){
		bola.setDx(newDx);
		bola.setDy(newDy);
	}
	/**
     * Adicionar un nuevo bloque en el juego arkanoid, los colores validos son(red,blue,green,gray,pink,yellow,orange y black).
     * @param color El color del nuevo bloque.
	 * @param x La coordenada horizontal del nuevo bloque.
	 * @param y La coordenada vertical del nuevo bloque.
	 * @param anchoB El ancho del nuevo bloque.
	 * @param altoB La altura del nuevo bloque.
	 * @throws ArkanoidException - COLOR_ERROR si el color no esta dentro de los parametros del arkanoid.
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
     * Incrementa un nivel en el juego arkanoid.
     * @throws ArkanoidException - FILE_ERROR Si ocurrio un error leyendo un nivel predeterminado.
    */
	public void addLevel() throws ArkanoidException{
		level++;
		addNivel();
		bola.goTo(ancho/2,alto-40);
	}
	/**
     * Muestra el nivel en el que esta el juego (iniciando en 0).
     * @return El valor del nivel en el que esta el juego (iniciando en 0).
    */
	public int getNivel(){
		return level;
	}
	/**
     * Cambia el nivel en el que esta el juego (si esta entre 0 y 5).
     * @param newLevel El valor del nuevo nivel en el que esta el juego.
    */
	public void setNivel(int newLevel){
		if(newLevel<6 && newLevel >-1){
			level = newLevel;
		}
	}
	/**
     * Determina si el juego esta jugando con niveles aleatorios.
     * @return El valor booleano que determina si el juego esta jugando con niveles aleatorios.
    */
	public boolean getRandom(){
		return isRandom;
	}
	/**
     * Determina si el juego esta jugando con una cpu.
     * @return El valor booleano que determina si el juego esta jugando con una cpu.
    */
	public boolean getCpu(){
		return cpu;
	}
	/**
     * Adicionar un nuevo nivel al juego arkanoid
     * @throws ArkanoidException - FILE_ERROR Si no se encuentra el archivo con el nivel.
    */
	public void addNivel() throws ArkanoidException{
		bloques = new ArrayList<>();
		sorpresas = new ArrayList<>();
		nivel = new Nivel(level);
		String configuracion[][];
		if(isRandom){configuracion = nivel.getNivel(4,13);}
		else{configuracion = nivel.getNivel();}
		int altoPlataformas = (alto/2-alto/4)/configuracion.length;
		int anchoPlataformas = (ancho-(66+configuracion[0].length))/configuracion[0].length;
		int y = alto/4;
		int x = 33;
		for(int i=0 ; i<configuracion.length ; i++) {
			x=33;
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
		return level==5;
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
		boolean win = true;
		for(Bloque b:bloques){
			if (b.esDestruible()){
				win = false;
				break;
			}
		}
		if(win){level++;}
		return win;
	}
	/**
     * Muestra el nivel que se esta jugando del arkanoid.
     * @return El valor entero del nivel.
    */
	public int getLevel() {
		return level;
	}
	/**
     * Muestra la cantidad de bloques en el juego arkanoid.
     * @return La longitud de la lista de bloques.
    */
	public int getBlocksAmount(){
		return bloques.size();
	}
	/**
     * Muestra un bloque del juego arkanoid.
	 * @param index El indice del bloque en la lista de bloques del juego arkanoid.
     * @return El bloque del juego arkanoid.
    */
	public Bloque getBloque(int index){
		return bloques.get(index);
	}
	/**
     * Muestra la cantidad de sorpresas en el juego arkanoid.
     * @return La longitud de la lista de sorpresas.
    */
	public int getSorprisesAmount(){
		return sorpresas.size();
	}
	/**
     * Muestra una sorpresa del juego arkanoid.
	 * @param index El indice de la sorpresa en la lista de bloques del juego arkanoid.
     * @return La sorpresa del juego arkanoid.
    */
	public Sorpresa getSorprise(int index){
		return sorpresas.get(index);
	}
	/**
     * Determina si la bola colisiono con algun objeto del juego arkanoid.
	 * @param x La coordenada Horizontal del tablero donde se verifica la colision.
	 * @param y La coordenada Vertical del tablero donde se verifica la colision.
     * @return El valor booleano que determina si hubo una colision para la bola.
	 * @throws ArkanoidException - SOUND_ERROR si hubo algun error generando un sonido.
    */
	public boolean colision(int x , int y) throws ArkanoidException{
		boolean colisionPlataform = colisionPlataforma(x,y);
		boolean colisionBlock = colisionBloques(x,y);
		return colisionPlataform||colisionBlock;
	}
	/**
     * Determina si la bola colisiono con alguna plataforma del juego arkanoid.
	 * @param x La coordenada Horizontal del tablero donde se verifica la colision.
	 * @param y La coordenada Vertical del tablero donde se verifica la colision.
     * @return El valor booleano que determina si hubo una colision.
	 * @throws ArkanoidException - SOUND_ERROR si hubo algun error generando un sonido.
    */
	public boolean colisionPlataforma(int x,int y)throws ArkanoidException{
		for(int i=0;i<jugadores.size();i++){
			if(jugadores.get(i).estaVivo()) {
				Plataforma plataform = jugadores.get(i).getPlataforma();
				if(bola.colisionPlat(plataform.getFigura(),x,y)){
					lastJugador = i;
					plataform.reaccione(bola);
					return true;
				}
			}
		}
		return false;
	}
	/**
     * Determina si la bola colisiono con algun bloque del juego arkanoid.
	 * @param x La coordenada Horizontal del tablero donde se verifica la colision.
	 * @param y La coordenada Vertical del tablero donde se verifica la colision.
     * @return El valor booleano que determina si hubo una colision.
	 * @throws ArkanoidException - SOUND_ERROR si hubo algun error generando un sonido.
	 * @throws ArkanoidException - FILE_ERROR Si ocurrio un error leyendo un nivel predeterminado.
    */
	public boolean colisionBloques(int x,int y)throws ArkanoidException{
		for(int i=0 ; i<bloques.size(); i++) {
			Bloque brick = bloques.get(i); 
			Rectangle2D.Double bloque = brick.getFigura();
			if(bola.colisionBlock(bloque,x,y)){
				brick.reaccione(this);
				Sorpresa sorpresa = brick.getSorpresa();
				if(sorpresa!=null) {ponerSorpresa(sorpresa);}
				brick.colision();
				if(brick.esDestruido()){
					jugadores.get(lastJugador).addScore(brick.getPuntaje());
					if(bloques.size()>i){bloques.remove(i);}
					lastBloque = brick;
				}
				return true;
			}
		}
		return false;
	}
	/**
     * Muestra el ultimo jugador que toco la bola en el juego arkanoid.
     * @return El jugador que toco la bola en el juego arkanoid.
    */
	public Jugador getLastPlayer(){
		return jugadores.get(lastJugador);
	}
	/**
     * Muestra la bola del juego arkanoid.
     * @return La bola del juego arkanoid.
    */
	public Bola getBola(){
		return bola;
	}
	/**
     * Cambia la bola del juego arkanoid.
     * @param bola La nueva bola del juego arkanoid.
    */
	public void setBola(Bola bola){
		this.bola = bola;
	}
	/**
     * Incorpora una nueva sorpresa en el juego arkanoid, emitiendo un sonido especial.
	 * @param sorpresa La nueva sorpresa que va a ser agregada.
	 * @throws ArkanoidException - SOUND_ERROR si hubo algun error generando un sonido.
    */
	private void ponerSorpresa(Sorpresa sorpresa) throws ArkanoidException{
		sorpresas.add(sorpresa);
		try {
            sonido = AudioSystem.getClip();
            //Se carga el fichero wav y comienza a reproducirse
            sonido.open(AudioSystem.getAudioInputStream(new File("resources/poder.wav")));
            sonido.loop(0); 
        }catch (Exception e) {
            throw new ArkanoidException(ArkanoidException.SOUND_ERROR);
        }
	}
	/**
     * Determina si hay un bloque en las coordenadas del tablero del juego arkanoid.
	 * @param x La coordenada Horizontal del tablero.
	 * @param y La coordenada Vertical del tablero.
     * @return El valor booleano que determina si hay un bloque en las coordenadas del tablero del juego arkanoid.
    */
	public boolean isOcupied(int x,int y){
		if(y<=20){return true;}
		for(Bloque bloque : bloques){
			if(bloque.getX()==x&&bloque.getY()==y){
				return true;
			}
		}
		return false;
	}
	/**
     * Mueve y elimina las sorpresas, dependiendo si algun jugador las atrapo o si saluieron del tablero.
    */
	public void reaccioneSorpresas() {
		for(int i=0 ; i<sorpresas.size(); i++) {
			Sorpresa sorpresa = sorpresas.get(i);
			if(choqueSorpresa(sorpresa)) {
				sorpresas.remove(i);
			}
			else {
				muevaSorpresa(sorpresa);
			}
		}
	}
	/**
     * Determina si una sorpesa choco, ya sea con una plataforma o esquina del tablero.
	 * @param sorpresa La sorpesa a revisar.
	 * @return El valor booleano que determina si la sorpesa choco.
    */
	public boolean choqueSorpresa(Sorpresa sorpresa) {
		if (sorpresa.getY()+10>=alto) {return true;}
		for(int i=0 ; i<jugadores.size(); i++) {
			if(jugadores.get(i).estaVivo()) {
				Plataforma plataforma = jugadores.get(i).getPlataforma();
				if(sorpresa.getFigura().getBounds2D().intersects(plataforma.getFigura().getBounds2D())){
					sorpresa.reaccione(bola,jugadores.get(i));
					return true;
				}
			}
		}
		return false;
	}
	/**
     * Mueve una sorpresa en tablero del juego arkanoid.
	 * @param sorpresa La sorpesa a mover.
    */
	public void muevaSorpresa(Sorpresa sorpresa) {
		sorpresa.goTo(sorpresa.getX(),sorpresa.getY()+1);
	}
	/**
     * Muestra el ultimo bloque destruido del juego arkanoid.
	 * @return El ultimo bloque destruido del juego arkanoid.
    */
	public Bloque getLastBloque(){
		return lastBloque;
	}
	/**
     * Juega un ciclo del juego arkanoid.
	 * @throws ArkanoidException - SOUND_ERROR si hubo algun error generando un sonido.
    */
	public void juegue() throws ArkanoidException{
		muevaJugadores();
		reaccioneSorpresas();
		int x = (int) bola.getFigura().getBounds().getX();
		int y = (int) bola.getFigura().getBounds().getY();
		if(colision(x,y)){bola.move();}
		else {
			if(bola.getX()-30<=0 || bola.getX()+30>=ancho){
				bola.choque();
				bola.inverseDx();
			}
			else if(bola.getY()-12<=0) {
				bola.choque();
				bola.inverseDy();
			}
			else if(bola.getY()+10>=alto) {
				gameOver = true;
				sorpresas = new ArrayList<>();
				getLastJugador();
				jugadores.get(lastJugador).subtractLive();
			}
			else if(bola.getY()==0 && bola.getX()==0) {
				bola.choque();
				bola.inverseDy();
				bola.inverseDx();
			}
			bola.move();
		}
	}
	/**
     * Mueve a los jugadores automaticos del juego arkanoid.
    */
	private void muevaJugadores() {
		for(Jugador jugador : jugadores) {
			if(jugador.puedeMoverse) {
				jugador.move(this);
			}
		}
	}
	/**
     * Genera el ultimo jugador que toco la bola.
    */
	private void getLastJugador() {
		if(jugadores.get(lastJugador).estaVivo()==false){
			if(lastJugador==0) {
				lastJugador=1;
			}
			else {
				lastJugador=0;
			}
		}
	}
	/**
     * Guarda el arkapoob desde un archivo con extension (.arka)
	 * @param file El archivo donde se va a guardar el arkapoob.
	 * @throws ArkanoidException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .arka.
	 * @throws ArkanoidException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el arkapoob.
	 * @throws ArkanoidException Si ocurrio un error al serializar el archivo.
    */
	public void salvar(File file) throws ArkanoidException{
		arkaDAO.save(this,file);
	}
	/**
     * Guarda el arkapoob desde un archivo con extension (.arka)
	 * @param file El archivo donde se va a guardar el arkapoob.
	 * @throws ArkanoidException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .arka.
	 * @throws ArkanoidException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el arkapoob.
	 * @throws ArkanoidException Si ocurrio un error al serializar el archivo.
    */
	public void exportar(File file) throws ArkanoidException{
		arkaDAO.exportar(this,file);
	}
	/**
     * Abre un nuevo arkapoob desde un archivo con extension (.arka)
	 * @param file El archivo donde se va a abrir el arkapoob.
     * @return El nuevo arkapoob del archivo.
	 * @throws ArkanoidException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .arka.
	 * @throws ArkanoidException Si ocurrio un error al deserializar el archivo.
    */
	public Arkanoid abrir(File file) throws ArkanoidException{
		return arkaDAO.open(file);
	}
	/**
     * Determina si hay jugadores jugando el arkapoob
     * @return El valor booleano que determina si hay jugadores jugando el arkapoob.
    */
	public boolean hasPlayer(){
		return jugadores.size()!=0;
	}
	/**
     * Reinicia la lista de jugadores del juego arkanoid.
    */
	private void resetJugadores(){
		jugadores = new ArrayList<>();
	}
	/**
     * Reinicia los jugadores del arkapoob.
    */
	public void resetPlayers(){
		arkanoid.resetJugadores();
	}
	/**
     * Muestra las vidas de un jugador del arkapoob
	 * @param player El numero del jugador en el arkapoob.
     * @return Las vidas del jugador del arkapoob.
    */
	public int getPlayerLives(int player){
		Jugador jugador = getJugador(player);
		return jugador.getLives();
	}
	/**
     * Muestra el puntaje de un jugador del arkapoob
	 * @param player El numero del jugador en el arkapoob.
     * @return El puntaje del jugador del arkapoob.
    */
	public int getPlayerScore(int player){
		Jugador jugador = getJugador(player);
		return jugador.getScore();
	}
	/**
     * Cambia las coordenadas de la plataforma de un jugador del arkapoob
	 * @param player El numero del jugador en el arkapoob.
	 * @param x La nueva coordenada Horizontal para la plataforma.
	 * @param y La nueva coordenada Vertical para la plataforma.
    */
	public void setPlayerPlataform(int player,int x,int y){
		Jugador jugador = getJugador(player);
		jugador.setPlataforma(x,y);
	}
	/**
     * Muestra la figura de la plataforma de un jugador del arkapoob
	 * @param player El numero del jugador en el arkapoob.
     * @return La figura de la plataforma del jugador del arkapoob.
    */
	public Rectangle2D.Double getPlayerPlataform(int player){
		Jugador jugador = getJugador(player);
		return jugador.getPlataforma().getFigura();
	}
	/**
     * Cambia las coordenadas de la bola del arkapoob
	 * @param x La nueva coordenada Horizontal de la bola.
	 * @param y La nueva coordenada Vertical de la bola.
    */
	public void bolaGoTo(int x,int y){
		bola.goTo(x,y);
	}
	/**
     * Muestra la figura de la bola del arkapoob
     * @return La figura de la bola del arkapoob.
    */
	public Ellipse2D.Double getFigureBall(){
		return bola.getFigura();
	}
	/**
     * Mueve a la derecha la plataforma de un jugador del arkapoob
	 * @param player El numero del jugador en el arkapoob.
	 * @param i La cantidad de pixeles a mover.
    */
	public void moveRightPlayerPlataform(int player,int i){
		Plataforma plataforma = jugadores.get(player).getPlataforma();
			boolean choque = false;
			if(jugadores.size()>1 && jugadores.get(0).estaVivo() && jugadores.get(1).estaVivo()) {
				if(jugadores.get(player).getPlataforma().isMovingLeft()) { choque = choquePlataformaAlaIzquierda(player,i);}
				else {choque = choquePlataformaAlaDerecha(player,i);}
			}
			if(choque == false) {
				Jugador jugador = getJugador(player);
				jugador.muevaPlataformaALaDerecha(i,bola);
				if(cpu) {
					jugadores.get(1).move(this);;
				}
			}
	}
	/**
     * Mueve a la izquierda la plataforma de un jugador del arkapoob
	 * @param player El numero del jugador en el arkapoob.
	 * @param i La cantidad de pixeles a mover.
    */
	public void moveLeftPlayerPlataform(int player,int i){
			boolean choque = false;
			if(jugadores.size()>1 && jugadores.get(0).estaVivo() && jugadores.get(1).estaVivo()) {
				if(jugadores.get(player).getPlataforma().isMovingLeft()) { choque = choquePlataformaAlaIzquierda(player,i);}
				else {choque = choquePlataformaAlaDerecha(player,i);}
			}
			if(choque == false) {
				Jugador jugador = getJugador(player);
				jugador.muevaPlataformaALaIzquierda(i,bola);
				if(cpu) {
					jugadores.get(1).move(this);
				}
			}
	}
	/**
     * Copia el ultimo movimiento del primer jugador.
    */
	public void copie() {
		Plataforma plataforma = jugadores.get(0).getPlataforma();
		if(plataforma.isMovingLeft()) {
			jugadores.get(1).muevaPlataformaALaIzquierda(plataforma.getSpeed(), bola);
		}
		else if (plataforma.isMovingRight()) {
			jugadores.get(1).muevaPlataformaALaDerecha(plataforma.getSpeed(), bola);
		}
	}
	/**
     * Determina si la plataforma de un jugador se choco con otra por el lado derecho.
	 * @param player El numero del jugador en el arkapoob.
	 * @param i La cantidad de pixeles a mover.
	 * @return El valor booleano que determina si la plataforma se choco con otra por el lado derecho.
    */
	private boolean choquePlataformaAlaDerecha(int player , int i) {
		if(jugadores.size()>1){
			int other = 0;
			if(player==0) {other = 1;}
			Plataforma p1 = jugadores.get(player).getPlataforma();
			Plataforma p2 = jugadores.get(other).getPlataforma();
			return p1.chocaALaDerechaCon(p2,i);
		}
		return false;
	}
	/**
     * Determina si la plataforma de un jugador se choco con otra por el lado izquierdo.
	 * @param player El numero del jugador en el arkapoob.
	 * @param i La cantidad de pixeles a mover.
	 * @return El valor booleano que determina si la plataforma se choco con otra por el lado izquierdo.
    */
	private boolean choquePlataformaAlaIzquierda(int player , int i) {
		if(jugadores.size()>1){
			i=-i;
			int other = 0;
			if(player==0) {other = 1;}
			Plataforma p1 = jugadores.get(player).getPlataforma();
			Plataforma p2 = jugadores.get(other).getPlataforma();
			return p1.chocaALaIzquierdaCon(p2,i);
		}
		return false;
	}
	/**
     * Muestra la cantidad de jugadores del juego arkanoid.
	 * @return La longitud de la lista de jugadores del juego.
    */
	public int getPlayersAmount(){
		return jugadores.size();
	}
	/**
     * Muestra los jugadores del juego arkanoid.
	 * @return Los jugadores del juego.
    */
	public ArrayList<Jugador> getPlayers(){
		return jugadores;
	}
	/**
     * Cambia un bloque del juego arkanoid.
	 * @param index El lugar del bloque dentro de la lista de bloques.
	 * @param newBloque El nuevo bloque que va a cambiar.
    */
	public void changesBlock(int index , Bloque newBloque){
		bloques.set(index,newBloque);
	}
	/**
     * Conoce el indice de un bloque dentro de la lista de bloques (retorna -1 en caso de que no lo encuentre).
	 * @param bloque El bloque a buscar.
	 * @return El indice del bloque en la lsita de bloques.
    */
	public int indexOf(Bloque bloque){
		int index = -1;
		for(int i=0;i<bloques.size();i++){
			Bloque brick = bloques.get(i); 
			if(brick.getX()==bloque.getX() && brick.getY()==bloque.getY()){
				index = i;
				break;
			}
		}
		return index;
	}
	/**
     * Le permite a la bola moverse en el juego arkanoid.
    */
	public void puedeMoverBola() {
		bola.puedeMoverse(true);
	}
	/**
     * Adiciona un bloque a la lista de bloques.
	 * @param bloque El nuevo bloque que se va a adicionar.
    */
	public void addBloque(Bloque bloque){
		bloques.add(bloque);
	}
	/**
     * Adiciona una sorpresa a la lista de sorpresas.
	 * @param sorpresa La nueva sorpesa que se va a adicionar.
    */
	public void addSorpresa(Sorpresa sorpresa){
		sorpresas.add(sorpresa);
	}
	/**
     * Adiciona una nueva cpu en el juego arkanoid.
	 * @param nombre El nombre de la nueva cpu.
	 * @param tipo El tipo de cpu que se va a incorporar (Mimo,Destructor,Curioso).
    */
	public void addCPU(String nombre , String tipo) {
		Jugador jugador = null;
		if(tipo.equals("Mimo")) {
			jugador = new Mimo(nombre);
			cpuType = tipo;
		}
		else if(tipo.equals("Destructor")) {
			jugador = new Destructor(nombre);
			cpuType = tipo;
		}
		else if(tipo.equals("Curioso")) {
			jugador = new Curioso(nombre);
			cpuType = tipo;
		}
		jugadores.add(jugador);
	}
	/**
     * Adiciona una nueva cpu en el juego arkanoid.
	 * @param nombre El nombre de la nueva cpu.
	 * @param tipo El tipo de cpu que se va a incorporar (Mimo,Destructor,Curioso).
	 * @param score El puntaje de la nueva cpu.
	 * @param lives Las vidas de la nueva cpu.
    */
	public void addCPU(String nombre , String tipo, int score, int lives) {
		Jugador jugador = null;
		if(tipo.equals("Mimo")) {
			jugador = new Mimo(nombre,score,lives);
			cpuType = tipo;
		}
		else if(tipo.equals("Destructor")) {
			jugador = new Destructor(nombre,score,lives);
			cpuType = tipo;
		}
		else if(tipo.equals("Curioso")) {
			jugador = new Curioso(nombre,score,lives);
			cpuType = tipo;
		}
		jugadores.add(jugador);
	}
	/**
     * Muestra la cpu dentro del juego arkanoid.
	 * @return El tipo de cpu.
    */
	public String getCpuType(){
		return cpuType;
	}
}