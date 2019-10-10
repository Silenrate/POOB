/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import aplicacion.*;

/**
 *
 * @author Asus PC
 */
public class SeleccionTest {
   
    @Test

    public void deberiaAdicionarJugador() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("Yerry","Mina", "195","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
            assertTrue(jugador.equals(Seleccion.getInformacion("Yerry","Mina")));
            Jugador jugador2 = new Jugador("James","Rodriguez", "170","Mediocampista","");
            Seleccion.adicione(jugador2);
            assertTrue(jugador2.equals(Seleccion.getInformacion("James","Rodriguez")));
        } catch (SeleccionExcepcion e){
            fail("Lanzó excepcion");
        }  
    }
    
    @Test
    
    public void deberiaLanzarExcepcionSiAdicionaMalAlJugador() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("","", "194","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
        } catch (SeleccionExcepcion e){
            assertEquals(SeleccionExcepcion.EMPTY_BLOCK,e.getMessage());
        }
        try{
            Jugador jugador = new Jugador("Yerry","Mina", "195","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
            Jugador jugador2 = new Jugador("James","Rodriguez", "300","Mediocampista","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador2);
        } catch (SeleccionExcepcion e){
            assertEquals(SeleccionExcepcion.PLAYER_HEIGHT_LIMIT,e.getMessage());
        }
    }
    @Test
    
    public void deberiaAdionarListarYRetornarElNumeroDeJugadoresBien() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        StringBuffer lista=new StringBuffer();
        try{
            Jugador jugador = new Jugador("Yerry","Mina", "195","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
            lista.append(jugador.toString()); lista.append('\n'); lista.append('\n');
            Jugador jugador2 = new Jugador("James","Rodriguez", "175","Mediocampista","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador2);
            lista.append(jugador2.toString()); lista.append('\n'); lista.append('\n');
            Jugador jugador3 = new Jugador("Falcao","Garcia", "179","Delantero","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador3);
            lista.append(jugador3.toString()); lista.append('\n'); lista.append('\n');
            assertTrue(jugador.equals(Seleccion.getInformacion("Yerry","Mina")));
            assertTrue(jugador2.equals(Seleccion.getInformacion("James","Rodriguez")));
            assertTrue(jugador3.equals(Seleccion.getInformacion("Falcao","Garcia")));
            assertTrue(seleccion.numeroJugadores()==3);
            assertTrue(seleccion.toString().equals(lista.toString()));
        } catch (SeleccionExcepcion e){
            fail("Lanzó excepcion");
        }  
    }
    @Test
    
    public void deberiaListar() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        StringBuffer lista=new StringBuffer();
        try{
            Jugador jugador = new Jugador("Yerry","Mina", "195","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            seleccion.adicione(jugador);
            lista.append(jugador.toString()); lista.append('\n'); lista.append('\n');
            Jugador jugador2 = new Jugador("James","Rodriguez", "175","Mediocampista","Nominado al FIFA/FIFPro WorldXI 2018");
            seleccion.adicione(jugador2);
            lista.append(jugador2.toString()); lista.append('\n'); lista.append('\n');
            assertTrue(seleccion.toString().equals(lista.toString()));
            
        } catch (SeleccionExcepcion e){
            fail("Lanzó excepcion");
        }
    }
    
    @Test

    public void deberiaLanzarExcepcionSiExisteUnBloqueVacio() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("Yerry","", "195","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
        } catch (SeleccionExcepcion e){
            assertEquals(SeleccionExcepcion.EMPTY_BLOCK,e.getMessage());
        }  
    }
    @Test

    public void deberiaLanzarExcepcionSiExisteUnaAlturaComoTexto() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("Yerry","Mina", "alto","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
        } catch (SeleccionExcepcion e){
            assertEquals(SeleccionExcepcion.PLAYER_HEIGHT,e.getMessage());
        }  
    }
    
    @Test

    public void deberiaLanzarExcepcionSiYaExisteElJugador() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("Yerry","Mina", "197","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
            Jugador jugador2 = new Jugador("Yerry","Mina", "197","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador2);
        } catch (SeleccionExcepcion e){
            assertEquals(SeleccionExcepcion.PLAYER_ALREADY_EXISTS,e.getMessage());
        }  
    }
    
    @Test

    public void deberiaLanzarExcepcionSiLaPosicionEsIncorrecta() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("Yerry","Mina","197","Chef","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
        } catch (SeleccionExcepcion e){
            assertEquals(SeleccionExcepcion.BAD_POSITION,e.getMessage());
        }  
    }
    
    @Test

    public void deberiaBuscar() throws SeleccionExcepcion{
        Seleccion seleccion = new Seleccion();
        try{
            Jugador jugador = new Jugador("Yerry","Mina","197","Defensa","Nominado al FIFA/FIFPro WorldXI 2018");
            Seleccion.adicione(jugador);
            seleccion.busque("Y");
            assertTrue(seleccion.busque("Y").contains(jugador));
        } catch (Exception e){
            fail("Lanzó excepcion");
        }  
    }
}