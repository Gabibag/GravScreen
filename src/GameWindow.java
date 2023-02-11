import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class GameWindow extends JFrame implements KeyListener, ComponentListener{
    final double WINDOWTOSCREEN = 3;
    public int width;
    public int height;
    public World world;
    public Vector2 TopLeft = new Vector2(0,0);
    public GameWindow() {
        super();
        this.getRootPane().setDoubleBuffered(false);
        world = new World();
        world.collision.add((new Rectangle(50,50,50,50)));
        world.collision.add((new Rectangle(90,90,100,300)));
        this.setIgnoreRepaint(true);
        world.collision.add((new Rectangle(20,50,100,300)));
        world.collision.add((new Rectangle(30,50,100,300)));
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)size.getWidth();
        height = (int)size.getHeight();
        System.out.println(width);
        System.out.println(width/WINDOWTOSCREEN);
        size.setSize(width/WINDOWTOSCREEN, height/WINDOWTOSCREEN);
        this.setSize(size.width, size.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.addComponentListener(this);
        //add test image as the jframe backgroun
        this.setUndecorated(true);
//        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
        //add a rounded border to the jframe
//        this.world.Draw(this, this.getGraphics());
        this.setVisible(true);

    }
    @Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
        //g.setColor(Color.white);
        this.getRootPane().paintImmediately(0, 0, this.getWidth(), this.getHeight());
        this.world.Draw(this, g);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

       // System.out.println("ran");
        if(e.getKeyCode() == KeyEvent.VK_UP){
                if (TopLeft.y-1 > 0) {
                    TopLeft.y -= 1;
                }else {
                    TopLeft.y = 0;
                }

        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if (TopLeft.y+1 < height - this.getHeight()) {
                TopLeft.y += 1;
            }
            else {
                TopLeft.y = height - this.getHeight();
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if (TopLeft.x+20 < width - this.getWidth()){
                        TopLeft.x += 20;
                        this.setLocation(TopLeft.x, TopLeft.y);
                        //sleep for 0.0001 seconds
                        paintComponents(this.getGraphics());

                    TopLeft.x += 1;
                }else {
                    TopLeft.x = width - this.getWidth();
                }
                //sleep for 0.0001 seconds
                paintComponents(this.getGraphics());


        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if (TopLeft.x-1 > 0) {
                TopLeft.x -= 1;
            }else {
                TopLeft.x = 0;
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        }
        this.setLocation(TopLeft.x, TopLeft.y);
        paintComponents(this.getGraphics());

    }
     public void componentResized(ComponentEvent e) {
  }
  public void componentMoved(ComponentEvent e) {
    int x = e.getComponent().getX();
    int y = e.getComponent().getY();
    this.TopLeft = new Vector2(x,y);
  }

  public void componentShown(ComponentEvent e) {
  }

  public void componentHidden(ComponentEvent e) {
  }
    public void keyReleased(KeyEvent e) {
    }


}
