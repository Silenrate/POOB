package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import persistencia.ArkapoobDAO;

public class MenuJugadores extends JPanel{
	private final static int ANCHO = 900;
	private final static int ALTO = 700;
	private JPanel principal,menu;
	private JButton onePlayer,twoPlayers,cpu;
	private ArkanoidGUI parent;
	private boolean random;
	private String color1,color2;
	
	public MenuJugadores(ArkanoidGUI parent, boolean isRandom,String color1,String color2) {
		this.parent = parent;
		this.color1 = color1;
		this.color2 = color2;
		random = isRandom;
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		setLayout(null);
		setSize(ANCHO , ALTO);
		preparePanelPrincipal();
		add(principal);
	}
	private void preparePanelPrincipal() {
		principal = new PanelFondo("resources/principal.jpg");
		principal.setLayout(null);
		principal.setSize(900,700);
		menu = new JPanel();
		menu.setLayout(new GridLayout(5,1));
		onePlayer = new MiBoton("");
		ponerFondo(onePlayer,"resources/ONEPLAYER.png");
		twoPlayers = new MiBoton("");
		ponerFondo(twoPlayers,"resources/TWOPLAYERS.png");
		cpu = new MiBoton("");
		ponerFondo(cpu,"resources/CPU.png");
		menu.add(onePlayer);
		menu.add(new JLabel());
		menu.add(twoPlayers);
		menu.add(new JLabel());
		menu.add(cpu);
		menu.setOpaque(false);
		menu.setSize(ANCHO/4+50,ALTO/3);
		menu.setLocation(ANCHO/2-menu.getBounds().width/2,ALTO/2-menu.getBounds().height/2+30);
		principal.add(menu);
	}
	private void prepareAcciones() {
		onePlayer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionJuego1Jugador();
            }
		});
		twoPlayers.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionJuego2Jugadores();
            }
		});
	}
	private void ponerFondo(JComponent componente,String rutaimagen){
		try{
			ArkanoidFondo fondo = new ArkanoidFondo(ImageIO.read(new File(rutaimagen)));
			componente.setBorder(fondo);
		}
		catch (IOException ex) {
			ArkapoobDAO.registre(ex);
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	private void accionJuego2Jugadores(){
		parent.accionIrAlMenu();
		PantallaJuego tablero = new PantallaJuego(random,2,color1,color2);
		tablero.setVisible(true);
	}
	private void accionJuego1Jugador(){
		parent.accionIrAlMenu();
		PantallaJuego tablero = new PantallaJuego(random,1,color1,color2);
		tablero.setVisible(true);
	}
}