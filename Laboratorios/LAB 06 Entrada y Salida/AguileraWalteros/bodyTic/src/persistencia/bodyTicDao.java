package persistencia;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import aplicacion.*;
import java.awt.Color;

public class bodyTicDao {
	
	public bodyTicDao() {
	}
	
	public Salon abra(File file) throws bodyTICExcepcion{
		Salon salon = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			salon = (Salon) in.readObject();
			in.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new bodyTICExcepcion("Ocurrio un error al abrir " + file.getName());
		}
		return salon;
	}
	
	public void salve(Salon salon, File file) throws bodyTICExcepcion{
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(salon);
			out.close();
		}catch (Exception e) {
			throw new bodyTICExcepcion("Ocurrio un error al salvar " + file.getName());
		}
	}
	
	public void exporte(Salon salon,File file) throws bodyTICExcepcion{
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (Exception e) {
			throw new bodyTICExcepcion("Ocurrio un error al exportar " + file.getName());
		}
		int size = salon.numeroEnSalon();
		for(int i=0;i<size;i++){
			String temp = salon.getElementos().get(i).toString();
			out.println(salon.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
	}
	
	public Salon importe(File file) throws bodyTICExcepcion{
		Salon salon = null;
		try {
			Salon.nuevoSalon();
			salon = Salon.demeSalon();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(" ");
				if(linea[0].equals("Deportista")){new Deportista(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));}
				else if(linea[0].equals("DeportistaAvanzado")){new DeportistaAvanzado(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));}
				else if(linea[0].equals("DeportistaHablador")){new DeportistaHablador(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));}
				else if(linea[0].equals("DeportistaVigorexo")){new DeportistaVigorexo(salon,StringToColor(linea[1]),Integer.parseInt(linea[2]),linea[3],Integer.parseInt(linea[4]),Integer.parseInt(linea[5]));}
				else if(linea[0].equals("Bola")){new Bola(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));}
				else if(linea[0].equals("Pesa")){new Pesa(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));}
				line = in.readLine();
			}
			in.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new bodyTICExcepcion("Ocurrio un error al abrir " + file.getName());
		}
		return salon;
	}
	
	public Salon abra01(File file) throws bodyTICExcepcion{
		Salon salon = null;
		if (!file.getName().endsWith(".dat")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_DAT_ERROR);
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			salon = (Salon) in.readObject();
			in.close();
		}catch(ClassNotFoundException e) {
			throw new bodyTICExcepcion(bodyTICExcepcion.FILE_NOT_FOUND_ERROR);
		}catch (IOException e) {
			throw new bodyTICExcepcion("Ocurrio un error al abrir el archivo" + file.getName());
		}
		return salon;
	}
	
	public void salve01(Salon salon, File file) throws bodyTICExcepcion{
		if (!file.getName().endsWith(".dat")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_DAT_ERROR);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(salon);
			out.close();
		}catch (IOException e) {
			throw new bodyTICExcepcion("Ocurrio un error al salvar " + file.getName());
		}
	}
	
	public void exporte01(Salon salon,File file) throws bodyTICExcepcion{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new bodyTICExcepcion("No se encuentra el archivo " + file.getName());
		}
		int size = salon.numeroEnSalon();
		for(int i=0;i<size;i++){
			String temp = salon.getElementos().get(i).toString();
			out.println(salon.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
	}
	public void exporte02(Salon salon,File file) throws bodyTICExcepcion{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new bodyTICExcepcion("No se encuentra el archivo " + file.getName());
		}
		int size = salon.numeroEnSalon();
		for(int i=0;i<size;i++){
			String temp = salon.getElementos().get(i).toString();
			out.println(salon.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
	}
	
	public void exporte03(Salon salon,File file) throws bodyTICExcepcion{
		PrintWriter out = null;
		if (!file.getName().endsWith(".txt")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_TXT_ERROR);
		try {
			out = new PrintWriter(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new bodyTICExcepcion("No se encuentra el archivo " + file.getName());
		}
		int size = salon.numeroEnSalon();
		for(int i=0;i<size;i++){
			String temp = salon.getElementos().get(i).toString();
			out.println(salon.getElementos().get(i).getClass().getSimpleName()+" "+temp);
		}
		out.close();
	}
	
	public Salon importe01(File file) throws bodyTICExcepcion{
		Salon salon = null;
		if (!file.getName().endsWith(".txt")) throw new bodyTICExcepcion(bodyTICExcepcion.TYPE_TXT_ERROR);
		try {
			Salon.nuevoSalon();
			salon = Salon.demeSalon();
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line!=null){
				line = line.trim();
				if(line.equals("")){continue;}
				String[] linea = line.split(" ");
				if(linea[0].equals("Deportista")){new Deportista(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));}
				else if(linea[0].equals("DeportistaAvanzado")){new DeportistaAvanzado(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));}
				else if(linea[0].equals("DeportistaHablador")){new DeportistaHablador(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));}
				else if(linea[0].equals("DeportistaVigorexo")){new DeportistaVigorexo(salon,StringToColor(linea[1]),Integer.parseInt(linea[2]),linea[3],Integer.parseInt(linea[4]),Integer.parseInt(linea[5]));}
				else if(linea[0].equals("Bola")){new Bola(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));}
				else if(linea[0].equals("Pesa")){new Pesa(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));}
				line = in.readLine();
			}
			in.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
			throw new bodyTICExcepcion("Ocurrio un error al importar " + file.getName());
		}
		return salon;
	}
	
	public Salon importe02(File file) throws bodyTICExcepcion{
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
						new Pesa(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					}  catch(bodyTICExcepcion e) {
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
				if(linea[0].equals("Deportista") || linea[0].equals("DeportistaAvanzado") || linea[0].equals("DeportistaHablador") ){
					checkLength(4,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						Object o = c.getDeclaredConstructor(Salon.class , String.class , int.class , int.class).newInstance(salon,linea[1],Integer.parseInt(linea[2]),Integer.parseInt(linea[3]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {	
						throw new bodyTICExcepcion("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					}
				}
				else if(linea[0].equals("DeportistaVigorexo")){
					checkLength(6,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						Object o = c.getDeclaredConstructor(Salon.class , Color.class , int.class , String.class,int.class,int.class).newInstance(salon,StringToColor(linea[1]),Integer.parseInt(linea[2]),linea[3],Integer.parseInt(linea[4]),Integer.parseInt(linea[5]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new bodyTICExcepcion("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} catch(bodyTICExcepcion e) {
						if(e.getMessage().equals(bodyTICExcepcion.COLOR_ERROR)) {
							throw new bodyTICExcepcion("Error : linea "+i+" El formato del color es incorrecto (java.awt.Color[r=#,g=#,b=#])");
						}
					}
				}
				else if(linea[0].equals("Pesa") || linea[0].equals("Bola") ){
					checkLength(5,linea);
					try {
						Class c = Class.forName("aplicacion."+linea[0]);
						Object o = c.getDeclaredConstructor(Salon.class , Color.class , int.class , int.class , int.class ).newInstance(salon ,StringToColor(linea[1]),Integer.parseInt(linea[2]),Integer.parseInt(linea[3]),Integer.parseInt(linea[4]));
					} catch(NumberFormatException e) {
						throw new bodyTICExcepcion("Error : linea "+i+" No es posible convertir a entero");
					} catch(ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
						throw new bodyTICExcepcion("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
					} catch(bodyTICExcepcion e) {
						if(e.getMessage().equals(bodyTICExcepcion.COLOR_ERROR)) {
							throw new bodyTICExcepcion("Error : linea "+i+" El formato del color es incorrecto (java.awt.Color[r=#,g=#,b=#])");
						}
					}
				}
				else {
					throw new bodyTICExcepcion("Error: linea "+i+" dato "+linea[0]+" no es un elemento");
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
}


