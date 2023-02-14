import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class GamePanel extends JFrame implements KeyListener, ComponentListener {
    FrameDragListener frameDragListener = new FrameDragListener(this);
    final double WINDOWTOSCREEN = 6;
    public static int width;
    public static int height;
    public World world;
    public Player p;
    public Vector2 TopLeft = new Vector2(0, 0);
    public int yMove = 0;
    public int xMove = 0;
    public int draggable = 1;
    public static int gravity = 1;


    public GamePanel()  {
        super();
        p = new Player(100, 100);
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


        this.addKeyListener(this);

        this.addComponentListener(this);
        this.setAlwaysOnTop(true);

        this.setVisible(true);
        paintComponents(this.getGraphics());
        this.world.Draw(this, this.getGraphics());
        Timer timer = new Timer(1, timerActionEvent -> {
            p.move(xMove*draggable, yMove*draggable);
            //check to see if the window will be between the edges of the screen
        });
        Timer gravity = new Timer(3, timerActionEvent -> {
            p.fall();
            //check to see if the window will be between the edges of the screen
        });

        Timer refresh = new Timer(1000/240, timerActionEvent -> {
            if ((draggable==1)) {
                this.setLocation(p.x - (int)(width/WINDOWTOSCREEN/2), p.y - ((int) (height / WINDOWTOSCREEN / 2)));
            }
            paintComponents(this.getGraphics());
            p.refreshHitBox();
        });
        timer.start();
        refresh.start();
        gravity.start();


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
        World.collision.addAll(Arrays.asList((new Rectangle(1210, 520, 220, 10, true, false)), //kill floor
                                             (new Rectangle(0, 210, 1200, 20, false, false)),
                                             (new Rectangle(240, 530, 1200, 20, false, false)),
                                             (new Rectangle(0, 850, 1450, 20, false, false)),
                                             (new Rectangle(1340, 830, 40, 40, false, true)),
                                             (new Rectangle(0, 20, 1440, 1, false, false))
        ));
    }
    private static void ascendWorld(){
        //remove everything in collision
        World.collision.clear();
        World.collision.addAll(Arrays.asList(
                (new Rectangle(0, 210, 600, 20, false, false)),
                (new Rectangle(680, 210, 600, 20, false, false)),
                (new Rectangle(680, 410, 600, 20, false, false))
                )
        );
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

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'q') {
            this.removeMouseListener(frameDragListener);
            this.removeMouseMotionListener(frameDragListener);
            this.setSize((int) (width / (WINDOWTOSCREEN)), (int) (height / (WINDOWTOSCREEN)));
            draggable = 1;
        } else if (e.getKeyChar() == 'e') {
            this.addMouseListener(frameDragListener);
            this.addMouseMotionListener(frameDragListener);
            this.setSize((int) (width / (WINDOWTOSCREEN /2)), (int) (height / (WINDOWTOSCREEN / 2)));
            draggable = 0;
        }

        if (e.getKeyChar() == 'w') {
            if (!p.touching()) {
                gravity = -1;
            }
        }if (e.getKeyChar() == 's') {
            if (!p.touching()) {
                gravity = 1;
            }
        }
        if (e.getKeyChar() == 'a') {
            xMove = -1;
        }
        //if(e.getKeyChar() == 's') //
        if (e.getKeyChar() == 'd') {
            xMove = 1;
        }

        // System.out.println("ran");
        // System.out.println("ran");
        int movamtw = width / 3;
        int movamth = height / 3;
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
        if (e.getKeyChar() == 'a'||e.getKeyChar() == 'd') {
            xMove = 0;
        }
    }


}
