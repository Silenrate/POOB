package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import aplicacion .*;
public class  DeportistaTest  {
    @Test
    public void deberianPoderMoverBien(){
        Salon salon = Salon.demeSalon();
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        //assertTrue(deportista1.puedeMover('E'));
        Deportista deportista2 = new Deportista(salon,"juan",200,100);
        //assertTrue(deportista2.puedeMover('N'));
        Deportista deportista3 = new Deportista(salon,"juan",300,100);
        //assertTrue(deportista3.puedeMover('S'));
        Deportista deportista4 = new Deportista(salon,"juan",100,350);
        //assertTrue(deportista4.puedeMover('O'));
    }
    @Test
    public void noDeberiaPoderMoverBien(){
        Salon salon = Salon.demeSalon();
        Deportista deportista1 = new Deportista(salon,"juan",10000,1000);
        //assertFalse(deportista1.puedeMover('E'));
        Deportista deportista2 = new Deportista(salon,"juan",300,700);
        //assertFalse(deportista2.puedeMover('N'));
        Deportista deportista3 = new Deportista(salon,"juan",10,10);
        //assertFalse(deportista3.puedeMover('S'));
        Deportista deportista4 = new Deportista(salon,"juan",0,650);
        //assertFalse(deportista4.puedeMover('O'));
    }
    @Test
    public void deberiaIniciar(){
        Salon salon = Salon.demeSalon();
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        deportista1.inicie();
        assertTrue(deportista1.getPosicionX()==120 || deportista1.getPosicionX()==80);
        Deportista deportista2 = new Deportista(salon,"juan",200,100);
        deportista2.inicie();
        assertTrue(deportista2.getPosicionX()==220 || deportista2.getPosicionX()==180);
        Deportista deportista3 = new Deportista(salon,"juan",300,100);
        deportista3.inicie();
        assertTrue(deportista3.getPosicionX()==320 || deportista3.getPosicionX()==280);
        Deportista deportista4 = new Deportista(salon,"juan",400,1000);
        deportista4.inicie();
        assertTrue(deportista4.getPosicionX()==420 || deportista4.getPosicionX()==380);
    }
    @Test
    public void deberiaParar(){
        Salon salon = Salon.demeSalon();
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        deportista1.pare();
        assertTrue(deportista1.mensaje().equals("¡Uff!"));
        Deportista deportista2 = new Deportista(salon,"juan",200,100);
        deportista2.pare();
        assertTrue(deportista2.mensaje().equals("¡Uff!"));
        Deportista deportista3 = new Deportista(salon,"juan",300,100);
        deportista3.pare();
        assertTrue(deportista3.mensaje().equals("¡Uff!"));
        Deportista deportista4 = new Deportista(salon,"juan",400,1000);
        deportista4.pare();
        assertTrue(deportista4.mensaje().equals("¡Uff!"));
    }
}