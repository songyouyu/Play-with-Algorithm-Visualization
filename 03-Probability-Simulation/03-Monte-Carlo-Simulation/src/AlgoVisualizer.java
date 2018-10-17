import java.awt.*;
import java.util.LinkedList;

/**
 * @author youyusong
 * @date 2018/10/16
 */
public class AlgoVisualizer {
    private static int DELAY = 40;

    private Circle circle;
    private LinkedList<Point> points;

    /**
     * 视图
     */
    private AlgoFrame frame;

    private int N;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N){

        // 初始化数据
        if(sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("This demo must be run in a square window!");
        }

        this.N = N;
        circle = new Circle(sceneWidth/2, sceneHeight/2, sceneWidth/2);
        points = new LinkedList<Point>();

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get Pi with Monte Carlo", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    /**
     * 动画逻辑
     */
    private void run(){

        for(int i = 0 ; i < N ; i ++){

            frame.render(circle, points);
            AlgoVisHelper.pause(DELAY);

            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p);
        }

    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }

}
