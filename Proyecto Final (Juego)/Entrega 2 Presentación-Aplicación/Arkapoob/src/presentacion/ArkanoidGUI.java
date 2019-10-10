package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
import javax.imageio.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import aplicacion.*;

public class ArkanoidGUI extends JFrame{
	private final static int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final static int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JFileChooser fileChooser;
	private JPanel menu,upPanel,downPanel,menuOptions,opciones,optionPanel,returnPanel,cards,logo,colorb,colorp;
	private JButton play,open,options,returnMenu;
	private JTextField bolaColors,platColors;
	private JComboBox<String> bolaColor,platColor;
	private ArkanoidGUI() throws IOException{
		setResizable(false);
		new Arkanoid();
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		/*Prepare JFrame*/
		setTitle("Arkanoid");
		setSize(ANCHO/2 , ALTO/2);
		setLocation(ANCHO/4,ALTO/4);
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		cards = new JPanel();
		cards.setLayout(new CardLayout());
		prepareElementosMenu();
		prepareOpciones();
		add(cards);
	}
	
	private void prepareElementosMenu(){
		menu = new JPanel();
		upPanel = new JPanel();
		downPanel = new JPanel();
		logo = new JPanel();
		ponerFondo(logo,"images/logo.jpg");
		menu.setBackground(Color.BLACK);
		downPanel.setBackground(Color.BLACK);
		menu.setLayout(new GridLayout(2,1));
		upPanel.setLayout(new BorderLayout());
		downPanel.setLayout(new GridLayout(1,3));
		prepareBotonesMenu();
		upPanel.add(logo,BorderLayout.CENTER);
		downPanel.add(new JLabel());
		downPanel.add(menuOptions);
		downPanel.add(new JLabel());
		menu.add(upPanel);
		menu.add(downPanel);
		cards.add(menu,"menu");
	}
	
	private void prepareBotonesMenu(){
		menuOptions = new JPanel();
		play = new JButton("");
		open = new JButton("");
		options = new JButton("");
		ponerFondo(play,"images/play.jpg");
		ponerFondo(open,"images/open.jpg");
		ponerFondo(options,"images/options.jpg");
		menuOptions.setBackground(Color.BLACK);
		play.setBackground(Color.BLACK);
		open.setBackground(Color.BLACK);
		options.setBackground(Color.BLACK);
		menuOptions.setLayout(new GridLayout(3,1));
		menuOptions.add(play);
		menuOptions.add(open);
		menuOptions.add(options);
	}
	
	private void prepareOpciones(){
		opciones = new JPanel();
		optionPanel = new JPanel();
		returnPanel = new JPanel();
		returnMenu = new JButton("Guardar Cambios");
		prepareComboBola();
		prepareComboPlataforma();
		opciones.setLayout(new BorderLayout());
		optionPanel.setLayout(new GridLayout(4,1));
		returnPanel.setLayout(new BorderLayout());
		optionPanel.setBackground(Color.black);
		returnPanel.setBackground(Color.black);
		returnPanel.add(returnMenu,BorderLayout.EAST);
		optionPanel.add(colorb);
		optionPanel.add(colorp);
		opciones.add(optionPanel,BorderLayout.CENTER);
		opciones.add(returnPanel,BorderLayout.SOUTH);
		cards.add(opciones,"opciones");
	}
	
	private void prepareComboBola(){
		colorb = new JPanel();
		colorb.setLayout(new GridLayout(1,2));
		bolaColor  = new JComboBox<>();
		bolaColors = new JTextField("Color de la bola: ");
		bolaColor .addItem("Aun no los decidimos");
		bolaColors.setEditable(false);
		colorb.add(bolaColors);
		colorb.add(bolaColor);
	}
	
	private void prepareComboPlataforma(){
		colorp = new JPanel();
		colorp.setLayout(new GridLayout(1,2));
		platColor  = new JComboBox<>();
		platColors = new JTextField("Color de la plataforma: ");
		platColor .addItem("Aun no los decidimos");
		platColors.setEditable(false);
		colorp.add(platColors);
		colorp.add(platColor);
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
	}
	public void accionIrAlMenu(){
		((CardLayout)cards.getLayout()).show(cards,"menu");
	}
	/*
	private void accionJugar(){
		if(isPaused){
			pause.setText("Pause");
			isPaused=false;
			//Aqui esta el hilo
			timer = new Timer(speed, new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
					//tablero.accionJugar();
				} 
			}); 
			timer.start();
			//poner el juego en marcha
		}
		else{
			accionPausa();	
		}
	}
	
	private void accionPausa(){
		pause.setText("Continue");
		isPaused=true;
		timer.stop();
		//poner el juego en pausa
	}
	/*
	public void prepararCierre(){
		reiniciar();
	}
	*/
	private void accionIrAlJuego(){
		//ir al Menu :3
		MenuJugadores menuJugadores = new MenuJugadores(this);
		cards.add(menuJugadores,"MenuJugadores");
		((CardLayout)cards.getLayout()).show(cards,"MenuJugadores");
	}
	
	private void accionIrAOpciones(){
		((CardLayout)cards.getLayout()).show(cards,"opciones");
	}
	
	private void accionOpenFile(){
		fileChooser.setVisible(true);
		int seleccion = fileChooser.showOpenDialog(open);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			File fichero = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(open,"El archivo "+fichero+" no se puede abrir porque las funcionalidades estan en construccion ");
		}
	}
	/*
	private void accionSaveFile(){
		accionPausa();
		fileChooser.setVisible(true);
		int seleccion = fileChooser.showSaveDialog(save);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			File fichero = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(save,"El archivo en la ruta "+fichero+" no se puede guardar porque las funcionalidades estan en construccion ");
		}
		else{
			accionJugar();
		}
	}
	*/
	private void accionSalir(){
		int confirmado = JOptionPane.showConfirmDialog(null, "Seguro que deseas salir", "Guardar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		if (confirmado == 0){
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
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	public static void main(String[] args) throws IOException, InstantiationException{
		ArkanoidGUI gui = new ArkanoidGUI();
		gui.setVisible(true);
	}
}