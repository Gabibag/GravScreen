package asdf;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyChecker extends KeyAdapter {
    gamepanel panel;

    public KeyChecker(gamepanel panel){
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        panel.Keypressed(e);
    }
    @Override
    public void keyReleased(KeyEvent e){
        panel.KeyReleased(e);
    }
}
