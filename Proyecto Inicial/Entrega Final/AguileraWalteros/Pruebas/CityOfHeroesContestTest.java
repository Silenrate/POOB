package Pruebas;
import GettingJump.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

/**
 * The test class CityOfHeroesContestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CityOfHeroesContestTest
{
    /**
     * Default constructor for test class CityOfHeroesContestTest
     */
    public CityOfHeroesContestTest()
    {
    }

    private int[] configuracion; private int[][] building;
    private String correct [][]; private String solved [][];
    
    @Test
    public void SegunAWshouldSolve(){
        CityOfHeroesContest ch = new CityOfHeroesContest();
        configuracion = new int[] {20 ,1 ,1000 ,1000, 7 ,1};
        int building [][] = new int [][]{{1000 ,1 ,1000 ,1 ,1000 ,1000 ,1000 ,1 ,1 ,1000 ,1 ,1 ,1000 ,1 ,1000 ,1000 ,1 ,1000 ,1000 ,1}};
        solved = ch.solve(configuracion,building);
        correct= new String [][] {{"1","1","1","1","1","1","0","1","1","1","1","1","1","1","1","1","1","1","1","1"}};
        assertTrue(Arrays.deepEquals(solved,correct));

        configuracion = new int[] {1, 20, 1 ,1000 ,1 ,14};
        building = new int [][] {{1000},{1},{1},{1000},{1},{1000},{1},{1},{1000},{1},{1},{1},{1},{1},{1},{1},{1},{1000},{1000},{1000}};
        solved = ch.solve(configuracion,building);
        correct = new String [][] {{"1"},{"1"},{"1"},{"1"},{"1"} ,{"1"},{"1"},{"1"},{"1"},{"1"},{"1"},{"1"},{"1"},{"0"},{"1"},{"1"},{"1"},{"1"},{"1"},{"1"}};
        assertTrue(Arrays.deepEquals(solved,correct));

        configuracion = new int[] {2,2,700,83,1,1};
        building = new int [][] {{703,854},{763,240}};
        solved = ch.solve(configuracion,building);
        correct = new String [][] {{"0","X"},{"X","1"}};
        assertTrue(Arrays.deepEquals(solved,correct));

        configuracion = new int[] {20,1,2,500,1,1};
        building = new int [][] {{1,1000,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1000,1}};
        solved = ch.solve(configuracion,building);
        correct = new String [][] {{"0","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"}};
        assertTrue(Arrays.deepEquals(solved,correct));
    }

    @Test
    public void SegunAWshouldSimulate()throws CityOfHeroesExcepcion{
        CityOfHeroesContest ch = new CityOfHeroesContest();
        configuracion = new int[] {4,1,100,55,1,1};
        building = new int [][] {{10,1000,60,10}};
        assertFalse(ch.simulate(configuracion,building,3));
        assertFalse(ch.simulate(configuracion,building,2));
        assertFalse(ch.simulate(configuracion,building,4));

        configuracion = new int[] {4,1,100,55,1,1};
        building = new int [][] {{0,10,300,30}};
        assertFalse(ch.simulate(configuracion,building,3));
        assertFalse(ch.simulate(configuracion,building,4));
    }


    @Test
    public void segunJMDeberÃ­aHacerSolve(){
        CityOfHeroesContest ch = new CityOfHeroesContest();
        configuracion = new int[] {3,2,60,45,1,1};
        building = new int [][] {{10,70,150},{210,270,350}};
        solved = ch.solve(configuracion,building);
        correct = new String[][] {{"0","1","2"},{"X","X","X"}};
        assertTrue(Arrays.deepEquals(solved,correct));

        configuracion = new int[] {3,2,60,45,1,1};
        building = new int [][] {{10,70,150},{210,270,350}};
        solved = ch.solve(configuracion,building);
        correct = new String[][] {{"2","0","1"},{"X","1","X"}};
        assertFalse(Arrays.deepEquals(solved,correct));
    }

    @Test
    public void segunJMDeberiaHacerSimulate()throws CityOfHeroesExcepcion{
        CityOfHeroesContest ch = new CityOfHeroesContest();
        configuracion = new int[] {4,1,100,55,1,1};
        building = new int [][] {{30,40,50,60}};
        assertTrue(ch.simulate(configuracion,building,2));
    }

    @Test
    public void deberiaResolverCaso1(){
        CityOfHeroesContest ch = new CityOfHeroesContest();

        int [] configuracion = new int[] {4,1,100,55,1,1};
        int building [][] = new int [][]{{10,40,60,10}};
        String[][] solved = ch.solve(configuracion,building);
        String[][] correct= new String [][] {{"0","1","1","1"}};
        assertTrue(Arrays.deepEquals(solved,correct));
    }

    @Test
    public void deberiaResolverCaso2(){
        CityOfHeroesContest ch = new CityOfHeroesContest();
        int []configuracion = new int[] {4,4,100,55,1,1};
        int[][]building = new int [][] {{0,10,20,30},{10,20,30,40},{20,30,200,50},{30,40,50,60}};
        String[][] solved = ch.solve(configuracion,building);        
        String[][] correct= new String [][] {{"0","1","1","2"},{"1","1","1","2"},{"1","1","X","2"},{"2","2","2","3"}};        
        assertTrue(Arrays.deepEquals(solved,correct));
    }

    @Test
    public void noDeberiaSimularCaso2()throws CityOfHeroesExcepcion{
        CityOfHeroesContest ch = new CityOfHeroesContest();
        int []configuracion = new int[] {4,4,100,55,1,1};
        int[][]building = new int [][] {{0,10,20,30},{10,20,30,40},{20,30,200,50},{30,40,50,60}};
        boolean simulate = ch.simulate(configuracion,building,1);        
        assertFalse(simulate);
    }
}
