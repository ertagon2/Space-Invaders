import javax.swing.*;

public class Main {

    Main(){
        JFrame frame = new JFrame("Alien Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main (String args []){
        new Main();
    }
}
