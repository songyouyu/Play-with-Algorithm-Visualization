import java.awt.*;

/**
 * @author youyusong
 * @date 2018/10/16
 */
public class Circle {

    /**
     * x,y 为坐标
     */
    public int x;
    public int y;

    /**
     * 半径
     */
    private int r;

    /**
     * vx,vy 为速度
     */
    public int vx;
    public int vy;

    public boolean isFilled = false;

    public Circle(int x, int y, int r, int vx, int vy){
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    public void move(int minx, int miny, int maxx, int maxy){
        x += vx;
        y += vy;

        checkCollision(minx, miny, maxx, maxy);
    }

    private void checkCollision(int minx, int miny, int maxx, int maxy){

        if (x - r < minx) {
            x = r;
            vx = -vx;
        }

        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }

        if (y - r < miny) {
            y = r;
            vy = -vy;
        }

        if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

    public boolean contain(Point p){
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) <= r * r;
    }
}
