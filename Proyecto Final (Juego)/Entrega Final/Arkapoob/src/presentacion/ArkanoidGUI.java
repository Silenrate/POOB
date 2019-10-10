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
	private JPanel principal,menu,cards;
	private JButton play,open,importar;
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
		open = new MiBoton("");
		ponerFondo(open,"resources/abrir.png");
		importar = new MiBoton("");
		ponerFondo(importar,"resources/importar.png");
		menu.add(play);
		menu.add(new JLabel());
		menu.add(open);
		menu.add(new JLabel());
		menu.add(importar);
		menu.setOpaque(false);
		menu.setSize(ANCHO/4,ALTO/2);
		menu.setLocation(ANCHO/2-menu.getBounds().width/2,ALTO/2-100);
		principal.add(menu);
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
		open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionOpenFile();
            }
        });
		importar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionImportFile();
            }
        });
	}
	
	public void accionIrAlMenu(){
		((CardLayout)cards.getLayout()).show(cards,"menu");
	}
	
	private void accionIrAlJuego(){
		//ir al Menu :3
		MenuJugadores menuJugadores = new MenuJugadores(this,color1,color2);
		menuJugadores.setVisible(true);
	}
	
	private void accionOpenFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Abrir");
		int seleccion = fileChooser.showOpenDialog(open);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				Arkanoid game = ArkapoobDAO.open(fileChooser.getSelectedFile());
				PantallaJuego pantalla = new PantallaJuego(isRandom,game.getPlayersAmount(),game.getPlayers(),"Orange","Green",game.getCpu(),"");
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
	private void accionImportFile(){
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Importar");
		int seleccion = fileChooser.showOpenDialog(open);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				Arkanoid game = ArkapoobDAO.importar(fileChooser.getSelectedFile());
				PantallaJuego pantalla = new PantallaJuego(isRandom,game.getPlayersAmount(),game.getPlayers(),"Orange","Green",game.getCpu(),"");
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