package presentacion;

import javax.swing.*;
import java.awt.event.*;

public class PanelTeclado extends JPanel implements KeyListener, MouseListener {
	public PanelTeclado(){
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	public boolean isFocusable(){
		return true;
	}
	public void mousePressed( MouseEvent e ) {}
	
	public void mouseReleased( MouseEvent e ) {}
	
	public void mouseClicked( MouseEvent e ) {}
	
	public void mouseEntered( MouseEvent e ) {
		this.requestFocus();
	}
	public void mouseExited( MouseEvent e ) {}
	
	public void keyReleased( KeyEvent e ) {
	}
	public void keyTyped( KeyEvent e ) {
	}
	public void keyPressed( KeyEvent e ) {
	}
}