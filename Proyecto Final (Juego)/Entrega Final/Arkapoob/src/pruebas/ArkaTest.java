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
    	Plataforma plataforma = new Plataforma(500,100);
    	int xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseIzquierda(-10,new Bola(100,100));
    	assertTrue(plataforma.getFigura().getX()==xi-10);
    	xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseIzquierda(-10,new Bola(100,100));
    	assertTrue(plataforma.getFigura().getX()==xi-10);
    	
        xi = (int) plataforma.getFigura().getX();
        plataforma.muevaseDerecha(10,new Bola(100,100));
    	assertTrue(plataforma.getFigura().getX()==xi+10);
    	xi = (int) plataforma.getFigura().getX();
    	plataforma.muevaseDerecha(10,new Bola(100,100));
    	assertTrue(plataforma.getFigura().getX()==xi+10);
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
    		Nivel nivel = new Nivel(4);
    		String[][] configuracion = nivel.getNivel();
    	}catch(ArkanoidException e) {
    		assertTrue(e.getMessage().equals(ArkanoidException.FILE_ERROR));
    	}
    }
    @Test
    public void deberiaAñadirBienElNivel() {
    	Arkanoid game = Arkanoid.deme(false,false);
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
		try{
			bloque.colision();
			bloque.colision();
			bloque.colision();
			assertTrue(!bloque.esDestruido());
		}
    	catch (ArkanoidException e){
			assertTrue(bloque.esDestruido());
		}
    }
    @Test
    public void bloqueAmarilloDeberiaAumentarVida() {
    	Arkanoid.nuevoArkanoid(true, true);
		Arkanoid arka = Arkanoid.deme(true,false);
    	Bloque bloque = new BloqueAmarillo(100,100,100,100);
    	Jugador j = new Jugador("Jorge");
		try {
			arka.addJugador(j);
		} catch (ArkanoidException e) {
			fail("Lanzó excepcion");
		}
    	bloque.reaccione(arka);
    	assertTrue(j.getLives()==4);
    }
    @Test
    public void bloqueAzulDeberiaLanzarSorpresa() {
		
		Arkanoid game = Arkanoid.deme(true,false);
    	Bloque bloque = new BloqueAzul(100,100,100,100);
    	bloque.reaccione(game);
    	assertTrue(bloque.getSorpresa()!=null);
		
    }
    @Test
    public void bloqueRosadoDeberiaCambiarDeNivel(){
		
    	Bloque bloque = new BloqueRosado(100,100,100,100);
    	Arkanoid game = Arkanoid.deme(true,false);
    	try {
			game.addJugador("Nicolas");
			game.addBloque(bloque);
			bloque.reaccione(game);
			assertTrue(game.getLevel()==1);
		} catch (ArkanoidException e) {
			fail("Lanzó excepcion");
		}
    }
    @Test
    public void bloqueVerdeDeberiaTenerDosVidas() {
    	Bloque bloque = new BloqueVerde(100,100,100,100);
    	assertTrue(bloque.esDestruido()==false);
		try{
			bloque.colision();
			assertFalse(bloque.esDestruido());
			bloque.colision();
			assertTrue(!bloque.esDestruido());
		}
    	catch (ArkanoidException e){
			assertTrue(bloque.esDestruido());
		}
    }
    @Test
    public void plataformaEspecialDeberiaMoverseEnDireccionContraria(){
    	Plataforma pe = new PlataformaEspecial(100,100);
    	int xi = pe.getX();
    	pe.muevaseDerecha(9,new Bola(10,10));
    	assertTrue(pe.getX()+9==xi);
    	pe.muevaseIzquierda(-9,new Bola(10,10));
    	assertTrue(pe.getX()==xi);
    }
    @Test
    public void sorpresaBolaDeberiaCambiarVelocidad(){
    	Bola bola = new Bola(100,100);
    	bola.increaseSpeed();
    	int xi = bola.getX();
    	int yi = bola.getY();
    	Sorpresa sorpresa = new SorpresaBola(100,100);
    	sorpresa.reaccione(bola, new Jugador("Nicolas"));
    	bola.move();
    	assertTrue(bola.getX()==xi-3 || bola.getX()==xi-1);
    	assertTrue(bola.getY()==yi-3 || bola.getY()==yi-1);
    }
    @Test
    public void mimoDeberiaCopiarMovimientos(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Jugador m = new Mimo("Juan");
    	int xi1 = m.getPlataforma().getX();
    	Jugador p = new Jugador("Felipe");
    	p.setPlataforma(new Plataforma(200,200));
    	int xi2 = p.getPlataforma().getX();
    	try {
			juego.addJugador(p);
			juego.addJugador(m);
		} catch (ArkanoidException e) {
			fail("Lanzó excepcion");
		}
    	juego.moveRightPlayerPlataform(0, 10);
    	assertTrue(m.getPlataforma().getX()==xi1+10);
    	assertTrue(p.getPlataforma().getX()==xi2+10);
    }
    @Test
    public void destructorDeberiaSeguirBola(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Bola bola = new Bola(300,300);
    	Jugador d = new Destructor("Juan");
    	juego.setBola(bola);
    	int dx = Math.abs(d.getPlataforma().getX()-bola.getX());
    	try {
			juego.addJugador(d);
			juego.juegue();
	    	assertTrue(dx> Math.abs(d.getPlataforma().getX()-bola.getX()));
	    	juego.juegue();
	    	assertTrue(dx> Math.abs(d.getPlataforma().getX()-bola.getX()));
	    	juego.juegue();
	    	assertTrue(dx> Math.abs(d.getPlataforma().getX()-bola.getX()));
		} catch (ArkanoidException e) {
			fail("Lanzó excepcion");
		}
    }
    @Test
    public void curiosoDeberiaSeguirPoder(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Bola bola = new Bola(300,300);
    	Sorpresa sorpresa = new SorpresaBola(600,300);
    	Jugador c = new Curioso("Juan");
    	juego.setBola(bola);
    	juego.addSorpresa(sorpresa);
    	int dx = Math.abs(c.getPlataforma().getX()-sorpresa.getX());
    	try {
			juego.addJugador(c);
			juego.juegue();
	    	assertTrue(dx> Math.abs(c.getPlataforma().getX()-bola.getX()));
	    	juego.juegue();
	    	assertTrue(dx> Math.abs(c.getPlataforma().getX()-bola.getX()));
	    	juego.juegue();
	    	assertTrue(dx> Math.abs(c.getPlataforma().getX()-bola.getX()));
		} catch (ArkanoidException e) {
			fail("Lanzó excepcion");
		}
    }
    @Test
    public void deberiaLanzarExcepcionSiElJugadorYaExiste(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Jugador c = new Curioso("Juan");
    	try {
			juego.addJugador(c);
			juego.addJugador(c);
		} catch (ArkanoidException e) {
			assertTrue(e.getMessage().equals(ArkanoidException.PLAYER_ALREADY_ERROR));
		}
    }
    @Test
    public void deberiaLanzarExcepcionSiElJugadorNoTieneNombre(){
    	Arkanoid.nuevoArkanoid(true, true);
    	Arkanoid juego = Arkanoid.deme(true,true);
    	Jugador j = new Jugador("");
    	try {
    		juego.addJugador(j);
		} catch (ArkanoidException e) {
			assertTrue(e.getMessage().equals(ArkanoidException.PLAYER_NAME_ERROR));
		}
    }
}
