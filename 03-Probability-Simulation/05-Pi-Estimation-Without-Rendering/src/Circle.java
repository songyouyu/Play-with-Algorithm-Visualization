import java.awt.*;

/**
 * @author youyu.song
 * @date 2020/2/10 21:44
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
        return Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2) <= r*r;
    }

}
