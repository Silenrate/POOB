package aplicacion;

import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Color;

public class Nivel {
	/**
	 * Clase que ejecuta un nivel del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 25/04/2019
	*/ 
	private static final String [] levels = {"levels/level1.txt","levels/level2.txt","levels/level3.txt","levels/level4.txt","levels/level5.txt","",""};
	//private static final String [] levels = {"src/aplicacion/level1 - Prueba.txt","src/aplicacion/level2 - Prueba.txt","levels/level3.txt","levels/level4.txt","levels/level5.txt"};
	//El atributo comentado se usa para pruebas
	private String [][] configuracion;
	private int level;
	/**
     * Constructor para la clase nivel
	 * @param level El numero del nivel.
    */
	public Nivel(int level) {
		this.level = level;
	}
	/**
     * Obtiene el nivel de un formato .txt
	 * @return La matriz de los bloques del nivel
	 * @throws ArkanoidException - FILE_ERROR Si no se encuentra el archivo con el nivel.
    */
	public String[][] getNivel() throws ArkanoidException{
		int f = 0 , c = 0;
		try{
			ArrayList<String> temporal = new ArrayList<>();
			BufferedReader in = new BufferedReader(new FileReader(levels[level]));
			String linea = in.readLine();
			f=0;
			while(linea!=null) {
				StringTokenizer colores = new StringTokenizer(linea);
				c=0;
				while(colores.hasMoreTokens()) {
					temporal.add(colores.nextToken());
					c+=1;
				}
				f+=1;
				linea = in.readLine();
			}
			configuracion = new String[f][c];
			int cont = 0;
			for(int i=0 ; i<f ; i++ ) {
				for(int j=0 ; j<c ; j++) {
					configuracion[i][j] = temporal.get(cont);
					cont++;
				}
			}
		}
		catch(IOException e){
			throw new ArkanoidException(ArkanoidException.FILE_ERROR);
		}
		return configuracion;
	}
}