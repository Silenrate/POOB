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
     * Abre un nuevo arkapoob desde un archivo con extension (.arka)
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
     * Guarda el arkapoob desde un archivo con extension (.arka)
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
			System.out.println(e.getMessage());
			System.out.println(e.getClass().getName());
			throw new ArkanoidException("Ocurrio un error al salvar " + file.getName());
		}
	}
	public static void exportar(Arkanoid game,File file) throws ArkanoidException{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new ArkanoidException(ArkanoidException.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new ArkanoidException("No se encuentra el archivo " + file.getName());
		}
		out.println("h");
		out.close();
	}
	/*
	
	public Salon importe03(File file) throws bodyTICExcepcion{
		int i = 1;
		Salon salon = null;
		if (!file.getName().endsWith(".txt")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_TXT_ERROR);
		try {
			Salon.nuevoSalon();
			salon = Salon.demeSalon();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			if(line==null) {
				throw new bodyTICExcepcion(bodyTICExcepcion.NO_TEXT_FOUND);
			}
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(" ");
				if(linea[0].equals("Deportista")){
					checkLength(4,linea);
					try {
						new Deportista(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else if(linea[0].equals("DeportistaAvanzado")){
					checkLength(4,linea);
					try {
						new DeportistaAvanzado(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else if(linea[0].equals("DeportistaHablador")){
					checkLength(4,linea);
					try {
						new DeportistaHablador(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					}
				}
				else if(linea[0].equals("DeportistaVigorexo")){
					checkLength(6,linea);
					try {
						new DeportistaVigorexo(salon,StringToColor(linea[1]),Integer.parseInt(linea[2]),linea[3],Integer.parseInt(linea[4]),Integer.parseInt(linea[5]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					} catch(bodyTICExcepcion e) {
						if(e.getMessage().equals(bodyTICExcepcion.COLOR_ERROR)) {
							throw new bodyTICExcepcion("Error : linea "+i+" El formato del color es incorrecto (java.awt.Color[r=#,g=#,b=#])");
						}
					}
				}
				else if(linea[0].equals("Bola")){
					checkLength(5,linea);
					try {
						new Bola(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					}  catch(bodyTICExcepcion e) {
						if(e.getMessage().equals(bodyTICExcepcion.COLOR_ERROR)) {
							throw new bodyTICExcepcion("Error : linea "+i+" El formato del color es incorrecto (java.awt.Color[r=#,g=#,b=#])");
						}
					}
					
				}
				else if(linea[0].equals("Pesa")){
					checkLength(5,linea);
					try {
						Class c = Class.forName("aplicacion,"+linea[0]);
						Object o = c.getDeclaredConstructor(Salon.class , Color.class , int.class , int.class , int.class ).newInstance(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						
					} catch(bodyTICExcepcion e) {
						if(e.getMessage().equals(bodyTICExcepcion.COLOR_ERROR)) {
							throw new bodyTICExcepcion("Error : linea "+i+" El formato del color es incorrecto (java.awt.Color[r=#,g=#,b=#])");
						}
					}
				}
				else {
					throw new bodyTICExcepcion("Error : linea "+i+" La linea "+linea[0]+" no es una clase definida");
				}
				line = in.readLine();
				i+=1;
			}
			in.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
			throw new bodyTICExcepcion("Ocurrio un error al importar " + file.getName());
		}
		return salon;
	}
	
	private void checkLength(int size , String[] line) throws bodyTICExcepcion{
		if(line.length!=size) {
			throw new bodyTICExcepcion(bodyTICExcepcion.SIZE_ERROR);
		}
	}
	private Color StringToColor(String cadena) throws bodyTICExcepcion{
		int r; int g; int b;
		try {
			String[] rgb = cadena.substring(15,cadena.length()-1).split(",");
			r = Integer.parseInt(rgb[0].substring(2,rgb[0].length()));
			g = Integer.parseInt(rgb[1].substring(2,rgb[1].length()));
			b = Integer.parseInt(rgb[2].substring(2,rgb[2].length()));
		}
		catch(Exception e) {
			throw new bodyTICExcepcion(bodyTICExcepcion.COLOR_ERROR);
		}
		return new Color(r,g,b);
	}
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


