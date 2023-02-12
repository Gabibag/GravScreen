
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Player {
    double x;
    double y;
    double w;
    double h;
    double xspeed;
    double yspeed;

    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        

        w = 50;
        h = 60;
        hitBox = new Rectangle(x,y, (int) w, (int) h);
    }

    public void set(){
        if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.1;
        if(keyLeft && !keyRight) xspeed--;
        if(keyRight && !keyLeft) xspeed++;
        if(xspeed > 0 && xspeed < 0.1) xspeed = 0;
        if(xspeed < 0 && xspeed > -0.1) xspeed = 0;

        if(xspeed > 1) xspeed = 0.2;
        if(xspeed < -1) xspeed = -0.2;


        if(keyUp && (y > 299 && y < 301)) {
            y -= 100;
        }
        keyUp = false;


            if(y < 300){
                yspeed = 0.1;
            }
            if(y >300) yspeed = 0;




        x += xspeed;
        y += yspeed;
        hitBox.x = (int) x;
        hitBox.y = (int) y;


    }

    public void draw(Graphics2D gtd){
        gtd.setColor(Color.CYAN);
        gtd.fillRect((int) x, (int) y, (int) w, (int) h);

    }

}

