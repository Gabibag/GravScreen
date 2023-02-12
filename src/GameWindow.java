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
        Thread s = new Thread(() -> {
            while(true){
                p.move();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        s.start();

        this.getRootPane().setDoubleBuffered(false);
        world = new World();
        World.collision.add((new Rectangle(0, 110, 1200, 50)));
        World.collision.add((new Rectangle(240, 430, 1200, 50)));
        World.collision.add((new Rectangle(0, 750, 1200, 50)));
      //  World.collision.add((new Rectangle(90, 90, 100, 300)));
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
    }
    @Override
    public void update(Graphics g){
        System.out.println("aaaaaaaa");
        p.move(0,0);
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }



    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyChar());
        if(e.getKeyChar() == 'w'){
            p.move(0,-20);
        }
        if(e.getKeyChar() == 'a') {p.move(-20, 0);}
        //if(e.getKeyChar() == 's') //
        if(e.getKeyChar() == 'd') {p.move(20, 0);}
        if(e.getKeyChar() == 's') {p.move(0, 20);}
       // System.out.println("ran");
        /*int movamtw = width/3;
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
                //move to bottom by a fifth of the screen. put that inside a for loop, then add a 1ms delay
                smoothness = (height-TopLeft.x)/100;
                for (int i = 0; i < smoothness; i++) {
                    TopLeft.y += smoothness;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    paintComponents(this.getGraphics());
                    this.setLocation(TopLeft.x, TopLeft.y);
                    paintComponents(this.getGraphics());

                    //sleep for 0.0001 second


                }
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
        paintComponents(this.getGraphics());*/
        // System.out.println("ran");
        int movamtw = width/3;
        int movamth = height/3;
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if (TopLeft.y-movamth > 0) {
                TopLeft.y -= movamth;
            }else {
                TopLeft.y = 0;
            }
        paintComponents(this.getGraphics());


        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if (TopLeft.y+movamth < height - this.getHeight()) {
                TopLeft.y += movamth;
            }
            else {
                TopLeft.y = height - this.getHeight();
            }

        paintComponents(this.getGraphics());
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if (TopLeft.x+movamtw < width - this.getWidth()){
                TopLeft.x += movamtw;
                //sleep for 0.0001 seconds

                TopLeft.x += 1;
            }else {
                TopLeft.x = width - this.getWidth();
            }
            //sleep for 0.0001 seconds

        paintComponents(this.getGraphics());
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if (TopLeft.x-movamtw > 0) {
                TopLeft.x -= movamtw;
            }else {
                TopLeft.x = 0;
            }
        paintComponents(this.getGraphics());

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
