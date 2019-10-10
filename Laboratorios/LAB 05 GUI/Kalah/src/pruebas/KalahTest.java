package pruebas;
import aplicacion.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import aplicacion.*;
/**
 * The test class KalahTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class KalahTest{
    @Test
    public void shouldTakeARound() throws KalahException{
        Kalah ka = new Kalah(6,13);
        ka.juegue(3);
        //ka.consulte();
        int[] res = ka.getBoard2();
        int[] res2 = {14,14,14,14,14,14};
        assertArrayEquals(res,res2);
    }
    @Test
    public void shouldTakeEnemiesSeeds() throws KalahException{
        Kalah ka = new Kalah(6,14);
        ka.juegue(4);
        int[] res = ka.getBoard2();
        int[] res2 = {15,15,15,0,15,15};
        assertArrayEquals(res,res2);
    }
    @Test
    public void shouldGetTurnoExtra() throws KalahException{
        Kalah ka = new Kalah(6,3);
        ka.juegue(4);
        assertTrue(ka.getTurnoExtra());
        
        ka.juegue(5);
        assertFalse(ka.getTurnoExtra());
    }
    @Test
    public void shouldTie() throws KalahException{
        Kalah ka = new Kalah(1,3);
        ka.juegue();
        assertTrue(1==ka.gane());
    }
    @Test
    public void shouldThrowsExceptionIfHousesAreNegative() throws KalahException{
    	try {
    		Kalah ka = new Kalah(-1,3);
    	}catch(KalahException e) {
    		assertTrue(e.getMessage().equals(KalahException.BAD_DIMENSIONS));
    	}
    }
    @Test
    public void shouldThrowsExceptionIfNumberOfHouseIsTooBig() throws KalahException{
    	try {
    		Kalah ka = new Kalah(6,6);
    		ka.juegue(8);
    	}catch(KalahException e) {
    		assertTrue(e.getMessage().equals(KalahException.BAD_HOUSE));
    	}
    }
    
}
