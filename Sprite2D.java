import javax.swing.*;
import java.awt.*;

public abstract class Sprite2D {
//    environment
    protected int gameWidth= GamePanel.DesiredSize.width;
    protected int gameHeight= GamePanel.DesiredSize.height;

//    object
    protected double x,y;
    protected double xVector, yVector;
    protected Image img, img1;
    protected int width, height;


    Sprite2D(String imgPath){
        img= new ImageIcon(getClass().getResource(imgPath)).getImage();
        width=img.getWidth(null);
        height=img.getHeight(null);
    }

    Sprite2D(String imgPath, String imgPath2){
        img= new ImageIcon(getClass().getResource(imgPath)).getImage();
        img1= new ImageIcon(getClass().getResource(imgPath2)).getImage();

        width= img.getWidth(null);
        height= img.getHeight(null);

        int width2= img1.getWidth(null);
        int height2= img1.getHeight(null);

        if(width!=width2 || height!=height2){
            JOptionPane.showMessageDialog(null, imgPath+" and "+imgPath2+" have different dimensions!");
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY(){
        return (int)y;
    }

    public Image getImg(){
        return img;
    }
}
