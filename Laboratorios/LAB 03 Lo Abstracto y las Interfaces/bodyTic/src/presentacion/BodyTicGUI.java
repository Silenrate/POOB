package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;
import java.io.*;


import aplicacion.*;


public class BodyTicGUI extends JFrame{
    
    private Salon salon=null;
    
    private JPanel botones;
    private JScrollPane contenedor;
    private JButton botonEntrada;
    private JButton botonSalida;
    private JButton botonInicio;
    private JButton botonParada;    
    private JButton botonDecision;     
    private FotoSalon foto;
    
    
    public BodyTicGUI() {
        super("Body Tic");
        try {
            salon=Salon.demeSalon();     
            elementos();
            acciones();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void elementos() throws Exception {
        
        setLayout(new BorderLayout());    
        contenedor = new JScrollPane();
        
        foto= new FotoSalon();
        contenedor.getViewport().add(foto);
        
        botones=new JPanel(new GridLayout(1,2));
        botonEntrada=new JButton("Entren");
        botonInicio=new JButton("Inicien");
        botonParada=new JButton("Paren");
        botonDecision=new JButton("Decidan");          
        botonSalida=new JButton("Salgan");
        
        botones.add(botonEntrada);
        botones.add(botonInicio);
        botones.add(botonParada);
        botones.add(botonDecision);        
        botones.add(botonSalida);        
        
        add(contenedor,BorderLayout.CENTER);
        add(botones,BorderLayout.SOUTH);
        
        pack();
        setSize(Salon.MAXIMO+100,Salon.MAXIMO+135);

        setResizable(false);
    }
    
    
    private void acciones(){
        
        ActionListener oyenteBotonEntrada=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                entrada();
            }   
        };  
        botonEntrada.addActionListener(oyenteBotonEntrada);
        
        ActionListener oyenteBotonInicio=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                inicio();
            }   
        };  
        botonInicio.addActionListener(oyenteBotonInicio);
        
        ActionListener oyenteBotonParada=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                parada();
            }   
        };  
        botonParada.addActionListener(oyenteBotonParada);
        
        ActionListener oyenteBotonDecision=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                decision();
            }   
        };  
        botonDecision.addActionListener(oyenteBotonDecision);
        
        ActionListener oyenteBotonSalida=new ActionListener(){
            public void actionPerformed(ActionEvent e){
                salida();
            }   
        };  
        botonSalida.addActionListener(oyenteBotonSalida);
        
        WindowListener w = new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {
                salir();
            }
        };  
        this.addWindowListener(w);
        
    }   
    
    
    private void entrada(){
         salon.entrada();
         foto.actualice();
    }
    
    private void salida(){
         salon.salida();
         foto.actualice();
    }
    
    private void inicio(){
         salon.inicio();
         foto.actualice();
    }
        
    
    private void parada(){       
        salon.parada();
        foto.actualice();
    }       
  
    private void decision(){       
        salon.decision();
        foto.actualice();
    }  
    
    
    private void salir(){
        dispose();
        System.exit(0);
    }   
    
    
    
    public static void main(String[] args) {
        BodyTicGUI gui=new BodyTicGUI();
        gui.setVisible(true);
    }   
    
    
    private class FotoSalon extends JComponent {
        private int x,y;
        
        private static final int MAX=Salon.MAXIMO;
        
        
        public void actualice(){
            salon=Salon.demeSalon();
            repaint();
        }
        
        public void paintComponent(Graphics g){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 
            
            for (int i=1; i<=salon.numeroEnSalon(); i++) {
                
                EnSalon e=salon.deme(i);
                int x=e.getPosicionX();
                int y=MAX-e.getPosicionY();  
                
                g.setColor(e.getColor()); 
                g.drawString(e.mensaje(),x+20,y+10);   
                
                if (e.forma().equals("Persona")){
                    humano(g,(Persona)e,x,y);
                } else  if (e.forma().equals("Circulo")){
                    g.fillOval(x+10,y+0,10,10);
                } else  if (e.forma().equals("Cuadrado")){
                    g.fillRect(x,y,10,10);
                }
                else  if (e.forma().equals("Bola")){
                    int size = ((Bola) e).getSize();
                    g.fillOval(x,y,size,size);
                }
                else  if (e.forma().equals("Pesa")){
                    int size = ((Pesa) e).getSize();
                    g.fillOval(x,y-10,size/4,size/4);
                    g.fillOval(x+size-5,y-10,size/4,size/4);
                    g.fillRect(x+size/8,y+size/8,size,10);
                }
            }
            super.paintComponent(g);
        }
        
        
        public void humano(Graphics g, Persona e,int x, int y){
            int pos;
            g.setColor(Color.PINK);
            g.fillOval(x+10,y+0,10,10);/*cabeza*/
            g.setColor(e.getColor()); 
            g.drawLine(x+10+5,y+10,x+10+5,y+10+20);
            
            pos=e.getPosicionBrazo('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10);/*brazo izq arriba*/
            } else if (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+5);/*brazo izq al frente*/
            } else {
                g.drawLine(x+10+5,y+10+5,x+10+15,y+10+10);/*brazo izq abajo*/
            }
            
            pos=e.getPosicionBrazo('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+5,y+10+5,x+5,y+10);/*brazo der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+5,y+10+5,x+5,y+10+5);/*brazo der al frente*/
            } else{
                g.drawLine(x+10+5,y+10+5,x+5,y+10+10);/*brazo der abajo*/
            }
            
            g.drawLine(x+10+5,(y+15)+10+5,x+10+15,(y+15)+10+15);
            g.drawLine(x+10+5,(y+15)+10+5,x+5,(y+15)+10+15);
            
           pos=e.getPosicionPierna('D');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+5,(y+15)+10+15,x+5+10,(y+15)+10+15);/*pierna der arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+5,(y+15)+10+15,x+5-10,(y+15)+10+15+5);/*pierna der al frente*/
            } else{
                g.drawLine(x+5,(y+15)+10+15,x+5,(y+15)+10+15+10);/*pierna der abajo*/
            }
            
            pos=e.getPosicionPierna('I');
            if (pos==Persona.ARRIBA){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15-10,(y+15)+10+15);/*pierna izq arriba*/
            } else if  (pos==Persona.FRENTE){
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15+10,(y+15)+10+15+5);/*pierna izq al frente*/
            }else {
                g.drawLine(x+10+15,(y+15)+10+15,x+10+15,(y+15)+10+15+10);/*piernaizqabajo*/
            }
        }
    }
}





