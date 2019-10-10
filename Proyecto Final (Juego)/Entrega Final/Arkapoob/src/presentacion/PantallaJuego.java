package presentacion;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import persistencia.ArkapoobDAO;

import aplicacion.*;

public class PantallaJuego extends JFrame{
	private final static int ANCHO = 900;
	private final static int ALTO = 700;
	private JPanel gamebar,principalPanel,onePlayer,twoPlayers,cpuPlayer;
	private JLabel score,lives,score2,lives2,round;
	private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem salvar,exportar;
	private Painter juego;
	private JButton pause;
	private int jugadores;
	private boolean cpu;
	private boolean isPaused,isSaved,isGameOn,random;
	private String color1,nombre1;
	private String color2,nombre2;
	private String typeCpu;
	private ArrayList<String> colors,nombres;
	private JFileChooser fileChooser;

	private PantallaJuego(boolean isRandom,String color1,String color2,boolean CPU , String tipo){
		isGameOn = false;
		isSaved = false;
		isPaused = true;
		typeCpu = tipo;
		random = isRandom;
		this.cpu = CPU;
		this.color1 = color1;
		this.color2 = color2;
	}
	public PantallaJuego(boolean isRandom,int jugadores,String nombre1 , String nombre2, String color1,String color2,boolean CPU,String tipo){
		this(isRandom,color1,color2,CPU,tipo);
		this.nombre1=nombre1;
		this.nombre2=nombre2;
		this.jugadores = jugadores;
		prepareElementos();
		prepareAcciones();
	}
	public PantallaJuego(boolean isRandom,int jugadores,ArrayList<Jugador> players,String color1,String color2,boolean CPU, String tipo){
		this(isRandom,color1,color2,CPU,tipo);
		this.jugadores = jugadores;
		prepareElementos(players);
		prepareAcciones();
	}
	private void prepareElementos(){
		/*Prepare JFrame*/
		setTitle("Juego");
		principalPanel = new JPanel(new BorderLayout());
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		prepareElementosMenu();
		prepareJuego();
		add(principalPanel);
		setIconImage(new ImageIcon("resources/icon.png").getImage());
	}
	
	private void prepareElementos(ArrayList<Jugador> players){
		/*Prepare JFrame*/
		setTitle("Juego");
		principalPanel = new JPanel(new BorderLayout());
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		setSize(ANCHO, ALTO);
		setLocationRelativeTo(null);
		prepareElementosMenu();
		if(players==null){prepareJuego();}
		else{prepareJuego(players);}
		add(principalPanel);
		setIconImage(new ImageIcon("resources/icon.png").getImage());
	}
	
	private void prepareElementosMenu(){
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		menu = new JMenu("Opciones");
		menubar.add(menu);
		salvar = new JMenuItem("Guardar");
		exportar = new JMenuItem("Exportar");
		menu.add(salvar);
		menu.add(exportar);
	}																																																																																																																																																																																																																																												
	private void prepareJuego(){
		juego = new Painter(this,random,jugadores,cpu);
		gamebar = new JPanel();
		new JLabel("        ");
		new JLabel("        ");
		new JLabel("        ");
		setLayout(new BorderLayout());
		gamebar.setLayout(new GridLayout(1,4));
		if(jugadores>1){
			gamebar.setLayout(new GridLayout(1,6));
			prepareGameBar2();
		}
		else{
			gamebar.setLayout(new GridLayout(1,4));
			prepareGameBar();
		}
		principalPanel.add(gamebar,BorderLayout.SOUTH);
		principalPanel.add(juego,BorderLayout.CENTER);
	}
	
	private void prepareJuego(ArrayList<Jugador> players){
		String[] nombres = new String[players.size()];
		for(int i=0;i<players.size();i++){
			nombres[i] = players.get(i).getNombre();
		}
		juego = new Painter(this,random,jugadores,nombres,cpu);
		gamebar = new JPanel();
		new JLabel("        ");
		new JLabel("        ");
		new JLabel("        ");
		setLayout(new BorderLayout());
		if(jugadores>1){
			gamebar.setLayout(new GridLayout(1,6));
			prepareGameBar2();
		}
		else{
			gamebar.setLayout(new GridLayout(1,4));
			prepareGameBar();
		}
		principalPanel.add(gamebar,BorderLayout.SOUTH);
		principalPanel.add(juego,BorderLayout.CENTER);
	}
	
