package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import aplicacion .*;
public class  DeportistaAvanzadoTest  {
    @Test
    public void deberianPoderMoverBien(){
        Salon salon = Salon.demeSalon();
        DeportistaAvanzado deportista1 = new DeportistaAvanzado(salon,"juan",100,100);
        //assertTrue(deportista1.puedeMover('E'));
        DeportistaAvanzado deportista2 = new DeportistaAvanzado(salon,"juan",200,100);
        //assertTrue(deportista2.puedeMover('N'));
        DeportistaAvanzado deportista3 = new DeportistaAvanzado(salon,"juan",300,100);
        //assertTrue(deportista3.puedeMover('S'));
        DeportistaAvanzado deportista4 = new DeportistaAvanzado(salon,"juan",100,350);
        //assertTrue(deportista4.puedeMover('O'));
    }
    @Test
    public void noDeberiaPoderMoverBien(){
        Salon salon = Salon.demeSalon();
        DeportistaAvanzado deportista1 = new DeportistaAvanzado(salon,"juan",10000,1000);
        //assertFalse(deportista1.puedeMover('E'));
        DeportistaAvanzado deportista2 = new DeportistaAvanzado(salon,"juan",300,700);
        //assertFalse(deportista2.puedeMover('N'));
        DeportistaAvanzado deportista3 = new DeportistaAvanzado(salon,"juan",10,10);
        //assertFalse(deportista3.puedeMover('S'));
        DeportistaAvanzado deportista4 = new DeportistaAvanzado(salon,"juan",0,650);
        //assertFalse(deportista4.puedeMover('O'));
    }
    @Test
    public void deberiaIniciar(){
        Salon salon = Salon.demeSalon();
        DeportistaAvanzado deportista1 = new DeportistaAvanzado(salon,"juan",100,100);
        deportista1.inicie();
        assertTrue(deportista1.getPosicionX()==140 || deportista1.getPosicionX()==60);
        DeportistaAvanzado deportista2 = new DeportistaAvanzado(salon,"juan",200,100);
        deportista2.inicie();
        assertTrue(deportista2.getPosicionX()==240 || deportista2.getPosicionX()==160);
        DeportistaAvanzado deportista3 = new DeportistaAvanzado(salon,"juan",300,100);
        deportista3.inicie();
        assertTrue(deportista3.getPosicionX()==260 || deportista3.getPosicionX()==340);
        DeportistaAvanzado deportista4 = new DeportistaAvanzado(salon,"juan",400,1000);
        deportista4.inicie();
        assertTrue(deportista4.getPosicionX()==360 || deportista4.getPosicionX()==440);
    }
}