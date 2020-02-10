import java.awt.*;
import java.util.LinkedList;

/**
 * 使用蒙特卡洛算法估算 pi 值
 * @author youyusong
 * @date 2018/10/16
 */
public class AlgoVisualizer {
    private static int DELAY = 40;

    private MonteCarloPiData data;

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
        Circle circle = new Circle(sceneWidth/2, sceneHeight/2, sceneWidth/2);
        this.data = new MonteCarloPiData(circle);

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
            if (i % 100 == 0) {
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                System.out.println(data.pi());
            }

            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            data.addPoint(p);
        }

    }

    public static void main(String[] args) {

        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }

}
