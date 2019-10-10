

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class EquipoTest{
   

    
    public EquipoTest(){
    }


    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void deberiaCalcularElValorDeUnEquipo(){
        String [] nombres={"Pedro","Judas"};
        Equipo eq= new Equipo(nombres);
        try{
           assertEquals(60000,eq.valorHora());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }    
    }    
    
    @Test
    public void deberiaLanzarExcepcionSiElEquipoNoTienePersonas(){
        String [] nombres={};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanzó excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.EQUIPO_VACIO,e.getMessage());
        }    
    }    
    
    
   @Test
    public void deberiaLanzarExcepcionSiNoSeConoceElValorDeUnaPersona(){
        String [] nombres={"Pedro","Garcia","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_DESCONOCIDO,e.getMessage());
        }    
    }     
    
   @Test
   public void deberiaLanzarExcepcionSiNoSeConoceUnaPersona(){
        String [] nombres={"Pedro","Carlos","Juan"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHora();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.PERSONA_DESCONOCIDA,e.getMessage());
        }
        
        try { 
           int valor=eq.valorHoraEstimado();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.PERSONA_DESCONOCIDA,e.getMessage());
        }    
   }
   @Test
    public void deberiaCalcularElValorHoraEstimado(){
        String [] nombres={"Pedro","Judas","Santiago"};
        Equipo eq= new Equipo(nombres);
        try{
           assertEquals(80000,eq.valorHoraEstimado());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }
        String [] nombres2={"Pedro","Judas","Garcia"};
        Equipo eq2= new Equipo(nombres2);
        try{
           assertEquals(90000,eq2.valorHoraEstimado());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }    
    }
    @Test
    public void deberiaLanzarExcepcionSiNoEsPosibleCalcularElValorEstimado(){
        String [] nombres={"Pedro","Ospina","Garcia"};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHoraEstimado();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_ESTIMADO_DESCONOCIDO,e.getMessage());
        }    
        String [] nombres2={"Pedro","Guarin","Garcia"};
        Equipo eq2= new Equipo(nombres2);
        try { 
           int valor=eq2.valorHoraEstimado();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_ESTIMADO_DESCONOCIDO,e.getMessage());
        }    
    }
    @Test
    public void deberiaLanzarExcepcionSiNoEsPosibleCalcularElValorAsumido(){
        String [] nombres={""};
        Equipo eq= new Equipo(nombres);
        try { 
           int valor=eq.valorHoraAsumido();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_ASUMIDO_DESCONOCIDO,e.getMessage());
        }    
        String [] nombres2={"Carlos","Guarin","Garcia"};
        Equipo eq2= new Equipo(nombres2);
        try { 
           int valor=eq2.valorHoraAsumido();
           fail("No lanza la excepcion");
        } catch (EquipoExcepcion e) {
            assertEquals(EquipoExcepcion.VALOR_ASUMIDO_DESCONOCIDO,e.getMessage());
        }    
    }
    @Test
    public void deberiaCalcularElValorHoraAsumido(){
        String [] nombres={"Pedro","Garcia","Carlos"};
        Equipo eq= new Equipo(nombres);
        try{
           assertEquals(30000,eq.valorHoraAsumido());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }
        String [] nombres2={"Pedro","Carlos","Marcos"};
        Equipo eq2= new Equipo(nombres2);
        try{
           assertEquals(50000,eq2.valorHoraAsumido());
        } catch (EquipoExcepcion e){
            fail("Lanzó excepcion");
        }    
    }
}