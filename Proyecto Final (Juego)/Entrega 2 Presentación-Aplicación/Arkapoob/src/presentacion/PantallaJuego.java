package presentacion;

import javax.swing.*;

import aplicacion.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PantallaJuego extends JFrame{
	private final static int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final static int ALTO = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JPanel gamebar,cards,principalPanel;
	private JLabel score,lives,round;
	private Painter juego;
	private JButton pause,save,restart,returnMenu;
	private Timer timer;
	private boolean isPaused,isSaved,isGameOn;
	private int speed;
	private JFileChooser fileChooser;
	private Arkanoid game;
	
	public PantallaJuego() throws IOException{
		setResizable(false);
		speed=10;//Si se requiere que la bola vaya mas rpido se reduce esta valor(minimo 1)
		isGameOn = false;
		isSaved = false;
		isPaused = true;
		prepareElementos();
		prepareAcciones();
	}
	
	private void prepareElementos() throws IOException{
		/*Prepare JFrame*/
		setTitle("Juego");
		principalPanel = new JPanel(new BorderLayout());
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		setSize(ANCHO/2 , ALTO/2);
		setLocation(ANCHO/4,ALTO/4);
		prepareJuego();
		add(principalPanel);
	}
	private void prepareJuego() throws IOException{
		juego = new Painter(this);
		gamebar = new JPanel();
		new JLabel("        ");
		new JLabel("        ");
		new JLabel("        ");
		setLayout(new BorderLayout());
		gamebar.setLayout(new GridLayout(1,8));
		prepareGameBar();
		principalPanel.add(gamebar,BorderLayout.SOUTH);
		principalPanel.add(juego,BorderLayout.CENTER);
	}
	
	private void prepareGameBar(){
		score = new JLabel("<html>SCORE<br><H1 align=center>0</H1></html>",SwingConstants.CENTER);
		lives = new JLabel("<html>LIVES<br><H1 align=center>3</H1></html>",SwingConstants.CENTER);
		round = new JLabel("<html>LEVEL<br><H1 align=center>1</H1></html>",SwingConstants.CENTER);
		pause = new JButton("Play");
		save = new JButton("Save");
		restart = new JButton("Restart");
		returnMenu = new JButton("Menu");
		gamebar.add(score);
		gamebar.add(lives);
		gamebar.add(round);
		gamebar.add(pause);
		gamebar.add(save);
		gamebar.add(restart);
		gamebar.add(returnMenu);
	}
	
	
	private void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                accionSalir();
            }
        });
		pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionJugar();
            }
        });
		save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionSaveFile();
            }
        });
		restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				reinicie();
            }
        });
		returnMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionIrAlMenu();
            }
        });
	}
	public void accionIrAlMenu(){
		if(!isSaved){
			if(isGameOn){accionPausa();}
			int confirmado = JOptionPane.showConfirmDialog(this,"Estas seguro de irte sin guardar?");
			if (JOptionPane.OK_OPTION == confirmado){
				isGameOn=false;
				dispose();
			}
			else{
				if(isGameOn){accionJugar();}
			}
		}
		else{
			isGameOn=false;
			dispose();
		}
	}
	public void setSaved(boolean save){
		isSaved = save;
	}
	public void IrAlMenu(){
		isGameOn=false;
		dispose();
	}
	private void accionJugar(){
		isGameOn=true;
		if(isPaused){
			setTextPause("Pause",false);
			juego.accionJugar();
		}
		else{
			accionPausa();	
		}
	}
	public void setTextPause(String mensaje , boolean state) {
		pause.setText(mensaje);
		isPaused = state;
	}
	private void accionPausa(){
		setTextPause("Continue",true);
		juego.detengase();
		//poner el juego en pausa
	}
	public void resetGamebar(){
		setTextPause("Play", true);
		score.setText("<html>SCORE<br><H1 align=center>0</H1></html>");
		lives.setText("<html>LIVES<br><H1 align=center>3</H1></html>");
		round.setText("<html>LEVEL<br><H1 align=center>1</H1></html>");
	}
	
	private void accionSalir(){
		if(isGameOn){accionPausa();}
		int confirmado = JOptionPane.showConfirmDialog(null, "El progreso no ha sido guardado, vas a irte sin guardar?", "Guardar",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		if (confirmado == 0){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
		else if (confirmado == 1 || confirmado == 2){
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			if(isGameOn){accionJugar();}	
		}
	}
	
	private void accionSaveFile(){
		if(isGameOn){accionPausa();}
		fileChooser.setVisible(true);
		int seleccion = fileChooser.showSaveDialog(save);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			File fichero = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(save,"El archivo en la ruta "+fichero+" no se puede guardar porque las funcionalidades estan en construccion ");
		}
		else{
			if(isGameOn){accionJugar();}
		}
	}
	private void reinicie(){
		setTextPause("Play",true);
		isGameOn=false;
		juego.reinicie();
		accionPausa();
		pause.setText("Play");
		resetGamebar();
	}
	public void setScore(int score){
		this.score.setText("<html>SCORE<br><H1 align=center>"+score+"</H1></html>");
	}
	public void setNivel(int nivel){
		this.round.setText("<html>LEVEL<br><H1 align=center>"+nivel+"</H1></html>");
	}
	public void setLives(int lives){
		this.lives.setText("<html>LIVES<br><H1 align=center>"+lives+"</H1></html>");
	}
}