	private void prepareGameBar(){
		score = new JLabel("<html>SCORE<br><H1 align=center>0</H1></html>",SwingConstants.CENTER);
		lives = new JLabel("<html>LIVES<br><H1 align=center>3</H1></html>",SwingConstants.CENTER);
		round = new JLabel("<html>LEVEL<br><H1 align=center>1</H1></html>",SwingConstants.CENTER);
		pause = new JButton("Play");
		new JButton("Menu");
		gamebar.add(score);
		gamebar.add(lives);
		gamebar.add(round);
		gamebar.add(pause);
	}
	private void prepareGameBar2(){
		score = new JLabel("<html>SCORE<br><H1 align=center>0</H1></html>",SwingConstants.CENTER);
		lives = new JLabel("<html>LIVES<br><H1 align=center>3</H1></html>",SwingConstants.CENTER);
		round = new JLabel("<html>LEVEL<br><H1 align=center>1</H1></html>",SwingConstants.CENTER);
		score2 = new JLabel("<html>SCORE<br><H1 align=center>0</H1></html>",SwingConstants.CENTER);
		lives2 = new JLabel("<html>LIVES<br><H1 align=center>3</H1></html>",SwingConstants.CENTER);
		pause = new JButton("Play");
		new JButton("Menu");
		gamebar.add(score);
		gamebar.add(lives);
		gamebar.add(round);
		gamebar.add(score2);
		gamebar.add(lives2);
		gamebar.add(pause);
	}
	
	
	private void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                accionSalir();
            }
        });
		addKeyListener(new KeyAdapter() {
			public void keyReleased( KeyEvent e ) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					accionJugar();
				}
			}
		});
		pause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionJugar();
            }
        });
		salvar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionSaveFile();
            }
        });
		exportar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionExportFile();
            }
        });
		/*
		abrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionOpenFile();
            }
        });
		/*nuevo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				reinicie();
            }
        });
		returnMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionIrAlMenu();
            }
        });
		*/
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
	public void accionJugar(){
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
		fileChooser.setDialogTitle("Guardar");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo DAT","dat"));
		int seleccion = fileChooser.showSaveDialog(salvar);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				juego.salvar(fileChooser.getSelectedFile());
			}
			else{
				if(isGameOn){accionJugar();}
			}
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(salvar,e.getMessage());
		}
		isSaved=true;
	}
	private void accionExportFile(){
		if(isGameOn){accionPausa();}
		fileChooser.setVisible(true);
		fileChooser.setDialogTitle("Exportar");
    	fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo TXT","txt"));
		int seleccion = fileChooser.showSaveDialog(exportar);
		try{
			if (seleccion == JFileChooser.APPROVE_OPTION){
				juego.exportar(fileChooser.getSelectedFile());
			}
			else{
				if(isGameOn){accionJugar();}
			}
		}
		catch(ArkanoidException e){
			ArkapoobDAO.registre(e);
			JOptionPane.showMessageDialog(exportar,e.getMessage());
		}
	}
	public void reinicie(){
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
	public void setScore2(int score){
		this.score2.setText("<html>SCORE<br><H1 align=center>"+score+"</H1></html>");
	}
	public void setNivel(int nivel){
		this.round.setText("<html>LEVEL<br><H1 align=center>"+nivel+"</H1></html>");
	}
	public void setLives(int lives){
		this.lives.setText("<html>LIVES<br><H1 align=center>"+lives+"</H1></html>");
	}
	public void setLives2(int lives){
		this.lives2.setText("<html>LIVES<br><H1 align=center>"+lives+"</H1></html>");
	}
	public void setGame(Arkanoid game){
		juego.setGame(game);
	}
	public ArrayList<String> getColors(){
		colors = new ArrayList<String>();
		if(color1!=null) {
			colors.add(color1);
		}
		if(color2!=null) {
			colors.add(color2);
		}
		return colors;
	}
	public ArrayList<String> getNombres(){
		nombres = new ArrayList<String>();
		if(nombre1!=null) {
			nombres.add(nombre1);
		}
		if(nombre2!=null) {
			nombres.add(nombre2);
		}
		return nombres;
	}
	public String getCpuType() {
		return typeCpu;
	}
	public void iniciar(){
		juego.accionJugar();
		accionPausa();
		juego.updateStatistics();
	}
}