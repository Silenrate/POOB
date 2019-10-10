package presentacion;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
 
public class ArkanoidFondo implements Border {
     
    private BufferedImage mImagen = null;

    public ArkanoidFondo(BufferedImage pImagen) {
        mImagen = pImagen;       
    }
     
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if (mImagen != null) {
            g.drawImage(mImagen, 0, 0, width, height, null);
        }
    }
     
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, 0, 0);
    }
     
    public boolean isBorderOpaque() {
        return true;
    }
}