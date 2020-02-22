import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Sierpinski 分形绘制
 * @author youyu.song
 * @date 2020/2/22 17:30
 */
public class AlgoVisualizer {

    private static final int DELAY = 40;

    private FractalData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int maxDepth) {
        data = new FractalData(maxDepth);
        int sceneWidth = (int)Math.pow(3, maxDepth);
        int sceneHeight = (int)Math.pow(3, maxDepth);

        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Visualizer", sceneWidth,sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData(data.depth);
    }

    private void setData(int depth){
        if (depth >= 0) {
            data.depth = depth;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event){
            if(event.getKeyChar() >= '0' && event.getKeyChar() <= '9'){
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args) {
        int maxDepth = 6;

        AlgoVisualizer algoVisualizer = new AlgoVisualizer(maxDepth);
    }

}
