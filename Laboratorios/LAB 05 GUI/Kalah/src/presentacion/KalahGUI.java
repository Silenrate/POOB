package presentacion;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import aplicacion.*;
public class KalahGUI extends JFrame{
	private Kalah kalah;
	private JMenuBar menubar;
    private JMenu file,config;
    private JMenuItem open,save,saveas,exit,pcolor,ssize,ccolor,hsize;
	private JFileChooser fileChooser;
	private JColorChooser colorChooser;
	private JPanel juego,houses,tablero,panelSuperior,panelInferior;
	private JButton playerStorage,cpuStorage,reiniciar;
	private JLabel cpu,player,titulo,jugadas; 
	private ArrayList<JButton> casas;
	private int housesAmount;
	private int seedsAmount;
	private static final Color backGroundColor = Color.WHITE;
	private int numJugadas;
	private Color cpuColor;
	private Color playerColor;
	private boolean isGameOn;
	private boolean turn;
	private boolean isChanging;
	boolean finish;
	private KalahGUI(){
		numJugadas = 0;
		isChanging=false;
		turn = false;
		finish=false;
		isGameOn=false;
		housesAmount=6;
		seedsAmount=3;
		cpuColor = Color.red;
		playerColor = Color.blue;
		casas = new ArrayList<JButton>();
		try{
			kalah = new Kalah(6,3);
		}
		catch(KalahException e){
			JOptionPane.showMessageDialog(null,"Error al crear tablero","Error",JOptionPane.ERROR_MESSAGE);
		}
		prepareElementos();
		prepareAcciones();
	}
	private void prepareElementos(){
		prepareElementosTablero();
		/*Prepare JFrame*/
		setTitle("Kalah");
		setSize((Toolkit.getDefaultToolkit().getScreenSize().width)/2 , (Toolkit.getDefaultToolkit().getScreenSize().height)/2);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/4, (Toolkit.getDefaultToolkit().getScreenSize().height)/4 );
		prepareElementosMenu();
		fileChooser = new JFileChooser();
		fileChooser.setVisible(false);
		colorChooser = new JColorChooser();
		colorChooser.setVisible(false);
	}
	private void prepareElementosTablero(){
		reiniciar = new JButton("reiniciar");
		tablero = new JPanel();
		juego = new JPanel();
		houses = new JPanel();
		panelSuperior = new JPanel();
		panelInferior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		panelInferior.setLayout(new BorderLayout());
		houses.setLayout(new GridLayout(2,housesAmount,3,3));
		juego.setLayout(new BorderLayout());
		tablero.setLayout(new BorderLayout());
		tablero.setBackground(backGroundColor);
		juego.setBackground(backGroundColor);
		houses.setBackground(backGroundColor);
		panelSuperior.setBackground(backGroundColor);
		panelInferior.setBackground(backGroundColor);
		playerStorage = new KalahButton("0 fichas",0,playerColor);
		playerStorage.setBackground(playerColor);
		playerStorage.setEnabled(false);
		cpuStorage = new KalahButton("0 fichas",0,cpuColor);
		cpuStorage.setBackground(cpuColor);
		cpuStorage.setEnabled(false);
		prepareElementosCasillas();
		prepareLetreros();
		juego.add(houses,BorderLayout.CENTER);
		juego.add(playerStorage,BorderLayout.EAST);
		juego.add(cpuStorage,BorderLayout.WEST);
		panelSuperior.add(titulo,BorderLayout.CENTER);
		panelSuperior.add(jugadas,BorderLayout.NORTH);
		panelSuperior.add(cpu,BorderLayout.SOUTH);
		panelInferior.add(player,BorderLayout.CENTER);
		panelInferior.add(reiniciar,BorderLayout.SOUTH);
		tablero.add(panelInferior,BorderLayout.SOUTH);
		tablero.add(panelSuperior,BorderLayout.NORTH);
		tablero.add(juego,BorderLayout.CENTER);
		add(tablero,BorderLayout.CENTER);
		juego.setVisible(true);
	}
	private void prepareLetreros(){
		
		titulo = new JLabel("", SwingConstants.CENTER);
		titulo.setIcon(new ImageIcon("icono.png"));
		jugadas = new JLabel("Numero de jugadas: "+numJugadas, SwingConstants.CENTER);
		cpu = new JLabel("", SwingConstants.CENTER);
		player = new JLabel("", SwingConstants.CENTER);
		cpu.setIcon(new ImageIcon("imageCPU.png"));
		player.setIcon(new ImageIcon("imagePLAYER.png"));
	}
	private void prepareElementosCasillas(){
		casas = new ArrayList<>();
		for(int i=0;i<2*housesAmount;i++){
			JButton casilla;
			if (i<housesAmount){
				casilla = new KalahButton(seedsAmount+" fichas",i-housesAmount,cpuColor);
				casilla.setBackground(cpuColor);
				casilla.setEnabled(false);
			}
			else{
				casilla = new KalahButton(seedsAmount+" fichas",i-housesAmount,playerColor);
				casilla.setBackground(playerColor);
				casilla.setEnabled(true);
			}
			houses.add(casilla);
			casas.add(casilla);
		}
	}
	private void prepareElementosMenu(){
		menubar = new JMenuBar();
		setJMenuBar(menubar);
		file = new JMenu("File");
		config  = new JMenu("Configuration");
		menubar.add(file);
		menubar.add(config);
		/*Prepare opciones del menu file*/
		open = new JMenuItem("Open");
		file.add(open);
		save = new JMenuItem("Save");
		file.add(save);
		saveas = new JMenuItem("Save As...");
		file.add(saveas);
		exit = new JMenuItem("Exit");
		file.add(exit);
		/*Prepare opciones del menu configuration*/
		ccolor = new JMenuItem("Change CPU Color");
		config.add(ccolor);
		pcolor = new JMenuItem("Change Player Color");
		config.add(pcolor);
		hsize = new JMenuItem("Change Houses Amount");
		config.add(hsize);
		ssize = new JMenuItem("Change Seeds Amount");
		config.add(ssize);
	}
	private void prepareAcciones(){
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev){
                accionSalir();
            }
        });
		exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionSalir();
            }
        });
		open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionOpenFile();
            }
        });
		save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionSaveFile();
            }
        });
		saveas.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionSaveFile();
            }
        });
		ccolor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionColorCpu();
            }
        });
		pcolor.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				accionColorPlayer();
            }
        });
		hsize.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				if(!isChanging){
					accionSizeHouse();
				}
            }
        });
		ssize.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){
				if(!isChanging){
					accionSizeSeeds();
				}
            }
        });
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				reiniciar();
			}
		});
		for(int i=housesAmount;i<2*housesAmount;i++){
			KalahButton boton = (KalahButton)casas.get(i);
			boton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ev){
					accionJugar(boton.getCoordX());
				}
			});
		}
	}
	private void accionJugar(int casa){
		boolean playerTurn=false;
		boolean cpuTurn;
		isGameOn=true;
		config.setEnabled(false);
		try{
			playerTurn=kalah.juegue(casa+1);
		}
		catch(KalahException e){
			JOptionPane.showMessageDialog(null,"Error al ingresar valor, por favor vuelva a intentar","Error",JOptionPane.ERROR_MESSAGE);
		}
		numJugadas++;
		refresque();
		turn = !playerTurn;
		finish = kalah.finish();
		while(turn &&!finish){
			cpuTurn=kalah.juegue();
			numJugadas++;
			refresque();
			turn = cpuTurn;
			finish = kalah.finish();
			if(finish){break;}
		}
		if(finish){
			isGameOn=false;
			config.setEnabled(true);
			prepararCierre();
		}
	}
	public void prepararCierre(){
		int gane = kalah.gane();
		if(gane==2){
			JOptionPane.showMessageDialog(null,"Ganaste","Fin del Juego",JOptionPane.ERROR_MESSAGE);
		}
		else if(gane==0){
			JOptionPane.showMessageDialog(null,"Perdiste","Fin del Juego",JOptionPane.ERROR_MESSAGE);
		}
		else{
			JOptionPane.showMessageDialog(null,"Empate","Fin del Juego",JOptionPane.ERROR_MESSAGE);
		}
		reiniciar();
	}
	private void accionSalir(){
		int confirmado = JOptionPane.showConfirmDialog(exit,"Are you sure you want to exit?");
		if (JOptionPane.OK_OPTION == confirmado){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.exit(0);
		}
	}
	private void accionOpenFile(){
		fileChooser.setVisible(true);
		int seleccion = fileChooser.showOpenDialog(open);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			File fichero = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(open,"El archivo "+fichero+" no se puede abrir porque las funcionalidades estan en construccion ");
		}
	}
	private void accionSaveFile(){
		fileChooser.setVisible(true);
		int seleccion = fileChooser.showSaveDialog(save);
		if (seleccion == JFileChooser.APPROVE_OPTION){
			File fichero = fileChooser.getSelectedFile();
			JOptionPane.showMessageDialog(save,"El archivo en la ruta "+fichero+" no se puede guardar porque las funcionalidades estan en construccion ");
		}
	}
	private void accionColorPlayer(){
		colorChooser.setVisible(true);
		Color color = JColorChooser.showDialog(null, "Seleccione un Color", Color.BLUE);
		if (color.equals(cpuColor)){
			JOptionPane.showMessageDialog(colorChooser,"No se puede escoger el mismo color que el del otro jugador");
		}
		else{
			playerColor = color;
			/*actualiza las casillas*/
			for(int i=housesAmount;i<2*housesAmount;i++){
				casas.get(i).setBackground(color);
			}
			playerStorage.setBackground(color);
		}
	}
	private void accionColorCpu(){
		colorChooser.setVisible(true);
		Color color = JColorChooser.showDialog(null, "Seleccione un Color", Color.BLUE);
		if (color.equals(playerColor)){
			JOptionPane.showMessageDialog(colorChooser,"No se puede escoger el mismo color que el del otro jugador");
		}
		else{
			cpuColor = color;
			/*actualiza las casillas*/
			for(int i=0;i<housesAmount;i++){
				casas.get(i).setBackground(color);
			}
			cpuStorage.setBackground(color);
		}	
	}
	private void accionSizeHouse(){
		isChanging=true;
		String amount = JOptionPane.showInputDialog("How many houses you want per player?");
		try{
			housesAmount = Integer.parseInt(amount.trim());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error al ingresar valor, por favor vuelva a intentar","Error",JOptionPane.ERROR_MESSAGE);
			accionSizeHouse();			
		}
		actualizarTablero();
		try{
			kalah = new Kalah(housesAmount,seedsAmount);
		}
		catch(KalahException e){
			JOptionPane.showMessageDialog(null,"Error al construir tablero, por favor vuelva a intentar","Error",JOptionPane.ERROR_MESSAGE);			
		}
		prepareAcciones();
		isChanging=false;
	}
	private void accionSizeSeeds(){
		isChanging=true;
		String amount = JOptionPane.showInputDialog("How many seeds want per house?");
		try{
			seedsAmount = Integer.parseInt(amount.trim());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,"Error al ingresar valor, por favor vuelva a intentar","Error",JOptionPane.ERROR_MESSAGE);
			accionSizeSeeds();			
		}
		actualizarTablero();
		try{
			kalah = new Kalah(housesAmount,seedsAmount);
		}
		catch(KalahException e){
			JOptionPane.showMessageDialog(null,"Error al construir tablero, por favor vuelva a intentar","Error",JOptionPane.ERROR_MESSAGE);			
		}
		prepareAcciones();
		isChanging=false;
	}
	private void actualizarTablero(){
		houses.setVisible(false);
		houses = new JPanel();
		houses.setLayout(new GridLayout(2,housesAmount,3,3));
		houses.setBackground(backGroundColor);
		casas = new ArrayList<JButton>();
		for(int i=0;i<2*housesAmount;i++){
			JButton casilla;
			if (i<housesAmount){
				casilla = new KalahButton(seedsAmount+" fichas",0,cpuColor);
				casilla.setBackground(cpuColor);
				casilla.setEnabled(false);
			}
			else{
				casilla = new KalahButton(seedsAmount+" fichas",i-housesAmount , playerColor);
				casilla.setBackground(playerColor);
			}
			houses.add(casilla);
			casas.add(casilla);
		}
		juego.add(houses,BorderLayout.CENTER);
	}
	private void refresque(){
		int[] numeros = kalah.consulte();
		jugadas.setText("Numero de jugadas: "+numJugadas);
		/*actualiza las casillas*/
		for(int i=0;i<numeros.length;i++){
			if (i!=0&&i!=housesAmount+1){
				if(i<=housesAmount){
					casas.get(i-1).setText(numeros[i]+" fichas");
				}
				else{
					if(numeros[i]==0){
						casas.get(i-2).setEnabled(false);
					}
					else{
						casas.get(i-2).setEnabled(true);
					}
					casas.get(i-2).setText(numeros[i]+" fichas");
				}
			}
		}
		/*actualiza playerStorage*/
		playerStorage.setText(numeros[housesAmount+1]+" fichas");
		/*actualiza cpuStorage*/
		cpuStorage.setText(numeros[0]+" fichas");
	}
	private void reiniciar(){
		try{
			numJugadas = 0;
			kalah = new Kalah(housesAmount,seedsAmount);
			remove(tablero);
			prepareElementos();
			prepareAcciones();
			setVisible(true);
		}
		catch(KalahException e){
			JOptionPane.showMessageDialog(null,"Error al reiniciar","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void main(String[] args){
		KalahGUI gui = new KalahGUI();
		gui.setVisible(true);
	}
}