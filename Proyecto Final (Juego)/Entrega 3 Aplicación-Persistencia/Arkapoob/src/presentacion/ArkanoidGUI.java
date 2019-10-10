package presentacion;

import javax.swing.*;

import aplicacion.Arkanoid;
import aplicacion.ArkanoidException;
import persistencia.ArkapoobDAO;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ArkanoidGUI extends JFrame{
	private final static int ANCHO = 900;
	private final static int ALTO = 700;
	private JFileChooser fileChooser;
	private JPanel principal,opciones,menu,optionPanel,returnPanel,cards,random,colorp1,colorp2;
	private JButton play,open,importar,options,returnMenu;
	private JTextField randomText,platColors1,platColors2;
	private JComboBox<String> randomBox,platColor1,platColor2;
	private String color1,color2;
	private boolean isRandom;
	private Clip sonido;
	
	private ArkanoidGUI(){
		setResizable(false);
		isRandom = false;
		color1 = "Orange";
		color2 = "Green";
		prepareSonido();
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		/*Prepare JFrame*/
		setTitle("Arkanoid");
		setSize(ANCHO , ALTO);
		setLocationRelativeTo(null);
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		prepareElementosMenu();
		prepareOpciones();
		add(cards);
		setIconImage(new ImageIcon("resources/icon.png").getImage());
	}
	public void prepareSonido(){
		try {
            sonido = AudioSystem.getClip();
            //Se carga el fichero wav y comienza a reproducirse
            //sonido.open(AudioSystem.getAudioInputStream(new File("resources/musica.wav")));
            //sonido.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {
            ArkapoobDAO.registre(e);
        }
	}
	private void prepareElementosMenu(){
		principal = new PanelFondo("resources/principal.jpg");
		principal.setLayout(null);
		cards.add(principal,"menu");
		prepareBotonesMenu();
	}
	
	private void prepareBotonesMenu(){
		menu = new JPanel();
		menu.setLayout(new GridLayout(7,1));
		play = new MiBoton("");
		ponerFondo(play,"resources/jugar.png");
		options = new MiBoton("");
		ponerFondo(options,"resources/opciones.png");
		open = new MiBoton("");
		ponerFondo(open,"resources/abrir.png");
		importar = new MiBoton("");
		ponerFondo(importar,"resources/importar.png");
		menu.add(play);
		menu.add(new JLabel());
		menu.add(options);
		menu.add(new JLabel());
		menu.add(open);
		menu.add(new JLabel());
		menu.add(importar);
		menu.setOpaque(false);
		menu.setSize(ANCHO/4,ALTO/3);
		menu.setLocation(ANCHO/2-menu.getBounds().width/2,ALTO/2-menu.getBounds().height/2+30);
		principal.add(menu);
	}
	
	private void prepareOpciones(){
		opciones = new JPanel();
		optionPanel = new JPanel();
		returnPanel = new JPanel();
		returnMenu = new JButton("Guardar Cambios");
		prepareRandom();
		prepareCombosPlataforma();
		opciones.setLayout(new BorderLayout());
		optionPanel.setLayout(new GridLayout(4,1));
		returnPanel.setLayout(new BorderLayout());
		optionPanel.setBackground(Color.black);
		returnPanel.setBackground(Color.black);
		returnPanel.add(returnMenu,BorderLayout.EAST);
		optionPanel.add(random);
		optionPanel.add(colorp1);
		optionPanel.add(colorp2);
		opciones.add(optionPanel,BorderLayout.CENTER);
		opciones.add(returnPanel,BorderLayout.SOUTH);
		cards.add(opciones,"opciones");
	}
	
	private void prepareRandom(){
		random = new JPanel();
		random.setLayout(new GridLayout(1,2));
		randomBox  = new JComboBox<>();
		randomText = new JTextField("Bloques Random :");
		randomBox.addItem("No");
		randomBox.addItem("Si");
		randomText.setEditable(false);
		random.add(randomText);
		random.add(randomBox);
	}
	
	private void prepareCombosPlataforma(){
		colorp1 = new JPanel();
		colorp2 = new JPanel();
		colorp1.setLayout(new GridLayout(1,2));
		colorp2.setLayout(new GridLayout(1,2));
		platColor1  = new JComboBox<>();
		platColor2	= new JComboBox<>();
		platColors1 = new JTextField("Color del Primer Jugador: ");
		platColors2 = new JTextField("Color del Segundo Jugador: ");
		prepareItemsComboColor(platColor1,true);
		prepareItemsComboColor(platColor2,false);
		platColors1.setEditable(false);
		platColors2.setEditable(false);
		colorp1.add(platColors1);
		colorp1.add(platColor1);
		colorp2.add(platColors2);
		colorp2.add(platColor2);
	}
	
	private void prepareItemsComboColor(JComboBox<String> combo,boolean p1){
		if(p1){
			combo.addItem("Orange");
			combo.addItem("Yellow");
			combo.addItem("Blue");
			combo.addItem("Red");
			combo.addItem("Green");
			combo.addItem("Purple");
		}
		else{
			combo.addItem("Green");
			combo.addItem("Yellow");
			combo.addItem("Blue");
			combo.addItem("Red");
			combo.addItem("Purple");
			combo.addItem("Orange");
		}	
	}
	
	private void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                accionSalir();
            }
        });
		play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionIrAlJuego();
            }
        });
		options.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionIrAOpciones();
            }
        });
		open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionOpenFile();
            }
        });
		returnMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionIrAlMenu();
            }
        });
		randomBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionRandom();
            }
        });
		platColor1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionColor1();
            }
        });
		platColor2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionColor2();
            }
        });
	}
	
	public void accionIrAlMenu(){
		((CardLayout)cards.getLayout()).show(cards,"menu");
	}
	
	private void accionIrAlJuego(){
		//ir al Menu :3
		MenuJugadores menuJugadores = new MenuJugadores(this,isRandom,color1,color2);
		cards.add(menuJugadores,"MenuJugadores");
		((CardLayout)cards.getLayout()).show(cards,"MenuJugadores");
	}
	
	private void accionIrAOpciones(){
		((CardLayout)cards.getLayout()).show(cards,"opciones");
	}
	
	private void accionRandom(){
		String item = (String)randomBox.getSelectedItem();
		if(item.equals("Si")){
			isRandom = true;
		}
		else{
			isRandom = false;
		}
	}
	private void accionColor1(){
		String item = (String)platColor1.getSelectedItem();
		if(item.equals(color2)){
			JOptionPane.showMessageDialog(platColor1,"No puedes escoger el mismo color que el del otro jugador");
		}
		else{
			color1 = item;
		}
	}
	private void accionColor2(){
		String item = (String)platColor2.getSelectedItem();
		if(item.equals(color1)){
			JOptionPane.showMessageDialog(platColor1,"No puedes escoger el mismo color que el del otro jugador");
		}
		else{
			color2 = item;
		}
	}
	
	private void accionOpenFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Abrir");
		int seleccion = fileChooser.showOpenDialog(open);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				Arkanoid game = ArkapoobDAO.open(fileChooser.getSelectedFile());
				PantallaJuego pantalla = new PantallaJuego(isRandom,game.getPlayersAmount(),game.getPlayers(),"Orange","Green");
				pantalla.setGame(game);
				pantalla.iniciar();
				pantalla.setVisible(true);
			}
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(fileChooser,e.getMessage());
		}
	}
	private void accionSalir(){
		int confirmado = JOptionPane.showConfirmDialog(null, "Seguro que deseas salir","Guardar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		if (confirmado == 0){
			// Se cierra el clip.
            sonido.close();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
		else if (confirmado == 1 || confirmado == 2){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
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
	public static void main(String[] args) throws IOException, InstantiationException{
		ArkanoidGUI gui = new ArkanoidGUI();
		gui.setVisible(true);
	}
}