package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import aplicacion .*;
public class  DeportistaVigorexoTest  {
    @Test
    public void deberiaIniciar(){
        Salon salon = Salon.demeSalon();
        DeportistaVigorexo deportista1 = new DeportistaVigorexo(salon,"juan",100,100);
        deportista1.inicie();
        assertTrue(deportista1.getPosicionX()==160 || deportista1.getPosicionX()==40);
        DeportistaVigorexo deportista2 = new DeportistaVigorexo(salon,"juan",200,100);
        deportista2.inicie();
        assertTrue(deportista2.getPosicionX()==260 || deportista2.getPosicionX()==140);
        DeportistaVigorexo deportista3 = new DeportistaVigorexo(salon,"juan",300,100);
        deportista3.inicie();
        assertTrue(deportista3.getPosicionX()==360 || deportista3.getPosicionX()==240);
        DeportistaVigorexo deportista4 = new DeportistaVigorexo(salon,"juan",400,1000);
        deportista4.inicie();
        assertTrue(deportista4.getPosicionX()==340 || deportista4.getPosicionX()==460);
   }
   @Test
    public void deberiaParar(){
        Salon salon = Salon.demeSalon();
        DeportistaVigorexo deportista1 = new DeportistaVigorexo(salon,"juan",100,100);
        for(int i = 0 ; i<15 ; i++){deportista1.inicie();}
        assertTrue(deportista1.mensaje().equals("Ya me canse"));
        DeportistaVigorexo deportista2 = new DeportistaVigorexo(salon,"juan",100,100);
        for(int i = 0 ; i<13 ; i++){deportista2.inicie();}
        assertTrue(deportista2.mensaje().equals("Ya me canse"));
        DeportistaVigorexo deportista3 = new DeportistaVigorexo(salon,"juan",100,100);
        for(int i = 0 ; i<11 ; i++){deportista3.inicie();}
        assertTrue(deportista3.mensaje().equals("Ya me canse"));
   }
   @Test
    public void noDeberiaParar(){
        Salon salon = Salon.demeSalon();
        DeportistaVigorexo deportista1 = new DeportistaVigorexo(salon,"juan",100,100);
        deportista1.pare();
        assertTrue(deportista1.mensaje().equals("Aun no voy a parar"));
        
        DeportistaVigorexo deportista2 = new DeportistaVigorexo(salon,"juan",200,100);
        deportista2.pare();
        assertTrue(deportista2.mensaje().equals("Aun no voy a parar"));

        DeportistaVigorexo deportista3 = new DeportistaVigorexo(salon,"juan",300,100);
        deportista3.pare();
        assertTrue(deportista3.mensaje().equals("Aun no voy a parar"));

        DeportistaVigorexo deportista4 = new DeportistaVigorexo(salon,"juan",400,1000);
        deportista4.pare();
        assertTrue(deportista4.mensaje().equals("Aun no voy a parar"));

   }
}