import java.awt.*;


public class Player {
    public int x;
    public int y;
    double w;
    double h;

    Rectangle hitBox;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        w = 20;
        h = 20;
        hitBox = new Rectangle(x, y, (int) w, (int) h, false);
    }

    public void move(int i, int j) {
        //   j+= 10;
        Vector2 v = new Vector2((int) (this.x + i), (int) this.y + j);
        Rectangle s = new Rectangle(v.x, v.y, hitBox.width, hitBox.height, false);
        for (Rectangle r : World.collision) {
            //check if insdie r
            if (r.contains(s)) {
                i = 0;
                j = 0;

                if (r.isBad()) {
                    this.x = 0;
                    this.y = 0;
                }

            }
            if ((this.x<0&& i <0) || (this.y < 0 && j < 0) || (this.x > GamePanel.width- hitBox.width && i > 0) || (this.y > GamePanel.height -
                                                                                                                             hitBox.height && j > 0)){
                i = 0;
                j = 0;
            }
        }
        this.x += i;
        this.y += j;
        this.hitBox = s;


    }

    public void refreshHitBox() {
        this.hitBox = new Rectangle((int) this.x, (int) this.y, hitBox.width, hitBox.height, false);
    }

    public void fall() {
        this.move(0, 1);
    }


    public void draw(GamePanel w, Graphics g) {
        Rectangle r = new Rectangle((int) this.x, (int) this.y, (int) this.h, (int) this.w, false);
        if (new Rectangle(w.getLocationOnScreen().x, w.getLocationOnScreen().y, w.getWidth(), w.getHeight(),
                          false).contains(r)) {
            // System.out.println("aa");
            g.setColor(Color.black);
            //g.drawRect(5,5, 20,20);
            g.drawRect(r.BottomLeft.x - w.getX(), r.BottomLeft.y - w.getY(), (int) this.w, (int) this.h);

        }

    }

    public boolean touching() {
        for (Rectangle other : World.collision) {
            if ((this.x + other.width > other.x && this.x < other.x + other.width && this.y + other.height > other.y &&
                 this.y < other.y + other.height)) {
                return true;
            }
        }
        return false;
    }
}

