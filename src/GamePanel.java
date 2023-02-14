import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class GamePanel extends JFrame implements KeyListener, ComponentListener {
    FrameDragListener frameDragListener = new FrameDragListener(this);
    final double WINDOWTOSCREEN = 3;
    public static int width;
    public static int height;
    public World world;
    public Player p;
    public Vector2 TopLeft = new Vector2(0, 0);
    public int yMove = 0;
    public int xMove = 0;
    public int draggable = 1;


    public GamePanel()  {
        super();
        p = new Player(15, 15);
        this.rootPane.setDoubleBuffered(false);

        world = new World();
        innitWorld();
        this.setIgnoreRepaint(true);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) size.getWidth();
        height = (int) size.getHeight();
        System.out.println(width);
        System.out.println(width / WINDOWTOSCREEN);
        size.setSize(width / WINDOWTOSCREEN, height / WINDOWTOSCREEN);
        this.setSize(size.width, size.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        this.addMouseListener(frameDragListener);
        this.addMouseMotionListener(frameDragListener);

        this.addKeyListener(this);

        this.addComponentListener(this);
        this.setAlwaysOnTop(true);

        this.setVisible(true);
        paintComponents(this.getGraphics());
        this.world.Draw(this, this.getGraphics());
        Timer timer = new Timer(1, timerActionEvent -> {
            p.fall();
            p.move(xMove*draggable, yMove*draggable);
            //check to see if the window will be between the edges of the screen
            if ((draggable==1)) {
                this.setLocation(p.x - width/6, p.y - height/6);
            }

            paintComponents(this.getGraphics());
        });
        Timer refresh = new Timer(0, timerActionEvent -> {
            p.refreshHitBox();
        });
        timer.start();
        refresh.start();


    }
    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

    private static void innitWorld() {
        World.collision.addAll(Arrays.asList((new Rectangle(1330, 520, 100, 10, true, false)), //kill floor
                                             (new Rectangle(0, 210, 1200, 20, false, false)),
                                             (new Rectangle(240, 530, 1200, 20, false, false)),
                                             (new Rectangle(0, 850, 1450, 20, false, false)),
                                             (new Rectangle(1340, 830, 40, 40, false, false))
        ));
    }

    @Override
    public void paintComponents(Graphics g) {
        //p.set();
        super.paintComponents(g);
        g.setColor(Color.gray);
        this.world.Draw(this, g);
        p.draw(this, g);
        p.refreshHitBox();

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }
    public void moveFrame(int x, int y) {
        TopLeft.x += x;
        TopLeft.y += y;
        this.setLocation(TopLeft.x, TopLeft.y);
        //sleep for 1ms

        p.refreshHitBox();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        paintComponents(this.getGraphics());
    }
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'q') {
            this.removeMouseListener(frameDragListener);
            this.removeMouseMotionListener(frameDragListener);
            draggable = 1;
        } else if (e.getKeyChar() == 'e') {
            this.addMouseListener(frameDragListener);
            this.addMouseMotionListener(frameDragListener);
            draggable = 0;
        }

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
        /*if (e.getKeyCode() == KeyEvent.VK_UP) {
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

        }*/
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
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
        if ((e.getKeyChar() == 'a') || (e.getKeyChar() == 'd')) {
            xMove = 0;
        }
    }


}
