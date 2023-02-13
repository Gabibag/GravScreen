import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class GamePanel extends JFrame implements KeyListener, ComponentListener {
    final double WINDOWTOSCREEN = 3;
    public static int width;
    public static int height;
    public World world;
    public Player p;
    public Vector2 TopLeft = new Vector2(0, 0);
    public int yMove = 0;
    public int xMove = 0;
    public int winXMove = 0;
    public int winYMove = 0;


    public GamePanel() {
        super();
        p = new Player(15, 15);
        this.rootPane.setDoubleBuffered(false);

        world = new World();
        innitWorld();
        //  World.collision.add((new Rectangle(90, 90, 100, 300)));
        this.setIgnoreRepaint(true);
        //World.collision.add((new Rectangle(20, 50, 100, 300)));
        //World.collision.add((new Rectangle(30, 50, 100, 300)));
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) size.getWidth();
        height = (int) size.getHeight();
        System.out.println(width);
        System.out.println(width / WINDOWTOSCREEN);
        size.setSize(width / WINDOWTOSCREEN, height / WINDOWTOSCREEN);
        this.setSize(size.width, size.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.addKeyListener(this);
        this.addComponentListener(this);
        //add test image as the jframe backgroun

//        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
        //add a rounded border to the jframe
//        this.world.Draw(this, this.getGraphics());
        this.setVisible(true);
        paintComponents(this.getGraphics());
        Timer timer = new Timer(2, timerActionEvent -> {
            p.fall();
            p.move(xMove, yMove);

        });
        Timer refresh = new Timer(1, timerActionEvent -> {
            paintComponents(this.getGraphics());
            p.refreshHitBox();
        });
        timer.start();
        refresh.start();

/*        Thread s = new Thread(() -> {
            while(true){
                p.fall();
                paintComponents(this.getGraphics());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        s.start();*/

    }

    private static void innitWorld() {
        World.collision.addAll(Arrays.asList((new Rectangle(1330, 420, 100, 10, true)), //kill floor
                                             (new Rectangle(0, 110, 1200, 20, false)),
                                             (new Rectangle(240, 430, 1200, 20, false)),
                                             (new Rectangle(0, 750, 1450, 20, false))
        ));
    }

    @Override
    public void paintComponents(Graphics g) {
        //p.set();
        super.paintComponents(g);
        //g.setColor(Color.white);
        this.world.Draw(this, g);
        p.draw(this, g);

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }
    public void moveFrame(int x, int y) {
        TopLeft.x += x;
        TopLeft.y += y;
        this.setLocation(TopLeft.x, TopLeft.y);
        //sleep for 1ms

        paintComponents(this.getGraphics());
        p.refreshHitBox();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.refreshHitBox();
        paintComponents(this.getGraphics());
    }
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == 'w') {
            if (!p.touching()) {
                for (int i = 0; i < 2; i++) {
                    p.move(0, -40);
                }

            }
        }
        if (e.getKeyChar() == 'a') {
            xMove = -1;
            p.move(xMove, yMove);
        }
        //if(e.getKeyChar() == 's') //
        if (e.getKeyChar() == 'd') {
            xMove = 1;
            p.move(xMove, yMove);
        }

        // System.out.println("ran");
        // System.out.println("ran");
        int movamtw = width / 3;
        int movamth = height / 3;
        int smoothness = 10;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (TopLeft.y - movamth > 0) {
                for (int i = 0; i < movamth; i++) {
                    moveFrame(0, -1);
                }
            }
            else {
                while (TopLeft.y != 0) {
                    moveFrame(0, -1);
                }
            }


        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (TopLeft.y + movamth < height - this.getHeight()) {
                for (int i = 0; i < movamth; i++) {
                    moveFrame(0, 1);
                }
            }
            else {
                while (TopLeft.y != height - this.getHeight()) {
                    moveFrame(0, 1);

                }
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (TopLeft.x + movamtw < width - this.getWidth()) {
                for (int i = 0; i < movamtw; i++) {
                    moveFrame(1, 0);
                }

                TopLeft.x += 1;
            }
            else {
                while (TopLeft.x != width - this.getWidth()){
                    moveFrame(1, 0);

                }
            }
            //sleep for 0.0001 seconds

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (TopLeft.x - movamtw > 0) {
                for (int i = 0; i < movamtw; i++) {
                    moveFrame(-1, 0);
                }
            }
            else {
                while (TopLeft.x != 0) {
                    moveFrame(-1, 0);
                }
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        paintComponents(this.getGraphics());
        this.setLocation(TopLeft.x, TopLeft.y);
        p.refreshHitBox();
        paintComponents(this.getGraphics());


    }


    public void componentResized(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
        int x = e.getComponent().getX();
        int y = e.getComponent().getY();
        this.TopLeft = new Vector2(x, y);
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        if ((e.getKeyChar() == 'w') || (e.getKeyChar() == 's')) {
            yMove = 0;

        }
        if ((e.getKeyChar() == 'a') || (e.getKeyChar() == 'd')) {
            xMove = 0;
        }
    }


}
