public class Rectangle {
    public Vector2 TopRight;
    public Vector2 BottomLeft;
    public int width;
    public int height;
    public int x;
    public int y;
    public boolean bad;

    public Rectangle(int x, int y, int width, int height, boolean bad) {
        this.TopRight = new Vector2(x + width, y + height);
        this.BottomLeft = new Vector2(x, y);
        this.x = x;
        this.width = width;
        this.height = height;
        this.bad = bad;
        this.y = y;

    }

    public boolean isBad() {
        return bad;
    }

    public void setBad(boolean bad) {
        this.bad = bad;
    }

    public boolean contains(Rectangle other) {
        if (this.TopRight.y < other.BottomLeft.y
            || this.BottomLeft.y > other.TopRight.y) {
            return false;
        }
        return this.TopRight.x >= other.BottomLeft.x
               && this.BottomLeft.x <= other.TopRight.x;
        // System.out.println("inside");

    }


}
