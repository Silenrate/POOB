

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SuperCalculadoraTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SuperCalculadoraTest
{
    private SuperCalculadora calculadora;
    private Velocidad v0,v1,v2,v3,v4;
    public static final double MAXERROR = 0.00000000000001;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        this.calculadora = new SuperCalculadora();
        v0=new Velocidad (0, new Angulo(0,1));
        v1=new Velocidad (50 , new Angulo(90,1));
        v2=new Velocidad (100, new Angulo(3600,1));
        v3=new Velocidad (90 , new Angulo(400,2));
        v4=new Velocidad (-90 , new Angulo(400,2));
    }
    @Test
    public void deberiaAgregar() {
        calculadora.adicione(0,0);
        assertEquals(v0,calculadora.velocidad());
        calculadora.adicione(-50,-90);
        assertEquals(v1,calculadora.velocidad());
        calculadora.adicione(100,360);
        assertEquals(v2,calculadora.velocidad());
        calculadora.adicione(90,360);
        assertEquals(v3,calculadora.velocidad());
        calculadora.adicione(-50,-90);
        calculadora.adicione(0,0);
        assertEquals(v0,calculadora.velocidad());
    }
    @Test
    public void deberiaEliminar(){
        calculadora.adicione(0,0);
        calculadora.adicione(-50,-90);
        calculadora.elimine();
        assertEquals(v0,calculadora.velocidad());
        calculadora.elimine();
        assertEquals(null,calculadora.velocidad());
    }
    @Test
    public void noDeberiaEliminar(){
        calculadora.elimine();
        assertEquals(null,calculadora.velocidad());
        calculadora.elimine();
        assertEquals(null,calculadora.velocidad());
        calculadora.elimine();
        assertEquals(null,calculadora.velocidad());
    }
    @Test
    public void deberiaDuplicar(){
        calculadora.adicione(0,0);
        calculadora.duplique();
        assertEquals(v0,calculadora.velocidad());
        calculadora.elimine();
        assertEquals(v0,calculadora.velocidad());
        calculadora.adicione(0,0);
        calculadora.adicione(90,3600);
        calculadora.duplique();
        assertEquals(v3,calculadora.velocidad());
        calculadora.elimine();
        assertEquals(v3,calculadora.velocidad());
    }
    @Test
    public void noDeberiaDuplicar(){
        calculadora.duplique();
        calculadora.duplique();
        calculadora.duplique();
        assertEquals(null,calculadora.velocidad());
        calculadora.adicione(0,0);
        calculadora.duplique();
        calculadora.elimine();
        calculadora.elimine();
        calculadora.duplique();
        assertEquals(null,calculadora.velocidad());
    }
     @Test
    public void deberiaOperar(){
        calculadora.adicione(90,50);
        calculadora.adicione(-90,50);
        calculadora.calcule('+');
        assertEquals(v0,calculadora.velocidad());
        calculadora.adicione(0,0);
        calculadora.adicione(-90,180);
        calculadora.calcule('+');
        assertEquals(new Velocidad(90,new Angulo(0,1)) , calculadora.velocidad());
        calculadora.adicione(-90,50);
        calculadora.adicione(90,50);
        calculadora.calcule('-');
        assertEquals(new Velocidad(180,new Angulo(50,1)),calculadora.velocidad());
        calculadora.adicione(90,50);
        calculadora.adicione(90,50);
        calculadora.calcule('-');
        assertEquals(v0,calculadora.velocidad());
        calculadora.adicione(0,52094);
        calculadora.adicione(-20,50);
        calculadora.calcule('x');
        assertEquals(v0,calculadora.velocidad());
        calculadora.adicione(500,-330);
        calculadora.calcule('v');
        assertEquals(250,calculadora.velocidad().componenteV(),MAXERROR);
        calculadora.adicione(-1000,60);
        calculadora.calcule('h');
        assertEquals(-500,calculadora.velocidad().longitud(),MAXERROR);
    }
    @Test
    public void noDeberiaOperar(){
        calculadora.adicione(90,50);
        calculadora.calcule('+');
        assertFalse(calculadora.ok());
        calculadora.calcule('+');
        assertFalse(calculadora.ok());
        calculadora.elimine();
        calculadora.calcule('-');
        calculadora.calcule('*');
        assertFalse(calculadora.ok());
        calculadora.adicione(90,50);
        calculadora.adicione(90,50);
        calculadora.calcule('+');
        calculadora.calcule('*');
        assertFalse(calculadora.ok());
        calculadora.elimine();
        calculadora.calcule('h');
        assertFalse(calculadora.ok());
    }
    @Test
    public void deberiaCalcularVelocidadTiempo(){
        calculadora.adicione(0,0);
        calculadora.calcule('t',0);
        assertEquals(v0,calculadora.velocidad());
        calculadora.adicione(0,0);
        calculadora.calcule('t',60);
        assertEquals(new Velocidad(588,new Angulo(90,1)),calculadora.velocidad());
        calculadora.adicione(90,90);
        calculadora.calcule('t',60);
        assertEquals(new Velocidad(678,new Angulo(90,1)),calculadora.velocidad());
        calculadora.adicione(90,180);
        calculadora.calcule('t',60);
        double magnitud = Math.sqrt(Math.pow(588,2)+Math.pow(-90,2));
        magnitud=Math.round(magnitud * Math.pow(10, 4)) / Math.pow(10, 4);
        assertEquals(new Velocidad(magnitud,new Angulo(278.7022,1)),calculadora.velocidad());
    }
    @Test
    public void noDeberiaCalcularVelocidadTiempo(){
        calculadora.adicione(90,60);
        calculadora.elimine();
        calculadora.calcule('t',-34);
        assertFalse(calculadora.ok());
        calculadora.calcule('+',78);
        assertFalse(calculadora.ok());
        calculadora.calcule('-',35);
        assertFalse(calculadora.ok());
        calculadora.calcule('x',54);
        assertFalse(calculadora.ok());
        calculadora.calcule('h',90);
        calculadora.calcule('t',-89);
        assertFalse(calculadora.ok());
        calculadora.calcule('v',0);
        assertFalse(calculadora.ok());
    }
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
