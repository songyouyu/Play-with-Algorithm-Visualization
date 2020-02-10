import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author youyu.song
 * @date 2020/2/10 21:23
 */
public class MonteCarloPiData {

    private Circle circle;
    private List<Point> points;
    private int insideCircle;

    public MonteCarloPiData(Circle circle) {
        this.circle = circle;
        this.points = new LinkedList<>();
    }

    public Circle getCircle() {
        return circle;
    }

    public Point getPoint(int i) {
        if (i < 0 || i >= points.size()) {
            throw new IllegalArgumentException("out of bound in getPoint");
        }

        return points.get(i);
    }

    public int getPointsNumber() {
        return points.size();
    }

    public void addPoint(Point point) {
        points.add(point);
        if (circle.contain(point)) {
            insideCircle ++;
        }
    }

    public double pi() {
        if (points.size() == 0) {
            return 0.0;
        }

        int circleArea = insideCircle;
        int squareArea = points.size();
        double pi = 4 * (double)circleArea / squareArea;

        return pi;
    }

}
