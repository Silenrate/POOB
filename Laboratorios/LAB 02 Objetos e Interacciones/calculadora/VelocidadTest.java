import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * The test class VelocidadTest.
 *
 * @author  (Nicolas Aguilera && Daniel Walteros)
 * @version (10/02/2019)
 */
public class VelocidadTest
{
    private Velocidad va0 , va90 , vg1, va360, va180 , vrPI ,  vg400 , vg800 , v2PI , vg01;
    private Angulo a0, a90 ,g1 ,a360 ,a180 ,rPI ,g400 ,g800 , r2PI , g01;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        a0 = new Angulo (0, Angulo.GRADOS);
        va0 = new Velocidad(90,a0);
        
        a90 = new Angulo (90, Angulo.GRADOS);
        va90 = new Velocidad (90,a90);
        
        g01 = new Angulo (0.1, Angulo.RADIANES);
        vg01 = new Velocidad (90,g01);
        
        a360 = new Angulo (-360, Angulo.GRADOS);
        va360 = new Velocidad (-360,a360);
        
        rPI = new Angulo (Math.PI, Angulo.RADIANES);
        vrPI = new Velocidad (180, rPI);
        
        g400 = new Angulo (400, Angulo.GRADIANES);
        vg400 = new Velocidad(90,g400);
        
        g1 = new Angulo (1, Angulo.GRADIANES);
        vg1 = new Velocidad(70,g1);
        
        g800 = new Angulo (-800, Angulo.GRADIANES);
        vg800 = new Velocidad(90,g800);
        
        r2PI = new Angulo (-Math.PI*2, Angulo.RADIANES);
        v2PI = new Velocidad(35.46 , r2PI);
        
    }
    @Test  
    public void deberiaRetonarBienComponenteEnX() {
        assertEquals (90.0,vg800.componenteH(),Velocidad.MAXERROR);
        assertEquals(35.46 , v2PI.componenteH() , Velocidad.MAXERROR);
        assertEquals(70*Math.cos(g1.radianes()), vg1.componenteH() , Velocidad.MAXERROR);
        assertEquals(0.0,va90.componenteH() , Velocidad.MAXERROR);
        assertEquals(-180.0 , vrPI.componenteH() , Velocidad.MAXERROR);
        assertEquals(-360.0 , va360.componenteH() , Velocidad.MAXERROR);
    }
    @Test  
    public void deberiaRetonarBienComponenteEnY() {
        assertEquals (0.0,vg400.componenteV(),Velocidad.MAXERROR);
        assertEquals(90*Math.sin(g01.radianes()) , vg01.componenteV() , Velocidad.MAXERROR);
        assertEquals(0.0, vg800.componenteV() , Velocidad.MAXERROR);
        assertEquals(0.0, va0.componenteV() , Velocidad.MAXERROR);
        assertEquals(90.0 , va90.componenteV() , Velocidad.MAXERROR);
    }
    @Test  
    public void noDeberianSerIguales() {
        assertFalse(va360.equals(va0));
        assertFalse(va90.equals(vrPI));
    }
    @Test  
    public void noDeberianSerObjetosIguales() {
        assertFalse(va360.equals(a0));
        assertFalse(va90.equals(a360));
    }
    @Test
    public void deberiaRotar(){
        assertTrue(va90.angulo().equals(va360.rote(a90).angulo()));
        assertEquals(0.0,va360.rote(new Angulo(400,Angulo.GRADIANES)).angulo().grados(),Angulo.MAXERROR);
        assertEquals(180.0,va90.rote(new Angulo(100,Angulo.GRADIANES)).angulo().grados(),Angulo.MAXERROR);
        assertEquals(20.0,vg800.rote(new Angulo(20,Angulo.GRADOS)).angulo().grados(),Angulo.MAXERROR);
        assertEquals(180.0,vg800.rote(new Angulo(180,Angulo.GRADOS)).angulo().grados(),Angulo.MAXERROR);
        assertEquals(0.0,vrPI.rote(new Angulo(-540,Angulo.GRADOS)).angulo().grados(),Angulo.MAXERROR);
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
