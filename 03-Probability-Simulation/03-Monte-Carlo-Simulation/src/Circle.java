import java.awt.*;

/**
 * @author youyusong
 * @date 2018/10/17
 */
public class Circle {

    /**
     * 圆心的坐标 x, y 以及半径 r
     */
    private int x, y, r;

    public Circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public boolean contain(Point p) {
        return Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) <= r*r;
    }

}
