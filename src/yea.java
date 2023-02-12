package asdf;
import java.awt.*;

public class yea extends javax.swing.JFrame{

    public yea(){
        gamepanel panel = new gamepanel();
        panel.setLocation(0,0);
        panel.setSize(this.getSize());
        panel.setBackground(Color.BLACK);
        panel.setVisible(true);
        this.add(panel);

        addKeyListener(new KeyChecker(panel));

    }


}
