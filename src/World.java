import java.util.*;
import java.awt.*;
public class World {
    public java.util.List<Rectangle> collision = new ArrayList<Rectangle>();

    public World() {
    }

    public void Draw(GameWindow w, Graphics g) {
//        foreach rectangle, check if its should be drawn
        for (Rectangle r : this.collision) {
            if (new Rectangle(w.getLocationOnScreen().x, w.getLocationOnScreen().y, w.getWidth(),
                              w.getHeight()).contains(r)) {
                             g.drawRect(r.BottomLeft.x - w.getX(), r.BottomLeft.y - w.getY(), r.width, r.height);
            }
        }
    }
}
