
import java.awt.Color;
import java.awt.Graphics;


public class Player {
    public double x;
    public double y;
    double w;
    double h;
    double xspeed;
    double yspeed;

    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;
    public void move(int i, int j) {
     //   j+= 10;
        Vector2 v = new Vector2((int) this.x + i, (int) this.y + j);
        Rectangle s = new Rectangle(v.x, v.y, hitBox.width, hitBox.height, false);
        for (Rectangle r : World.collision) {
            //check if insdie r
            if (r.contains(s)){
                i = 0;
                j = 0;

                if (r.isBad()) {
                    this.x = 0;
                    this.y = 0;
                }

            }
        }
        this.x += i;
        this.y += j;
        this.hitBox = s;


    }
    //create a istouching method

    public void fall(){
        this.move(0, 1 );
    }
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        w = 40;
        h = 40;
        hitBox = new Rectangle(x, y, (int) w, (int) h, false);
    }

    /*
     public void set() {
         System.out.println(y + "y / x" + x);
         if (keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.1;
         if (keyLeft && !keyRight) xspeed--;
         if (keyRight && !keyLeft) xspeed++;
         if (xspeed > 0 && xspeed < 0.1) xspeed = 0;
         if (xspeed < 0 && xspeed > -0.1) xspeed = 0;
         if (xspeed > 1) xspeed = 0.2;
         if (xspeed < -1) xspeed = -0.2;
         if (keyUp && (y > 299 && y < 301)) {
             y -= 100;
         }
         keyUp = false;
         if (y < 300) {
             yspeed = 0.1;
         }
         if (y > 300) yspeed = 0;
         x += xspeed;
         y += yspeed;
         hitBox.BottomLeft.x = (int) x;
         hitBox.BottomLeft.y = (int) y;
     }
    */


    public void draw(GamePanel w, Graphics g) {
        Rectangle r = new Rectangle((int)this.x, (int)this.y, (int)this.h, (int)this.w, false);
        if (new Rectangle(w.getLocationOnScreen().x, w.getLocationOnScreen().y, w.getWidth(),
                          w.getHeight(), false).contains(r)) {
           // System.out.println("aa");
            g.setColor(Color.black);
            //g.drawRect(5,5, 20,20);
            g.drawRect(r.BottomLeft.x - w.getX(), r.BottomLeft.y - w.getY(), (int) this.w, (int) this.h);

        }

    }
    public boolean touching(){
        for (Rectangle other : World.collision) {
            if ((this.x + other.width > other.x && this.x < other.x + other.width && this.y + other.height > other.y &&
                 this.y < other.y + other.height)) {
                return true;
            }
        }
        return false;
    }
}

