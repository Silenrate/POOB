package Pruebas;
import GettingJump.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CityOfHeroesTest01.
 *
 * @author  Fredy Díaz
 * @version February 02, 2019
 */
public class CityOfHeroesTest01 {
    @Test
    public void ShouldAddBuildings() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
    }
    
    @Test
    public void ShouldNotAddBuildings(){
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        try{
            ch.addBuilding(0, 10, 20, 5);
            assertTrue(ch.ok());
            ch.addBuilding(0, 10, 20, 10);
            assertFalse(ch.ok());
            ch.addBuilding(20, 150, 20, 10);
            assertFalse(ch.ok());
        }
        catch(CityOfHeroesExcepcion e){
            assertFalse(ch.ok());
        }
    }
    
    @Test
    public void ShouldAddHeroes() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yElLoW", 2, 100);
        assertTrue(ch.ok());
    }
    
    @Test
    public void ShouldNotAddHeroes(){
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        try{
            ch.addBuilding(0, 10, 20, 5);
            assertTrue(ch.ok());
            ch.addBuilding(11, 10, 20, 10);
            assertTrue(ch.ok());
            ch.addHeroe("Blue", 1, 100);
            assertTrue(ch.ok());
            ch.addHeroe("yElLoW", 3, 100);
            assertFalse(ch.ok());
        }
        catch(CityOfHeroesExcepcion e){
            assertFalse(ch.ok());
        }
    }
    
    @Test
    public void ShouldNotifyNoDeads() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yElLoW", 2, 100);
        assertTrue(ch.ok());
        
        assertArrayEquals(ch.deads(), new String[]{});
    }
    
    @Test
    public void ShouldNotifyDeads() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(25, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yElLoW", 2, 100);
        assertTrue(ch.ok());
        
        ch.jump("blue", 200, 45, true);
        assertArrayEquals(ch.deads(), new String[]{"blue"});
    }
    
    @Test
    public void ShouldJump() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(22, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(33, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(44, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(55, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(66, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(77, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("red", 5, 100);
        assertTrue(ch.ok());
        ch.jump("blue", 5, 10, true);
        assertTrue(ch.ok());
        ch.jump("red", 5, 10, true);
        assertTrue(ch.ok());
    }
    
    @Test
    public void ShouldNotJump(){
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        try{
            ch.addBuilding(0, 10, 20, 5);
            assertTrue(ch.ok());
            ch.addBuilding(11, 10, 20, 10);
            assertTrue(ch.ok());
            ch.addBuilding(22, 10, 20, 10);
            assertTrue(ch.ok());
            ch.addBuilding(33, 10, 20, 10);
            assertTrue(ch.ok());
            ch.addBuilding(44, 10, 20, 10);
            assertTrue(ch.ok());
            ch.addHeroe("Blue", 1, 100);
            assertTrue(ch.ok());
            ch.addHeroe("red", 5, 100);
            assertTrue(ch.ok());
            ch.jump("yellow", 5, 10, true);
            assertFalse(ch.ok());
            ch.jump("green", 5, 10, true);
            assertFalse(ch.ok());
        }
        catch(CityOfHeroesExcepcion e){
            assertFalse(ch.ok());
        }
    }
    
    @Test
    public void ShouldNotifyDamage() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        ch.addBuilding(22, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(33, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(44, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(55, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(66, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(77, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        
        ch.jump("blue", 200, 20, true);
        assertTrue(ch.isDamaged(2));
    }
    
    @Test
    public void ShouldNotNotifyDamage() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        
        assertFalse(ch.isDamaged(1));
        assertFalse(ch.isDamaged(2));
    }
    
    @Test
    public void ShouldBeSafeJump() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(22, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(33, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(44, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(55, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(66, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        
        assertTrue(ch.isSafeJump("blue", 5, 20));
    }
    
    @Test
    public void ShouldNotBeSafeJump() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        
        assertFalse(ch.isSafeJump("blue", 200, 20));
    }
    
    @Test
    public void ShouldRemoveBuilding() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        
        ch.removeBuilding(1);
        assertTrue(ch.ok());
        ch.removeBuilding(1);
        assertTrue(ch.ok());
    }
    
    @Test
    public void ShouldNotRemoveBuilding(){
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        try{
            ch.addBuilding(0, 10, 10, 5);
            assertTrue(ch.ok());
            ch.addBuilding(11, 10, 200, 10);
            assertTrue(ch.ok());
            ch.removeBuilding(1);
            assertTrue(ch.ok());
            ch.removeBuilding(1);
            assertTrue(ch.ok());
            ch.removeBuilding(3);
            assertFalse(ch.ok());
        }
        catch(CityOfHeroesExcepcion e){
            assertFalse(ch.ok());
        }
        
    }
    
    @Test
    public void ShouldRemovedHeroes() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yElLoW", 2, 100);
        assertTrue(ch.ok());
        
        ch.removeHeroe("blue");
        assertTrue(ch.ok());
        ch.removeHeroe("yellow");
        assertTrue(ch.ok());
    }
    
    @Test
    public void ShouldNotRemoveHeroes(){
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        try{
            ch.addBuilding(0, 10, 20, 5);
            assertTrue(ch.ok());
            ch.addBuilding(11, 10, 20, 10);
            assertTrue(ch.ok());
            ch.addHeroe("Blue", 1, 100);
            assertTrue(ch.ok());
            ch.addHeroe("yElLoW", 2, 100);
            assertTrue(ch.ok());
            ch.removeHeroe("blue");
            assertTrue(ch.ok());
            ch.removeHeroe("blue");
            assertFalse(ch.ok());
            ch.removeHeroe("yellow");
            assertTrue(ch.ok());
            ch.removeHeroe("yellow");
            assertFalse(ch.ok());
            ch.removeHeroe("green");
            assertFalse(ch.ok());
        }
        catch(CityOfHeroesExcepcion e){
            assertFalse(ch.ok());
        }
    }
    
    @Test
    public void ShouldNotifyStrength() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 20, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        ch.addHeroe("yElLoW", 2, 50);
        assertTrue(ch.ok());
        
        assertEquals(ch.strength("blue"), 100);
        assertEquals(ch.strength("yellow"), 50);
    }
    
    @Test
    public void ShouldReduceStrength() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(100, 250);
        ch.addBuilding(0, 10, 10, 5);
        assertTrue(ch.ok());
        ch.addBuilding(11, 10, 200, 10);
        assertTrue(ch.ok());
        ch.addBuilding(22, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(33, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(44, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(55, 10, 20, 10);
        assertTrue(ch.ok());
        ch.addBuilding(66, 10, 20, 10);
        assertTrue(ch.ok());
        
        ch.addHeroe("Blue", 1, 100);
        assertTrue(ch.ok());
        
        assertEquals(ch.strength("blue"), 100);
        ch.jump("blue", 200, 20, true);
        assertEquals(ch.strength("blue"), 90);
    }
}
