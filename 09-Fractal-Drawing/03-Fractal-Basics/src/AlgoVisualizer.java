import java.awt.*;

/**
 * Vicsek 分形图的绘制
 * @author youyu.song
 * @date 2020/2/22 17:30
 */
public class AlgoVisualizer {

    private static final int DELAY = 40;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int depth) {
        data = new FractalData(depth);
        int sceneWidth = (int)Math.pow(3, depth);
        int sceneHeight = (int)Math.pow(3, depth);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData();
    }

    private void setData(){
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args) {
        int depth = 6;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(depth);
    }

}
