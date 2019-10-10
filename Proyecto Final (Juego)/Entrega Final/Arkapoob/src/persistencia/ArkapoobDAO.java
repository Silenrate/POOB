package persistencia;

import java.io.*;
import java.lang.reflect.*;
import aplicacion.*;
import java.awt.Color;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public abstract class ArkapoobDAO{
	/**
	 * Clase que ejecuta controla la relacion entre archivos y el arkapoob.
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/
	public static String nombre="Arkapoob";
	/**
     * Abre un nuevo arkapoob desde un archivo con extension (.dat)
	 * @param file El archivo donde se va a abrir el arkapoob.
     * @return El nuevo arkapoob del archivo.
	 * @throws ArkanoidException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .dat.
	 * @throws ArkanoidException Si ocurrio un error al deserializar el archivo.
    */
	public static Arkanoid open(File file) throws ArkanoidException{
		Arkanoid game = null;
		if (!file.getName().endsWith(".dat")) throw new ArkanoidException(ArkanoidException.TYPE_DAT_ERROR);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			game = (Arkanoid) in.readObject();
			in.close();
		}catch(ClassNotFoundException e) {
			throw new ArkanoidException(ArkanoidException.FILE_NOT_FOUND_ERROR);
		}catch (IOException e) {
			throw new ArkanoidException("Ocurrio un error al abrir el archivo" + file.getName());
		}
		return game;
	}
	/**
     * Guarda el arkapoob desde un archivo con extension (.dat)
	 * @param game El juego arkapoob que va a ser guardado.
	 * @param file El archivo donde se va a guardar el arkapoob.
	 * @throws ArkanoidException - TYPE_ARKA_ERROR Si el archivo no tiene la extension .dat.
	 * @throws ArkanoidException - FILE_NOT_FOUND_ERROR Si no se encontro el archivo para guardar el arkapoob.
	 * @throws ArkanoidException Si ocurrio un error al serializar el archivo.
    */
	public static void save(Arkanoid game, File file) throws ArkanoidException{
		if (!file.getName().endsWith(".dat")) throw new ArkanoidException(ArkanoidException.TYPE_DAT_ERROR);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(game);
			out.close();
		}catch (IOException e) {
			throw new ArkanoidException("Ocurrio un error al salvar " + file.getName());
		}
	}
	/**
     * Exporta el arkapoob desde un archivo con extension (.txt)
	 * @param game El juego arkapoob que va a ser guardado.
	 * @param file El archivo donde se va a exportar el arkapoob.
	 * @throws ArkanoidException - TYPE_TXT_ERROR Si el archivo no tiene la extension .txt.
	 * @throws ArkanoidException - Si no se encontro el archivo para exportar el arkapoob.
    */
	public static void exportar(Arkanoid game,File file) throws ArkanoidException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new ArkanoidException(ArkanoidException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new ArkanoidException("No se encuentra el archivo " + file.getName());
		}
		out.println(game.getNivel()+","+game.getRandom()+","+game.getCpu());
		out.println(game.getBola().getClass().getSimpleName()+","+game.getBola().toString());
		for(int i=0;i<game.getSorprisesAmount();i++){
			Sorpresa sorpresa = game.getSorprise(i);
			out.println(sorpresa.getClass().getSimpleName()+","+sorpresa.toString());
		}
		for(int i=0;i<game.getBlocksAmount();i++){
			Bloque bloque = game.getBloque(i);
			out.println(bloque.getClass().getSimpleName()+","+bloque.toString());
		}
		for(int i=0;i<game.getPlayersAmount();i++){
			Jugador jugador = game.getJugador(i);
			Plataforma plataforma = jugador.getPlataforma();
			out.println(jugador.getClass().getSimpleName()+","+jugador.toString());
			out.println(plataforma.getClass().getSimpleName()+","+plataforma.toString());
		}
		out.close();
	}	
	/**
     * Importa el arkapoob desde un archivo con extension (.txt)
	 * @param file El archivo donde se va a importar el arkapoob.
	 * @return El juego obtenido del archivo txt.
	 * @throws ArkanoidException - TYPE_TXT_ERROR Si el archivo no tiene la extension .txt.
	 * @throws ArkanoidException - NO_TEXT_FOUND Si el archivo esta vacio.
	 * @throws ArkanoidException - Si el archivo no cumple con los requerimientos.
	 * @throws ArkanoidException - Si el archivo tiene un error de escritura.
	 * @throws ArkanoidException - Si el archivo tiene una clase desconocida.
	 * @throws ArkanoidException - Si ocurrio un error al importar.
    */
	public static Arkanoid importar(File file) throws ArkanoidException{
		int i = 1;
		Arkanoid game = null;
		Jugador jugador = null;
		if (!file.getName().endsWith(".txt")) throw new ArkanoidException(ArkanoidException.TYPE_TXT_ERROR);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new ArkanoidException(ArkanoidException.NO_TEXT_FOUND);
			}
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(",");
				if(i==1){
					try{
						Arkanoid.nuevoArkanoid(Boolean.parseBoolean(linea[1]),Boolean.parseBoolean(linea[2]));
						game = game.deme(Boolean.parseBoolean(linea[1]),Boolean.parseBoolean(linea[2]));
						game.setNivel(Integer.parseInt(linea[0]));
					}catch(Exception e) {
						throw new ArkanoidException("No cumple los requerimientos de un archivo arkapoob");
					}
				}
				else{
					if(linea[0].startsWith("Bloque")){
						checkLength(6,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class , int.class , int.class , int.class ).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),Integer.parseInt(linea[5]));
							game.addBloque((Bloque)o);
						} catch(NumberFormatException e) {
							throw new ArkanoidException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].equals("Jugador")){
						checkLength(4,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(String.class , int.class , int.class).newInstance(linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
							jugador = (Jugador)o;
							game.addJugador(jugador);
						} catch(NumberFormatException e) {
							throw new ArkanoidException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].equals("Mimo")||linea[0].equals("Destructor")||linea[0].equals("Curioso")){
						checkLength(4,linea);
						try {
							game.addCPU(linea[1],game.getCpuType(),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
						} catch(NumberFormatException e) {
							throw new ArkanoidException("Error : linea "+i+" No es posible convertir a entero");
						}
					}
					else if(linea[0].startsWith("Plataforma")){
						checkLength(6,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class , int.class , int.class,String.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]),linea[5]);
							jugador.setPlataforma((Plataforma)o);
						} catch(NumberFormatException e) {
							throw new ArkanoidException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].startsWith("Sorpresa")){
						checkLength(3,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]));
							game.addSorpresa((Sorpresa)o);
						} catch(NumberFormatException e) {
							throw new ArkanoidException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}
					}
					else if(linea[0].equals("Bola")){
						checkLength(5,linea);
						try {
							Class c = Class.forName("aplicacion."+linea[0]);
							Object o = c.getDeclaredConstructor(int.class , int.class , int.class , int.class).newInstance(Integer.parseInt(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
							game.setBola((Bola)o);
						} catch(NumberFormatException e) {
							throw new ArkanoidException("Error : linea "+i+" No es posible convertir a entero");
						} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {}	
					}
					else {
						throw new ArkanoidException("Error : En la linea "+i+" la clase "+linea[0]+" no es una clase definida");
					}
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			throw new ArkanoidException("Ocurrio un error al importar " + file.getName());
		}
		return game;
	}
	/**
     * Verifica si la longitud de una linea es la correcta.
	 * @param size El tamaño correcto de la linea de texto.
	 * @param line La linea de texto.
	 * @throws ArkanoidException - SIZE_ERROR Si la longitud de una linea es incorrecta.
    */
	private static void checkLength(int size , String[] line) throws ArkanoidException{
		if(line.length!=size) {
			throw new ArkanoidException(ArkanoidException.SIZE_ERROR);
		}
	}
	/**
     * Escribe en el log de errores una excepcion.
	 * @param e La excepcion a escribir.
    */
    public static void registre(Exception e){
        try{
            Logger logger=Logger.getLogger(nombre);
            logger.setUseParentHandlers(false);
            FileHandler file=new FileHandler(nombre+".log",true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE,e.toString(),e);
            file.close();
        }catch (Exception oe){
            oe.printStackTrace();
            System.exit(0);
        }
    }
}


