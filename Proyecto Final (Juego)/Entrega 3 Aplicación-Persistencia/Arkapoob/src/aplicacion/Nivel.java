package aplicacion;

import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Color;
import java.util.Random;

public class Nivel implements Serializable{
	/**
	 * Clase que ejecuta un nivel del juego arkapoob
	 * @author: Nicolas Aguilera y Daniel Walteros
	 * @version: 12/05/2019
	*/ 
	//private static final String [] levels = {"levels/level1.txt","levels/level2.txt","levels/level3.txt","levels/level4.txt","levels/level5.txt","",""};
	private static final String [] levels = {"src/aplicacion/level1 - Prueba.txt","src/aplicacion/level2 - Prueba.txt","levels/level3.txt","levels/level4.txt","levels/level5.txt"};
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
	/**
     * Genera el nivel de forma aleatoria distribuyendo los bloques por color de la siguiente forma:
	 * Rojo - Mas Comun 20%
	 * Verde - Comun 15%
	 * Gris - Mas Comun 20%
     * Rosado - Raro 5%
	 * Amarillo - Raro 5%
	 * Naranja - Comun 15%
	 * Negro - Poco Comun 10%
	 * Azul - Poco Comun 10%
	 * @param filas El numero de filas del nivel.
	 * @param columnas El numero de columnas del nivel.
	 * @return La matriz de los bloques del nivel
    */
	public String [][] getNivel(int filas,int columnas){
		configuracion = new String[filas][columnas];
		for(int i=0 ; i<filas ; i++ ) {
			for(int j=0 ; j<columnas ; j++) {
				Random random = new Random();
				int valor = random.nextInt(100)+1;  
				if(i==filas-1){
					//Aqui no puede haber grises ni rosados
					if(valor<10){configuracion[i][j] = "yellow";}
					else if(valor>9 && valor<24){configuracion[i][j] = "black";}
					else if(valor>23 && valor<38){configuracion[i][j] = "blue";}
					else if(valor>37 && valor<57){configuracion[i][j] = "orange";}
					else if(valor>56 && valor<76){configuracion[i][j] = "green";}
					else{configuracion[i][j] = "red";}
				}
				else{
					//Aqui pueden ir todos
					if(valor<6){configuracion[i][j] = "pink";}
					else if(valor>5 && valor<11){configuracion[i][j] = "yellow";}
					else if(valor>10 && valor<21){configuracion[i][j] = "black";}
					else if(valor>20 && valor<31){configuracion[i][j] = "blue";}
					else if(valor>30 && valor<46){configuracion[i][j] = "orange";}
					else if(valor>45 && valor<61){configuracion[i][j] = "green";}
					else if(valor>60 && valor<81){configuracion[i][j] = "red";}
					else{configuracion[i][j] = "gray";}
				}
			}
		}
		return configuracion;
	}
}