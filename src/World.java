
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.*;
import java.awt.*;
public class World {
    public static java.util.List<Rectangle> collision = new ArrayList<Rectangle>();

    public World() {
    }
    public static BufferedImage ground;
    static{
        try{
    ground  = ImageIO.read(new File("./src/Textures/Stone 2 - Pixel.png"));
        }catch(Exception ignored){
            ignored.printStackTrace();
        }
        }

public static BufferedImage resize(BufferedImage img, int newW, int newH) {
    int w = img.getWidth();
    int h = img.getHeight();
    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
    Graphics2D g = dimg.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    return dimg;
}
    public void Draw(GameWindow w, Graphics g) {
//        foreach rectangle, check if its should be drawn
        for (Rectangle r : collision) {
            try{
            if (new Rectangle(w.getLocationOnScreen().x, w.getLocationOnScreen().y, w.getWidth(),
                              w.getHeight()).contains(r)) {
                             g.drawImage(resize(ground, r.width, r.height),r.BottomLeft.x - w.getX(), r.BottomLeft.y - w.getY() ,null );
                             //g.drawRect(, r.width, r.height);
            }
            }catch(Exception e){
                //System.out.println("failed " + e);
                continue;
            }
        }
    }
}
