package presentacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.imageio.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.JFrame;

import persistencia.ArkapoobDAO;

public class MenuJugadores extends JFrame{
	private final static int ANCHO = 900;
	private final static int ALTO = 700;
	private JPanel principal,menu,oneP,twoP,cpuP,cards;
	private JButton onePlayer,twoPlayers,cpu;
	private JTextField nombreOP,nombreTP1,nombreTP2,nombreCPU1;
	private JComboBox boxRandom1,boxRandom2,boxRandom3,boxColorOP,boxColorTP1,boxColorTP2,boxColorCPU1,boxColorCPU2,boxTypeCPU;
	private boolean random;
	private JButton play1,play2,play3;
	private String[] colores = {"Orange","Yellow","Blue","Red","Green","Purple"};
	private String[] tipos = {"Destructor","Mimo","Curioso"};
	private String[] op = {"Si","No"};
	
	public MenuJugadores(ArkanoidGUI parent,String color1,String color2) {
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		setSize(ANCHO , ALTO);
		setLocationRelativeTo(null);
		preparePanelPrincipal();
		preparePantallas();
		cards.add(principal,"principal");
		add(cards);
		CardLayout c = (CardLayout)(cards.getLayout());
		c.show(cards, "principal");
		setIconImage(new ImageIcon("resources/icon.png").getImage());
		setResizable(false);
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
	private void preparePantallas() {
		preparePantalla1Jugador();
		preparePantalla2Jugadores();
		preparePantallaCPU();
	}
	private void preparePantalla1Jugador() {
		oneP = new PanelFondo("resources/menu.png");
		oneP.setBackground(Color.black);
		oneP.setSize(ANCHO,ALTO);
		oneP.setLayout(null);
		
		nombreOP = new JTextField(10);
		oneP.add(nombreOP);
		nombreOP.setBounds(ANCHO/2,ALTO/2,100,20);
		
		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon("resources/onep.png");
		Image temporal = icono.getImage().getScaledInstance(250,100, Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(temporal));
		imagen.setBounds(310, 100, 250, 100);
		oneP.add(imagen);
		
		JLabel nombreJ = new JLabel("Ingresa tu nombre");
		nombreJ.setForeground(Color.WHITE);
		nombreJ.setBounds(ANCHO/2-110, ALTO/2, 150, 20);
		oneP.add(nombreJ);
		
		boxColorOP = new JComboBox<>(colores);
		boxColorOP.setBounds(ANCHO/2,ALTO/2+30,100,20);
		oneP.add(boxColorOP);
		
		JLabel color = new JLabel("Elige color");
		color.setForeground(Color.WHITE);
		color.setBounds(ANCHO/2-110, ALTO/2+30, 150, 20);
		oneP.add(color);
		
		boxRandom1 = new JComboBox<>(op);
		boxRandom1.setBounds(ANCHO/2,ALTO/2+60,100,20);
		oneP.add(boxRandom1);
		
		JLabel random = new JLabel("Juego Aleatorio");
		random.setForeground(Color.WHITE);
		random.setBounds(ANCHO/2-110, ALTO/2+60, 150, 20);
		oneP.add(random);
		
		play1 = new MiBoton("");
		ponerFondo(play1,"resources/jugar1.png");
		play1.setBounds(ANCHO/2-90,ALTO/2+150,150,50);
		oneP.add(play1);
		cards.add(oneP,"oneP");
	}
	private void preparePantallaCPU() {
		int ANCHO2 = ANCHO-200;
		cpuP = new PanelFondo("resources/menu.png");
		cpuP.setBackground(Color.black);
		cpuP.setSize(ANCHO,ALTO);
		cpuP.setLayout(null);
		
		nombreCPU1 = new JTextField(10);
		cpuP.add(nombreCPU1);
		nombreCPU1.setBounds(ANCHO/4,ALTO/2,100,20);
		
		boxTypeCPU = new JComboBox<>(tipos);
		boxTypeCPU.setBounds(ANCHO2,ALTO/2,100,20);
		cpuP.add(boxTypeCPU);
		
		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon("resources/pcpu.png");
		Image temporal = icono.getImage().getScaledInstance(300,100, Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(temporal));
		imagen.setBounds(310, 100, 300, 100);
		cpuP.add(imagen);
		
		JLabel nombreJ = new JLabel("Ingresa tu nombre");
		nombreJ.setForeground(Color.WHITE);
		nombreJ.setBounds(ANCHO/4-110, ALTO/2, 150, 20);
		cpuP.add(nombreJ);
		
		JLabel tipoCPU = new JLabel("Tipo de CPU");
		tipoCPU.setForeground(Color.WHITE);
		tipoCPU.setBounds(ANCHO2-110, ALTO/2, 150, 20);
		cpuP.add(tipoCPU);
		
		boxColorCPU1 = new JComboBox<>(colores);
		boxColorCPU1.setBounds(ANCHO/4,ALTO/2+30,100,20);
		cpuP.add(boxColorCPU1);
		
		boxColorCPU2 = new JComboBox<>(colores);
		boxColorCPU2.setBounds(ANCHO2,ALTO/2+30,100,20);
		cpuP.add(boxColorCPU2);
		
		boxRandom3 = new JComboBox<>(op);
		boxRandom3.setBounds(ANCHO/2,ALTO/2,100,20);
		cpuP.add(boxRandom3);
		
		JLabel random = new JLabel("Juego Aleatorio");
		random.setForeground(Color.WHITE);
		random.setBounds(ANCHO/2-100, ALTO/2, 150, 20);
		cpuP.add(random);
		
		JLabel color = new JLabel("Elige color");
		color.setForeground(Color.WHITE);
		color.setBounds(ANCHO/4-110, ALTO/2+30, 150, 20);
		cpuP.add(color);
		
		JLabel color2 = new JLabel("Elige color");
		color2.setForeground(Color.WHITE);
		color2.setBounds(ANCHO2-110, ALTO/2+30, 150, 20);
		cpuP.add(color2);
		
		play3 = new MiBoton("");
		ponerFondo(play3,"resources/jugar1.png");
		play3.setBounds(ANCHO/2-70,ALTO/2+150,150,50);
		cpuP.add(play3);
		cards.add(cpuP,"cpu");
	}
	private void preparePantalla2Jugadores() {
		int ANCHO2 = ANCHO-200;
		twoP = new PanelFondo("resources/menu.png");
		twoP.setBackground(Color.black);
		twoP.setSize(ANCHO,ALTO);
		twoP.setLayout(null);
		
		nombreTP1 = new JTextField(10);
		twoP.add(nombreTP1);
		nombreTP1.setBounds(ANCHO/4,ALTO/2,100,20);
		
		nombreTP2 = new JTextField(10);
		twoP.add(nombreTP2);
		nombreTP2.setBounds(ANCHO2,ALTO/2,100,20);
		
		JLabel imagen = new JLabel();
		ImageIcon icono = new ImageIcon("resources/twop.png");
		Image temporal = icono.getImage().getScaledInstance(290,100, Image.SCALE_SMOOTH);
		imagen.setIcon(new ImageIcon(temporal));
		imagen.setBounds(310, 100, 290, 100);
		twoP.add(imagen);
		
		JLabel nombreJ = new JLabel("Ingresa tu nombre");
		nombreJ.setForeground(Color.WHITE);
		nombreJ.setBounds(ANCHO/4-110, ALTO/2, 150, 20);
		twoP.add(nombreJ);
		
		JLabel nombreJ2 = new JLabel("Ingresa tu nombre");
		nombreJ2.setForeground(Color.WHITE);
		nombreJ2.setBounds(ANCHO2-110, ALTO/2, 150, 20);
		twoP.add(nombreJ2);
		
		boxColorTP1 = new JComboBox<>(colores);
		boxColorTP1.setBounds(ANCHO/4,ALTO/2+30,100,20);
		twoP.add(boxColorTP1);
		
		boxColorTP2 = new JComboBox<>(colores);
		boxColorTP2.setBounds(ANCHO2,ALTO/2+30,100,20);
		twoP.add(boxColorTP2);
		
		boxRandom2 = new JComboBox<>(op);
		boxRandom2.setBounds(ANCHO/2,ALTO/2,100,20);
		twoP.add(boxRandom2);
		
		JLabel random = new JLabel("Juego Aleatorio");
		random.setForeground(Color.WHITE);
		random.setBounds(ANCHO/2-100, ALTO/2, 150, 20);
		twoP.add(random);
		
		JLabel color = new JLabel("Elige color");
		color.setForeground(Color.WHITE);
		color.setBounds(ANCHO/4-110, ALTO/2+30, 150, 20);
		twoP.add(color);
		
		JLabel color2 = new JLabel("Elige color");
		color2.setForeground(Color.WHITE);
		color2.setBounds(ANCHO2-110, ALTO/2+30, 150, 20);
		twoP.add(color2);
		
		
		play2 = new MiBoton("");
		ponerFondo(play2,"resources/jugar1.png");
		play2.setBounds(ANCHO/2-70,ALTO/2+150,150,50);
		twoP.add(play2);
		cards.add(twoP,"twoP");
	}
	private void configuracionJuego1Jugador() {
		((CardLayout)cards.getLayout()).show(cards,"oneP");
	}
	private void configuracionJuego2Jugadores() {
		((CardLayout)cards.getLayout()).show(cards,"twoP");
	}
	private void configuracionJuegoCPU() {
		((CardLayout)cards.getLayout()).show(cards,"cpu");
	}
	private void prepareAcciones() {
		onePlayer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	configuracionJuego1Jugador();
            }
		});
		play1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	jugar(1,false);
            }
		});
		play2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	jugar(2,false);
            }
		});
		play3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	jugar(2,true);
            }
		});
		twoPlayers.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	configuracionJuego2Jugadores();
            }
		});
		cpu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
            	configuracionJuegoCPU();
            }
		});
	}
	/**private void accionJuegoCpu() {
		parent.accionIrAlMenu();
		PantallaJuego tablero = new PantallaJuego(random,2,color1,color2);
		tablero.setVisible(true);
	}*/
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
	private void accionRandom(JComboBox comboBox){
		String elegido = (String)comboBox.getSelectedItem();
		if(elegido.equals("Si")) {
			random = true;
		}
		else {
			random = false;
		}
	}
	public void jugar(int jugadores,boolean CPU){
		PantallaJuego t = null;
		if (jugadores == 1) {
			dispose();
			accionRandom(boxRandom1);
			t = new PantallaJuego(random,jugadores,nombreOP.getText(),null,(String)boxColorOP.getSelectedItem(),null,false,null);
		}
		else if (jugadores == 2 && !CPU) {
			if(nombreTP1.getText().equals(nombreTP2.getText())){
				configuracionJuego2Jugadores();
				return;
			}
			else{
				dispose();
				accionRandom(boxRandom2);
				t = new PantallaJuego(random,jugadores,nombreTP1.getText(),nombreTP2.getText(),(String)boxColorTP1.getSelectedItem(),(String)boxColorTP2.getSelectedItem(),false,null);
			}
		}
		else {
			dispose();
			accionRandom(boxRandom3);
			t = new PantallaJuego(random,jugadores,nombreCPU1.getText(),"cpu",(String)boxColorCPU1.getSelectedItem(),(String)boxColorCPU2.getSelectedItem(),true,(String)(boxTypeCPU.getSelectedItem()));
		}
		t.setVisible(true);
	}
}