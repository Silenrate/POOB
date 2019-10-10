package pruebas;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import aplicacion .*;
public class  PersonaTest  {
    @Test
    public void DeberiaMoverBien(){
        Persona persona1 = new Persona("juan",100,100);
        persona1.muevase('E');
        assertTrue(persona1.getPosicionX()==120);
        Persona persona2 = new Persona("juan",300,100);
        persona2.muevase('O');
        assertTrue(persona2.getPosicionX()==280);
        Persona persona3 = new Persona("juan",350,100);
        persona3.muevase('N');
        assertTrue(persona3.getPosicionY()==80);
        Persona persona4 = new Persona("juan",100,300);
        persona4.muevase('S');
        assertTrue(persona4.getPosicionY()==320);
    }
    @Test
    public void NoDeberiaMoverseBien(){
        Persona persona1 = new Persona("juan",100,100);
        persona1.muevase('E');
        assertFalse(persona1.getPosicionX()==110);
        Persona persona2 = new Persona("juan",300,100);
        persona2.muevase('O');
        assertFalse(persona2.getPosicionX()==250);
        Persona persona3 = new Persona("juan",350,100);
        persona3.muevase('N');
        assertFalse(persona3.getPosicionY()==90);
        Persona persona4 = new Persona("juan",100,300);
        persona4.muevase('S');
        assertFalse(persona4.getPosicionY()==310);
    }
    
}