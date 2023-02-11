
public class Rectangle {
    public Vector2 TopRight;
    public Vector2 BottomLeft;
    public int width;
    public int height;
    public Rectangle(int x,int y,int width,int height){
        this.TopRight = new Vector2(x + width, y + height);
        this.BottomLeft = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }
    public boolean contains(Rectangle other){
        if (this.TopRight.y < other.BottomLeft.y
      || this.BottomLeft.y > other.TopRight.y) {
        return false;
    }
    if (this.TopRight.x < other.BottomLeft.x
      || this.BottomLeft.x > other.TopRight.x) {
        return false;
    }
   // System.out.println("inside");
    return true;
    }
}
