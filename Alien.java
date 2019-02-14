import java.awt.*;
import java.util.ArrayList;

public class Alien extends Sprite2D{
    static ArrayList <Alien> aliens = new ArrayList<>();
    private static int gridSize=GamePanel.DesiredSize.width-100;

    private static double xVector2;
    private static int offsetX=0, offsetY=0;
    private int frame=0;

    Alien(){
        super("images/alien1.png", "images/alien2.png");

        xVector2=3;
        yVector=height/4;

        if(offsetX>=gridSize){
            offsetX=0;
            offsetY+=height+15;

            x=offsetX;
            y=offsetY;

            offsetX+=width+15;
        }
        else{
            x=offsetX;
            y=offsetY;

            offsetX+=width+15;
        }
    }

    public void move(){
        x+=xVector2;

        if(x>gameWidth+width)
            x=gameWidth+width;

        if(x<0)
            x=0;
    }

    public void moveDown(){
        y+=yVector;
    }

    public static void bounce(){
        xVector2*=-1;
    }

    public boolean isCollision(){
        if(x<=0 || x+width>=gameWidth){
            return true;
        }
        else
            return false;
    }

    @Override
    public Image getImg() {
        switch (frame) {
            case 0:
                return img;
            case 1:
                return img1;
        }
        return img;
    }

    public void setFrame(int frame){
        this.frame=frame;
    }
}
