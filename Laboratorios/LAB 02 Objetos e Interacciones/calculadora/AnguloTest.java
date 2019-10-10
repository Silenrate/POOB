import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AnguloTest.
 *
 * @author  Juan Albornoz - ECI
 * @version 2012-1
 */
public class AnguloTest
{
    //Variables para hacer las pruebas: el "test fixture"
    private Angulo a0, a30, a45, a60, a90, a180, a360, a720, am90, aPI, gon100, gon400;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        // este metodo se invoca antes de ejecutar cada método de prueba (anotados con @Test)
        // aqui se inician los datos del "test fixture"
        a0 = new Angulo (0, Angulo.GRADOS);
        a30 = new Angulo (30, Angulo.GRADOS);
        a45 = new Angulo (45, Angulo.GRADOS);
        a60 = new Angulo (60, Angulo.GRADOS);
        a90 = new Angulo (90, Angulo.GRADOS);
        a180 = new Angulo (180, Angulo.GRADOS);
        a360 = new Angulo (360, Angulo.GRADOS);
        a720 = new Angulo (720, Angulo.GRADOS);
        am90 = new Angulo (-90, Angulo.GRADOS);
        aPI = new Angulo (Math.PI, Angulo.RADIANES);
        gon100 = new Angulo (100 , Angulo.GRADIANES);
        gon400 = new Angulo (400 , Angulo.GRADIANES);
    }


    @Test
    public void deberiaCrearBienLosAngulo() {
        assertTrue (a0.equals(a0));
        assertTrue (a0.equals(new Angulo(0,Angulo.GRADOS)));
        assertTrue (a0.equals(a360));
        System.out.println(a0.grados());
        System.out.println(a360.grados());
        assertTrue (a360.equals(a0));
        assertTrue (a0.equals(a720));
        assertTrue (a720.equals(a0));
        assertTrue (aPI.equals(a180));
        assertTrue (a180.equals(aPI));
        assertTrue (gon100.equals(a90));
        assertTrue (a90.equals(gon100));
        assertTrue (gon400.equals(a0));
        assertTrue (a0.equals(gon400));
        assertFalse (a0.equals(a45));
        assertFalse (a0.equals(new Angulo(0.01,Angulo.GRADOS)));
    }

    
    @Test
    public void deberiaDarElValorEnGrados() {
        assertEquals(0.0, a0.grados(),Angulo.MAXERROR);
        assertEquals(90, a90.grados(),Angulo.MAXERROR);
        assertEquals(270, am90.grados(),Angulo.MAXERROR);
        assertEquals(0.0, a360.grados(),Angulo.MAXERROR);
        assertEquals(0.0, a720.grados(),Angulo.MAXERROR);
        assertEquals(45, a45.grados(),Angulo.MAXERROR);
        assertEquals(180, aPI.grados(),Angulo.MAXERROR);
        assertEquals(90, gon100.grados(),Angulo.MAXERROR);
    }

    @Test
    public void deberiaDarElValorEnRadianes() {
        assertEquals (Math.PI, a180.radianes(),Angulo.MAXERROR);
        assertEquals (0.0, a0.radianes(),Angulo.MAXERROR);
        assertEquals (Math.PI/2, a90.radianes(),Angulo.MAXERROR);
        assertEquals (Math.PI*3/2, am90.radianes(),Angulo.MAXERROR);
        assertEquals (0.0, a360.radianes(),Angulo.MAXERROR);
        assertEquals (Math.PI/2, gon100.radianes(),Angulo.MAXERROR);
        assertEquals (0.0, gon400.radianes(),Angulo.MAXERROR);
    }


    @Test
    public void deberiaSumar() {
        assertEquals(a0, a0.sume(a0));
        assertEquals(a90, a90.sume(a0));
        assertEquals(a90, a0.sume(a90));
        assertEquals (a90, a45.sume(a45));
        assertEquals (a90, a30.sume(a60));
        assertEquals (a360, aPI.sume(a90.sume(a90)));
        assertEquals (gon400, aPI.sume(a90.sume(a90)));
    }


    @Test
    public void deberiaRestar() {
        assertEquals(a0, a0.reste(a0));
        assertEquals (a90, a90.reste(a0));
        assertEquals (am90, a0.reste(a90));
        assertEquals (a0, a45.reste(a45));
        assertEquals (a30, a30.reste(a0));
        assertEquals (a90, a180.reste(a90));
        assertEquals (am90, a90.reste(a180));
        assertEquals (am90, a0.reste(a90));
        assertEquals (a0, a180.reste(a90).reste(a90));
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
