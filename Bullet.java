import java.util.ArrayList;
import java.util.Iterator;

public class Bullet extends Sprite2D{

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    private boolean remove=false;

    Bullet(double x, double y){
        super("images/bullet.png");
        this.x=x;
        this.y=y;
        yVector= -5;
    }

    public void move(){
        if(y+height<0)
            remove=true;

        y+= yVector;
        Iterator<Alien> aliens = Alien.aliens.iterator();
        while (aliens.hasNext()){
            Alien alien = aliens.next();
            if ((  (x<alien.x && x+width>alien.x) ||(alien.x<x && alien.x+alien.width>x)  ) &&(  (y<alien.y && y+height>alien.y) ||(alien.y<y && alien.y+alien.height>y)  )){
                remove=true;
                Alien.aliens.remove(alien);
                GamePanel.aliveEnemies--;
                break;
            }
        }
    }

    public boolean isRemove() {
        return remove;
    }
}
//if ((  (x1<x2 && x1+w1>x2) ||(x2<x1 && x2+w2>x1)  ) &&(  (y1<y2 && y1+h1>y2) ||(y2<y1 && y2+h2>y1)  ))
