package asdf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class gamepanel extends javax.swing.JPanel implements ActionListener{
    Player player = new Player(400,300,this);
    Timer gameTimer;

    @Override
    public void actionPerformed(ActionEvent e) {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                revalidate();
            }
        },0,17);
    }

    public void paint(Graphics g){
        super.paint(g);

        Graphics2D gtd = (Graphics2D) g;

        player.set();
        revalidate();
        player.draw(gtd);
        repaint();
    }
    public void Keypressed(KeyEvent e) {
        if(e.getKeyChar() == 'w') player.keyUp = true;
        if(e.getKeyChar() == 'a') player.keyLeft = true;
        if(e.getKeyChar() == 's') player.keyDown = true;
        if(e.getKeyChar() == 'd') player.keyRight = true;
    }

    public void KeyReleased(KeyEvent e) {
        if(e.getKeyChar() == 'w') player.keyUp = false;
        if(e.getKeyChar() == 'a') player.keyLeft = false;
        if(e.getKeyChar() == 's') player.keyDown = false;
        if(e.getKeyChar() == 'd') player.keyRight = false;
    }
}
