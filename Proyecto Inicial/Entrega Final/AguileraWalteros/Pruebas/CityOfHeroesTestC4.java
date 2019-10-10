package Pruebas;
import GettingJump.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CityOfHeroesTestC4.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CityOfHeroesTestC4
{
    /**
     * Default constructor for test class CityOfHeroesTestC4
     */
    public CityOfHeroesTestC4()
    {
    }

    @Test
    public void segunCVdeberiaEscalar() throws CityOfHeroesExcepcion{
        CityOfHeroes c1 = new CityOfHeroes(600,600);
        c1.addBuilding(0,100,100,30);
        c1.addBuilding(150,100,400,50);
        c1.addHeroe("Climber", "yellow",1, 50);
        c1.jump("yellow",50,15,false);
        assertTrue(c1.ok());

        CityOfHeroes c2 = new CityOfHeroes(600,600);
        c2.addBuilding(0,100,100,30);
        c2.addBuilding(150,100,400,50);
        c2.addBuilding(102,20,500,50);
        c2.addHeroe("Climber", "yellow",1, 50);
        c2.jump("yellow",30,45,false);
        assertTrue(c2.ok());
    }

    @Test
    public void segunCVdeberiaSerCuidadoso() throws CityOfHeroesExcepcion{
        CityOfHeroes c1 = new CityOfHeroes(600,600);
        c1.addBuilding(0,100,100,30);
        c1.addBuilding(150,100,400,50);
        c1.addHeroe("Careful", "green",1, 50);
        c1.jump("green",50,15,false);
        assertFalse(c1.ok());

        CityOfHeroes c2 = new CityOfHeroes(600,600);
        c2.addBuilding(0,100,100,30);
        c2.addBuilding(150,100,400,50);
        c2.addBuilding(102,20,500,50);
        c2.addHeroe("Careful", "green",1, 50);
        c2.jump("green",30,45,false);
        assertFalse(c2.ok());
    }

    @Test
    public void segunCVdeberiaParatrooper()throws CityOfHeroesExcepcion{
        CityOfHeroes c1 = new CityOfHeroes(600,600);
        c1.addBuilding(0,100,100,30);
        c1.addBuilding(150,100,400,50);
        c1.addHeroe("ParaTrooper", "blue",2, 50);
        c1.jump("blue",70,10,false);
        assertTrue(c1.ok());

        CityOfHeroes c2 = new CityOfHeroes(600,600);
        c2.addBuilding(0,100,100,30);
        c2.addBuilding(150,100,400,50);
        c2.addBuilding(102,20,500,50);
        c2.addHeroe("ParaTrooper", "yellow",2, 50);
        c2.jump("yellow",50,170,false);
        assertTrue(c2.ok());
    }


    @Test 
    public void segunFGdeberiaSaltarCarefull()throws CityOfHeroesExcepcion{
        CityOfHeroes ciudad = new CityOfHeroes(600,600);
        ciudad.addBuilding(0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("careful","green", 2, 100);
        ciudad.jump("green",70,100,true);
        assertTrue(ciudad.ok());
    }

    @Test 
    public void segunFGnoDeberiaSaltarCarefull()throws CityOfHeroesExcepcion{
        CityOfHeroes ciudad = new CityOfHeroes(600,600);
        ciudad.addBuilding(0, 100, 200, 1);
        ciudad.addBuilding(100, 200, 100, 20);
        ciudad.addHeroe("careful","green", 2, 100);
        ciudad.jump("green",70,140,true);
        assertFalse(ciudad.ok());
    }


    @Test
    public void segunHRnodeberiasaltar() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(600,600);
        ch.addBuilding(0,100,200,30);
        ch.addBuilding(200,100,500,80);
        ch.addHeroe("careful","blue",1,40);
        ch.jump("blue",60,45,true);
        assertFalse(ch.ok());
    }
/*
    @Test
    public void deberiaSaltarysalirdlecanvasSegunHR() throws CityOfHeroesExcepcion{
        CityOfHeroes ch = new CityOfHeroes(600,600);
        ch.addBuilding("flexible",0,100,200,30);
        ch.addBuilding("flexible",200,100,500,80);
        ch.addBuilding("flexible",400,100,500,80);
        ch.addHeroe("blue",1,40);
        ch.jump("blue",60,45,true);
        assertFalse(ch.ok());
    }
*/
    @Test
    public void segunSSDeberiaEscalar() throws CityOfHeroesExcepcion{

        CityOfHeroes c2 = new CityOfHeroes(600,600);
        c2.addBuilding("Normal",0,100,100,30);
        c2.addBuilding("Normal",101,100,400,50);
        c2.addBuilding("Normal",250,100,150,50);
        c2.addHeroe("Climber","blue",1, 50);
        c2.jump("blue",50,15,false);

        assertTrue(c2.ok());

    }
    @Test
    public void segunSSDeberiaEscalarReverse()throws CityOfHeroesExcepcion{
        CityOfHeroes c2 = new CityOfHeroes(600,600);
        c2.addBuilding("Normal",0,100,100,30);
        c2.addBuilding("Normal",101,100,400,50);
        c2.addBuilding("Normal",250,100,150,50);
        c2.addHeroe("Climber","blue",3, 50);
        c2.jump("blue",50,150,false);

        assertTrue(c2.ok());

    }
 
    @Test
    public void segunJMNoDeberiaRealizarSalto()throws CityOfHeroesExcepcion{
        CityOfHeroes cof = new CityOfHeroes(500,500);
        cof.addBuilding("flexible",0,80,120,90);
        assertTrue(cof.ok());
        cof.addBuilding("flexible",150,150,320,60);
        assertTrue(cof.ok());
        cof.addHeroe("careful","white",1,90);
        assertTrue(cof.ok());
        cof.jump("white",45,45,false);
        assertFalse(cof.ok());
    }

    @Test
    public void SegunJMDeberiaDestruirRadical()throws CityOfHeroesExcepcion{
        CityOfHeroes cof = new CityOfHeroes(500,500);
        cof.addBuilding("normal",0,80,120,90);
        assertTrue(cof.ok());
        cof.addBuilding("radical",150,150,320,60);
        assertTrue(cof.ok());
        cof.addHeroe("red",1,90);
        assertTrue(cof.ok());
        cof.jump("red",45,45,false);
        assertTrue(cof.ok()); 

        cof.addBuilding(200,100,100,100);
        assertTrue(cof.ok());
    }
}

