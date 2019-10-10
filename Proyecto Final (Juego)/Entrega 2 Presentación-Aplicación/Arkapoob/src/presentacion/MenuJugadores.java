package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JComponent;

import aplicacion.Arkanoid;
import aplicacion.Jugador;

public class MenuJugadores extends JPanel{
	private final static int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final static int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JPanel panelCentral,panelSuperior,panelInferior,logo,panelModos,panelAbajo,left,right;
	private JButton onePlayer,twoPlayers,cpu;
	private ArkanoidGUI parent;
	
	public MenuJugadores(ArkanoidGUI parent) {
		this.parent = parent;
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		setLayout(new BorderLayout());
		setSize(ANCHO/2 , ALTO/2);
		setLocation(ANCHO/4,ALTO/4);
		preparePanelCentral();
		add(panelCentral);
	}
	private void preparePanelCentral() {
		panelCentral = new JPanel();
		panelAbajo = new JPanel();
		panelModos = new JPanel();
		logo = new JPanel();
		left = new JPanel();
		right = new JPanel();
		ponerFondo(logo,"images/logo.jpg");
		panelAbajo.setLayout(new GridLayout(1,3));
		panelModos.setLayout(new GridLayout(3,1));
		panelCentral.setLayout(new GridLayout(2,1));
		left.setBackground(Color.black);
		right.setBackground(Color.black);
		onePlayer = new JButton("");
		ponerFondo(onePlayer,"images/oneplayer.jpg");
		twoPlayers = new JButton("");
		ponerFondo(twoPlayers,"images/twoplayers.jpg");
		cpu = new JButton("");
		ponerFondo(cpu,"images/withcpu.jpg");
		panelModos.add(onePlayer);
		panelModos.add(twoPlayers);
		panelModos.add(cpu);
		panelAbajo.add(left);
		panelAbajo.add(panelModos);
		panelAbajo.add(right);
		panelCentral.add(logo);
		panelCentral.add(panelAbajo);
	}
	private void prepareAcciones() {
		onePlayer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	try {
					accionJuego1Jugador();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
		});
	}
	private void ponerFondo(JComponent componente,String rutaimagen){
		try{
			ArkanoidFondo fondo = new ArkanoidFondo(ImageIO.read(new File(rutaimagen)));
			componente.setBorder(fondo);
		}
		catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	private void accionJuego1Jugador() throws IOException{
		parent.accionIrAlMenu();
		PantallaJuego tablero = new PantallaJuego();
		tablero.setVisible(true);
	}
}
