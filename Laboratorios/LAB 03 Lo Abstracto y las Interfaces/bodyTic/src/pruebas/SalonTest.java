package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import aplicacion .*;
public class SalonTest{
    @Test
    public void DeberiaDarmeBien(){
        Salon salon = Salon.demeSalon();
        Deportista deportista = new Deportista(salon,"juan",100,100);
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        Deportista deportista2 = new Deportista(salon,"juan",100,100);
        Bola suroeste = new Bola(salon,0,-25,50);
        Bola noreste = new Bola(salon,540,500,50);
        assertFalse((salon.deme(4)).equals(suroeste));
    }
    @Test
    public void DeberiaNoDarmeBien(){
        Salon salon = Salon.demeSalon();
        Deportista deportista = new Deportista(salon,"juan",100,100);
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        Deportista deportista2 = new Deportista(salon,"juan",100,100);
        Bola suroeste = new Bola(salon,0,-25,50);
        Bola noreste = new Bola(salon,540,500,50);
        assertFalse(salon.deme(3).equals(deportista1));
    }
    @Test
    public void DeberiaAdicionarBien(){
        Salon salon = Salon.demeSalon();
        Deportista deportista = new Deportista(salon,"juan",100,100);
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        Deportista deportista2 = new Deportista(salon,"juan",100,100);
        Bola suroeste = new Bola(salon,0,-25,50);
        Bola noreste = new Bola(salon,540,500,50);
        assertTrue(5==salon.numeroEnSalon());
    }
    @Test
    public void NoDeberiaAdicionarBien(){
        Salon salon = Salon.demeSalon();
        Deportista deportista = new Deportista(salon,"juan",100,100);
        Deportista deportista1 = new Deportista(salon,"juan",100,100);
        Deportista deportista2 = new Deportista(salon,"juan",100,100);
        Bola suroeste = new Bola(salon,0,-25,50);
        Bola noreste = new Bola(salon,540,500,50);
        assertFalse(4==salon.numeroEnSalon());
    }
}