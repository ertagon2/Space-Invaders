import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class GamePanel extends JPanel implements KeyListener {
//    variables
    Spaceship spaceship;
    private int enemies=56;
    public static int aliveEnemies;

    GamePanel(){
//        create a Spaceship
        spaceship = new Spaceship();

//        create Aliens
        for(int i=0; i<enemies; i++){
            Alien.aliens.add(new Alien());
        }
        aliveEnemies=enemies;

//        KeyListener
        setFocusable(true);
        addKeyListener(this);

//        paint Thread
        new Thread(()->{
                while (true){
                    try{Thread.sleep(30);}
                    catch (InterruptedException e){}
                    repaint();
                }
        }).start();

//        move spaceship Thread + bullets
        new Thread(()->{
            while (true){
//                spaceship
                try{Thread.sleep(5);}
                catch (InterruptedException e){}
                spaceship.move();

                Iterator<Bullet> bullets = Bullet.bullets.iterator();
                while (bullets.hasNext()){
                    Bullet bullet = bullets.next();
                    bullet.move();
                    if(bullet.isRemove()){
                        Bullet.bullets.remove(bullet);
                        break;
                    }
                }
            }
        }).start();

//        Alien move Thread
        new Thread(()->{
            int i=0;
            while (true){
                 double y = 12478.57 + (-0.3371553 - 12478.57)/(1 + Math.pow((aliveEnemies/462.0558), 1.269298));
                 int sleep=(int)y;

                try{Thread.sleep(sleep);}
                catch (InterruptedException e){}

                boolean collision=false;
                i++;

                Iterator<Alien> aliens = Alien.aliens.iterator();
                while(aliens.hasNext()){
                    Alien alien = aliens.next();
                    alien.move();
                    alien.setFrame(i%2);
                    if(alien.isCollision())
                        collision=true;
                }

                if(collision) {
                    try{Thread.sleep(sleep);}
                    catch (InterruptedException e){}

                    aliens= Alien.aliens.iterator();
                    while(aliens.hasNext()){
                        Alien alien = aliens.next();
                        alien.moveDown();
                    }
                    Alien.bounce();
                }


            }
        }).start();

//        shooting
        new Thread(()->{
            while (true){
                try {Thread.sleep(10);}
                catch(InterruptedException e){}

                if(spaceship.isShooting()){
                    Bullet.bullets.add(new Bullet(spaceship.x+spaceship.width/2, spaceship.y-spaceship.height));

                    try {Thread.sleep(500);}
                    catch(InterruptedException e){}
                }
            }
        }).start();

    }

//    keys
    @Override
    public void keyPressed(KeyEvent e) {
        int key= e.getKeyCode();

        if(key==KeyEvent.VK_D || key==KeyEvent.VK_RIGHT){
            spaceship.setGoingRight(true);
        }

        if(key==KeyEvent.VK_A || key==KeyEvent.VK_LEFT){
            spaceship.setGoingLeft(true);
        }

        if(key==KeyEvent.VK_SPACE){
            spaceship.setShooting(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key= e.getKeyCode();

        if(key==KeyEvent.VK_D || key==KeyEvent.VK_RIGHT){
            spaceship.setGoingRight(false);
        }

        if(key==KeyEvent.VK_A || key==KeyEvent.VK_LEFT){
            spaceship.setGoingLeft(false);
        }

        if(key==KeyEvent.VK_SPACE){
            spaceship.setShooting(false);
        }
    }

//    paint
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();

//        draw background
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0,0, getWidth(), getHeight());

//        draw aliens
        Iterator<Alien> aliens = Alien.aliens.iterator();
        while (aliens.hasNext()){
            Alien alien = aliens.next();
            g2d.drawImage(alien.getImg(), alien.getX(), alien.getY(),null);
        }

//        draw bullets
        Iterator<Bullet> bullets = Bullet.bullets.iterator();
        while (bullets.hasNext()){
            Bullet bullet = bullets.next();
            g2d.drawImage(bullet.getImg(), bullet.getX(), bullet.getY(), null);
        }

        g2d.drawImage(spaceship.getImg(), spaceship.getX(), spaceship.getY(), null);
    }

//    Dimensions
    public static final Dimension DesiredSize = new Dimension(600,692);

    @Override
    public  Dimension getPreferredSize(){
        return DesiredSize;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
