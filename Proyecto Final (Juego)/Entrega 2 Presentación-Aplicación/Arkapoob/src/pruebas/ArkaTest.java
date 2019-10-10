package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import aplicacion.*;

/**
 * The test class CityOfHeroesContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ArkaTest
{ 
    @Test
    public void deberiaMoverPlataforma(){
    	Plataforma plataforma = new Plataforma(100,100);
    	int xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseIzquierda(10, 0);
    	assertTrue(plataforma.getFigura().getX()==xi-10);
    	xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseIzquierda(10, 90);
    	assertTrue(plataforma.getFigura().getX()==xi);
    	
        xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseDerecha(10, 200);
    	assertTrue(plataforma.getFigura().getX()==xi+10);
    	xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseDerecha(10, 20);
    	assertTrue(plataforma.getFigura().getX()==xi);
    }
    
    @Test
    public void deberiaMoverBola() {
    	Bola bola = new Bola(100,100);
    	bola.goTo(300,400);
    	assertTrue(bola.getX()==300);
    	assertTrue(bola.getY()==400);
    }
    
    @Test
    public void deberiaPerderVida() {
    	Jugador jugador = new Jugador("Nicolas");
    	jugador.subtractLive();
    	assertTrue(jugador.getLives()==2);
    	jugador.subtractLive();
    	assertTrue(jugador.getLives()==1);
    	jugador.subtractLive();
    	assertTrue(jugador.getLives()==0);
    }
    
    @Test
    public void deberiaGanarPuntos() {
    	Jugador jugador = new Jugador("Nicolas");
    	jugador.addScore(100);
    	assertTrue(jugador.getScore()==100);
    	jugador.addScore(300);
    	assertTrue(jugador.getScore()==400);
    	jugador.addScore(100);
    	assertTrue(jugador.getScore()==500);
    }
    
    @Test
    public void deberiaLanzarExcepcionSiElNivelNoExiste() {
    	try {
    		Nivel nivel = new Nivel(6);
    		String[][] configuracion = nivel.getNivel();
    	}catch(ArkanoidException e) {
    		assertTrue(e.getMessage().equals(ArkanoidException.FILE_ERROR));
    	}
    }
    @Test
    public void deberiaAñadirBienElNivel() {
    	Arkanoid game = new Arkanoid();
    	try {
    		game.addNivel();
    	}catch(ArkanoidException e) {
    		fail("Lanzó excepcion");
    	}
    }
    
    @Test
    public void deberiaDestruirBloque() {
    	Bloque bloque = new BloqueRojo(100,100,100,100);
    	assertTrue(bloque.esDestruido()==false);
    	bloque.colision();
    	bloque.colision();
    	bloque.colision();
    	assertTrue(bloque.esDestruido());
    }
    
}