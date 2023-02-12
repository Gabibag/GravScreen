import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class GameWindow extends JFrame implements KeyListener, ComponentListener{
    final double WINDOWTOSCREEN = 3;
    public int width;
    public int height;
    public World world;
    public Player p;
    public Vector2 TopLeft = new Vector2(0,0);
    public GameWindow() {
        super();
        p = new Player(15,15);
        this.getRootPane().setDoubleBuffered(false);
        world = new World();
        //World.collision.add((new Rectangle(50, 50, 50, 50)));
        //World.collision.add((new Rectangle(90, 90, 100, 300)));
        this.setIgnoreRepaint(true);
        //World.collision.add((new Rectangle(20, 50, 100, 300)));
        //World.collision.add((new Rectangle(30, 50, 100, 300)));
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
        //p.set();
        super.paintComponents(g);
        //g.setColor(Color.white);
        this.getRootPane().paintImmediately(0, 0, this.getWidth(), this.getHeight());
        this.world.Draw(this, g);
        p.draw(this,g);
       // repaint(15);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }



    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        //if(e.getKeyChar() == 'w') //
        if(e.getKeyChar() == 'a') {p.move(-10);}
        //if(e.getKeyChar() == 's') //
        if(e.getKeyChar() == 'd') {p.move(10);}
       // System.out.println("ran");
        int movamtw = width/3;
        int movamth = height/3;
        int smoothness = 100;
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if (TopLeft.y - movamth > 0) {//if the top
                for (int i = 0; i < smoothness; i++) {
                    TopLeft.y -= movamth / smoothness;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.setLocation(TopLeft.x, TopLeft.y);
                    //sleep for 0.0001 second

                    paintComponents(this.getGraphics());
                }
            }
            else {
                for (int i = 0; i < TopLeft.y; i++) {
                    TopLeft.y -= movamth / smoothness;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.setLocation(TopLeft.x, TopLeft.y);
                    //sleep for 0.0001 second

                    paintComponents(this.getGraphics());
                }
                TopLeft.y = 0;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if (TopLeft.y + movamth < height) {
                for (int i = 0; i < smoothness; i++) {
                    TopLeft.y += movamth / smoothness;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.setLocation(TopLeft.x, TopLeft.y);
                    //sleep for 0.0001 second

                    paintComponents(this.getGraphics());
                }
            }
            else {
                TopLeft.y = height-movamth;
            }

        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (TopLeft.x + movamtw< width-this.getWidth()) {
                for (int i = 0; i < smoothness; i++) {
                    TopLeft.x += movamtw / smoothness;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.setLocation(TopLeft.x, TopLeft.y);
                    //sleep for 0.0001 second

                    paintComponents(this.getGraphics());
                }
            }else {
                TopLeft.x = width-this.getWidth();
            }
                //sleep for 0.0001 seconds
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if (TopLeft.x - movamtw > 0) {
                for (int i = 0; i < smoothness; i++) {
                    TopLeft.x -= movamtw / smoothness;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    this.setLocation(TopLeft.x, TopLeft.y);
                    //sleep for 0.0001 second
                    paintComponents(this.getGraphics());
                }
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
         if(e.getKeyChar() == 'w') p.keyUp = false;
        if(e.getKeyChar() == 'a') p.keyLeft = false;
        if(e.getKeyChar() == 's') p.keyDown = false;
        if(e.getKeyChar() == 'd') p.keyRight = false;
    }


}
